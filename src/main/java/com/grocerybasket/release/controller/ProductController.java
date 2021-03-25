package com.grocerybasket.release.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocerybasket.release.Message.Message;
import com.grocerybasket.release.service.ProductService;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/upload")
    public Message upload() throws FileNotFoundException {

//        // Get file name
//        String uploadedFileName = productFile.getOriginalFilename();
//
//        if (StringUtils.isEmpty(uploadedFileName)) {
//            return new Message(uploadedFileName, "please select a file!", "fail");
//        }
//
//        if (!ExcelUtils.isExcelFile(productFile)) {
//            return new Message(uploadedFileName, "Not Excel Files", "fail");
//        }

        productService.upload();

        return new Message("", "Not Excel Files", "fail");
    }
}
