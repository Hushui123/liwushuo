package com.example.dllo.gift.bean;

import java.util.List;

/**
 * Created by dllo on 16/9/21.
 */
public class SpecialBean {

    /**
     * code : 200
     * data : {"secondary_banners":[{"ad_monitors":[],"id":43,"image_url":"http://img03.liwushuo.com/image/160517/8xb020ll2.jpg-w720","target_url":"liwushuo:///page?type=dailylucky","webp_url":"http://img03.liwushuo.com/image/160517/8xb020ll2.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"id":35,"image_url":"http://img03.liwushuo.com/image/160503/jj2y919zo.jpg-w720","target_url":"liwushuo:///page?type=topic&topic_id=243","webp_url":"http://img03.liwushuo.com/image/160503/jj2y919zo.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"id":33,"image_url":"http://img02.liwushuo.com/image/160503/ydv76bnsp.jpg-w720","target_url":"liwushuo:///page?type=topic&topic_id=246","webp_url":"http://img02.liwushuo.com/image/160503/ydv76bnsp.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"id":36,"image_url":"http://img01.liwushuo.com/image/160503/p3tkow02z.jpg-w720","target_url":"liwushuo:///page?type=topic&topic_id=247","webp_url":"http://img01.liwushuo.com/image/160503/p3tkow02z.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"id":32,"image_url":"http://img03.liwushuo.com/image/160503/oe88p5q8w.jpg-w720","target_url":"liwushuo:///page?type=topic&topic_id=244","webp_url":"http://img03.liwushuo.com/image/160503/oe88p5q8w.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"id":34,"image_url":"http://img01.liwushuo.com/image/160503/00qo7xn94.jpg-w720","target_url":"liwushuo:///page?type=topic&topic_id=245","webp_url":"http://img01.liwushuo.com/image/160503/00qo7xn94.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"id":11,"image_url":"http://img01.liwushuo.com/image/160503/9jwasbbm5.jpg-w720","target_url":"liwushuo:///page?type=topic&topic_id=174","webp_url":"http://img01.liwushuo.com/image/160503/9jwasbbm5.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"id":41,"image_url":"http://img02.liwushuo.com/image/160512/57v4i2z58.jpg-w720","target_url":"liwushuo:///page?url=http%3A%2F%2Fwww.liwushuo.com%2Fcredit%2Fmy_invitation&page_action=navigation&login=true&type=url&right_item_title=","webp_url":"http://img02.liwushuo.com/image/160512/57v4i2z58.jpg?imageView2/2/w/720/q/85/format/webp"}]}
     * message : OK
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * ad_monitors : []
         * id : 43
         * image_url : http://img03.liwushuo.com/image/160517/8xb020ll2.jpg-w720
         * target_url : liwushuo:///page?type=dailylucky
         * webp_url : http://img03.liwushuo.com/image/160517/8xb020ll2.jpg?imageView2/2/w/720/q/85/format/webp
         */

        private List<SecondaryBannersBean> secondary_banners;

        public List<SecondaryBannersBean> getSecondary_banners() {
            return secondary_banners;
        }

        public void setSecondary_banners(List<SecondaryBannersBean> secondary_banners) {
            this.secondary_banners = secondary_banners;
        }

        public static class SecondaryBannersBean {
            private int id;
            private String image_url;
            private String target_url;
            private String webp_url;
            private List<?> ad_monitors;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public String getTarget_url() {
                return target_url;
            }

            public void setTarget_url(String target_url) {
                this.target_url = target_url;
            }

            public String getWebp_url() {
                return webp_url;
            }

            public void setWebp_url(String webp_url) {
                this.webp_url = webp_url;
            }

            public List<?> getAd_monitors() {
                return ad_monitors;
            }

            public void setAd_monitors(List<?> ad_monitors) {
                this.ad_monitors = ad_monitors;
            }
        }
    }
}
