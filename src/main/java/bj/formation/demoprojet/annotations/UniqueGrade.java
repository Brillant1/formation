package bj.formation.demoprojet.annotations;


import bj.formation.demoprojet.validators.AgentMatriculeAlreadyExistValidator;
import bj.formation.demoprojet.validators.GradeCodeNotFoundValidator;
import bj.formation.demoprojet.validators.UniqueGradeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueGradeValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)

public @interface UniqueGrade {
    String message() default "Le grade existe déjà en base";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
