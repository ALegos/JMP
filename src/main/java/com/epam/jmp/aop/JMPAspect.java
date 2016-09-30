package com.epam.jmp.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class JMPAspect {

	// @Pointcut("@annotation(loggableService)")
	// public void loggableServicePointCutDef() {
	// }
	//
	// @Pointcut("execution(public com.epam.jmp.dao*.create(..))")
	// public void daoCreate() {
	// }
	//
	// @Pointcut("execution(public com.epam.jmp.dao*.delete(..))")
	// public void daoDelete() {
	// }
	//
	// @Pointcut("execution(public com.epam.jmp.dao*.update(..))")
	// public void daoUpdate() {
	// }

	@Pointcut("execution(public com.epam.jmp.dao.*.*(..))")
	public void allDAOCalls() {
	}

	@Before("allDAOCalls()")
	public void logAllDAOCalls() {
		System.out.println("test of aop");
	}

}
