package com.hisw.controller;

import com.hisw.biz.MongoService;
import com.hisw.biz.MongoTemplateService;
import com.hisw.biz.StudentService;
import com.hisw.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by swallow on 2016/8/3.
 */
@Controller
public class StudentController {
    private StudentService studentService;
    @Autowired
    private MongoService mongoService;
    @Autowired
    private MongoTemplateService mongoTemplateService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/listStudent", method = RequestMethod.GET)
    public String listStudent(ModelMap modelMap) {
        modelMap.addAttribute("students", studentService.list());
        return "listStudent";
    }

    @RequestMapping(value = "/listStudentByPage", method = RequestMethod.GET)
    public String listStudentByPage(ModelMap modelMap, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage) {
        int totalPages = studentService.getTotalPages();
        if (currentPage < 1 || currentPage > totalPages) {
            modelMap.addAttribute("error", "Funny?");
        } else {
            modelMap.addAttribute("students", studentService.listPage(currentPage));
            modelMap.addAttribute("totalPage", totalPages);
            modelMap.addAttribute("currentPage", currentPage);
        }
        return "listStudentByPage.vm";
    }

    @RequestMapping(value = "/listMongo", method = RequestMethod.GET)
    public String listMongo(ModelMap modelMap, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage) {
        int totalPages = mongoService.getTotalPages();
        if (currentPage < 1 || currentPage > totalPages) {
            modelMap.addAttribute("error", "Funny?");
        } else {
            modelMap.addAttribute("students", mongoService.list(currentPage));
            modelMap.addAttribute("totalPage", totalPages);
            modelMap.addAttribute("currentPage", currentPage);
        }
        boolean b = mongoTemplateService.save(new Student(12, "ccc", 12));
        System.out.println(b);
        return "listStudentByPage.vm";
    }
}
