package bj.formation.demoprojet.validators;


import bj.formation.demoprojet.annotations.GradeCodeNotFound;
import bj.formation.demoprojet.repositories.GradeRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GradeCodeNotFoundValidator implements ConstraintValidator<GradeCodeNotFound, String> {
    private  final GradeRepository gradeRepository;
    @Override
    public boolean isValid(String code, ConstraintValidatorContext constraintValidatorContext) {

        if(code == null || code.isEmpty()){
            return true;
        }
        boolean gradeExist =  gradeRepository.existsById(code);
        if (!gradeExist) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Le grade avec le code " + code + " n'existe pas")
                    .addConstraintViolation();
        }
        return gradeExist;
    }
}
