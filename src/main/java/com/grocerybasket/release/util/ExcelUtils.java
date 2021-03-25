package com.grocerybasket.release.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.grocerybasket.release.models.ExcelProduct;
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

                    if (cellIndex == 0) { // ID
                        product.setBarcode(currentCell.getStringCellValue());
                    } else if (cellIndex == 1) { // Name
                        product.setName(currentCell.getStringCellValue());
                    } else if (cellIndex == 2) { // Name
                        product.setVendor(currentCell.getStringCellValue());
                    } else if (cellIndex == 3) { // Address
                        product.setSalePrice(currentCell.getNumericCellValue());
                    } else if (cellIndex == 4) { // Address
                        product.setRetailPrice(currentCell.getNumericCellValue());
                    } else if (cellIndex == 5) { // Age
                        product.setStatus(currentCell.getStringCellValue());
                    } else if (cellIndex == 6) { // Age
                        product.setImageName("abcd");
                    } else if (cellIndex == 7) { // Age
                        product.setStockQuantity((int) currentCell.getNumericCellValue());
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
