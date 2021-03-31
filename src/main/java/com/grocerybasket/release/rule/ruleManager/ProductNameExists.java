package com.grocerybasket.release.rule.ruleManager;

import com.grocerybasket.release.entity.Barcodes;
import com.grocerybasket.release.entity.MasterInformation;
import com.grocerybasket.release.models.ExcelProduct;
import com.grocerybasket.release.repository.BarcodeRepository;
import com.grocerybasket.release.repository.MasterInformationRepository;
import com.grocerybasket.release.repository.PendingProductCloseMatchRepository;
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
public class ProductNameExists implements Rule {

    private PendingProductCloseMatchRepository pendingProductCloseMatchRepository;
    private BarcodeRepository barcodeRepository;
    private MasterInformationRepository masterInformationRepository;

    @Autowired
    public ProductNameExists(PendingProductCloseMatchRepository pendingProductCloseMatchRepository, BarcodeRepository barcodeRepository, MasterInformationRepository masterInformationRepository) {
        this.pendingProductCloseMatchRepository = pendingProductCloseMatchRepository;
        this.barcodeRepository = barcodeRepository;
        this.masterInformationRepository = masterInformationRepository;
    }

    @Condition
    public boolean doesProductNameExist(@Fact("excelProduct") ExcelProduct excelProduct) {

        Barcodes existingBarcode = barcodeRepository.findByBarcode(excelProduct.getBarcode());
        if (!doesBarcodeAlreadyExist(existingBarcode)) {
            List<MasterInformation> masterInformationList = masterInformationRepository.findAll();
            String subProductName = getfirstNWords(excelProduct.getName(), 2);
            for (MasterInformation masterInformation : masterInformationList) {
                if (getfirstNWords(masterInformation.getProductName(), 2).equals(subProductName)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean doesBarcodeAlreadyExist(Barcodes barcodes) {
        return StringUtils.isNotEmpty(barcodes.getBarcode());
    }

    private String getfirstNWords(String input, int numOfWords) {
        String[] tokens = input.split(" ");
        tokens = ArrayUtils.subarray(tokens, 0, numOfWords);
        return StringUtils.join(tokens, ' ');
    }

    @Action
    public void insertProducts(@Fact("excelProduct") ExcelProduct excelProduct) {
        pendingProductCloseMatchRepository.save(excelProduct.toPendingProductsCloseMatch());
    }
}
