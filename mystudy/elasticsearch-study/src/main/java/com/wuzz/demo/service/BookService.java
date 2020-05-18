package com.wuzz.demo.service;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/5/15 20:30
 * @since 1.0
 **/
@Service
public class BookService {

    private String indexName = "book"; //相当于数据库名称
    private String indexType = "technology"; //相当于数据表名称

    @Autowired
    private TransportClient client;

    public GetResponse getById(String id) {
        return this.client.prepareGet(indexName, indexType, id).get();
    }

    public IndexResponse add(String name, Double price, String publicationDate) throws Exception {

        XContentBuilder content = XContentFactory.jsonBuilder()
                .startObject()
                .field("name", name)
                .field("price", price)
                .field("publication_date", publicationDate)
                .endObject();

        IndexResponse response = this.client.prepareIndex(indexName, indexType)
                .setSource(content)
                .get();

        return response;

    }

    public DeleteResponse remove(String id) {
        return this.client.prepareDelete(indexName, indexType, id).get();
    }

    public UpdateResponse modify(String id, String name, Double price) throws Exception {
        UpdateRequest request = new UpdateRequest(indexName, indexType, id);

        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject();

        if (name != null) {
            builder.field("name", name);
        }
        if (price != null) {
            builder.field("price", price);
        }
        builder.endObject();

        request.doc(builder);
        return this.client.update(request).get();
    }
}

