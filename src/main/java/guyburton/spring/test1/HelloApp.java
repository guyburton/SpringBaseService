package guyburton.spring.test1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        HelloService helloService = context.getBean(HelloService.class);
        System.out.println(helloService.sayHello());
    }
}
