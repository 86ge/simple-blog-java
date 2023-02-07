package github.xiny.simpleblog.config;

import github.xiny.simpleblog.domain.AjaxJson;
import github.xiny.simpleblog.service.APILogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class GlobalAspect {
    /**
     * 定义AOP签名 --> 项目代码(所有class名成带有Controller字符的)
     */
    @Pointcut("execution(* github.xiny.simpleblog..*Controller*.*(..))")
    public void webLogProject(){}


    /**
     * 环绕通知,环绕增强，相当于MethodInterceptor
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("webLogProject()")
    public Object surround(ProceedingJoinPoint pjp) throws Throwable {
        // 1、开始时  移入
        APILogService.startRequest();
        try {
            // 2、执行时
            Object obj =  pjp.proceed();
            // 如果是 AjaxJson
            if(obj instanceof AjaxJson){
                APILogService.endRequest((AjaxJson)obj);
            }
            // 如果是 String
            else if (obj instanceof String) {
                APILogService.endRequest(AjaxJson.getSuccess(String.valueOf(obj)));
            }
            // 如果都不是
            else {
                APILogService.endRequest(AjaxJson.getSuccessData(obj));
            }
            return obj;
        } catch (Throwable e) {
            throw e;
        }
    }


}
