package com.lin.test.validate;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;


public class NullValidate extends ValidatorHandler<String> implements Validator<String> {

    private String fieldName;

    public NullValidate(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public boolean validate(ValidatorContext context, String s) {
        if (null == s) {
            context.addError(ValidationError.create("不能为空").
                    setErrorCode(-1).setField(fieldName).setInvalidValue(s));
            return false;
        }
        return true;
    }
}
