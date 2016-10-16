package com.epam.jmp.aop;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.epam.jmp.model.MetaData;
import com.epam.jmp.model.MetaDataSupportedAbstractEntity;

@Aspect
// @Configurable
@Component
public class JMPAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(JMPAspect.class);
	
	@Pointcut("within(com.epam.jmp.dao..*)")
	public void inDAOLayer() {
	}
	
	@Pointcut("execution(* com.epam.jmp.controller.*.create*(..))")
	public void inControllerCreate() {
	}
	
	@Pointcut("execution(* com.epam.jmp.controller.*.update*(..))")
	public void inControllerUpdate() {
	}
	
	@Before(value = "inControllerCreate() && args(request,entity,..)")
	public void populateCreateInfo(HttpServletRequest request, MetaDataSupportedAbstractEntity entity) {
		MetaData metadata = entity.getMetaData();
		metadata.setCreationDate(new Date());
		metadata.setModificationDate(metadata.getCreationDate());
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		metadata.setCreationInfo(ipAddress);
		metadata.setModificationInfo(ipAddress);
		entity.setMetaData(metadata);
		
	}
	
	@Before(value = "inControllerUpdate() && args(request,entity,..)")
	public void populatemodifyInfo(HttpServletRequest request, MetaDataSupportedAbstractEntity entity) {
		MetaData metadata = entity.getMetaData();
		metadata.setModificationDate(new Date());
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		metadata.setModificationInfo(ipAddress);
		entity.setMetaData(metadata);
	}
	
	@After("inDAOLayer()")
	public void logDAOCalls(JoinPoint joinPoint) {
		logger.info("Executed method signature: " + joinPoint.getSignature().toString());
		logger.info("Target type of " + joinPoint.getTarget());
		logger.info("Arguments :" + Arrays.toString(joinPoint.getArgs()));
	}
	
	@Around("execution(* com.epam.jmp.dao.*.get*(..))")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		Object retVal = pjp.proceed();
		logger.info("Return value:  " + retVal.toString());
		return retVal;
	}
}
