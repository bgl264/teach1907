package com.teach.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xxl on 2017/11/21.
 * 专业切换选择实体
 */

public class SpecialtyChooseEntity implements Serializable {

    private static final long serialVersionUID = 2662021829532653292L;
    /**
     * bigspecialty : 设计学院
     * data : [{"fid":3,"specialty_id":"1","specialty_name":"建筑设计"},{"fid":5,"specialty_id":"2","specialty_name":"室内设计"},{"fid":4,"specialty_id":"3","specialty_name":"景观设计"}]
     * icon : http://testedu.zhulong.com/img/classicon/sheji.png
     * is_classify : 1
     */

    private String bigspecialty;
    private String icon;
    private int is_classify;
    private List<DataBean> data;

    public String getBigspecialty() {
        return bigspecialty;
    }

    public void setBigspecialty(String bigspecialty) {
        this.bigspecialty = bigspecialty;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getIs_classify() {
        return is_classify;
    }

    public void setIs_classify(int is_classify) {
        this.is_classify = is_classify;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        private static final long serialVersionUID = -2581649475596325364L;
        /**
         * fid : 3
         * specialty_id : 1
         * specialty_name : 建筑设计
         */

        private int fid;
        private String specialty_id ;
        private String specialty_name;

        public int getFid() {
            return fid;
        }

        public void setFid(int fid) {
            this.fid = fid;
        }

        public String getSpecialty_id() {
            return specialty_id;
        }

        public void setSpecialty_id(String specialty_id) {
            this.specialty_id = specialty_id;
        }

        public String getSpecialty_name() {
            return specialty_name;
        }

        public void setSpecialty_name(String specialty_name) {
            this.specialty_name = specialty_name;
        }
    }
}
