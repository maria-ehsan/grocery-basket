package com.grocerybasket.release.util;

import com.grocerybasket.release.models.ExcelProduct;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class ExcelUtils {

    public static String EXCELTYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static List parseExcelFile(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheetAt(0);
            Iterator rows = sheet.iterator();

            List products = new ArrayList();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = (Row) rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator cellsInRow = currentRow.iterator();

                ExcelProduct product = new ExcelProduct();

                int cellIndex = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = (Cell) cellsInRow.next();

                    if (cellIndex == Constants.BARCODE) {
                        product.setBarcode(currentCell.getStringCellValue());
                    } else if (cellIndex == Constants.NAME) {
                        product.setName(currentCell.getStringCellValue());
                    } else if (cellIndex == Constants.VENDOR) {
                        product.setVendor(currentCell.getStringCellValue());
                    } else if (cellIndex == Constants.SALE_PRICE) { //
                        product.setSalePrice(currentCell.getNumericCellValue());
                    } else if (cellIndex == Constants.RETAIL_PRICE) {
                        product.setRetailPrice(currentCell.getNumericCellValue());
                    } else if (cellIndex == Constants.STATUS) {
                        product.setStatus(currentCell.getStringCellValue());
                    } else if (cellIndex == Constants.IMAGE) {
                        product.setImageName("abcd");
                    } else if (cellIndex == Constants.STOCK_QUANTITY) {
                        product.setStockQuantity((int) currentCell.getNumericCellValue());
                    } else if (cellIndex == Constants.CODE){
                        product.setCode(currentCell.getStringCellValue());
                    }

                    cellIndex++;
                }

                products.add(product);
            }

            // Close WorkBook
            workbook.close();

            return products;
        } catch (IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }

    public static boolean isExcelFile(MultipartFile file) {

        if(!EXCELTYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }
}
