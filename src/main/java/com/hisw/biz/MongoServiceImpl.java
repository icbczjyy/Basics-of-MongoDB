package com.hisw.biz;

import com.hisw.dao.MongoTestDao;
import com.hisw.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by swallow on 2016/8/10.
 */
@Service("mongoService")
public class MongoServiceImpl implements MongoService {
    @Autowired
    private MongoTestDao mongoTestDao;

    @Override
    public int getTotalPages() {
        return mongoTestDao.getTotalPages();
    }

    @Override
    public List list(int currentPage) {
        return mongoTestDao.list(currentPage);
    }

    @Override
    public boolean save(Student student) {
        return mongoTestDao.save(student);
    }

    @Override
    public boolean update(Student student) {
        return mongoTestDao.update(student);
    }

    @Override
    public boolean delete(Student student) {
        return mongoTestDao.delete(student);
    }
}
