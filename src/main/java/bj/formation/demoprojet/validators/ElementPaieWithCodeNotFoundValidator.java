package bj.formation.demoprojet.validators;


import bj.formation.demoprojet.annotations.ElementPaieWithCodeNotFound;
import bj.formation.demoprojet.repositories.ElementPaieRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ElementPaieWithCodeNotFoundValidator implements ConstraintValidator<ElementPaieWithCodeNotFound, String> {
    private  final ElementPaieRepository elementPaieRepository;


    @Override
    public boolean isValid(String code, ConstraintValidatorContext constraintValidatorContext) {

        if(code == null || code.isEmpty()){
            return true;
        }
        boolean elementPaieExist =  elementPaieRepository.existsById(code);
        if (!elementPaieExist) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("L'element paie avec le code " + code + " n'existe pas")
                    .addConstraintViolation();
        }
        return elementPaieExist;
    }
}
