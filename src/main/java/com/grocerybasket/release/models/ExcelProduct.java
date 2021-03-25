package com.grocerybasket.release.models;

import lombok.Getter;
import lombok.Setter;

import com.grocerybasket.release.entity.Barcodes;
import com.grocerybasket.release.entity.MasterInformation;
import com.grocerybasket.release.entity.ProductPrice;

@Getter
@Setter
public class ExcelProduct {

    private String barcode;
    private String name;
    private String code;
    private String imageName;
    private String vendor;
    private Double salePrice;
    private Double retailPrice;
    private String status;
    private Integer stockQuantity;

    public MasterInformation toMasterInformation() {
        MasterInformation masterInformation = new MasterInformation();
        masterInformation.setProductCode(getCode());
        masterInformation.setProductName(getName());
        masterInformation.setImage(getImageName());

        return  masterInformation;
    }

    public Barcodes toBarcodes(MasterInformation masterInformation) {
        Barcodes barcodes = new Barcodes();
        barcodes.setBarcode(getBarcode());
        barcodes.setStoreId(getVendor());
        barcodes.setMasterInformation(masterInformation);

        return barcodes;
    }

    public ProductPrice toProductPrice(MasterInformation masterInformation) {
        ProductPrice productPrice = new ProductPrice();
        productPrice.setRetailPrice(getRetailPrice());
        productPrice.setSalePrice(getRetailPrice());
        productPrice.setStockQuantity(getStockQuantity());
        productPrice.setStoreId(getVendor());
        productPrice.setMasterInformation(masterInformation);

        return  productPrice;
    }
}
