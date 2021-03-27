package com.grocerybasket.release.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "pending_product_close_match")
public class PendingProductsCloseMatch extends BaseEntity {

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "store_id")
    private String storeId;

    @Column(name = "retail_price")
    private Double retailPrice;

    @Column(name = "sale_price")
    private Double salePrice;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @Column(name = "done")
    private Boolean done;
}
