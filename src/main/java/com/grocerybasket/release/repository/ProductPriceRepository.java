package com.grocerybasket.release.repository;

import com.grocerybasket.release.entity.MasterInformation;
import com.grocerybasket.release.entity.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Integer>, JpaSpecificationExecutor<ProductPrice> {

    ProductPrice findByMasterInformation_ProductCodeAndStoreId(String productCode, String storeId);

    List<ProductPrice> findByMasterInformationProductNameContaining(String productName);


}
