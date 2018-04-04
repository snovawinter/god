package ru.shmntk.god.boot.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import ru.shmntk.god.boot.util.annotations.Monitoring;

@Aspect
@Log4j2
public class VerifiableAspect {

    @Around("@annotation(ru.shmntk.god.boot.util.annotations.Monitoring)")
    public Object test(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
	MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
	Monitoring monitoring = signature.getMethod().getAnnotation(Monitoring.class);
	// TODO Если это возможно, получить пользователя с помощью аннотации AuthenticationPrincipal, либо создать свою аннотацию
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	User principal = (User) authentication.getPrincipal();
	log.info("Вызван метод '" + AnnotatedElementUtils
			.getMergedAnnotation(AnnotatedElementUtils.forAnnotations(monitoring), Monitoring.class).methodDescription()
			+ "' пользователем " + principal.getUsername());
	return proceedingJoinPoint.proceed();
    }
}
