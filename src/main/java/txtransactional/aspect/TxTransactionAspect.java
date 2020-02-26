package txtransactional.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import txtransactional.annotation.TxTransaction;

import java.lang.reflect.Method;

@Aspect
@Component
public class TxTransactionAspect implements Ordered {
    @Around("@annotation(txtransactional.annotation.TxTransaction)")
    public void invoke(ProceedingJoinPoint point){
        MethodSignature signature= (MethodSignature) point.getSignature();
        Method method=signature.getMethod();
        TxTransaction txTransaction=method.getAnnotation(TxTransaction.class);
        System.out.println(txTransaction.isStart());
        try {
            //进入spring 方法的业务逻辑
            point.proceed(); asd
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public int getOrder() {
        //比spring的优先级低,所以先执行 不用纠结就是先执行我自定义的注解,然后在走spring的注解
        return 100000;
    }
}
