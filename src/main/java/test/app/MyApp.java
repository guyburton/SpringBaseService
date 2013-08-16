package test.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import test.service.MyService;

/**
 * @author guyburton
 *         Date: 15/08/2013
 */
public class MyApp {
    public static void main(String[] args) {
        ApplicationContext context = new GenericXmlApplicationContext("spring-config.xml");

        MyService myService = context.getBean(MyService.class);
        Long newEntityId = myService.create();
        myService.update(newEntityId);
    }
}
