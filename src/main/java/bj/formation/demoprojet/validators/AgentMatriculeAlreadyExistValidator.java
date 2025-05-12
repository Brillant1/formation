package bj.formation.demoprojet.validators;

import bj.formation.demoprojet.annotations.AgentMatriculeAlreadyExist;
import bj.formation.demoprojet.repositories.AgentRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class AgentMatriculeAlreadyExistValidator implements ConstraintValidator<AgentMatriculeAlreadyExist, String> {
    private final AgentRepository agentRepository;

    @Override
    public boolean isValid(String matricule, ConstraintValidatorContext constraintValidatorContext) {
        if(matricule == null || matricule.isEmpty()){
            return true;
        }



        boolean agentExist = !agentRepository.existsById(matricule);

        if (!agentExist) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("L'agent avec le matricule " + matricule + "existe déjà")
                    .addConstraintViolation();
        }
        return agentExist;
    }
}
