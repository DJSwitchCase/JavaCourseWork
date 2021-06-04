package ru.mirea.coursework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@EntityScan("ru.mirea.coursework.model")
//@ComponentScan("ru.mirea.coursework.model.service.repository")
//@EnableJpaRepositories("ru.mirea.coursework.model.service.repository")
@SpringBootApplication //(exclude = {DataSourceAutoConfiguration.class })
public class JavaCourseWorkApp {

    public static void main(String[] args) {
        SpringApplication.run(JavaCourseWorkApp.class, args); }
}

