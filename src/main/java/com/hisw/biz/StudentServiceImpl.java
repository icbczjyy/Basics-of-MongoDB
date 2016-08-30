package com.hisw.biz;

import com.hisw.dao.StudentDao;
import com.hisw.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by swallow on 2016/8/3.
 */
@Service("studentService")
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List list() {
        return studentDao.list();
    }

    @Override
    public int getTotalPages() {
        return studentDao.getTotalPages();
    }

    @Override
    public List listPage(int current) {
        return studentDao.listPage(current);
    }

    @Override
    public int save(Student student) {
        return studentDao.save(student);
    }

    @Override
    public int update(Student student) {
        return studentDao.update(student);
    }

    @Override
    public int delete(Student student) {
        return studentDao.delete(student);
    }
}
