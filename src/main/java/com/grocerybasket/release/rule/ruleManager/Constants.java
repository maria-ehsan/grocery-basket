package com.grocerybasket.release.rule.ruleManager;


public class Constants {
    public static final class RulePriority {

        private RulePriority() {
        }

        public static final int PRODUCT_NAME_EXISTS = 1;
        public static final int BARCODE_EXISTS_WITH_ANOTHER_STORE = 2;
        public static final int BARCODE_EXISTS_WITH_SAME_STORE = 3;
    }
}
