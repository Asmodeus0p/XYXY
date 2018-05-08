package com.jiyun.asmodeus.xyxy.model.entity;

import java.util.List;


public class PreferenceListBean {


    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<MajorsBean> majors;
        private List<CollegesBean> colleges;

        public List<MajorsBean> getMajors() {
            return majors;
        }

        public void setMajors(List<MajorsBean> majors) {
            this.majors = majors;
        }

        public List<CollegesBean> getColleges() {
            return colleges;
        }

        public void setColleges(List<CollegesBean> colleges) {
            this.colleges = colleges;
        }

        public static class MajorsBean {

            private int id;
            private int page;
            private int rows;
            private String name;
            private int sortord;
            private int isMy;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSortord() {
                return sortord;
            }

            public void setSortord(int sortord) {
                this.sortord = sortord;
            }

            public int getIsMy() {
                return isMy;
            }

            public void setIsMy(int isMy) {
                this.isMy = isMy;
            }
        }

        public static class CollegesBean {
            /**
             * id : 1
             * page : 1
             * rows : 20
             * name : 中央音乐学院
             * sortord : 1
             */

            private int id;
            private int page;
            private int rows;
            private String name;
            private int sortord;
            private int isMy;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSortord() {
                return sortord;
            }

            public void setSortord(int sortord) {
                this.sortord = sortord;
            }

            public int getIsMy() {
                return isMy;
            }

            public void setIsMy(int isMy) {
                this.isMy = isMy;
            }
        }
    }
}
