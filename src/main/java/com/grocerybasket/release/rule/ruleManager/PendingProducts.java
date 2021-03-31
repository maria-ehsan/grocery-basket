package com.grocerybasket.release.rule.ruleManager;


import com.grocerybasket.release.entity.Barcodes;
import com.grocerybasket.release.entity.MasterInformation;
import com.grocerybasket.release.models.ExcelProduct;
import com.grocerybasket.release.repository.BarcodeRepository;
import com.grocerybasket.release.repository.MasterInformationRepository;
import com.grocerybasket.release.repository.PendingProductsRepository;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@org.jeasy.rules.annotation.Rule(name = "pending_products", description = "", priority = Constants.RulePriority.PENDING_PRODUCTS)
@Component
public class PendingProducts implements Rule {

    private PendingProductsRepository pendingProductsRepository;
    private BarcodeRepository barcodeRepository;
    private MasterInformationRepository masterInformationRepository;

    @Autowired
    public PendingProducts(PendingProductsRepository pendingProductsRepository, BarcodeRepository barcodeRepository, MasterInformationRepository masterInformationRepository) {
        this.pendingProductsRepository = pendingProductsRepository;
        this.barcodeRepository = barcodeRepository;
        this.masterInformationRepository = masterInformationRepository;
    }

    @Condition
    public boolean doesProductNameExist(@Fact("excelProduct") ExcelProduct excelProduct) {

        List<Barcodes> existingBarcodes = barcodeRepository.findAll();
        if (!doesBarcodeAlreadyExist(excelProduct, existingBarcodes)) {
            List<MasterInformation> masterInformationList = masterInformationRepository.findAll();
            String subProductName = getfirstNWords(excelProduct.getName(), 2);
            for (MasterInformation masterInformation : masterInformationList) {
                if (getfirstNWords(masterInformation.getProductName(), 2).equals(subProductName)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean doesBarcodeAlreadyExist(@Fact("excelProduct") ExcelProduct excelProduct, List<Barcodes> existingBarcodes) {
        return existingBarcodes.stream().anyMatch(it -> it.getBarcode().equals(excelProduct.getBarcode()));
    }

    private String getfirstNWords(String input, int numOfWords) {
        String[] tokens = input.split(" ");
        tokens = ArrayUtils.subarray(tokens, 0, numOfWords);
        return StringUtils.join(tokens, ' ');
    }

    @Action
    public void insertProducts(@Fact("excelProduct") ExcelProduct excelProduct) {

        pendingProductsRepository.save(excelProduct.toPendingProducts());
    }
}
