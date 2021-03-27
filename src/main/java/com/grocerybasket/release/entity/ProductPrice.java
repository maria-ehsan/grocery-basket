package com.grocerybasket.release.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "product_price")
public class ProductPrice extends BaseEntity{

    @JsonBackReference
    @OneToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name= "product_code")
    private MasterInformation masterInformation;

    @Column(name = "store_id")
    private String storeId;

    @Column(name = "retail_price")
    private Double retailPrice;

    @Column(name = "sale_price")
    private Double salePrice;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @Column(name = "enabled")
    private Boolean enabled;

    public Boolean getEnabled() {
        if (enabled == null) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled == null ? Boolean.FALSE : enabled;
    }
}
