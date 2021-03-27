package com.grocerybasket.release.service;

import com.grocerybasket.release.repository.MasterInformationRepository;
import com.grocerybasket.release.rule.initialrulemanager.InitialRuleManager;
import com.grocerybasket.release.rule.ruleManager.RuleManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.grocerybasket.release.models.ExcelProduct;
import com.grocerybasket.release.util.ExcelUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ProductService {

    private final RuleManager ruleManager;
    private final InitialRuleManager initialRuleManager;
    private MasterInformationRepository masterInformationRepository;

    public void upload() {

        try {
            List<ExcelProduct> excelProducts = ExcelUtils.parseExcelFile(new FileInputStream(new File("/Users/salman/Downloads/SampleProductList.xlsx")));

            if(masterInformationRepository.count() > 0)
                ruleManager.run(excelProducts);
            else
                initialRuleManager.run(excelProducts);

        } catch (IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }
}
