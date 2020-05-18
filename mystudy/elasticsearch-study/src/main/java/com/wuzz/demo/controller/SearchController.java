package com.wuzz.demo.controller;

import com.wuzz.demo.config.MyPointConfig;
import com.wuzz.demo.model.SearchResult;
import com.wuzz.demo.service.BookService;
import com.wuzz.demo.service.NearbyService;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/5/15 20:29
 * @since 1.0
 **/
@RestController
public class SearchController {
    @Autowired
    private BookService bookService;

    @Autowired
    private NearbyService nearbyService;
    @Autowired
    private MyPointConfig myPointConfig;
    //这是我所在的坐标值

    private String myName = "wuzz";//我的名字

    @GetMapping("/get/book/technology")
    public ResponseEntity get(@RequestParam(name = "id", defaultValue = "") String id) {
        GetResponse response = bookService.getById(id);

        if (!response.isExists()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(response.getSource(), HttpStatus.OK);
    }

    @PostMapping("add/book/technology")
    public ResponseEntity add(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "price") String price,
            @RequestParam(name = "publicationDate") String publicationDate
    ) {
        IndexResponse response;
        try {
            response = bookService.add(name, Double.parseDouble(price), publicationDate);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @DeleteMapping("remove/book/technology")
    public ResponseEntity remove(@RequestParam(name = "id") String id) {
        DeleteResponse response = bookService.remove(id);

        return new ResponseEntity(response.getResult().toString(), HttpStatus.OK);
    }

    @PutMapping("modify/book/technology")
    public ResponseEntity modify(@RequestParam(name = "id") String id,
                                 @RequestParam(name = "name", required = false) String name,
                                 @RequestParam(name = "price", required = false) String price) {
        UpdateResponse response;
        try {
            response = bookService.modify(id, name, Double.parseDouble(price));
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(response.getResult().toString(), HttpStatus.OK);
    }


    @GetMapping("searchNearby")
    @ResponseBody
    public void searchNearby() {
        int size = 1000, radius = 1000000000;

        System.out.println("开始获取距离" + myName + radius + "米以内人");

        SearchResult result = nearbyService.search(myPointConfig.getLat(), myPointConfig.getLon(), radius, size, null);

        System.out.println("共找到" + result.getTotal() + "个人,优先显示" + size + "人，查询耗时" + result.getUseTime() + "秒");
        for (Map<String, Object> taxi : result.getData()) {

            String nickName = taxi.get("nickName").toString();

            String location = taxi.get("location").toString();
            Object geo = taxi.get("geoDistance");

            System.out.println(nickName + "，" +
                    "微信号:" + taxi.get("wxNo") +
                    "，性别:" + taxi.get("sex") +
                    ",距离" + myName + geo + "米" +
                    "(坐标：" + location + ")");
        }

        System.out.println("以上" + size + "人显示在列表中......");
        System.out.println("各位老司机们使出你们的撩妹技巧！！！");
    }

    @GetMapping("/initData")
    @ResponseBody
    public void initData() {
        int total = 1000;
        int inserted = 0;
        try {
            //建库、建表、建约束
            nearbyService.recreateIndex();
            //随机产生10W条数据
            inserted = nearbyService.addDataToIndex(myPointConfig.getLat(), myPointConfig.getLon(), total);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n========= 数据初始化工作完毕,共随机产生" + inserted + "条数据,失败(" + (total - inserted) + ")条 =========\n");

    }
}
