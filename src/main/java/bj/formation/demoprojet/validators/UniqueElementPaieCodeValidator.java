package bj.formation.demoprojet.validators;

import bj.formation.demoprojet.annotations.UniqueElementPaieCode;
import bj.formation.demoprojet.repositories.ElementPaieRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueElementPaieCodeValidator implements ConstraintValidator<UniqueElementPaieCode, String> {
    private final ElementPaieRepository elementPaieRepository;
    @Override
    public boolean isValid(String code, ConstraintValidatorContext constraintValidatorContext) {
        if(code == null || code.isEmpty()){
            return true;
        }
        return  !elementPaieRepository.existsById(code);
    }
}
