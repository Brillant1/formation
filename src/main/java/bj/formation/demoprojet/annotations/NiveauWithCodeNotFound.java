package bj.formation.demoprojet.annotations;


import bj.formation.demoprojet.validators.NiveauWithCodeNotFoundValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NiveauWithCodeNotFoundValidator.class)
public @interface NiveauWithCodeNotFound {
    String message() default "Le niveau n'existe pas";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
