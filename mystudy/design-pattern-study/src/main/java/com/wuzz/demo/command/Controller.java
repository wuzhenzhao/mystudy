package com.wuzz.demo.command;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/3/23 19:37
 * @since 1.0
 **/
public class Controller {
    private List<IAction> actions = new ArrayList<IAction>();

    public void addAction(IAction action){
        actions.add(action);
    }

    public void execute(IAction action){
        action.execute();
    }

    public void executes(){
        for (IAction action:actions) {
            action.execute();
        }
        actions.clear();
    }



}
