package com.service;

import com.model.GroupOfSubjects;
import com.repository.GroupOfSubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GroupOfSubjectsService {

    private GroupOfSubjectsRepository groupOfSubjectsRepository;
    private final int SEMESTERS_PER_YEAR = 2;
    private final int REQUIRED_ACADEMIC_YEAR = 3;
    private final int FINAL_ACADEMIC_YEAR = 4;


    @Autowired
    public GroupOfSubjectsService(GroupOfSubjectsRepository groupOfSubjectsRepository) {
        this.groupOfSubjectsRepository = groupOfSubjectsRepository;
    }

    public GroupOfSubjects findById(Long id) {
        return groupOfSubjectsRepository.findById(id).get();
    }

    public List<GroupOfSubjects> findByAcademicYearAndRenewedFlag(int academicYear, String academicProgramCode, boolean renewed) {
        if (renewed && academicYear < REQUIRED_ACADEMIC_YEAR) {
            return new ArrayList<>();
        }

        return findByAcademicYear(academicYear, academicProgramCode);
    }

    public List<GroupOfSubjects> findByAcademicYear(int academicYear, String academicProgramCode) {
        List<Integer> semesters = calculateSemestersByAcademicYear(academicYear);
        if(semesters.size() != SEMESTERS_PER_YEAR) {
            return new ArrayList<>();
        }

        System.out.println("GODINA " + academicYear + ", SEMESTRI " + semesters);
        return groupOfSubjectsRepository.findBySemesterAndAcademicProgramCode(semesters.get(0), semesters.get(1), academicProgramCode);
    }

    /*
    One academic year has 2 semesters
     */
    private List<Integer> calculateSemestersByAcademicYear(int academicYear) {
        List<Integer> semesters = new ArrayList<>();
        if (academicYear > FINAL_ACADEMIC_YEAR) {
            return semesters;
        }

        Collections.addAll(semesters, academicYear * SEMESTERS_PER_YEAR - 1, academicYear * SEMESTERS_PER_YEAR);
        return semesters;
    }

}
