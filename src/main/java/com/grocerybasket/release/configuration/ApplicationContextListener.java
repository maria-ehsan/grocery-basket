package com.grocerybasket.release.configuration;

import com.grocerybasket.release.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ApplicationContextListener {

    private ProductService productService;

    @EventListener(ContextRefreshedEvent.class)
    public void initializeCacheVariables() {
        productService.initLocalDictionary();
    }
}
