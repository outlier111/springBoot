package com.hqyj.javaSpringBoot.modules.test.repository;

import com.hqyj.javaSpringBoot.modules.test.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

//    通过姓名查询信息
//    List<Student> findByStudentName(String studentName);

//    通过模糊姓名查询信息-

//    List<Student> findByStudentNameLike(String studentName);

//    通过模糊姓名查询信息 取前两条

    List<Student> findTop2ByStudentNameLike(String studentName);

//    自定义查询
    @Query(value = "select s from Student s where s.studentName = ?1 and s.studentCard.cardId = ?2")
    /*@Query(value = "select s from Student s where " +
            "s.studentName = :studentName and s.studentCard.cardId = :cardId")
    List<Student> getStudentByParams(
            @Param("studentName") String studentName,
            @Param("cardId")int cardId);*/
    List<Student> getStudentByParams(String studentName,int cardId);
}
