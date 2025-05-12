package bj.formation.demoprojet.annotations;


import bj.formation.demoprojet.validators.ElementPaieWithCodeNotFoundValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Constraint(validatedBy = ElementPaieWithCodeNotFoundValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElementPaieWithCodeNotFound {
    String message() default "Element paie introuvable avec le code renseigné";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
