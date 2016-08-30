package com.hisw.dao;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by swallow on 2016/8/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/SpringMVC-servlet.xml")
public class MongoTemplateDaoImplTest {
    @Autowired
    private MongoTemplateDao mongoTemplateDao;

    @Test
    public void testGetTotalPages() throws Exception {
        int i = mongoTemplateDao.getTotalPages();
        assertEquals(2,i);
    }

    @Test
    @Ignore
    public void testList() throws Exception {

    }

    @Test
    @Ignore
    public void testSave() throws Exception {

    }

    @Test
    @Ignore
    public void testUpdate() throws Exception {

    }

    @Test
    @Ignore
    public void testDelete() throws Exception {

    }
}