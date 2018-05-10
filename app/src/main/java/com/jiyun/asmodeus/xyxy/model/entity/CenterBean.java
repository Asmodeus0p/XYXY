package com.jiyun.asmodeus.xyxy.model.entity;

import java.util.List;

public class CenterBean {

    /**
     * code : 0
     * message : 成功
     * data : [{"id":1,"page":1,"rows":20,"amountAndroid":10,"priceAndroid":1,"amountIos":"10","priceIos":"1"},{"id":2,"page":1,"rows":20,"amountAndroid":200,"priceAndroid":20,"amountIos":"20","priceIos":"2"},{"id":3,"page":1,"rows":20,"amountAndroid":500,"priceAndroid":50,"amountIos":"50","priceIos":"5"},{"id":4,"page":1,"rows":20,"amountAndroid":1000,"priceAndroid":100,"amountIos":"100","priceIos":"10"},{"id":5,"page":1,"rows":20,"amountAndroid":2000,"priceAndroid":200,"amountIos":"200","priceIos":"20"},{"id":6,"page":1,"rows":20,"amountAndroid":5000,"priceAndroid":500,"amountIos":"500","priceIos":"50"},{"id":7,"page":1,"rows":20,"amountAndroid":10000,"priceAndroid":1000,"amountIos":"1000","priceIos":"100"},{"id":8,"page":1,"rows":20,"amountAndroid":20000,"priceAndroid":2000,"amountIos":"2000","priceIos":"200"},{"id":9,"page":1,"rows":20,"amountAndroid":50000,"priceAndroid":1,"amountIos":"5000","priceIos":"500"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * page : 1
         * rows : 20
         * amountAndroid : 10
         * priceAndroid : 1
         * amountIos : 10
         * priceIos : 1
         */

        private int id;
        private int page;
        private int rows;
        private int amountAndroid;
        private double priceAndroid;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getRows() {
            return rows;
        }

        public void setRows(int rows) {
            this.rows = rows;
        }

        public int getAmountAndroid() {
            return amountAndroid;
        }

        public void setAmountAndroid(int amountAndroid) {
            this.amountAndroid = amountAndroid;
        }

        public double getPriceAndroid() {
            return priceAndroid;
        }

        public void setPriceAndroid(double priceAndroid) {
            this.priceAndroid = priceAndroid;
        }
    }
}
