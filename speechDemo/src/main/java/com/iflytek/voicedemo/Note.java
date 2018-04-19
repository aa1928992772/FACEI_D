package com.iflytek.voicedemo;

/**
 * Created by Stone on 2017/10/5.
 */


public class Note {     // 一条笔记有：id、创建时间、内容
    private String content;
    private String time;
    private String id;

    public Note(String content, String time, String id){
        this.content = content;
        this.time = time;
        this.id = id;
    }
    public String getContent(){
        return content;
    }
    public String getTime(){
        return time;
    }
    public String getId(){
        return id;
    }
}
