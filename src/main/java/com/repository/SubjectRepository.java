package com.repository;

import com.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findByAcademicYearLessThanEqual(int academicYear);

    List<Subject> findByAcademicYearLessThan(int academicYear);

    List<Subject> findByAcademicYearEquals(int academicYear);
}
