package com.wuzz.demo.aop;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/5/11 14:09
 * @since 1.0
 **/
@Service
public class TransationTestImpl implements TransationTest {

    @Transactional
    @Override
    public void isTransation() {
        DataSourceTransactionManager a = new DataSourceTransactionManager();
    }

    @Override
    public void noTransation() {

        TransactionTemplate transactionTemplate = new TransactionTemplate();
//        transactionTemplate.execute(new TransactionCallback<Object>() {
//            @Override
//            public Object doInTransaction(TransactionStatus status) {
//                return null;
//            }
//        });
    }
}
