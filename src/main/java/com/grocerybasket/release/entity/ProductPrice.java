package com.grocerybasket.release.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "product_price")
public class ProductPrice extends BaseEntity {

//    @OneToOne(cascade= CascadeType.ALL)
//    @JoinColumn(name= "MASTER_INFORMATION_PRODUCT_CODE")
//    private MasterInformation masterInformation;

    @OneToOne
    @JoinColumn(name = "product_code", nullable = false, referencedColumnName = "product_code")
    public MasterInformation masterInformation;


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
