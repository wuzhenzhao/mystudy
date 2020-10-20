package com.wuzz.demo.memento;

import java.util.Stack;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/3/25 14:26
 * @since 1.0
 **/
public class DraftsBox {
    private final Stack<ArticleMemento> STACK = new Stack<ArticleMemento>();

    public ArticleMemento getMemento(){
        ArticleMemento articleMemento = STACK.pop();

        return articleMemento;
    }

    public void addMemento(ArticleMemento articleMemento){
        STACK.push(articleMemento);
    }

}
