package com.cnh.validator;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * 自定义的校验器，真正的校验器
 */

@Target({METHOD,FIELD,ANNOTATION_TYPE,CONSTRUCTOR,PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {IsMobileValidator.class})////此处指定了注解的实现类为IsMobileValidator
public @interface IsMobile {

    //这个作为校验的条件，下面三条都是必须的，这个required() 只是一个参数
boolean required() default true;

    String message() default "输入的手机号码格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
