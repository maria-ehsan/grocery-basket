package com.grocerybasket.release.rule;

import com.grocerybasket.release.entity.Barcodes;
import com.grocerybasket.release.entity.MasterInformation;
import com.grocerybasket.release.entity.ProductPrice;
import com.grocerybasket.release.models.ExcelProduct;
import com.grocerybasket.release.repository.BarcodeRepository;
import com.grocerybasket.release.repository.MasterInformationRepository;
import com.grocerybasket.release.repository.ProductPriceRepository;
import lombok.AllArgsConstructor;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Rule(name = "initial_product_entry", description = "", priority = Constants.RulePriority.INITIAL_PRODUCT_ENTRY)
@Component
public class InitialProductEntryRule implements RuleInterface {

    private MasterInformationRepository masterInformationRepository;
    private ProductPriceRepository productPriceRepository;
    private BarcodeRepository barcodeRepository;


    @Condition
    public Boolean isDataExists() {
        if (masterInformationRepository.count() == 0) {
            return true;
        }
        return false;
    }

    @Action
    public void insertProducts(@Fact("enrichmentResponse")
                                       List<ExcelProduct> excelProducts) {
        List<MasterInformation> masterInformations = new ArrayList<>();
        List<ProductPrice> productPrices = new ArrayList<>();
        List<Barcodes> barcodes = new ArrayList<>();

        for (ExcelProduct product : excelProducts) {
            MasterInformation masterInformation = product.toMasterInformation();
            masterInformations.add(masterInformation);
            ProductPrice productPrice = product.toProductPrice(masterInformation);
            productPrices.add(productPrice);
            Barcodes barcode = product.toBarcodes(masterInformation);
            barcodes.add(barcode);
        }

        masterInformationRepository.saveAll(masterInformations);
        productPriceRepository.saveAll(productPrices);
        barcodeRepository.saveAll(barcodes);
    }
}
