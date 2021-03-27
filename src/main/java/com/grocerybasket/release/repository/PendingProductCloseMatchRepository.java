package com.grocerybasket.release.repository;


import com.grocerybasket.release.entity.PendingProductsCloseMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PendingProductCloseMatchRepository extends JpaRepository<PendingProductsCloseMatch, Integer>, JpaSpecificationExecutor<PendingProductsCloseMatch> {
}
