package com.pedrozimmer.excelToOfx.controller;

import com.pedrozimmer.excelToOfx.service.ExcelToOfxService;

import java.nio.file.Files;
import java.nio.file.Path;

public class CliController {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Uso: java -jar app.jar <xlsx> <tipo (1|2|3)>");
            return;
        }
        var svc = new ExcelToOfxService();
        try (var in = Files.newInputStream(Path.of(args[0]))) {
            Path ofxPath = svc.convert(in, Integer.parseInt(args[1]));
            System.out.println("OFX gerado em: " + ofxPath);
        }
    }
}
