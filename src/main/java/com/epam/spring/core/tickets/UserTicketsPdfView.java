package com.epam.spring.core.tickets;

import com.epam.spring.core.shared.AbstractPdfView;
import com.epam.spring.core.users.User;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class UserTicketsPdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(final Map<String, Object> model, final Document document, final PdfWriter writer,
                                    final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {
        User user = (User) model.get("user");

        String headerText = "Tickets booked by " + user.name + " (" + user.email + ")";
        Paragraph paragraph = new Paragraph(headerText, headerFont);
        paragraph.setSpacingAfter(20);
        document.add(paragraph);

        PdfPTable table = new PdfPTable(5);
        table.getDefaultCell().setFixedHeight(20);
        table.setWidthPercentage(100);

        table.addCell(new Phrase("Date", tableHeaderFont));
        table.addCell(new Phrase("Event", tableHeaderFont));
        table.addCell(new Phrase("Sit number", tableHeaderFont));
        table.addCell(new Phrase("Base price", tableHeaderFont));
        table.addCell(new Phrase("Discount price", tableHeaderFont));

        List<Ticket> tickets = (List<Ticket>) model.get("tickets");
        for (Ticket ticket : tickets) {
            table.addCell(new Phrase(ticket.date.toString("MM/dd/yyyy"), textFont));
            table.addCell(new Phrase(ticket.event.name, textFont));

            String seat = ticket.seat.toString();
            if (ticket.isVip) {
                seat += "*";
            }
            table.addCell(new Phrase(seat, textFont));

            table.addCell(new Phrase(ticket.basePrice.toString(), textFont));
            table.addCell(new Phrase(ticket.discountPrice.toString(), textFont));
        }

        document.add(table);
    }
}
