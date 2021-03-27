package com.grocerybasket.release.models;

import com.grocerybasket.release.entity.Barcodes;
import com.grocerybasket.release.entity.MasterInformation;
import com.grocerybasket.release.entity.PendingProducts;
import com.grocerybasket.release.entity.PendingProductsCloseMatch;
import com.grocerybasket.release.entity.ProductPrice;
import lombok.Getter;
import lombok.Setter;

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

        return masterInformation;
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
        productPrice.setSalePrice(getSalePrice());
        productPrice.setStockQuantity(getStockQuantity());
        productPrice.setStoreId(getVendor());
        productPrice.setMasterInformation(masterInformation);

        return productPrice;
    }

    public PendingProductsCloseMatch toPendingProductsCloseMatch() {
        PendingProductsCloseMatch pendingProductsCloseMatch = new PendingProductsCloseMatch();
        pendingProductsCloseMatch.setRetailPrice(getRetailPrice());
        pendingProductsCloseMatch.setSalePrice(getSalePrice());
        pendingProductsCloseMatch.setStockQuantity(getStockQuantity());
        pendingProductsCloseMatch.setStoreId(getVendor());
        return pendingProductsCloseMatch;
    }

    public PendingProducts toPendingProducts() {
        PendingProducts pendingProducts = new PendingProducts();
        pendingProducts.setRetailPrice(getRetailPrice());
        pendingProducts.setSalePrice(getSalePrice());
        pendingProducts.setStockQuantity(getStockQuantity());
        pendingProducts.setStoreId(getVendor());
        return pendingProducts;
    }
}
