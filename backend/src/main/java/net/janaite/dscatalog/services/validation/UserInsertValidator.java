package net.janaite.dscatalog.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import net.janaite.dscatalog.dto.UserInsertDTO;
import net.janaite.dscatalog.entities.User;
import net.janaite.dscatalog.repositories.UserRepository;
import net.janaite.dscatalog.resources.exceptions.FieldMessage;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public void initialize(UserInsertValid ann) {
		ConstraintValidator.super.initialize(ann);
	}

	@Override
	public boolean isValid(UserInsertDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		// validation tests (begin)
		
		User user = repository.findByEmail(value.getEmail());
		if(user != null) {
			list.add(new FieldMessage("email", "email already exists"));
		}
		
		// validation tests (end)
		
		list.forEach(fm -> {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fm.getMessage()).addPropertyNode(fm.getFieldName())
				.addConstraintViolation();
		});
		
		return list.isEmpty();
	}

}
