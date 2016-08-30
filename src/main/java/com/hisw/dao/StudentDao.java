package com.hisw.dao;

import com.hisw.model.Student;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by swallow on 2016/8/3.
 */
@Component
public interface StudentDao {
    List list();

    int getTotalPages();

    List listPage(int current);

    int save(Student student);

    int update(Student student);

    int delete(Student student);
}
