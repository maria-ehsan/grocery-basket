package com.grocerybasket.release.rule.initialrulemanager;

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
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@org.jeasy.rules.annotation.Rule(name = "initial_product_entry", description = "", priority = Constants.RulePriority.INITIAL_PRODUCT_ENTRY)
@Component
public class InitialProductEntryRule implements InitialRule {

    private MasterInformationRepository masterInformationRepository;
    private ProductPriceRepository productPriceRepository;
    private BarcodeRepository barcodeRepository;


    @Condition
    public boolean isDataExists(@Fact("excelProducts") List<ExcelProduct> excelProducts) {
        return true;
    }

    @Action
    public void insertProducts(@Fact("excelProducts") List<ExcelProduct> excelProducts) {

        List<MasterInformation> masterInformations = new ArrayList<>();
        List<ProductPrice> productPrices = new ArrayList<>();
        List<Barcodes> barcodes = new ArrayList<>();

        excelProductToEntites(excelProducts, masterInformations, productPrices, barcodes);
        persist(masterInformations, productPrices, barcodes);
    }

    private void persist(List<MasterInformation> masterInformations, List<ProductPrice> productPrices, List<Barcodes> barcodes) {
        masterInformationRepository.saveAll(masterInformations);
        productPriceRepository.saveAll(productPrices);
        barcodeRepository.saveAll(barcodes);
    }

    private void excelProductToEntites(@Fact("excelProducts") List<ExcelProduct> excelProducts, List<MasterInformation> masterInformations, List<ProductPrice> productPrices, List<Barcodes> barcodes) {
        for (ExcelProduct product: excelProducts) {
            MasterInformation masterInformation = product.toMasterInformation();
            masterInformations.add(masterInformation);
            ProductPrice productPrice = product.toProductPrice(masterInformation);
            productPrices.add(productPrice);
            Barcodes barcode = product.toBarcodes(masterInformation);
            barcodes.add(barcode);
        }
    }
}
