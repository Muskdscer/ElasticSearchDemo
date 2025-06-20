package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.controller.EsController;
import com.example.demo.entity.SubmitUserPayOrderRequest;
import com.example.demo.entity.model.es.PageEs;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ESTest {
    @Resource
    private EsController esController;
//    @Resource
//    private SubmitUserPayOrderRequestMapper order;

    @Test
    public void search(){
        System.out.println("============================测试开始=============================");
        String s = esController.searchDemo();
        System.out.println(s);
        System.out.println("============================测试结束=============================");
    }



    @Test
    public void submit(){
        for (int i = 0; i < 100; i++) {
            SubmitUserPayOrderRequest submitUserPayOrderRequest = new SubmitUserPayOrderRequest();
            String str = "20191212143440725_300004_9555";
            String s = str.substring(0, str.length() - 1) + i;
            submitUserPayOrderRequest.setId(s);
            submitUserPayOrderRequest.setMerchantId(807002L);
            submitUserPayOrderRequest.setUserPayId(0L);
            submitUserPayOrderRequest.setAmountOfConsumption(new BigDecimal(0.01));
            submitUserPayOrderRequest.setPayAblAmount(new BigDecimal(i));
            submitUserPayOrderRequest.setFreeAmount(new BigDecimal(i + 5));
            submitUserPayOrderRequest.setPayType(0);
            submitUserPayOrderRequest.setDiscountAmount(new BigDecimal(0.00));
            submitUserPayOrderRequest.setStatus(4);
            submitUserPayOrderRequest.setChannelId(3);
            submitUserPayOrderRequest.setEnjoyKingAmount(new BigDecimal(0.05));
            submitUserPayOrderRequest.setScanType(1);
            esController.submit(submitUserPayOrderRequest);
        }
    }

    @Test
    public void batchUpdate(){
        System.out.println("============================测试开始=============================");
        Map<String, JSONObject> map = new HashMap();
        for (int i = 0; i < 500; i++) {
            SubmitUserPayOrderRequest submitUserPayOrderRequest = new SubmitUserPayOrderRequest();
            String str = "20191212143440725_300004_9555";
            String s = str.substring(0, str.length() - 1) + i;
            submitUserPayOrderRequest.setId(s);
            submitUserPayOrderRequest.setMerchantId(807002L);
            submitUserPayOrderRequest.setUserPayId(0L);
            submitUserPayOrderRequest.setAmountOfConsumption(new BigDecimal(0.01));
            submitUserPayOrderRequest.setPayAblAmount(new BigDecimal(0.01));
            submitUserPayOrderRequest.setFreeAmount(new BigDecimal(0.01));
            submitUserPayOrderRequest.setPayType(0);
            submitUserPayOrderRequest.setDiscountAmount(new BigDecimal(0.00));
            submitUserPayOrderRequest.setStatus(4);
            submitUserPayOrderRequest.setChannelId(3);
            submitUserPayOrderRequest.setEnjoyKingAmount(new BigDecimal(0.05));
            submitUserPayOrderRequest.setScanType(1);
            map.put("01", (JSONObject) JSONObject.toJSON(submitUserPayOrderRequest));
        }

        for (int i = 0; i < 500; i++) {
            SubmitUserPayOrderRequest submitUserPayOrderRequest = new SubmitUserPayOrderRequest();
            String str = "20191212143440725_300005_9555";
            String s = str.substring(0, str.length() - 1) + i;
            submitUserPayOrderRequest.setId(s);
            submitUserPayOrderRequest.setMerchantId(807002L);
            submitUserPayOrderRequest.setUserPayId(0L);
            submitUserPayOrderRequest.setAmountOfConsumption(new BigDecimal(0.01));
            submitUserPayOrderRequest.setPayAblAmount(new BigDecimal(0.01));
            submitUserPayOrderRequest.setFreeAmount(new BigDecimal(0.01));
            submitUserPayOrderRequest.setPayType(0);
            submitUserPayOrderRequest.setDiscountAmount(new BigDecimal(0.00));
            submitUserPayOrderRequest.setStatus(4);
            submitUserPayOrderRequest.setChannelId(3);
            submitUserPayOrderRequest.setEnjoyKingAmount(new BigDecimal(0.05));
            submitUserPayOrderRequest.setScanType(1);
            map.put("02", (JSONObject) JSONObject.toJSON(submitUserPayOrderRequest));
        }

        for (int i = 0; i < 500; i++) {
            SubmitUserPayOrderRequest submitUserPayOrderRequest = new SubmitUserPayOrderRequest();
            String str = "20191212143440725_300006_9555";
            String s = str.substring(0, str.length() - 1) + i;
            submitUserPayOrderRequest.setId(s);
            submitUserPayOrderRequest.setMerchantId(807002L);
            submitUserPayOrderRequest.setUserPayId(0L);
            submitUserPayOrderRequest.setAmountOfConsumption(new BigDecimal(0.01));
            submitUserPayOrderRequest.setPayAblAmount(new BigDecimal(0.01));
            submitUserPayOrderRequest.setFreeAmount(new BigDecimal(0.01));
            submitUserPayOrderRequest.setPayType(0);
            submitUserPayOrderRequest.setDiscountAmount(new BigDecimal(0.00));
            submitUserPayOrderRequest.setStatus(4);
            submitUserPayOrderRequest.setChannelId(3);
            submitUserPayOrderRequest.setEnjoyKingAmount(new BigDecimal(0.05));
            submitUserPayOrderRequest.setScanType(1);
            map.put("02", (JSONObject) JSONObject.toJSON(submitUserPayOrderRequest));
        }
        esController.demoBatchUpdate("ww", map);
        System.out.println("============================测试结束=============================");
    }

    @Test
    public void searchSort(){
        System.out.println("============================测试开始=============================");
        MatchAllQueryBuilder bqb = QueryBuilders.matchAllQuery().queryName("payAblAmount");
        //QueryBuilder matchPhraseQuery= QueryBuilders.matchPhraseQuery("id","20191212143440725_300004_9555");
        String s = esController.searchSort("zz", bqb, "payAblAmount", SortOrder.ASC, 5);
        System.out.println(s);
        System.out.println("============================测试结束=============================");
    }


    @Test
    public void searchPagination(){
        System.out.println("============================测试开始=============================");
        MatchAllQueryBuilder bqb = QueryBuilders.matchAllQuery().queryName("payAblAmount");
        PageEs zz = esController.searchPagination(10, 100, "zz", bqb);
        System.out.println("共有：" + zz.getTotal() + "页");
        System.out.println("============================测试结束=============================");
    }

    @Test
    public void searchPaginationSort(){
        System.out.println("============================测试开始=============================");
        MatchAllQueryBuilder bqb = QueryBuilders.matchAllQuery().queryName("payAblAmount");
        PageEs pageEs = esController.searchPaginationSort(10, 10, "zz", bqb, "payAblAmount", SortOrder.ASC);
        System.out.println("共有：" + pageEs.getTotal() + "页");
        System.out.println("============================测试结束=============================");
    }

    @Test
    public void searchAfter(){
        System.out.println("============================测试开始=============================");
        MatchAllQueryBuilder bqb = QueryBuilders.matchAllQuery().queryName("payAblAmount");
        PageEs pageEs = esController.searchAfter(10, "wy", bqb, "payAblAmount", SortOrder.ASC);
        System.out.println("共有：" + pageEs.getTotal() + "页");
        System.out.println("============================测试结束=============================");
    }
}


