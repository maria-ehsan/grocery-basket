package com.grocerybasket.release.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "master_information")
public class MasterInformation extends BaseEntity{

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_code")
    private String productCode;

    @JsonBackReference
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private MasterInformation parent;

    @Column(name = "brand")
    private String brand;

    @Column(name = "image")
    private String image;

    @Column(name = "enabled")
    private Boolean enabled;

    @JsonManagedReference
    @OneToMany(targetEntity = MasterInformation.class, mappedBy = "parent", fetch = FetchType.LAZY)
    private Set<MasterInformation> childProducts;
}
