package com.grocerybasket.release.service;

import com.grocerybasket.release.models.ExcelProduct;
import com.grocerybasket.release.repository.MasterInformationRepository;
import com.grocerybasket.release.rule.initialrulemanager.InitialRuleManager;
import com.grocerybasket.release.rule.ruleManager.RuleManager;
import com.grocerybasket.release.util.ExcelUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ProductService {

    private final RuleManager ruleManager;
    private final InitialRuleManager initialRuleManager;
    private MasterInformationRepository masterInformationRepository;

    public void upload(MultipartFile file) {

        try {
            List<ExcelProduct> excelProducts = ExcelUtils.parseExcelFile(file.getInputStream());

            if (masterInformationRepository.count() > 0)
                ruleManager.run(excelProducts);
            else
                initialRuleManager.run(excelProducts);

        } catch (IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }
}
