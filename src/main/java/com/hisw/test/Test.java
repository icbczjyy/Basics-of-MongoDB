package com.hisw.test;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swallow on 2016/8/4.
 */
public class Test {
    public static void main(String[] args) {
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
//        StudentDao studentDao = (StudentDao) applicationContext.getBean("studentDao");
//        Student student = new Student(101, "qwe", 20);
//        for (int i = 0; i < 20; i++) {
//            student.setId(student.getId() + 1);
//            studentDao.save(student);
//        }
//        List<Student> list = studentDao.list();
//        System.out.println(list);
//        System.out.println(list.get(0).getId());
        MongoClient mongoClient = null;
        try {
            // 连接到 mongodb 服务
            mongoClient = new MongoClient("localhost", 27017);

            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
            System.out.println("Connect to database successfully " + mongoDatabase);
            MongoCollection<Document> collection = mongoDatabase.getCollection("test");
            List<Document> documents = new ArrayList<Document>();
            for (int i = 1; i < 10; i++) {
                Document document = new Document("id", i).
                        append("name", "abc").
                        append("age", 20);
                documents.add(document);
            }
            collection.insertMany(documents);

            FindIterable<Document> findIterable = collection.find();
            for (Document doc : findIterable) {
                doc.remove("_id");
                System.out.println(doc);
                System.out.println(doc.get("id"));
            }

/*
            collection.updateMany(Filters.eq("likes", 100), new Document("$set", new Document("likes", 200)));
            findIterable = collection.find();
            for (Document doc : findIterable) {
                System.out.println(doc);
            }

            collection.deleteOne(Filters.eq("likes", 200));
            collection.deleteMany(Filters.eq("likes", 200));

            findIterable = collection.find();
            for (Document doc : findIterable) {
                System.out.println(doc);
            }
*/


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            if (null != mongoClient) {
                mongoClient.close();
            }
        }
    }
}
