package com.repository;

import com.model.GroupOfSubjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupOfSubjectsRepository extends JpaRepository<GroupOfSubjects, Long> {

    @Query("SELECT DISTINCT g FROM GroupOfSubjects g LEFT JOIN FETCH g.subjects WHERE (g.semester = :semester1 OR g.semester = :semester2) " +
            "AND g.academicProgram.code LIKE :academicProgramCode")
    List<GroupOfSubjects> findBySemesterAndAcademicProgramCode(int semester1, int semester2, String academicProgramCode);

}
