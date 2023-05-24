package ar.edu.unq.grupok.backenddesappapi.model;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(0)
public class ExceptionHandlerAspectCustomPointcut {

	private Logger log = LoggerFactory.getLogger(ExceptionHandlerAspectCustomPointcut.class);
	
	@Pointcut("execution(* ar.edu.unq.grupok.backenddesappapi.webservice.*.*(..))")
	public void methodsStarterServicePointcut() {
	}
	
	@Around("methodsStarterServicePointcut()")
	public Object exceptionHandler(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("Exception Handler Aspect Pointcut - AROUND START");
		try {
			return joinPoint.proceed();
		} 
		catch (AppException e) {
			log.info("/////// ExceptionHandlerAspectCustomPointcut - AROUND POINTCUT - Exception caught: AppException ///////");
			return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
		}
		catch (P2PException e) {
			log.info("/////// ExceptionHandlerAspectCustomPointcut - AROUND POINTCUT - Exception caught: P2PException ///////");
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
