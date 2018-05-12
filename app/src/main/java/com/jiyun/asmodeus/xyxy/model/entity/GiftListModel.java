package com.jiyun.asmodeus.xyxy.model.entity;

import java.util.List;



public class GiftListModel {

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

        private int userBeanAmount;
        private List<GiftListBean> giftList;

        public int getUserBeanAmount() {
            return userBeanAmount;
        }

        public void setUserBeanAmount(int userBeanAmount) {
            this.userBeanAmount = userBeanAmount;
        }

        public List<GiftListBean> getGiftList() {
            return giftList;
        }

        public void setGiftList(List<GiftListBean> giftList) {
            this.giftList = giftList;
        }

        public static class GiftListBean {
            /**
             * id : 2
             * page : 1
             * rows : 20
             * name : 亲吻
             * amount : 50
             * img : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512448831867&di=a5c0d77825516b87ef5a4cf0f89bf79f&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01f85158b7832da801219c77450e94.gif
             */

            private int id;
            private int page;
            private int rows;
            private String name;
            private int amount;
            private String img;

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

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
