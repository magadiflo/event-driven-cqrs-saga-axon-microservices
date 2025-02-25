package dev.magadiflo.app;

import dev.magadiflo.app.command.interceptors.CreateProductCommandInterceptor;
import dev.magadiflo.app.core.errorhandling.ProductServiceEventErrorHandler;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Autowired
    public void registerCreateProductCommandInterceptor(ApplicationContext context, CommandBus commandBus) {
        CreateProductCommandInterceptor bean = context.getBean(CreateProductCommandInterceptor.class);
        commandBus.registerDispatchInterceptor(bean);
    }

    @Autowired
    public void configure(EventProcessingConfigurer config) {
        config.registerListenerInvocationErrorHandler("product-group", configuration -> new ProductServiceEventErrorHandler());
    }

}
