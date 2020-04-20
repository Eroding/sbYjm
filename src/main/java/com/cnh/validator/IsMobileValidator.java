package com.cnh.validator;

import com.cnh.util.ValidatorUtil;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;

/**
 * 因为定义了一个校验器，需要真正的校验方法
 */
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {


    /**
     * 初始化方法
     * @param constraintAnnotation
     */

private  boolean required = false;


    @Override
    public void initialize(IsMobile constraintAnnotation) {
        //在IsMobile中定义的一个参数，可以取出来
        required=  constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
/**
 * 先判断这个值是否必须要传
 */
        if(required){
            return ValidatorUtil.isMobile(s);
        }else {
            if(StringUtils.isEmpty(s)){
                return true;
            }else {
                return  ValidatorUtil.isMobile(s);
            }
        }
    }
}
