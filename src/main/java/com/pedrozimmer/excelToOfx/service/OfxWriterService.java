package com.pedrozimmer.excelToOfx.service;

import com.pedrozimmer.excelToOfx.entity.Transaction;
import java.util.List;
import java.util.stream.Collectors;

public class OfxWriterService {

    private static final String HEADER = """
        <OFX>
        <SIGNONMSGSRSV1> … """;   // cole seu cabeçalho aqui

    private static final String FOOTER = """
        </BANKTRANLIST>
        </STMTRS>
        </STMTTRNRS>
        </BANKMSGSRSV1>
        </OFX>""";

    private String toEntry(Transaction t) {
        return """
            <STMTTRN>
              <TRNTYPE>%s</TRNTYPE>
              <DTPOSTED>%s</DTPOSTED>
              <MEMO>%s</MEMO>
              <CHECKNUM>%s</CHECKNUM>
              <TRNAMT>%s</TRNAMT>
            </STMTTRN>
            """.formatted(
                t.getType(),
                t.getDate().format(java.time.format.DateTimeFormatter.BASIC_ISO_DATE),
                t.getDescription(),
                t.getChecknum(),
                t.getAmount()
            );
    }

    public String build(List<Transaction> txs) {
        return HEADER +
               txs.stream().map(this::toEntry).collect(Collectors.joining()) +
               FOOTER;
    }
}
