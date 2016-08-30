package com.hisw.dao;

import com.hisw.model.Student;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryOperators;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swallow on 2016/8/9.
 */
@Repository("mongoTestDao")
public class MongoTestDaoImpl implements MongoTestDao {
    static MongoClient mongoClient = null;
    static MongoDatabase mongoDatabase = null;
    static MongoCollection<Document> docs = null;

    static {
        try {
            // 连接到 mongodb 服务
            mongoClient = new MongoClient("localhost", 27017);
            // 连接到数据库
            mongoDatabase = mongoClient.getDatabase("test");
            docs = mongoDatabase.getCollection("test");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    @Override
    public int getTotalPages() {
        int i = (int) docs.count();
        return i % 5 == 0 ? i / 5 : i / 5 + 1;
    }

    @Override
    public List<Student> list(int currentPage) {
        FindIterable<Document> documents = docs.find().sort(new BasicDBObject()).skip(5 * currentPage - 5).limit(5);
        List<Student> students = new ArrayList<Student>();
        for (Document document : documents) {
            students.add(convert(document));
        }
/*
        FindIterable<Document> findIterable = docs.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while(mongoCursor.hasNext()){
            System.out.println(mongoCursor.next());
        }
*/
        return students;
    }

    @Override
    public boolean save(Student student) {
        Document document = new Document("id", student.getId()).append("name", student.getName()).append("age", student.getAge());
        docs.insertOne(document);
        return true;
    }

    @Override
    public boolean update(Student student) {
        Document document = new Document("id", student.getId()).append("name", student.getName()).append("age", student.getAge());
        UpdateResult result = docs.replaceOne(Filters.eq("id", student.getId()), document);
        return result.isModifiedCountAvailable();
    }

    @Override
    public boolean delete(Student student) {
        DeleteResult result = docs.deleteOne(Filters.eq("id", student.getId()));
        return result.wasAcknowledged();
    }

    private Student convert(Document document) {
        return new Student(Integer.parseInt(document.get("id").toString()), document.get("name").toString(), Integer.parseInt(document.get("age").toString()));
    }
}
/*
模糊查询条件：
1、完全匹配
Pattern pattern = Pattern.compile("^name$", Pattern.CASE_INSENSITIVE);
2、右匹配
Pattern pattern = Pattern.compile("^.*name$", Pattern.CASE_INSENSITIVE);
3、左匹配
Pattern pattern = Pattern.compile("^name.*$", Pattern.CASE_INSENSITIVE);
4、模糊匹配
Pattern pattern = Pattern.compile("^.*name8.*$", Pattern.CASE_INSENSITIVE);

记录总数查询：
count()，返回查询总数。

查询记录排序：
BasicDBObject sort = new BasicDBObject();
sort.put("name",1);
1,表示正序；－1,表示倒序

分页查询：
skip()，跳过多少条记录
limit()，返回多少条记录

package com.what21.mongodb.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class OperateDemo2 {

    /**
     * @return
     * @throws Exception
public static MongoClient getMongoClient()throws Exception{
    try {
        //===================================================//
        List<ServerAddress> serverList = new ArrayList<ServerAddress>();
        serverList.add(new ServerAddress("192.168.18.85", 27017));
        //===================================================//
        List<MongoCredential> mcList = new ArrayList<MongoCredential>();
        String username = "root";
        String database = "demo";
        char[] password = "root123".toCharArray();
        mcList.add(MongoCredential.createCredential(username, database,password));
        //===================================================//
        MongoClientOptions.Builder builder = MongoClientOptions.builder();
        // 与目标数据库能够建立的最大connection数量为50
        builder.connectionsPerHost(50);
        // 如果当前所有的connection都在使用中，则每个connection上可以有50个线程排队等待
        builder.threadsAllowedToBlockForConnectionMultiplier(50);
        // 一个线程访问数据库的时候，在成功获取到一个可用数据库连接之前的最长等待时间为2分钟
        // 这里比较危险，如果超过maxWaitTime都没有获取到这个连接的话，该线程就会抛出Exception
        // 故这里设置的maxWaitTime应该足够大，以免由于排队线程过多造成的数据库访问失败
        builder.maxWaitTime(1000*60*2);
        // 与数据库建立连接的timeout设置为1分钟
        builder.connectTimeout(1000*60*1);
        //===================================================//
        MongoClientOptions mco = builder.build();
        return new MongoClient(serverList, mcList, mco);
    } catch (Exception e) {
        throw e;
    }
}

    /**
     * @param dbname
     * @return
     * @throws Exception
    public static DB getDB(String dbname) throws Exception{
        return getMongoClient().getDB(dbname);
    }

    /**
     * @param db
    public static void collections(DB db){
        Set<String> colls = db.getCollectionNames();
        for (String collName : colls) {
            System.out.println(collName);
        }
    }

    /**
     * 记录总数查询
     *
     * @param db
     * @param name
    public static void count(DB db,String name){
        DBCollection dbColl = db.getCollection(name);
        int count = dbColl.find().count();
        System.out.println("共有： " + count + "个");
    }


    /**
     * 模糊查询
     *
     * @param db
     * @param name
    public static void query(DB db,String name){
        DBCollection dbColl = db.getCollection(name);
        //完全匹配
        //Pattern pattern = Pattern.compile("^name$", Pattern.CASE_INSENSITIVE);
        //右匹配
        //Pattern pattern = Pattern.compile("^.*name$", Pattern.CASE_INSENSITIVE);
        //左匹配
        //Pattern pattern = Pattern.compile("^name.*$", Pattern.CASE_INSENSITIVE);
        //模糊匹配
        Pattern pattern = Pattern.compile("^.*name8.*$", Pattern.CASE_INSENSITIVE);
        BasicDBObject query = new BasicDBObject();
        query.put("name",pattern);
        BasicDBObject sort = new BasicDBObject();
        // 1,表示正序； －1,表示倒序
        sort.put("name",1);
        DBCursor cur = dbColl.find(query).sort(sort);
        int count = 0;
        while (cur.hasNext()) {
            DBObject obj = cur.next();
            System.out.print("name=" + obj.get("name"));
            System.out.print(",email=" + obj.get("email"));
            System.out.println(",passwd=" + obj.get("passwd"));
            count ++;
        }
        System.out.println("共有： " + count + "个");
    }


    /**
     * 分页查询
     *
     * @param db
     * @param name
     * @param start
     * @param pageSize
    public static void page(DB db,String name,int start,int pageSize){
        DBCollection dbColl = db.getCollection(name);
        BasicDBObject sort = new BasicDBObject();
        sort.put("name",1);
        DBCursor cur = dbColl.find().sort(sort).skip(start).limit(pageSize);;
        int count = 0;
        while (cur.hasNext()) {
            DBObject obj = cur.next();
            System.out.print("name=" + obj.get("name"));
            System.out.print(",email=" + obj.get("email"));
            System.out.println(",passwd=" + obj.get("passwd"));
            count ++;
        }
        System.out.println("共有： " + count + "个");
    }
    /**
     * @param args
     * @throws Exception
    public static void main(String[] args) throws Exception {
        DB db = getDB("demo");
        collections(db);
        String name = "users";
        System.out.println("count()=================================================");
        count(db,name);
        System.out.println("query()=================================================");
        query(db,name);
        System.out.println("page()=================================================");
        page(db,name,10, 10);
    }

}
 */
