package com.epam.spring.core.tickets;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class UserTicketsPdfView extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(final Map<String, Object> model, final Document document, final PdfWriter writer,
                                    final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {

        String userName = (String) model.get("userName");
        Paragraph paragraph = new Paragraph(userName);

        document.add(paragraph);
    }
}
