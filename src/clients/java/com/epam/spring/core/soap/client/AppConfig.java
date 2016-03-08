package com.epam.spring.core.soap.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class AppConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.epam.spring.core.soap.client.gen");

        return marshaller;
    }

    @Bean
    public WebServiceClient webServiceClient(Jaxb2Marshaller marshaller) {
        WebServiceClient webServiceClient = new WebServiceClient();
        webServiceClient.setDefaultUri("http://localhost:8080/api/soap/userDefinition.wsdl");
        webServiceClient.setMarshaller(marshaller);
        webServiceClient.setUnmarshaller(marshaller);

        return webServiceClient;
    }
}
