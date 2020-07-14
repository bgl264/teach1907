package com.teach.teach1907.utils;
import com.teach.teach1907.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:Jason
 * <p>
 * Date:2016/12/13
 */

public class PublishExpression {

    public static int[] expressionImgs = new int[]{R.drawable.f02,
            R.drawable.f03, R.drawable.f04, R.drawable.f05, R.drawable.f06,
            R.drawable.f07, R.drawable.f08, R.drawable.f09, R.drawable.f10,
            R.drawable.f11, R.drawable.f12, R.drawable.f13, R.drawable.f14,
            R.drawable.f15, R.drawable.f16, R.drawable.f17, R.drawable.f18,
            R.drawable.f19, R.drawable.f20, R.drawable.f21, R.drawable.f22,
            R.drawable.f23, R.drawable.f24,R.drawable.ic_emotion_delete};

    /**
     * 本地表情的名字
     */
    public static String[] expressionImgNames = new String[]{"{男-惊讶}",
            "{男-流泪}", "{男-害羞}", "{男-闭嘴}", "{男-睡}", "{男-发怒}", "{男-得意}",
            "{男-抓狂}", "{男-吐}", "{男-偷笑}", "{男-疑问}", "{男-嘘}", "{男-晕}", "{男-衰}",
            "{男-再见}", "{男-猪头}", "{男-爱心}", "{男-心碎}", "{男-拥抱}", "{男-强}","{男-左哼哼}","{男-右哼哼}","{男-哈欠}", ""};

//    public static int[] expressionImgs1 = new int[]{R.drawable.f22,
//            R.drawable.f23, R.drawable.f24, R.drawable.f25,
//            R.drawable.f27, R.drawable.f26, R.drawable.f28, R.drawable.f29,
//            R.drawable.f30, R.drawable.f31, R.drawable.f32, R.drawable.f33,
//            R.drawable.f34, R.drawable.f35, R.drawable.f36, R.drawable.f37,
//            R.drawable.f38, 0, 0, 0, R.drawable.ic_emotion_delete};

    public static int[] expressionImgs1 = new int[] {R.drawable.f25, R.drawable.f27, R.drawable.f26, R.drawable.f28, R.drawable.f29,
            R.drawable.f30, R.drawable.f31, R.drawable.f32, R.drawable.f33,
            R.drawable.f34, R.drawable.f35, R.drawable.f36, R.drawable.f37,
            R.drawable.f38, R.drawable.f39, R.drawable.f41,
            R.drawable.f43, R.drawable.f44, R.drawable.f45, R.drawable.f46,
            R.drawable.f47, R.drawable.f48, R.drawable.f50, R.drawable.ic_emotion_delete };

//    /**
//     * 本地表情的名字
//     */
//    public static String[] expressionImgNames1 = new String[]{"{男-左哼哼}", "{男-右哼哼}",
//            "{男-哈欠}", "{男-鄙视}", "{男-委屈}", "{男-阴险}", "{男-亲亲}", "{男-可怜}",
//            "{男-勾引}", "{男-NO}", "{男-困}", "{男-流汗}", "{男-咒骂}", "{男-弱}", "{男-OK}",
//            "{男-抠鼻}", "{男-鼓掌}", "", "", "", ""};

    /**
     * 本地表情的名字
     */
    public static String[] expressionImgNames1 = new String[] {"{男-鄙视}", "{男-委屈}","{男-阴险}",
            "{男-亲亲}","{男-可怜}", "{男-勾引}",
            "{男-NO}", "{男-困}", "{男-流汗}",//
            "{男-咒骂}", "{男-弱}", "{男-OK}", "{男-抠鼻}",//
            "{男-鼓掌}", "{男-爱你}", "{男-大哭}", //
            "{男-蛋糕}", "{男-给力}", "{男-滚}", "{男-蜡烛}",//
            "{男-礼物}", "{男-胜利}","{男-微笑}", "" };

    public static int getExpressionMap(String key) {
        Map<String, Integer> expressionMap = null;
        if (expressionMap == null) {
            expressionMap = new HashMap<String, Integer>();
            for (int i = 0; i < expressionImgNames.length; i++) {
                expressionMap.put(expressionImgNames[i], expressionImgs[i]);
            }
            for (int j = 0; j < expressionImgNames1.length; j++) {
                expressionMap.put(expressionImgNames1[j], expressionImgs1[j]);
            }
        }
        return expressionMap.get(key);
    }
}
