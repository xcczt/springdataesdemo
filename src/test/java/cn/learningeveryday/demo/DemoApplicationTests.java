package cn.learningeveryday.demo;

import cn.learningeveryday.demo.Dao.ItemDao;
import cn.learningeveryday.demo.Model.Item;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Optional;


@SpringBootTest
class DemoApplicationTests {

	@Qualifier("elasticsearchClient")
	@Autowired
	RestHighLevelClient highLevelClient;

	@Autowired
	private ItemDao itemDao;

	@Autowired
	private ElasticsearchOperations elasticsearchOperations;
	@Test
	void contextLoads() throws IOException {
		// 1、创建索引请求
		GetRequest getRequest= new GetRequest("kibana_sample_data_ecommerce", "V5z1f28BdseAsPClo7bC");
		GetResponse getResponse = highLevelClient.get(getRequest, RequestOptions.DEFAULT);
		System.out.println(getResponse.getIndex());
		System.out.println(getResponse.toString());
	}

	//创建索引并且插入数据
	@Test
	public void testSave() {
		Item item = new Item();
		item.setId("3");
		item.setCategory("测试分类");

//		IndexCoordinates indexCoordinates = elasticsearchOperations.getIndexCoordinatesFor(item.getClass());
//		IndexQuery indexQuery = new IndexQueryBuilder()
//				.withId(item.getId())
//				.withObject(item)
//				.build();
//		String documentId = elasticsearchOperations.index(indexQuery, indexCoordinates);
//		System.out.println(documentId);
		itemDao.save(item);
	}

	@Test
	public void testFindAll() {
		long count = itemDao.count();
		System.out.println(count);
		Iterable<Item> result = itemDao.findAll();
		Iterator<Item> data = result.iterator();
		while (data.hasNext()) {
			System.out.println(data.next());
		}
	}

	@Test
	public void testFindById() {
		Optional<Item> data = itemDao.findById("3");
		if (data.isPresent()){
			System.out.println(data.get());
		}

	}

}
