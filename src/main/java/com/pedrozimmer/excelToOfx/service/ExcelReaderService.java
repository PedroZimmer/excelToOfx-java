package com.pedrozimmer.excelToOfx.service;

import com.pedrozimmer.excelToOfx.entity.Transaction;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ExcelReaderService {

    public List<Transaction> parse(InputStream in, int formato) throws Exception {
        Workbook wb = new XSSFWorkbook(in);
        Sheet sheet = wb.getSheetAt(0);

        List<Transaction> list = new ArrayList<>();
        for (Row row : sheet) {
            // pule cabeçalhos …
            if (row.getRowNum() == 0) continue;

            LocalDate date = row.getCell(0).getDateCellValue()
                                 .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String desc1 = row.getCell(1).getStringCellValue();
            String desc2 = row.getCell(2).getStringCellValue();
            String checknum = row.getCell(3).getStringCellValue();
            BigDecimal valorOriginal = new BigDecimal(row.getCell(4).getNumericCellValue());
            BigDecimal valorPago     = new BigDecimal(row.getCell(5).getNumericCellValue());

            // adapte este switch aos seus `add_transacao_tipo*`
            switch (formato) {
                case 1 -> {/* montar Transaction como no Python tipo1 */}
                case 2 -> {/* montar Transaction como no Python tipo2 */}
                // …
            }

            // list.add(transaction);
        }
        wb.close();
        return list;
    }
}
