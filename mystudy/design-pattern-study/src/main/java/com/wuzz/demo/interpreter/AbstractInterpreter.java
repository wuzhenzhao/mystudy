package com.wuzz.demo.interpreter;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/3/25 10:50
 * @since 1.0
 **/
public abstract class AbstractInterpreter implements IArithmeticInterpreter {

    protected IArithmeticInterpreter left;
    protected IArithmeticInterpreter right;

    public AbstractInterpreter(IArithmeticInterpreter left, IArithmeticInterpreter right) {
        this.left = left;
        this.right = right;
    }
}
