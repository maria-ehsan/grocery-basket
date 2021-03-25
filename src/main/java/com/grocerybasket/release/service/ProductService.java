package com.grocerybasket.release.service;

import com.grocerybasket.release.repository.BarcodeRepository;
import com.grocerybasket.release.repository.ProductPriceRepository;
import lombok.AllArgsConstructor;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.grocerybasket.release.models.ExcelProduct;
import com.grocerybasket.release.entity.Barcodes;
import com.grocerybasket.release.entity.MasterInformation;
import com.grocerybasket.release.entity.ProductPrice;
import com.grocerybasket.release.repository.MasterInformationRepository;
import com.grocerybasket.release.util.ExcelUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ProductService {

    private MasterInformationRepository masterInformationRepository;
    private ProductPriceRepository productPriceRepository;
    private BarcodeRepository barcodeRepository;

    private List<Barcodes> localBarcodesCache;
    private List<MasterInformation> localMasterInformationCache;

    public void upload() {

        try {
            List<ExcelProduct> excelProducts = ExcelUtils.parseExcelFile(new FileInputStream(new File("/Users/salman/Downloads/SampleProductList.xlsx")));


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

            if (masterInformationRepository.count() == 0) {
                masterInformationRepository.saveAll(masterInformations);
                productPriceRepository.saveAll(productPrices);
                barcodeRepository.saveAll(barcodes);
            }

        } catch (IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }

    @Synchronized
    @Scheduled(fixedRateString = "${properties.cache.scheduler.refresh-rate:86400000}")
    public void initLocalDictionary() {
        try {
            log.info("Going to fetch all care barcodes ");

            localBarcodesCache = barcodeRepository.findAll();
            localMasterInformationCache = masterInformationRepository.findAll();

            log.info("Fetched all care dispositions ");
        } catch (IllegalArgumentException e) {
            log.error("Unable to fetch dispositions List ", e);
        }
    }

}
