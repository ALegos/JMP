package com.epam.jmp.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
// @Configurable
@Component
public class JMPAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(JMPAspect.class);
	
	@Pointcut("within(com.epam.jmp.dao..*)")
	public void inDAOLayer() {
	}
	
	// @Before("com.epam.jmp.controller.PersonViewController.createPerson() &&
	// args(request,..)")
	// public void logEntityCreate(HttpServletRequest request, JoinPoint
	// joinPoint) {
	// AbstractEntity person = (AbstractEntity) joinPoint.getArgs()[0];
	// person.setDateCreated(new Date());
	// String ipAddress = request.getHeader("X-FORWARDED-FOR");
	// if (ipAddress == null) {
	// ipAddress = request.getRemoteAddr();
	// }
	// person.setCreatedByUser(ipAddress);
	//
	// }
	//
	// @Before("com.epam.jmp.controller.PersonViewController.updatePerson() &&
	// args(request,..)")
	//
	// public void logEntityUpdate(HttpServletRequest request, JoinPoint
	// joinPoint) {
	// AbstractEntity person = (AbstractEntity) joinPoint.getArgs()[0];
	// person.setDateCreated(new Date());
	// String ipAddress = request.getHeader("X-FORWARDED-FOR");
	// if (ipAddress == null) {
	// ipAddress = request.getRemoteAddr();
	// }
	// person.setCreatedByUser(ipAddress);
	// }
	
	@After("inDAOLayer()")
	public void logDAOCalls(JoinPoint joinPoint) {
		logger.info("Executed method signature: " + joinPoint.getSignature().toString());
		logger.info("Target type of " + joinPoint.getTarget());
		logger.info("Arguments :" + Arrays.toString(joinPoint.getArgs()));
	}
	
	@Around("execution(* com.epam.jmp.dao.*.get*(..))")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		Object retVal = pjp.proceed();
		logger.info(retVal.toString());
		return retVal;
	}
}
