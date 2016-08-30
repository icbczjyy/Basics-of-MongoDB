package com.hisw.biz;

import com.hisw.model.Student;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by swallow on 2016/8/3.
 */
@Component
public interface StudentService {

    List list();

    int getTotalPages();

    List listPage(int current);

    /**
     *  保存一个学生
     * @param student
     * @return
     */
    int save(Student student);

    int update(Student student);

    int delete(Student student);
}
