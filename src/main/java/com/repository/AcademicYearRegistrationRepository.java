package com.repository;

import com.model.AcademicYearRegistration;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AcademicYearRegistrationRepository extends JpaRepository<AcademicYearRegistration, Long> {

    AcademicYearRegistration findByAcademicYearAndStudentId(int academicYear, Long studentId);

}
