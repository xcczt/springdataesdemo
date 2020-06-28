package cn.learningeveryday.demo.Dao;

import cn.learningeveryday.demo.Model.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDao  extends ElasticsearchRepository<Item, String> {

}
