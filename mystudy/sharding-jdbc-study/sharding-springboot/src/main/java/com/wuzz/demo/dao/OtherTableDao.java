package com.wuzz.demo.dao;

import com.wuzz.demo.entity.OtherTable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/8/10 19:59
 * @since 1.0
 **/
@Repository
public interface OtherTableDao {

    long addOne(OtherTable table);

    List<OtherTable> getAll();

}
