package com.grocerybasket.release.controller;

import com.grocerybasket.release.Message.Message;
import com.grocerybasket.release.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Message getProduct(){
        return new Message(null,"ok","ok");
    }

    @PostMapping(value = "/upload")
    public Message upload(@RequestParam(value = "file") MultipartFile file) throws FileNotFoundException {

        productService.upload(file);

        return new Message("", "Not Excel Files", "fail");
    }
}
