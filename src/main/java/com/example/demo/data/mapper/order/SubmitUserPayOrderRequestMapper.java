package com.example.demo.data.mapper.order;


import com.example.demo.entity.SubmitUserPayOrderRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SubmitUserPayOrderRequestMapper {
    @Select("select * from user_0 where id=#{id}")
    SubmitUserPayOrderRequest findOrderById(String id);
}
