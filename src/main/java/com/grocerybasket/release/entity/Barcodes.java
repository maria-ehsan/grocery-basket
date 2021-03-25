package com.grocerybasket.release.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "barcodes")
public class Barcodes extends BaseEntity{

    @JsonBackReference
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "product_code")
    private MasterInformation masterInformation;

    @Column(name = "store_id")
    private String storeId;

    @Column(name = "barcode")
    private String barcode;
}
