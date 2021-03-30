package com.grocerybasket.release.repository;

import com.grocerybasket.release.entity.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Integer>, JpaSpecificationExecutor<ProductPrice> {

    ProductPrice findByMasterInformation_ProductCodeAndStoreId(String productCode, String storeId);
}
