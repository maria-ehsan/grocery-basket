package com.grocerybasket.release.models;

import com.grocerybasket.release.entity.MasterInformation;
import com.grocerybasket.release.entity.ProductPrice;
import jdk.jfr.Category;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductCatalog {
    private String productCode;
    private String name;
    private String imageName;
    private Category category;
    private Double salePrice;
    private Double retailPrice;
    private String status;
    private Integer stockQuantity;
    private String brand;

    public static ProductCatalog from(ProductPrice productPrice){

        MasterInformation masterInformation = new MasterInformation();

        return ProductCatalog.builder()
                .productCode(productPrice.masterInformation.getProductCode())
                .name(productPrice.masterInformation.getProductName())
                .retailPrice(productPrice.getRetailPrice())
                .salePrice(productPrice.getSalePrice())
                .status(productPrice.masterInformation.getEnabled().toString())
                .build();
    }
}
