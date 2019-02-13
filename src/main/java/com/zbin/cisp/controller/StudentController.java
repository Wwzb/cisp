package com.zbin.cisp.controller;

import com.zbin.cisp.dao.StudentMapper;
import com.zbin.cisp.domain.Student;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Zbin on 2019-02-13
 */
@Controller
public class StudentController {

  @Resource
  StudentMapper studentMapper;

  @RequestMapping("/getInfo")
  @ResponseBody
  public Student getInfo() {
    Student student = studentMapper.selectById(1L);
    return student;
  }

  @RequestMapping("insert")
  @ResponseBody
  public String insert() {
    Student student = new Student();
    student.setId(3L);
    student.setAge(30);
    student.setName("LGS");
    int result = studentMapper.insert(student);
    if (result == 1) {
      return "success";
    } else {
      return "failed";
    }
  }


}
