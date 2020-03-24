package com.wuzz.demo.command;

import com.wuzz.demo.command.actions.PauseAction;
import com.wuzz.demo.command.actions.PlayAction;
import com.wuzz.demo.command.actions.SpeedAction;
import com.wuzz.demo.command.actions.StopAction;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/3/23 19:36
 * @since 1.0
 **/
public class Test {
    public static void main(String[] args) {

        Player player = new Player();
        Controller controller = new Controller();
        controller.execute(new PlayAction(player));

        controller.addAction(new PauseAction(player));
        controller.addAction(new PlayAction(player));
        controller.addAction(new StopAction(player));
        controller.addAction(new SpeedAction(player));
        controller.executes();
    }
}
