package bj.formation.demoprojet.validators;

import bj.formation.demoprojet.annotations.AgentWithMatriculeNotFound;
import bj.formation.demoprojet.repositories.AgentRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AgentWithMatriculeNotFoundValidator implements ConstraintValidator<AgentWithMatriculeNotFound, String> {
    private  final AgentRepository agentRepository;

    @Override
    public boolean isValid(String matricule, ConstraintValidatorContext constraintValidatorContext) {

        if(matricule == null || matricule.isEmpty()){
            return true;
        }
        boolean matriculeExist =  agentRepository.existsById(matricule);
        if (!matriculeExist) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("L'agent avec le matricule " + matricule + " n'existe pas")
                    .addConstraintViolation();
        }
        return matriculeExist;

    }
}
