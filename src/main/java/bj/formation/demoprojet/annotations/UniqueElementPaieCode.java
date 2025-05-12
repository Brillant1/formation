package bj.formation.demoprojet.annotations;

import bj.formation.demoprojet.validators.UniqueElementPaieCodeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueElementPaieCodeValidator.class)
public @interface UniqueElementPaieCode {
    String message() default "Un élément avec ce code existe déjà en base";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
