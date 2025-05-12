package bj.formation.demoprojet.validators;

import bj.formation.demoprojet.annotations.UniqueGrade;
import bj.formation.demoprojet.repositories.GradeRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;



public class UniqueGradeValidator implements ConstraintValidator<UniqueGrade, String>{
    private final GradeRepository gradeRepository;

    public UniqueGradeValidator(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public boolean isValid(String code, ConstraintValidatorContext context){
        if(code == null || code.isEmpty()){
            return true;
        }

        boolean gradeExist =  !gradeRepository.existsById(code);
        if (!gradeExist) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Le grade avec le code " + code + " existe deja")
                    .addConstraintViolation();

        }
        return gradeExist;
    }
}
