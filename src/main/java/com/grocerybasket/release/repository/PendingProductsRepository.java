package com.grocerybasket.release.repository;

import com.grocerybasket.release.entity.PendingProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PendingProductsRepository extends JpaRepository<PendingProducts, Integer>, JpaSpecificationExecutor<PendingProducts> {
}
