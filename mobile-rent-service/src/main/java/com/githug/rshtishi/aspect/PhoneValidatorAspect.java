package com.githug.rshtishi.aspect;

import com.githug.rshtishi.exception.PhoneNotFoundException;
import com.githug.rshtishi.repository.PhoneRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PhoneValidatorAspect {

    @Autowired
    private PhoneRepository phoneRepository;

    @Before("@annotation(PhoneNullCheck)")
    public void checkForNull(JoinPoint joinPoint) throws Throwable {
            long id = (Long) joinPoint.getArgs()[0];
            if(phoneRepository.findById(id).isEmpty()){
                throw new PhoneNotFoundException("Phone not found");
            }
    }
}
