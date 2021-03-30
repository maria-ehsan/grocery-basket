package com.grocerybasket.release.rule.ruleManager;

import com.grocerybasket.release.entity.Barcodes;
import com.grocerybasket.release.entity.MasterInformation;
import com.grocerybasket.release.models.ExcelProduct;
import com.grocerybasket.release.repository.BarcodeRepository;
import com.grocerybasket.release.repository.ProductPriceRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@org.jeasy.rules.annotation.Rule(name = "barcode_exists_with_another_store", description = "", priority = Constants.RulePriority.BARCODE_EXISTS_WITH_ANOTHER_STORE)
@Component
public class BarcodeExistsWithAnotherStore implements Rule {

    private BarcodeRepository barcodeRepository;
    private ProductPriceRepository productPriceRepository;
    private List<Barcodes> existingBarcodes;

    @Autowired
    public BarcodeExistsWithAnotherStore(BarcodeRepository barcodeRepository, ProductPriceRepository productPriceRepository) {
        this.barcodeRepository = barcodeRepository;
        this.productPriceRepository = productPriceRepository;
    }

    @Condition
    public boolean doesBarcodeExistWithAnotherStore(@Fact("excelProduct") ExcelProduct excelProduct) {

        existingBarcodes = barcodeRepository.findByBarcodeAndAndStoreIdNot(excelProduct.getBarcode(), excelProduct.getVendor());
        return doesBarcodeExistWithAnotherStore(existingBarcodes);
    }

    private boolean doesBarcodeExistWithAnotherStore(List existingBarcodes) {
        if (CollectionUtils.isEmpty(existingBarcodes))
            return false;
        return true;
    }

    @Action
    public void insertProduct(@Fact("excelProduct") ExcelProduct excelProduct) {

        MasterInformation masterInformation = excelProduct.toMasterInformation();
        barcodeRepository.save(excelProduct.toBarcodes(masterInformation));
        productPriceRepository.save(excelProduct.toProductPrice(masterInformation));
    }
}
