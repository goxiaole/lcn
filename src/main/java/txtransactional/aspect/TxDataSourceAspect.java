package txtransactional.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import txtransactional.connection.TxConnection;

import java.sql.Connection;

@Aspect
@Component
public class TxDataSourceAspect {

    @Around("execution(* javax.sql.DataSource.getConnection(..))")
    public Connection around(ProceedingJoinPoint point){
        try {
            Connection connection= (Connection) point.proceed();
            return new TxConnection(connection);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
