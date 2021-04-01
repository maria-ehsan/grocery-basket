package com.grocerybasket.release.service;

import com.grocerybasket.release.models.Category;
import com.grocerybasket.release.models.ExcelProduct;
import com.grocerybasket.release.models.ProductCatalog;
import com.grocerybasket.release.repository.MasterInformationRepository;
import com.grocerybasket.release.repository.ProductPriceRepository;
import com.grocerybasket.release.rule.initialrulemanager.InitialRuleManager;
import com.grocerybasket.release.rule.ruleManager.RuleManager;
import com.grocerybasket.release.util.ExcelUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CategoryService {


    public List<Category> getCategories(String query) {
        return null;
    }

    public boolean addCategories(Category category) {
        return false;
    }
}
