package com.wuzz.demo.integratedway1;

import java.util.Date;

/**
 * User: Wuzhenzhao
 * Date: 2019/12/9
 * Time: 19:38
 * Description:
 * ClassPath:com.wuzz.demo.integratedway1.Message
 */
public class Message {
    private Long id;    //id

    private String msg; //消息

    private Date sendTime;  //时间戳

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

}
