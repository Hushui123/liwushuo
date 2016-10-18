package com.example.dllo.gift.bean;

import java.util.List;

/**
 * Created by dllo on 16/9/20.
 */
public class MyHomeBean {

    /**
     * code : 200
     * data : {"candidates":[{"editable":true,"id":9,"name":"送男票","url":null},{"editable":true,"id":110,"name":"穿搭","url":null},{"editable":true,"id":129,"name":"海淘","url":null},{"editable":true,"id":111,"name":"礼物","url":null},{"editable":true,"id":5,"name":"送闺蜜","url":null},{"editable":true,"id":6,"name":"送爸妈","url":null},{"editable":true,"id":17,"name":"送同事","url":null},{"editable":true,"id":26,"name":"送基友","url":null},{"editable":true,"id":24,"name":"送宝贝","url":null},{"editable":true,"id":3,"name":"手工","url":""},{"editable":true,"id":127,"name":"设计感","url":null},{"editable":true,"id":125,"name":"创意生活","url":null},{"editable":true,"id":14,"name":"文艺风","url":null},{"editable":true,"id":28,"name":"科技范","url":null},{"editable":true,"id":11,"name":"萌萌哒","url":null},{"editable":true,"id":126,"name":"奇葩搞怪","url":null}],"channels":[{"editable":false,"id":103,"name":"精选"},{"editable":true,"id":9,"name":"送男票","url":null},{"editable":true,"id":110,"name":"穿搭","url":null},{"editable":true,"id":129,"name":"海淘","url":null},{"editable":true,"id":111,"name":"礼物","url":null},{"editable":true,"id":5,"name":"送闺蜜","url":null},{"editable":true,"id":6,"name":"送爸妈","url":null},{"editable":true,"id":17,"name":"送同事","url":null},{"editable":true,"id":26,"name":"送基友","url":null},{"editable":true,"id":24,"name":"送宝贝","url":null},{"editable":true,"id":3,"name":"手工","url":""},{"editable":true,"id":127,"name":"设计感","url":null},{"editable":true,"id":125,"name":"创意生活","url":null},{"editable":true,"id":14,"name":"文艺风","url":null},{"editable":true,"id":28,"name":"科技范","url":null},{"editable":true,"id":11,"name":"萌萌哒","url":null},{"editable":true,"id":126,"name":"奇葩搞怪","url":null}]}
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
         * editable : true
         * id : 9
         * name : 送男票
         * url : null
         */

        private List<CandidatesBean> candidates;
        /**
         * editable : false
         * id : 103
         * name : 精选
         */

        private List<ChannelsBean> channels;

        public List<CandidatesBean> getCandidates() {
            return candidates;
        }

        public void setCandidates(List<CandidatesBean> candidates) {
            this.candidates = candidates;
        }

        public List<ChannelsBean> getChannels() {
            return channels;
        }

        public void setChannels(List<ChannelsBean> channels) {
            this.channels = channels;
        }

        public static class CandidatesBean {
            private boolean editable;
            private int id;
            private String name;
            private Object url;

            public boolean isEditable() {
                return editable;
            }

            public void setEditable(boolean editable) {
                this.editable = editable;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getUrl() {
                return url;
            }

            public void setUrl(Object url) {
                this.url = url;
            }
        }

        public static class ChannelsBean {
            private boolean editable;
            private int id;
            private String name;

            public boolean isEditable() {
                return editable;
            }

            public void setEditable(boolean editable) {
                this.editable = editable;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
