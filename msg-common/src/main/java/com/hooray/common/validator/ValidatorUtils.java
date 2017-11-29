package com.hooray.common.validator;

import com.hooray.common.exception.ServiceException;
import com.hooray.common.utils.StringUtils;
import com.hooray.common.utils.api.APIResultEnum;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * hibernate-validator校验工具类
 *
 * 参考文档：http://docs.jboss.org/hibernate/validator/5.4/reference/en-US/html_single/
 *
 */
public class ValidatorUtils {
    private static Validator validator;
    
    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
    
    /**
     * 校验对象
     * @param object        待校验对象
     * @param groups        待校验的组
     * @throws ServiceException  校验不通过，则报ServiceException异常
     */
    public static void validateEntity(Object object, Class<?>... groups) throws ServiceException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        List<String> errorList = new ArrayList<String>();
        if (!constraintViolations.isEmpty()) {
            constraintViolations.stream().forEach(objectConstraintViolation -> {
                ConstraintViolation<Object> constraint = (ConstraintViolation<Object>)objectConstraintViolation;
                errorList.add(constraint.getPropertyPath().toString()+":"+constraint.getMessage());
            });

			throw new ServiceException(APIResultEnum.VALID_FAIL.getCode(), StringUtils.fillStringByArgs(
					APIResultEnum.VALID_FAIL.getMsg(),
					new String[] { org.springframework.util.StringUtils.collectionToDelimitedString(errorList, ",") }));
        }
    }

}
