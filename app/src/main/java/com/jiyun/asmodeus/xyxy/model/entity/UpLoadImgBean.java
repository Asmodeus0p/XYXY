package com.jiyun.asmodeus.xyxy.model.entity;

public class UpLoadImgBean {

    /**
     * code : 0
     * message : 成功
     * data : {"path":"http://image.5roo.com/uwo-file/fast/data/00/9B/ctcYLln1OqCAckfKAAACEOI5REA541.png","fileName":"评.png","code":"0000","thumbPath":"http://image.5roo.com/uwo-file/fast/data/00/9B/ctcYLln1OqCAckfKAAACEOI5REA541_400x400.png","group":"group1"}
     */

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
        /**
         * path : http://image.5roo.com/uwo-file/fast/data/00/9B/ctcYLln1OqCAckfKAAACEOI5REA541.png
         * fileName : 评.png
         * code : 0000
         * thumbPath : http://image.5roo.com/uwo-file/fast/data/00/9B/ctcYLln1OqCAckfKAAACEOI5REA541_400x400.png
         * group : group1
         */

        private String path;
        private String fileName;
        private String code;
        private String thumbPath;
        private String group;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getThumbPath() {
            return thumbPath;
        }

        public void setThumbPath(String thumbPath) {
            this.thumbPath = thumbPath;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "path='" + path + '\'' +
                    ", fileName='" + fileName + '\'' +
                    ", code='" + code + '\'' +
                    ", thumbPath='" + thumbPath + '\'' +
                    ", group='" + group + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UpLoadImgModel{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

