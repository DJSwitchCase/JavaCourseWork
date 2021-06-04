package ru.mirea.coursework;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@EnableAspectJAutoProxy
@Aspect
@Component
@Slf4j
public class LogAspect {
@Pointcut("execution(* ru.mirea.coursework.model.service..*(..))")
public void tellTime(){
}
    @Around("tellTime()")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();

        Object returnValue = joinPoint.proceed();
        StringBuilder message = new StringBuilder("Method: ");
        message.append(joinPoint.getSignature().getName()).append(".");

        long spendTime = System.currentTimeMillis() - startTime;
        message.append(" Time: ").append(spendTime).append(" milliseconds.");

        if(returnValue != null)
            message.append(" Return: ").append(returnValue.toString());

        System.out.println(message.toString());
        return returnValue;
    }
}
