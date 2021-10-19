import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class TimingAspectConfig {

    private static final Logger LOGGER = Logger.getLogger(TimingAspectConfig.class);

    @Pointcut("execution(* *(..)) && @annotation(TimeIt)")
    public void methodsMarkedTimeIt() {
    }

    @Pointcut("execution(* @TimeIt *.*(..))")
    public void classesMarkedTimeIt() {
    }

    @Around("methodsMarkedTimeIt() || classesMarkedTimeIt()")
    public Object timeIt(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.nanoTime();
        Object returnedObject = proceedingJoinPoint.proceed();
        long endTime = System.nanoTime();
        LOGGER.info(proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName() + "." + proceedingJoinPoint.getSignature().getName() +
                " took " + ((endTime - startTime) / 1000000) + " ms");
        return returnedObject;
    }

}
