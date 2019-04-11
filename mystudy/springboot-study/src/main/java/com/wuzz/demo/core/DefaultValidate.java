package com.wuzz.demo.core;

import com.wuzz.demo.exception.ValidateException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class DefaultValidate<T> implements Validate{
		
		protected static Validator validator;
		
		static{
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	        validator = factory.getValidator();
		}
		
		public void validate() throws ValidateException {
			Set<ConstraintViolation<T>> constraintViolations = validator.validate( (T)this);
			Iterator iterator = constraintViolations.iterator() ;
			
			if(constraintViolations.size() > 0) {
				List<String> errs = new ArrayList<String>() ;
				while(iterator.hasNext()) {
					ConstraintViolation<T> msg = (ConstraintViolation<T>) iterator.next() ;
					errs.add(msg.getMessage()) ;
				}
				throw new ValidateException(errs) ;
			}

	}
}
