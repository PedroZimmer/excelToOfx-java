package com.pedrozimmer.excelToOfx.entity;

import java.util.List;

public record OfxFile(String header, List<Transaction> body, String footer) { }