package com.epam.spring.core.tickets;

import com.epam.spring.core.events.Show;
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

public class ShowTicketsPdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(final Map<String, Object> model, final Document document, final PdfWriter writer,
                                    final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {

        Show show = (Show) model.get("show");

        String headerText = "Tickets for " + show.getEvent().getName();
        Paragraph headerParagraph = new Paragraph(headerText, headerFont);
        headerParagraph.setSpacingAfter(20);
        document.add(headerParagraph);

        String dateText = "Date: " + show.getDate().toString("MM/dd/yyyy HH:mm");
        Paragraph dateParagraph = new Paragraph(dateText, tableHeaderFont);
        dateParagraph.setSpacingAfter(20);
        document.add(dateParagraph);

        String auditoriumText = "Auditorium: " + show.getAuditorium().getName();
        Paragraph auditoriumParagraph = new Paragraph(auditoriumText, tableHeaderFont);
        auditoriumParagraph.setSpacingAfter(20);
        document.add(auditoriumParagraph);

        PdfPTable table = new PdfPTable(3);
        table.getDefaultCell().setFixedHeight(20);
        table.setWidthPercentage(100);

        table.addCell(new Phrase("Sit number", tableHeaderFont));
        table.addCell(new Phrase("Base price", tableHeaderFont));
        table.addCell(new Phrase("Booked by", tableHeaderFont));

        List<Ticket> tickets = (List<Ticket>) model.get("tickets");
        for (Ticket ticket : tickets) {
            String seat = ticket.getSeat().toString();
            if (ticket.isVip()) {
                seat += "*";
            }
            table.addCell(new Phrase(seat, textFont));

            table.addCell(new Phrase(ticket.getBasePrice().toString(), textFont));

            User user = ticket.getUser();
            String userName;
            if (user == null) {
                userName = "--";
            } else {
                userName = user.getName();
            }
            table.addCell(new Phrase(userName, textFont));
        }

        document.add(table);
    }
}
