package com.pedrozimmer.excelToOfx.service;

import java.io.*;
import java.nio.file.*;
import java.util.List;

import com.pedrozimmer.excelToOfx.entity.Transaction;

public class ExcelToOfxService {

    private final ExcelReaderService reader = new ExcelReaderService();
    private final OfxWriterService writer = new OfxWriterService();

    public Path convert(InputStream xlsx, int formato) throws Exception {
        List<Transaction> txs = reader.parse(xlsx, formato);
        String ofx = writer.build(txs);

        Path outDir = Files.createTempDirectory("ofx_");   // ou ~/Downloads etc.
        Path out = outDir.resolve("arquivo.ofx");
        Files.writeString(out, ofx);
        return out;
    }
}
