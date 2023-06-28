package ar.edu.unq.grupok.backenddesappapi.audit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
public class EndpointLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(EndpointLoggingAspect.class);

    @Pointcut("execution(* ar.edu.unq.grupok.backenddesappapi.webservice.*.*(..))")
    public void controllerMethods() {
    }

    @AfterReturning(value = "controllerMethods()", returning = "returnValue")
    public void logEndpointInvocation(JoinPoint joinPoint, Object returnValue) {
        String endpoint = joinPoint.getSignature().toShortString();

        Object[] args = joinPoint.getArgs();
        String parameters = Arrays.stream(args)
                .map(Object::toString)
                .collect(Collectors.joining(", "));

        logger.info("-Endpoint Audit-");
        logger.info("Timestamp: {}" , LocalDateTime.now());
        logger.info("Endpoint invocado: {}", endpoint);
        logger.info("Parámetros: {}", parameters);

    }
}
