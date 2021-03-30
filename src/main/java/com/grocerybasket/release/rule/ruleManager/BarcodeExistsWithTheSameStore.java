package com.grocerybasket.release.rule.ruleManager;

import com.grocerybasket.release.entity.Barcodes;
import com.grocerybasket.release.entity.MasterInformation;
import com.grocerybasket.release.models.ExcelProduct;
import com.grocerybasket.release.repository.BarcodeRepository;
import com.grocerybasket.release.repository.ProductPriceRepository;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@org.jeasy.rules.annotation.Rule(name = "barcode_exists_with_the_same_store", description = "", priority = Constants.RulePriority.BARCODE_EXISTS_WITH_SAME_STORE)
@Component
public class BarcodeExistsWithTheSameStore {

    private BarcodeRepository barcodeRepository;
    private ProductPriceRepository productPriceRepository;
    private Barcodes existingBarcode;

    @Autowired
    public BarcodeExistsWithTheSameStore(BarcodeRepository barcodeRepository, ProductPriceRepository productPriceRepository) {
        this.barcodeRepository = barcodeRepository;
        this.productPriceRepository = productPriceRepository;
    }

    @Condition
    public boolean doesBarcodeExistWithSameStore(@Fact("excelProduct") ExcelProduct excelProduct) {

        existingBarcode = barcodeRepository.findByBarcodeAndAndStoreId(excelProduct.getBarcode(), excelProduct.getVendor());
        return doesBarcodeExistWithSameStore(existingBarcode);
    }

    private boolean doesBarcodeExistWithSameStore(Barcodes existingBarcode) {
        if (StringUtils.isEmpty(existingBarcode))
            return false;
        return true;
    }

    @Action
    public void insertProduct(@Fact("excelProduct") ExcelProduct excelProduct) {

        MasterInformation masterInformation = excelProduct.toMasterInformation();
        productPriceRepository.save(excelProduct.toProductPrice(masterInformation));
    }
}
