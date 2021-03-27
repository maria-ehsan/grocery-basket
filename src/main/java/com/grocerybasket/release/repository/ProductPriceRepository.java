package com.grocerybasket.release.repository;

import com.grocerybasket.release.entity.MasterInformation;
import com.grocerybasket.release.entity.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Integer>, JpaSpecificationExecutor<ProductPrice> {

    ProductPrice findByMasterInformation(String productCode);
}
