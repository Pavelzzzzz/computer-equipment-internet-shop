package by.sam_solutions.spring.shop.jdbc;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * Class for migrate database on start app.
 */


@Component
public class FlyWayComponent extends Flyway implements InitializingBean {

      public void afterPropertiesSet() {
        this.migrate();
    }
}
