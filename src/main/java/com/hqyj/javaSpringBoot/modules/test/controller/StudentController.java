package com.hqyj.javaSpringBoot.modules.test.controller;

import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;
import com.hqyj.javaSpringBoot.modules.test.entity.Student;
import com.hqyj.javaSpringBoot.modules.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /*
    127.0.0.1/api/student--------post
    {"studentName":"jige","studentCard":{"cardId":"1"}}
     */
    @PostMapping(value = "/student", consumes = "application/json")
    public Result<Student> insertStudent(@RequestBody Student student){
        return studentService.insertStudent(student);
    }

    /*
    127.0.0.1/api/student/1--------get
     */
    @GetMapping("/student/{studentId}")
    public Student getStudentByStudentId(@PathVariable int studentId) {
        return studentService.getStudentByStudentId(studentId);
    }

    /*
      127.0.0.1/api/students ---- post
      {"currentPage":"1","pageSize":"5","keyWord":"hu","orderBy":"studentName","sort":"desc"}
     */
    @PostMapping(value = "/students",consumes = "application/json")
    public Page<Student> getStudentBySearchVo(@RequestBody SearchVo searchVo) {
        return studentService.getStudentBySearchVo(searchVo);
    }

    /*
    127.0.0.1/api/students?studentName=jige1--------get
     */
    @GetMapping("/students")
    public List<Student> getStudentsByParams(
            @RequestParam String studentName,
            @RequestParam(required = false,defaultValue = "0") Integer cardId) {
        return studentService.getStudentsByStudentName(studentName,cardId);
    }
}
