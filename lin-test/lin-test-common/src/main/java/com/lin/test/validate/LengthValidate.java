package com.lin.test.validate;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;

public class LengthValidate extends ValidatorHandler<String> implements Validator<String> {

    private int max;

    private int min;

    private String fieldName;

    public LengthValidate(int max, int min, String fieldName) {
        this.max = max;
        this.min = min;
        this.fieldName = fieldName;
    }

    @Override
    public boolean validate(ValidatorContext context, String s) {
        if (null == s && s.length() > max && s.length() < min) {
            context.addError(ValidationError.create("长度限制").setErrorCode(-1).
                    setField(fieldName).setInvalidValue(s));
            return false;
        }
        return true;
    }
}
