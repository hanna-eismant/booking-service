package com.epam.spring.core.api.rest;

import com.epam.spring.core.tickets.Ticket;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Ticket2PdfConverter implements HttpMessageConverter<Ticket> {
    @Override
    public boolean canRead(final Class<?> clazz, final MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(final Class<?> clazz, final MediaType mediaType) {
        return true;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        List<MediaType> mediaTypes = new ArrayList<>(1);
        mediaTypes.add(MediaType.valueOf("application/pdf"));
        return mediaTypes;
    }

    @Override
    public Ticket read(final Class<? extends Ticket> clazz, final HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(final Ticket ticket, final MediaType contentType, final HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {

        StringBuilder stringBuilder = new StringBuilder()
                .append("\n").append("Booked ticket: ").append(ticket.getId())
                .append("\n").append("Seat number:   ").append(ticket.getSeat())
                .append("\n").append("Base price:    ").append(ticket.getBasePrice())
                .append("\n").append("Final price:   ").append(ticket.getDiscountPrice())
                .append("\n").append("User:          ").append(ticket.getUser().getName())
                .append("\n").append("Date:          ").append(ticket.getShow().getDate().toString("MM/dd/yyyy HH:mm"))
                .append("\n").append("Auditorium:    ").append(ticket.getShow().getAuditorium().getName())
                .append("\n").append("Event:         ").append(ticket.getShow().getEvent().getName());

        OutputStream outputStream = outputMessage.getBody();
        byte[] bytes = stringBuilder.toString().getBytes();

        outputStream.write(bytes);
    }
}
