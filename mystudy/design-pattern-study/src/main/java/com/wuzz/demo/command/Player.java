package com.wuzz.demo.command;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/3/23 19:36
 * @since 1.0
 **/
public class Player {

    public void play() {
        System.out.println("正常播放");
    }

    public void speed() {
        System.out.println("拖动进度条");
    }

    public void stop() {
        System.out.println("停止播放");
    }

    public void pause() {
        System.out.println("暂停播放");
    }

}
