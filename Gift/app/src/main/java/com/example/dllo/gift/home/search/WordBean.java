package com.example.dllo.gift.home.search;

import java.util.List;

/**
 * Created by dllo on 16/10/8.
 */
public class WordBean {

    /**
     * code : 200
     * data : {"words":["礼物","婚礼礼物","礼物大礼包","成人礼礼物","礼物 男","DlY礼物盒","礼物纸盒","物袋礼","礼物袋盒","礼物男","礼物女生","礼物闺密","足球 礼物","礼物纸","礼物盒","香礼物","男礼物","买礼物","装礼物","礼物书","小礼物","礼物女","礼物的包装","迷你礼物","结果礼物"]}
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
        private List<String> words;

        public List<String> getWords() {
            return words;
        }

        public void setWords(List<String> words) {
            this.words = words;
        }
    }
}
