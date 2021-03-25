package com.grocerybasket.release.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.grocerybasket.release.entity.ProductPrice;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Integer>, JpaSpecificationExecutor<ProductPrice> {
}
