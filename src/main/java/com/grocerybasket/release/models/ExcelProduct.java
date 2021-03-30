package com.grocerybasket.release.models;

import com.grocerybasket.release.entity.Barcodes;
import com.grocerybasket.release.entity.MasterInformation;
import com.grocerybasket.release.entity.PendingProducts;
import com.grocerybasket.release.entity.PendingProductsCloseMatch;
import com.grocerybasket.release.entity.ProductPrice;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.UUID;

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
    private String brand;

    public void setCode(String code) {
        if (StringUtils.isEmpty(this.code)) {
            this.code = UUID.randomUUID().toString();
        } else {
            this.code = code;
        }
    }

    public void setBrand(String brand) {
        if (StringUtils.isEmpty(this.brand)) {
            this.brand = UUID.randomUUID().toString();
        } else {
            this.brand = brand;
        }
    }

    public MasterInformation toMasterInformation() {
        MasterInformation masterInformation = new MasterInformation();
        masterInformation.setProductCode(this.code);
        masterInformation.setProductName(this.name);
        masterInformation.setImage(this.imageName);
        masterInformation.setProductCode(UUID.randomUUID().toString());
        masterInformation.setEnabled(false);

        return masterInformation;
    }

    public Barcodes toBarcodes(MasterInformation masterInformation) {
        Barcodes barcodes = new Barcodes();
        barcodes.setBarcode(this.barcode);
        barcodes.setStoreId(this.vendor);
        barcodes.setMasterInformation(masterInformation);

        return barcodes;
    }

    public ProductPrice toProductPrice(MasterInformation masterInformation) {
        ProductPrice productPrice = new ProductPrice();
        productPrice.setRetailPrice(this.retailPrice);
        productPrice.setSalePrice(this.salePrice);
        productPrice.setStockQuantity(this.stockQuantity);
        productPrice.setStoreId(this.vendor);
        productPrice.setMasterInformation(masterInformation);
        productPrice.setEnabled(false);

        return productPrice;
    }

    public PendingProductsCloseMatch toPendingProductsCloseMatch() {
        PendingProductsCloseMatch pendingProductsCloseMatch = new PendingProductsCloseMatch();
        pendingProductsCloseMatch.setRetailPrice(this.retailPrice);
        pendingProductsCloseMatch.setSalePrice(this.salePrice);
        pendingProductsCloseMatch.setStockQuantity(this.stockQuantity);
        pendingProductsCloseMatch.setStoreId(this.vendor);

        return pendingProductsCloseMatch;
    }

    public PendingProducts toPendingProducts() {
        PendingProducts pendingProducts = new PendingProducts();
        pendingProducts.setRetailPrice(this.retailPrice);
        pendingProducts.setSalePrice(this.salePrice);
        pendingProducts.setStockQuantity(this.stockQuantity);
        pendingProducts.setStoreId(this.vendor);

        return pendingProducts;
    }
}
