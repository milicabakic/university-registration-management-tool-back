package com.repository;

import com.model.AcademicProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicProgramRepository extends JpaRepository<AcademicProgram, Long> {

    AcademicProgram findByCode(String code);
}
