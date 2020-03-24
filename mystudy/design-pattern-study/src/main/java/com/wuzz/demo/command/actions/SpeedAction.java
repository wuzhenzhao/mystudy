package com.wuzz.demo.command.actions;

import com.wuzz.demo.command.IAction;
import com.wuzz.demo.command.Player;

/**
 * Created by Tom.
 */
public class SpeedAction implements IAction {
    private Player player;

    public SpeedAction(Player player) {
        this.player = player;
    }

    public void execute() {
        player.speed();
    }
}
