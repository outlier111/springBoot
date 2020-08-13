package com.hqyj.javaSpringBoot.modules.test.service.impl;

import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;
import com.hqyj.javaSpringBoot.modules.test.entity.Student;
import com.hqyj.javaSpringBoot.modules.test.repository.StudentRepository;
import com.hqyj.javaSpringBoot.modules.test.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.OpenOption;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    @Transactional
    public Result<Student> insertStudent(Student student) {
        student.setCreateDate(LocalDateTime.now());
        studentRepository.saveAndFlush(student);
        return new Result<Student>(Result.ResultStatus.SUCCESS.status,
                "Insert success", student);
    }

    @Override
    public Student getStudentByStudentId(int studentId) {
        return studentRepository.findById(studentId).get();
    }

    @Override
    public Page<Student> getStudentBySearchVo(SearchVo searchVo) {
        Sort.Direction direction = "desc".equalsIgnoreCase(searchVo.getSort()) ?
                Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = new Sort(direction,
                StringUtils.isBlank(searchVo.getOrderBy()) ?
                        "studentId" : searchVo.getOrderBy());
//      起始页从0开始
        Pageable pageable = PageRequest.of(searchVo.getCurrentPage() - 1,
                searchVo.getPageSize(), sort);
        // build Example 对象
        // 如果 keyWord 为 null，则设置的 studentName 不参与查询条件
        Student student = new Student();
        student.setStudentName(searchVo.getKeyWord());
        ExampleMatcher matcher = ExampleMatcher.matching()
                // 全部模糊查询，即 %{studentName} %
                .withMatcher("studentName", match -> match.contains())
                // 忽略字段，即不管id是什么值都不加入查询条件
                .withIgnorePaths("studentId");
        Example<Student> example = Example.of(student, matcher);
        return studentRepository.findAll(example, pageable);
    }


    @Override
    public List<Student> getStudentsByStudentName(String studentName,int cardId) {
       if (cardId > 0){
           return studentRepository.getStudentByParams(studentName,cardId);
       }else{

            /*return Optional
                    .ofNullable(studentRepository.findByStudentName(studentName))
                    .orElse(Collections.emptyList());*/
    /*        return Optional
                    .ofNullable(studentRepository.findByStudentNameLike
                            (String.format("%s%S%s","%",studentName,"%")))
                            .orElse(Collections.emptyList());*/
            return Optional
                    .ofNullable(studentRepository.findTop2ByStudentNameLike(
                            String.format("%s%S%s","%",studentName,"%")))
                            .orElse(Collections.emptyList());
       }

    }
}
