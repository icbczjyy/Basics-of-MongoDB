package com.hisw.biz;

import com.hisw.dao.MongoTemplateDao;
import com.hisw.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by swallow on 2016/8/10.
 */
@Service("mongoTemplateService")
public class MongoTemplateServiceImpl implements MongoTemplateService {
    @Autowired
    private MongoTemplateDao mongoTemplateDao;

    @Override
    public int getTotalPages() {
        return mongoTemplateDao.getTotalPages();
    }

    @Override
    public List list(int currentPage) {
        return mongoTemplateDao.list(currentPage);
    }

    @Override
    public boolean save(Student student) {
        return mongoTemplateDao.save(student);
    }

    @Override
    public boolean update(Student student) {
        return mongoTemplateDao.update(student);
    }

    @Override
    public boolean delete(Student student) {
        return mongoTemplateDao.delete(student);
    }
}
