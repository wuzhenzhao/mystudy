package com.wuzz.demo.interpreter;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/3/25 10:50
 * @since 1.0
 **/
public class SubInterpreter extends AbstractInterpreter {
    public SubInterpreter(IArithmeticInterpreter left, IArithmeticInterpreter right) {
        super(left, right);
    }

    public int interpret() {
        return this.left.interpret() - this.right.interpret();
    }
}