package bj.formation.demoprojet.validators;


import bj.formation.demoprojet.annotations.NiveauWithCodeNotFound;
import bj.formation.demoprojet.repositories.NiveauEnfantRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NiveauWithCodeNotFoundValidator implements ConstraintValidator<NiveauWithCodeNotFound, String> {
    private  final NiveauEnfantRepository niveauEnfantRepository;
    @Override
    public boolean isValid(String code, ConstraintValidatorContext constraintValidatorContext) {

        if(code == null || code.isEmpty()){
            return true;
        }
        boolean niveauExist =  niveauEnfantRepository.existsById(code);
        if (!niveauExist) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Le niveau avec le code " + code + " n'existe pas")
                    .addConstraintViolation();
        }
        return niveauExist;
    }
}
