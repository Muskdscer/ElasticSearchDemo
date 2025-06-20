package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.shade.com.alibaba.fastjson.JSON;
import com.example.demo.component.AlEsClientComponent;
import com.example.demo.entity.SubmitUserPayOrderRequest;
import com.example.demo.entity.model.es.PageEs;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EsController {
    @Autowired
    AlEsClientComponent alEsClientComponent;

    /**
     * 插入es
     * @param userPayOrderRequest 订单详情
     * @return
     */
    @PostMapping("/es/submit")
    public String submit(@RequestBody SubmitUserPayOrderRequest userPayOrderRequest){
        Long result=alEsClientComponent.save("wy", JSON.toJSONString((userPayOrderRequest)));
        return String.valueOf(result);
    }

    /**
     * 查询es
     * @param
     * @return
     */
    @PostMapping("/es/search")
    public String search(){
        /**
         * matchAllQuery() :匹配全部文档
         * matchQuery(String name,Object  text)匹配单个字段，匹配字段名为filedname,值为value的文档
         * multiMatchQuery(Object text, String... fieldNames):多个字段匹配某一个值
         * wildcardQuery():模糊查询 (?匹配单个字符，*匹配多个字符)
         * BoolQueryBuilder:复合查询 使用must
         */
        Map<String,String> result=new HashMap<>();
        //匹配所有文档 参数列表(字段名称或者value值)
        MatchAllQueryBuilder bqb = QueryBuilders.matchAllQuery().queryName("payAblAmount");
        String matchAllQueryResult=alEsClientComponent.search("ww",bqb,10);
        result.put("matchAllQueryResult",matchAllQueryResult);
        //分词查询 text为中文，matchPhraseQuery/matchPhraseQueryResult都可以查询
        QueryBuilder matchPhraseQuery=QueryBuilders.matchPhraseQuery("id","三大");
        String matchPhraseQueryResult=alEsClientComponent.search("zz",matchPhraseQuery,10);
        result.put("matchPhraseQueryResult",matchPhraseQueryResult);
        //text为英文，matchPhrasePrefixQuery适用于英文 一般在存储的时候会默认把英文按照单词分词
        QueryBuilder matchPhrasePrefixQuery=QueryBuilders.matchPhrasePrefixQuery("id","text");
        String matchPhrasePrefixQueryResult=alEsClientComponent.search("zz",matchPhrasePrefixQuery,10);
        result.put("matchPhrasePrefixQueryResult",matchPhrasePrefixQueryResult);
        //匹配字段数据  es中index:zz，column:id text:阿三大苏打  三苏也能查询到这条数据
        QueryBuilder matchQuery=QueryBuilders.matchQuery("id","三苏");
        String matchQueryResult=alEsClientComponent.search("zz",matchQuery,10);
        result.put("matchQueryResult",matchQueryResult);
        //将text匹配多个字段 参数列表(text，Field1，Field2，Field3，Field4........)
        QueryBuilder multiMatchQuery=QueryBuilders.multiMatchQuery("1","id","status");
        String multiMatchQueryResult=alEsClientComponent.search("zz",multiMatchQuery,10);
        result.put("multiMatchQueryResult",multiMatchQueryResult);
        //模糊查询 由于分词关系 (?匹配单个字符，*匹配多个字符) 中文只能查询单个字 而英文可以任意长度
        QueryBuilder wildcardQuery=QueryBuilders.wildcardQuery("id","*extzzwq*");
        String wildcardQueryResult=alEsClientComponent.search("zz",wildcardQuery,10);
        result.put("wildcardQueryResult",wildcardQueryResult);
        //复合查询 must的意义等同sql查询中的and  must的参数为上面任意一个QueryBuilder
        QueryBuilder BoolQueryBuilder=QueryBuilders.boolQuery().must(matchPhraseQuery).must(matchQuery);
        String BoolQueryBuilderResult=alEsClientComponent.search("zz",BoolQueryBuilder,10);
        result.put("BoolQueryBuilderResult",BoolQueryBuilderResult);
        return JSON.toJSONString(result);
    }

    /**
     * 查询es
     * @param
     * @return
     */
    @PostMapping("/es/search/demo")
    public String searchDemo(){
        /**
         * matchAllQuery() :匹配全部文档
         * matchQuery(String name,Object  text)匹配单个字段，匹配字段名为filedname,值为value的文档
         * multiMatchQuery(Object text, String... fieldNames):多个字段匹配某一个值
         * wildcardQuery():模糊查询 (?匹配单个字符，*匹配多个字符)
         * BoolQueryBuilder:复合查询 使用must
         */
        Map<String,String> result=new HashMap<>();
        //匹配所有文档 参数列表(字段名称或者value值)
//        MatchAllQueryBuilder bqb = QueryBuilders.matchAllQuery().queryName("payAblAmount");
//        String matchAllQueryResult=alEsClientComponent.search("zz",bqb,10);
//        result.put("matchAllQueryResult",matchAllQueryResult);

//        QueryBuilder matchPhraseQuery=QueryBuilders.matchPhraseQuery("id","20191212143440725_300004_9558");
//        String matchPhraseQueryResult=alEsClientComponent.search("zz",matchPhraseQuery,10);
//        result.put("matchPhraseQueryResult",matchPhraseQueryResult);

        //分词查询 text为中文，matchPhraseQuery/matchPhraseQueryResult都可以查询
//        QueryBuilder matchPhraseQuery=QueryBuilders.matchPhraseQuery("id","20191212143440725_300004_9555");
//        String matchPhraseQueryResult=alEsClientComponent.search("zz",matchPhraseQuery,10);
//        result.put("matchPhraseQueryResult",matchPhraseQueryResult);

        //text为英文，matchPhrasePrefixQuery适用于英文 一般在存储的时候会默认把英文按照单词分词
//        QueryBuilder matchPhrasePrefixQuery=QueryBuilders.matchPhrasePrefixQuery("id","20191212143440725");
//        String matchPhrasePrefixQueryResult=alEsClientComponent.search("zz",matchPhrasePrefixQuery,10);
//        result.put("matchPhrasePrefixQueryResult",matchPhrasePrefixQueryResult);

        //匹配字段数据  es中index:zz，column:id text:阿三大苏打  三苏也能查询到这条数据
//        QueryBuilder matchQuery=QueryBuilders.matchQuery("id","20191212143440725");
//        String matchQueryResult=alEsClientComponent.search("zz",matchQuery,10);
//        result.put("matchQueryResult",matchQueryResult);

        QueryBuilder matchPhraseQuery=QueryBuilders.matchPhraseQuery("id","20191212143440725");
        QueryBuilder matchQuery=QueryBuilders.matchQuery("id","20191212143440725");
        //复合查询 must的意义等同sql查询中的and  must的参数为上面任意一个QueryBuilder
        QueryBuilder BoolQueryBuilder=QueryBuilders.boolQuery().must(matchPhraseQuery).must(matchQuery);
        String BoolQueryBuilderResult=alEsClientComponent.search("zz",BoolQueryBuilder,10);
        result.put("BoolQueryBuilderResult",BoolQueryBuilderResult);
        return JSON.toJSONString(result);
    }

    /**
     * 批量跟新
     * @param
     * @return
     */
    @PostMapping("/es/search/demo/batchUpdate")
    public boolean demoBatchUpdate(String index, Map<String, JSONObject> documentMap){
        return alEsClientComponent.batchUpdate(index, documentMap);
    }

    /**
     * 批量跟新
     * @param
     * @return
     */
    @PostMapping("/es/search/demo/searchSort")
    public String searchSort(String index, QueryBuilder queryBuilder, String sortName,
                             SortOrder order, Integer size){
        return alEsClientComponent.searchSort(index, queryBuilder, sortName, order, size);
    }

    /**
     * 分页 + 条件查询
     * @param pageNum 页 (从0开始)
     * @param pageSize 每页多少条
     * @param index 索引
     * @param type 索引类型
     * @param queryBuilder 条件 可为null
     * @return null 查询失败
     */
    @PostMapping("/es/search/demo/searchPagination")
    public PageEs searchPagination(int pageNum, int pageSize, String index,
                                   QueryBuilder queryBuilder){
        return alEsClientComponent.searchPagination(pageNum, pageSize, index, queryBuilder);
    }


    /**
     * 分页 + 条件查询 +排序
     * @param pageNum 页 (从0开始)
     * @param pageSize 每页多少条
     * @param index 索引
     * @param type 索引类型
     * @param queryBuilder 条件 可为null
     * @param sortName 排序字段
     * @param order 排序规则 SortOrder.ASC:正序；SortOrder.DESC倒序
     * @return null 查询失败
     */
    @PostMapping("/es/search/demo/searchPaginationSort")
    public PageEs searchPaginationSort(int pageNum, int pageSize, String index,
                                       QueryBuilder queryBuilder, String sortName,
                                       SortOrder order){
        return alEsClientComponent.searchPaginationSort(pageNum, pageSize, index, queryBuilder, sortName, order);
    }

    /**
     * search_after
     * @param pageSize 每页多少条
     * @param index 索引
     * @param queryBuilder 条件 可为null
     * @param sortName 排序字段
     * @param order 排序规则 SortOrder.ASC:正序；SortOrder.DESC倒序
     * @return null 查询失败
     */
    @PostMapping("/es/search/demo/searchAfter")
    public PageEs searchAfter(int pageSize, String index,
                                       QueryBuilder queryBuilder, String sortName,
                                       SortOrder order){
        return alEsClientComponent.searchAfter(pageSize, index, queryBuilder, sortName, order);
    }
}
