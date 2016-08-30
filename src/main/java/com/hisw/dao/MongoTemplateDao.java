package com.hisw.dao;

import com.hisw.model.Student;

import java.util.List;

/**
 * Created by swallow on 2016/8/10.
 */
public interface MongoTemplateDao {
    int getTotalPages();

    List list(int currentPage);

    boolean save(Student student);

    boolean update(Student student);

    boolean delete(Student student);
}
