package com.wuzz.demo.command.actions;

import com.wuzz.demo.command.IAction;
import com.wuzz.demo.command.Player;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/3/23 19:36
 * @since 1.0
 **/
public class PauseAction implements IAction {
    private Player player;

    public PauseAction(Player player) {
        this.player = player;
    }

    public void execute() {
        player.pause();
    }
}
