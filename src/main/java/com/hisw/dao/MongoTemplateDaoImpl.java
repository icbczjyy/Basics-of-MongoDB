package com.hisw.dao;

import com.hisw.model.Student;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swallow on 2016/8/10.
 */
@Repository
public class MongoTemplateDaoImpl implements MongoTemplateDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public int getTotalPages() {
        int i = (int) mongoTemplate.getCollection("test").count();
        return i % 5 == 0 ? i / 5 : i / 5 + 1;
    }

    @Override
    public List list(int currentPage) {
        DBCursor dbObjects = mongoTemplate.getCollection("test").find().skip(5 * currentPage - 5).limit(5);
        List<Student> students = new ArrayList<Student>();
        for (DBObject dbObject : dbObjects) {
            students.add(convert(dbObject));
        }
        return students;
    }

    @Override
    public boolean save(Student student) {
        System.out.println(mongoTemplate.getCollection("test").count());
        List<Student> list = mongoTemplate.find(new Query(Criteria.where("id").gt(5)), Student.class, "test");
        System.out.println("in dao" + list);
        mongoTemplate.save(student);
        return mongoTemplate.getCollection("test").save(encode(student)).wasAcknowledged();
    }

    @Override
    public boolean update(Student student) {
        return false;
    }

    @Override
    public boolean delete(Student student) {
        return false;
    }

    private Student convert(DBObject document) {
        return new Student(Integer.parseInt(document.get("id").toString()), document.get("name").toString(), Integer.parseInt(document.get("age").toString()));
    }

    private DBObject encode(Student student) {
        return new BasicDBObject("id", student.getId()).append("name", student.getName()).append("age", student.getAge());
    }
}
