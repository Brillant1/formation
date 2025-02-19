package bj.formation.demoprojet.mappers;


import bj.formation.demoprojet.dtos.GradeDto;
import bj.formation.demoprojet.entities.Grade;
import bj.formation.demoprojet.requests.GradeRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class GradeMapper {
    public Grade toGrade(GradeDto gradeDto){
        Grade grade = new Grade();
        BeanUtils.copyProperties(gradeDto, grade);
        return grade;
    }


}
