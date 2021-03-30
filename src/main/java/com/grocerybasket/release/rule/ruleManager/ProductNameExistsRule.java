package com.grocerybasket.release.rule.ruleManager;

import com.grocerybasket.release.entity.Barcodes;
import com.grocerybasket.release.entity.MasterInformation;
import com.grocerybasket.release.models.ExcelProduct;
import com.grocerybasket.release.repository.BarcodeRepository;
import com.grocerybasket.release.repository.MasterInformationRepository;
import com.grocerybasket.release.repository.PendingProductCloseMatchRepository;
import com.grocerybasket.release.repository.PendingProductsRepository;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@org.jeasy.rules.annotation.Rule(name = "product_name_exists", description = "", priority = Constants.RulePriority.PRODUCT_NAME_EXISTS)
@Component
public class ProductNameExistsRule implements Rule {

    private PendingProductCloseMatchRepository pendingProductCloseMatchRepository;
    private PendingProductsRepository pendingProductsRepository;
    private BarcodeRepository barcodeRepository;
    private MasterInformationRepository masterInformationRepository;
    private Boolean isPendingProductCloseMatch = Boolean.FALSE;

    @Autowired
    public ProductNameExistsRule(PendingProductCloseMatchRepository pendingProductCloseMatchRepository, PendingProductsRepository pendingProductsRepository, BarcodeRepository barcodeRepository, MasterInformationRepository masterInformationRepository) {
        this.pendingProductCloseMatchRepository = pendingProductCloseMatchRepository;
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
                    isPendingProductCloseMatch = true;
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
        if (isPendingProductCloseMatch){
            pendingProductCloseMatchRepository.save(excelProduct.toPendingProductsCloseMatch());
        } else {
            pendingProductsRepository.save(excelProduct.toPendingProducts());
        }
    }
}
