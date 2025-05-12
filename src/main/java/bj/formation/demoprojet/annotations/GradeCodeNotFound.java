package bj.formation.demoprojet.annotations;


import bj.formation.demoprojet.validators.GradeCodeNotFoundValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GradeCodeNotFoundValidator.class)
public @interface GradeCodeNotFound {
    String message() default "Le grade n'existe pas";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
