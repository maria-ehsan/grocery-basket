package com.grocerybasket.release.models;

import java.util.List;

public class Category {
    String categoryCode;
    String categoryName;
    List<Category> childCategory;
}
