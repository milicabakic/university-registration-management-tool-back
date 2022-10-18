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


    @Autowired
    public GroupOfSubjectsService(GroupOfSubjectsRepository groupOfSubjectsRepository) {
        this.groupOfSubjectsRepository = groupOfSubjectsRepository;
    }

    public GroupOfSubjects findById(Long id) {
        return groupOfSubjectsRepository.findById(id).get();
    }

    public List<GroupOfSubjects> findGroupsByAcademicYear(int academicYear, String academicProgramCode) {
        List<Integer> semesters = calculateSemestersByAcademicYear(academicYear);
        if(semesters.size() != 2) {
            System.out.println("Greska u racunanju semestara");
            return null;
        }
        return groupOfSubjectsRepository.findBySemesterAndAcademicProgramCode(semesters.get(0), semesters.get(1), academicProgramCode);
    }

    /*
    One academic year has 2 semesters
     */
    private List<Integer> calculateSemestersByAcademicYear(int academicYear) {
        List<Integer> semesters = new ArrayList<>();
        switch (academicYear) {
            case 1:
                Collections.addAll(semesters, 1, 2);
                break;
            case 2:
                Collections.addAll(semesters, 3, 4);
                break;
            case 3:
                Collections.addAll(semesters, 5, 6);
                break;
            case 4:
                Collections.addAll(semesters, 7, 8);
                break;
        }

        return semesters;
    }

}
