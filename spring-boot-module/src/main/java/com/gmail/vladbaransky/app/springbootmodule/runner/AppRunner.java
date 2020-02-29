package com.gmail.vladbaransky.app.springbootmodule.runner;

import com.gmail.vladbaransky.app.springbootmodule.Homework;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.gmail.vladbaransky.app.repository",
        "com.gmail.vladbaransky.app.service",
        "com.gmail.vladbaransky.app.springbootmodule"})
public class AppRunner implements ApplicationRunner {

    private Homework homework;

    public AppRunner(Homework homework) {
        this.homework = homework;
    }

    @Override
    public void run(ApplicationArguments args) {
        homework.getHomework();
    }
}
