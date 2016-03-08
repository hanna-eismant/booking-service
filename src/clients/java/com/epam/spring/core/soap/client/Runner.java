package com.epam.spring.core.soap.client;

import com.epam.spring.core.soap.client.gen.GetUserInfoResponse;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        WebServiceClient webServiceClient = context.getBean(WebServiceClient.class);
        GetUserInfoResponse admin = webServiceClient.getUserByName("admin");

        System.out.println(admin.getUser().getId());
        System.out.println(admin.getUser().getName());
        System.out.println(admin.getUser().getEmail());
        System.out.println(admin.getUser().getPassword());
    }

}
