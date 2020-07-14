package com.teach.teach1907.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.teach.frame.FrameApplication;
import com.yiyatech.utils.ext.FileUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtils {

    /**
     * 把本地图片上传图片处理后变为字节输入流
     */
    public static InputStream revitionImageSizeToInputStream(String picfile,
                                                             boolean isWidth, int size) {
        InputStream inputStream = null;
        ByteArrayOutputStream os = null;
        try {
            //根据输入大小得到压缩之后的图片资源
            boolean[] isCompress = new boolean[1];
            Bitmap bm = revitionImageSize(picfile, isWidth, size, isCompress);
            os = bytesToInStream(bm);
            inputStream = new ByteArrayInputStream(os.toByteArray());
            os.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return inputStream;
    }


    /**
     * 把本地图片上传图片处理后变为字节输出流
     */
    public static Bitmap revitionImageSize(String picfile,
                                           boolean isWidth, int size, boolean[] isCompress)
            throws IOException {
        if (size != -1 && size <= 0) {
            throw new IllegalArgumentException("size must be greater than 0!");
        }

        if (!FileUtils.isFileExist(picfile)) {
            throw new FileNotFoundException(picfile == null ? "null" : picfile);
        }

        if (!verifyBitmap(picfile)) {
            throw new IOException("");
        }

        FileInputStream input = new FileInputStream(picfile);
        final BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(input, null, opts);
        try {
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int rate = 0;
        if (size != -1) {
            int outSize = isWidth ? opts.outWidth : opts.outHeight;
            for (int i = 0; ; i++) {
                if (outSize >> i <= size) {
                    rate = i;
                    break;
                }
                isCompress[0] = true;
            }
            opts.inSampleSize = (int) Math.pow(2, rate);
        } else {
            // 上传策略
            float screenWidth = FrameApplication.getFrameApplication().getDeviceInfo()
                    .getScreenWidth();
            if (screenWidth < 750) {
                screenWidth = 750;
            }
            float screenHeight =FrameApplication.getFrameApplication().getDeviceInfo()
                    .getScreenHeight();
            if (screenHeight < 1334) {
                screenHeight = 1334;
            }
            float outWidth = opts.outWidth;
            float outHeight = opts.outHeight;
            float scale = (float) 16 / 9;
            float scaleWith = outWidth / screenWidth;
            float scaleHeight = outHeight / screenHeight;
            if (Math.max(outWidth, outHeight)// 比例外就上传原图
                    / Math.min(outWidth, outHeight) > scale) {
                if (outWidth < outHeight && scaleWith > 1) {
                    scale = Math.min(scaleWith, scaleHeight);
                    opts.inSampleSize = Math.round(scale);
                } else if (outWidth > outHeight && scaleHeight > 1) {
                    scale = Math.min(scaleWith, scaleHeight);
                    opts.inSampleSize = Math.round(scale);
                } else {
                    opts.inSampleSize = 1;
                }

            } else {
                if (screenWidth > outWidth && screenHeight > outHeight) {
                    opts.inSampleSize = 1;
                } else {
                    scale = Math.min(scaleWith, scaleHeight);
                    opts.inSampleSize = Math.round(scale);
                }
            }
        }

        opts.inJustDecodeBounds = false;

        return safeDecodeBitmapFile(picfile, opts);
    }


    /**
     * 如果加载时遇到OutOfMemoryError,则将图片加载尺寸缩小一半并重新加载
     */
    public static Bitmap safeDecodeBitmapFile(String bmpFile,
                                              BitmapFactory.Options opts) {
        BitmapFactory.Options optsTmp = opts;
        if (optsTmp == null) {
            optsTmp = new BitmapFactory.Options();
            optsTmp.inSampleSize = 1;
        }

        Bitmap bmp = null;
        FileInputStream input = null;

        final int MAX_TRIAL = 5;
        for (int i = 0; i < MAX_TRIAL; ++i) {
            try {
                input = new FileInputStream(bmpFile);
                bmp = BitmapFactory.decodeStream(input, null, optsTmp);
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                optsTmp.inSampleSize *= 2;
                try {
                    if (input != null)
                        input.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                break;
            }
        }
        if (bmp != null) {
            return bmp;
        } else {
            return null;
        }
    }


    /**
     * 把bitmap转换成字节输出流
     */
    public static ByteArrayOutputStream bytesToInStream(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 100, baos);
        bitmap.recycle();
        return baos;
    }


    /**
     * 处理图片
     *
     * @param bm 所要转换的bitmap
     * @return 指定宽高的bitmap
     */
    public static Bitmap zoomImg(Bitmap bm, int newWidth, int newHeight) {
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
//        bm.recycle();
        return newbm;
    }

    /**
     * 缩放图片，等比放大
     */
    public static Bitmap zoomInImg(Bitmap bm, boolean isWidth, int size) {
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();

        // 计算缩放比例
        float scaleSize = ((float) (isWidth ? width : height)) / size;

        if (scaleSize >= 1)
            return bm;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleSize, scaleSize);

        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
    }

    /**
     * 根据图片后缀判断是否为图片 jpg,png,jpeg,gif,webp,wbmp,ico,bmp
     */
    public static boolean isImage(String filename) {
        if (filename.endsWith(".jpg") || filename.endsWith(".JPG")
                || filename.endsWith(".png") || filename.endsWith(".PNG")
                || filename.endsWith(".jpeg") || filename.endsWith(".JPEG")
                || filename.endsWith(".gif") || filename.endsWith(".GIF")
                || filename.endsWith(".webp") || filename.endsWith(".WEBP")
                || filename.endsWith(".wbmp") || filename.endsWith(".WBMP")
                || filename.endsWith(".ico") || filename.endsWith(".ICO")
                || filename.endsWith(".bmp") || filename.endsWith(".BMP")) {
            return true;

        } else {
            return false;
        }

    }


    /**
     * 图片圆角
     *
     * @Description
     * @author 江军海
     * @date 2013-10-18
     */
    public static void corner(ImageView imageView, int px) {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Config.RGB_565);
        // 得到画布
        Canvas canvas = new Canvas(output);

        // 将画布的四角圆化
        final int color = Color.RED;
        final Paint paint = new Paint();
        // 得到与图像相同大小的区域 由构造的四个值决定区域的位置以及大小
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        // 值越大角度越明显
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        // drawRoundRect的第2,3个参数一样则画的是正圆的一角，如果数值不同则是椭圆的一角
        canvas.drawRoundRect(rectF, px, px, paint);

        paint.setXfermode(new PorterDuffXfermode(
                Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        imageView.setImageDrawable(new BitmapDrawable(output));
    }


    /**
     * 检测是否可以解析成位图
     *
     * @return boolean
     */
    public static boolean verifyBitmap(String path) {
        try {
            return verifyBitmap(new FileInputStream(path));
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 检测是否可以解析成位图
     *
     * @return boolean
     */
    public static boolean verifyBitmap(InputStream input) {
        if (input == null) {
            return false;
        }
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        input = input instanceof BufferedInputStream ? input
                : new BufferedInputStream(input);
        BitmapFactory.decodeStream(input, null, options);
        try {
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (options.outHeight > 0) && (options.outWidth > 0);
    }


    public static final Bitmap defaultPhoto(Context context) {

        try {
            Bitmap bitmap = ImageUtils.corner(
                    BitmapFactory.decodeStream(context.getAssets().open(
                            "noavatar_middle.png")), 21);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return new BitmapDrawable().getBitmap();
        }
    }


    /**
     * 图片圆角
     */
    public static Bitmap corner(Bitmap bitmap, int px) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Config.RGB_565);
        // 得到画布
        Canvas canvas = new Canvas(output);

        // 将画布的四角圆化
        final int color = Color.RED;
        final Paint paint = new Paint();
        // 得到与图像相同大小的区域 由构造的四个值决定区域的位置以及大小
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        // 值越大角度越明显
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        // drawRoundRect的第2,3个参数一样则画的是正圆的一角，如果数值不同则是椭圆的一角
        canvas.drawRoundRect(rectF, px, px, paint);

        paint.setXfermode(new PorterDuffXfermode(
                Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }


    /**
     * 圆角
     */
    public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Config.ARGB_8888);

        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;

        final Paint paint = new Paint();

        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        final RectF rectF = new RectF(rect);

        final float roundPx = pixels;

        paint.setAntiAlias(true);

        canvas.drawARGB(0, 0, 0, 0);

        paint.setColor(color);

        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        try {
            canvas.drawBitmap(bitmap, rect, rect, paint);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return output;

    }


    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }
        return inSampleSize;
    }


    public static Bitmap decodeBitmapFromFile(String path, DisplayMetrics dm) {
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        Bitmap bmp = null;
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(path, options);
        // options.inSampleSize = calculateInSampleSize(options, width, height);
        options.inSampleSize = computeSampleSize(options, -1, width * height);

        options.inJustDecodeBounds = false;

        try {
            bmp = BitmapFactory.decodeFile(path, options);
            // return BitmapFactory.decodeFile(path, options);

        } catch (OutOfMemoryError err) {
            err.printStackTrace();
        }
        return bmp;
    }


    public static String makeJPG(String[] path, File dstFile, DisplayMetrics dm) {

        String resultPath = "";
        boolean result = false;
        int outHeight = 0;
        int outWidth = 0;
        Bitmap dstBit = null;
        for (int i = 0; i < path.length; i++) {
            Bitmap srcBit = decodeBitmapFromFile(path[i], dm);
            if (srcBit.getWidth() > outWidth) {
                outWidth = srcBit.getWidth();
            }
            outHeight += srcBit.getHeight();
            srcBit.recycle();
        }

        dstBit = Bitmap.createBitmap(outWidth, outHeight,
                Config.ARGB_8888);
        Canvas canvas = new Canvas(dstBit);
        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint();
        // 用来对位图进行滤波处理
        paint.setFilterBitmap(true);

        int top = 0;
        int leftP = 0;
        int rightP = 0;
        for (int i = 0; i < path.length; i++) {
            Bitmap srcBit = decodeBitmapFromFile(path[i], dm);
            Rect srcRect = new Rect(0, 0, srcBit.getWidth(), srcBit.getHeight());
            Rect dstRect = new Rect();
            leftP = outWidth / 2 - srcBit.getWidth() / 2;
            rightP = leftP + srcBit.getWidth();
            dstRect.set(leftP, top, rightP, srcBit.getHeight() + top);
            // 把图片srcBit上srcRect画到dstRect的区域内
            canvas.drawBitmap(srcBit, srcRect, dstRect, paint);
            top += srcBit.getHeight();
            srcBit.recycle();
        }
        if (!dstFile.exists()) {
            dstFile.mkdirs();
        }

        // byte[] bitmap2Bytes = Bitmap2Bytes(dstBit);
        // String picName = Md5Util.getFileMd5(bitmap2Bytes);
        String picName = System.currentTimeMillis() + "";
        StringBuilder sb = new StringBuilder();
        sb.append(dstFile.getAbsolutePath()).append("/").append(picName)
                .append(".jpg");
        File imageFile = new File(sb.toString());
        if (!imageFile.exists()) {
            try {
                imageFile.createNewFile();
                FileOutputStream fos = new FileOutputStream(imageFile);
                result = dstBit.compress(CompressFormat.JPEG, 100, fos);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            result = true;
        }
        if (result == true) {
            resultPath = imageFile.getAbsolutePath();
        }

        return resultPath;
    }


    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }


    // 刷新相册
    public static void scanPhotos(Context context, String filePath) {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(new File(filePath));
        intent.setData(uri);
        context.sendBroadcast(intent);
    }


    /**
     * 保存bitmap，图片格式为JPEG
     */
    public static void saveJPGE_After(Bitmap bitmap, String path) {
        File file = new File(path);
        try {
            FileOutputStream out = new FileOutputStream(file);
            if (bitmap == null) {
                return;
            }

            if (bitmap.compress(CompressFormat.JPEG, 100, out)) {
                out.flush();
                out.close();
            }
            if (!bitmap.isRecycled()) {
                bitmap.recycle(); // 回收图片所占的内存
                System.gc();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 读取路径中的图片，然后将其旋转指定后再保存
     */
    public static void saveRotate(String path, int angle) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        Matrix matrix = new Matrix();
        matrix.setRotate(angle);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        saveJPGE_After(bitmap, path);
    }


    /**
     * 读取Bitmap，然后将其旋转指定角度
     */
    public static Bitmap changeRotate(Bitmap bitmap, int angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        final int MAX_TRIAL = 5;
        for (int i = 0; i < MAX_TRIAL; ++i) {
            try {
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                        matrix, true);
                break;
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                width = width / 2;
                height = width / 2;
            } catch (Exception e) {
                break;
            }
        }

        return bitmap;
    }


    public static int computeSampleSize(BitmapFactory.Options options,
                                        int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength,
                maxNumOfPixels);
        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }
        return roundedSize;
    }


    private static int computeInitialSampleSize(BitmapFactory.Options options,
                                                int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;
        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
                .sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
                Math.floor(w / minSideLength), Math.floor(h / minSideLength));
        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }
        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

    /**
     * 读取图片属性：旋转的角度
     *
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /*
     * 旋转图片
     * @param angle
     * @param bitmap
     * @return Bitmap
     */
    public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        //旋转图片 动作
        Matrix matrix = new Matrix();
        ;
        matrix.postRotate(angle);
        System.out.println("angle2=" + angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    private static boolean isEmptyBitmap(final Bitmap src) {
        return src == null || src.getWidth() == 0 || src.getHeight() == 0;
    }

    /**
     * 压缩并保持图片到本地
     * @param src 图片的Bitmap
     * @param file 图片保持的路径
     */
    public static void compressSaveImage(final Bitmap src, final File file) {
        //质量压缩，图片最大100KB
        byte[] bytes = compressByQuality(src, 100 * 1024, false);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Return the compressed bitmap using quality.
     *
     * @param src         The source of bitmap.
     * @param maxByteSize The maximum size of byte.
     * @param recycle     True to recycle the source of bitmap, false otherwise.
     * @return the compressed bitmap
     */
    public static byte[] compressByQuality(final Bitmap src,
                                           final long maxByteSize,
                                           final boolean recycle) {
        if (isEmptyBitmap(src) || maxByteSize <= 0) return null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        src.compress(CompressFormat.JPEG, 100, baos);
        byte[] bytes;
        if (baos.size() <= maxByteSize) {
            bytes = baos.toByteArray();
        } else {
            baos.reset();
            src.compress(CompressFormat.JPEG, 0, baos);
            if (baos.size() >= maxByteSize) {
                bytes = baos.toByteArray();
            } else {
                // find the best quality using binary search
                int st = 0;
                int end = 100;
                int mid = 0;
                while (st < end) {
                    mid = (st + end) / 2;
                    baos.reset();
                    src.compress(CompressFormat.JPEG, mid, baos);
                    int len = baos.size();
                    if (len == maxByteSize) {
                        break;
                    } else if (len > maxByteSize) {
                        end = mid - 1;
                    } else {
                        st = mid + 1;
                    }
                }
                if (end == mid - 1) {
                    baos.reset();
                    src.compress(CompressFormat.JPEG, st, baos);
                }
                bytes = baos.toByteArray();
            }
        }
        if (recycle && !src.isRecycled()) src.recycle();
        return bytes;
    }
}
