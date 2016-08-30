package com.hisw.dao;

import com.hisw.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by swallow on 2016/8/3.
 */
@Repository("studentDao")
public class StudentDaoImpl implements StudentDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List list() {
        String sql = "SELECT id,name,age FROM student";
        return jdbcTemplate.query(sql, new RowMapper() {
            @Override
            public Student mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Student(resultSet.getInt("id"), resultSet.getString(2), resultSet.getInt(3));
            }
        });
    }

    @Override
    public int getTotalPages() {
        String sql = "SELECT COUNT(*) FROM student";
//        int i = Integer.parseInt(String.valueOf(jdbcTemplate.queryForObject(sql, Integer.TYPE)));
        int i = jdbcTemplate.queryForObject(sql, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt(1);
            }
        });
        return i % 5 == 0 ? i / 5 : i / 5 + 1;
    }

    @Override
    public List listPage(int current) {
        String sql = "SELECT id,name,age FROM student LIMIT ?,5";
        return jdbcTemplate.query(sql, new Object[]{5 * (current - 1)}, new RowMapper() {
            @Override
            public Student mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Student(resultSet.getInt("id"), resultSet.getString(2), resultSet.getInt(3));
            }
        });
    }

    @Override
    public int save(Student student) {
        String sql = "INSERT student VALUES (?,?,?)";
        return jdbcTemplate.update(sql, student.getId(), student.getName(), student.getAge());
    }

    @Override
    public int update(Student student) {
        String sql = "UPDATE student SET AGE=? WHERE ID=?";
        return jdbcTemplate.update(sql, student.getAge(), student.getId());
    }

    @Override
    public int delete(Student student) {
        String sql = "DELETE FROM student WHERE ID=?";
        return jdbcTemplate.update(sql, student.getId());
    }
}
