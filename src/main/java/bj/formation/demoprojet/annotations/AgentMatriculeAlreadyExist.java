package bj.formation.demoprojet.annotations;

import bj.formation.demoprojet.validators.AgentMatriculeAlreadyExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = AgentMatriculeAlreadyExistValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AgentMatriculeAlreadyExist {
    String message() default "Le matricule existe déjà en base";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
