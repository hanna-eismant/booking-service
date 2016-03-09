package com.epam.spring.core.soap.client;

import com.epam.spring.core.soap.client.gen.GetUserInfoResponse;
import com.epam.spring.core.soap.client.gen.GetUsersResponse;
import com.epam.spring.core.soap.client.gen.RegisterUserResponse;
import org.joda.time.LocalDate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        WebServiceClient webServiceClient = context.getBean(WebServiceClient.class);

        // get all users
        System.out.println("*** all users");
        GetUsersResponse allUsers = webServiceClient.getAllUsers();
        allUsers.getUsersList().forEach(soapUser -> {
            System.out.println(soapUser.getId());
            System.out.println(soapUser.getName());
            System.out.println(soapUser.getEmail());
            System.out.println(soapUser.getPassword());
            System.out.println(soapUser.getAccount().getMoney());
            System.out.println();
        });

        // create new user
        RegisterUserResponse registerUser = webServiceClient.registerUser("test", "test@mail", "pass", LocalDate.parse("1987-02-02"));
        System.out.println("*** register user");
        System.out.println(registerUser.isStatus());
        System.out.println(registerUser.getMessage());
        System.out.println();

        // get single user's info
        GetUserInfoResponse testUser = webServiceClient.getUserByName("test");
        System.out.println("*** single user");
        System.out.println(testUser.getUser().getId());
        System.out.println(testUser.getUser().getName());
        System.out.println(testUser.getUser().getEmail());
        System.out.println(testUser.getUser().getPassword());
        System.out.println();
    }

}
