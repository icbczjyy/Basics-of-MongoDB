package com.hisw.biz;

import com.hisw.model.Student;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by swallow on 2016/8/10.
 */
@Component
public interface MongoTemplateService {
    int getTotalPages();

    List list(int currentPage);

    boolean save(Student student);

    boolean update(Student student);

    boolean delete(Student student);
}
