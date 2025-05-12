package bj.formation.demoprojet.annotations;
import bj.formation.demoprojet.validators.AgentWithMatriculeNotFoundValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgentWithMatriculeNotFoundValidator.class)
public @interface AgentWithMatriculeNotFound {
    String message() default "L'agent avec le matricule n'existe pas";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
