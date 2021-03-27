package com.grocerybasket.release.rule.ruleManager;

import com.grocerybasket.release.entity.Barcodes;
import com.grocerybasket.release.entity.MasterInformation;
import com.grocerybasket.release.entity.ProductPrice;
import com.grocerybasket.release.models.ExcelProduct;
import com.grocerybasket.release.repository.BarcodeRepository;
import com.grocerybasket.release.repository.MasterInformationRepository;
import com.grocerybasket.release.repository.PendingProductCloseMatchRepository;
import com.grocerybasket.release.repository.PendingProductsRepository;
import com.grocerybasket.release.repository.ProductPriceRepository;
import lombok.AllArgsConstructor;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@org.jeasy.rules.annotation.Rule(name = "barcode_exists_with_another_store", description = "", priority = Constants.RulePriority.BARCODE_EXISTS_WITH_ANOTHER_STORE)
@Component
public class ProductExistsWithAnotherStore implements Rule {

    private BarcodeRepository barcodeRepository;
    private ProductPriceRepository productPriceRepository;
    private MasterInformation existingProductCode;

    @Autowired
    public ProductExistsWithAnotherStore(BarcodeRepository barcodeRepository, ProductPriceRepository productPriceRepository) {
        this.barcodeRepository = barcodeRepository;
        this.productPriceRepository = productPriceRepository;
    }

    @Condition
    public boolean doesProductExistsWithAnotherStore(@Fact("excelProduct") ExcelProduct excelProduct) {

        List<Barcodes> existingBarcodes = barcodeRepository.findAll();
        return IsBarcodeExistsWithAnotherStore(excelProduct, existingBarcodes);
    }

    private boolean IsBarcodeExistsWithAnotherStore(@Fact("excelProduct") ExcelProduct excelProduct, List<Barcodes> existingBarcodes) {
//        existingProductCode = (MasterInformation) existingBarcodes.stream()
//                .filter(it -> it.getBarcode().equals(excelProduct.getBarcode()))
//                .filter(it -> !it.getStoreId().equals(excelProduct.getVendor()))
//                .map(Barcodes::getMasterInformation);
//        if(existingProductCode == null)
//            return false;
        return true;
    }

    @Action
    public void insertProducts(@Fact("excelProduct") ExcelProduct excelProduct) {

        excelProduct.setCode(existingProductCode.getProductCode());
        barcodeRepository.save(excelProduct.toBarcodes(excelProduct.toMasterInformation()));
//        MasterInformation masterInformation = excelProduct.toMasterInformation();
//        Barcodes br = excelProduct.toBarcodes(masterInformation);
//        barcodeRepository.save(br);

        ProductPrice productPrice = productPriceRepository.findByMasterInformation(excelProduct.getCode());
    }
}
