package com.grocerybasket.release.repository;


import com.grocerybasket.release.entity.Barcodes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BarcodeRepository extends JpaRepository<Barcodes, Integer>, JpaSpecificationExecutor<Barcodes> {

    List<Barcodes> findByBarcodeAndAndStoreIdNot(String barcode, String storeId);
    Barcodes findByBarcodeAndAndStoreId(String barcode, String storeId);
    Barcodes findByBarcode(String barcode);
}
