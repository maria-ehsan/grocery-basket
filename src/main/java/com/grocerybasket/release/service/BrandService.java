package com.grocerybasket.release.service;

import com.grocerybasket.release.models.Brand;
import com.grocerybasket.release.models.Category;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BrandService {


    public List<Brand> getBrands(String query) {
        return null;
    }

    public boolean addBrands(Brand brand) {
        return false;
    }
}
