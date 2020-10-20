package com.wuzz.demo.interpreter;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/3/25 10:54
 * @since 1.0
 **/
public class NumInterpreter implements IArithmeticInterpreter {
    private int value;

    public NumInterpreter(int value) {
        this.value = value;
    }


    public int interpret() {
        return this.value;
    }
}
