package com.wuzz.demo.command.actions;

import com.wuzz.demo.command.IAction;
import com.wuzz.demo.command.Player;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/3/23 19:36
 * @since 1.0
 **/
public class PlayAction implements IAction {
    private Player player;

    public PlayAction(Player player) {
        this.player = player;
    }

    public void execute() {
        player.play();
    }
}
