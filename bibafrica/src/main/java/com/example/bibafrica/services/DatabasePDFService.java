package com.example.bibafrica.services;
import com.example.bibafrica.model.Lawyer;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

public class DatabasePDFService {
    public static ByteArrayInputStream employeePDFReport(List<Lawyer> lawyers) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);
            document.open();

            // Add Content to PDF file ->
            Font fontHeader = FontFactory.getFont(FontFactory.TIMES_BOLD, 22);
            Paragraph para = new Paragraph("Volunteer Structure", fontHeader);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(6);
            // Add PDF Table Header ->
            Stream.of("ID", "NAMES","PHONE","EMAIL", "LOCATION", "DATE","CASES").forEach(headerTitle -> {
                PdfPCell header = new PdfPCell();
                Font headFont = FontFactory.getFont(FontFactory.TIMES_BOLD);
                header.setBackgroundColor(Color.CYAN);
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setBorderWidth(2);
                header.setPhrase(new Phrase(headerTitle, headFont));
                table.addCell(header);
            });

            for (Lawyer lawyer : lawyers) {
                PdfPCell idCell = new PdfPCell(new Phrase(String.valueOf(lawyer.getId())));
                idCell.setPaddingLeft(4);
                idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(idCell);

                PdfPCell firstNameCell = new PdfPCell(new Phrase(lawyer.getNames()));
                firstNameCell.setPaddingLeft(4);
                firstNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                firstNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(firstNameCell);

                PdfPCell lastNameCell = new PdfPCell(new Phrase(String.valueOf(lawyer.getTel())));
                lastNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                lastNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                lastNameCell.setPaddingRight(4);
                table.addCell(lastNameCell);

                PdfPCell deptCell = new PdfPCell(new Phrase(String.valueOf(lawyer.getEmails())));
                deptCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                deptCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                deptCell.setPaddingRight(4);
                table.addCell(deptCell);

                PdfPCell phoneNumCell = new PdfPCell(new Phrase(String.valueOf(lawyer.getLawyer())));
                phoneNumCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                phoneNumCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                phoneNumCell.setPaddingRight(4);
                table.addCell(phoneNumCell);

                PdfPCell emailNumCell = new PdfPCell(new Phrase(String.valueOf(lawyer.getDate().toString())));
                phoneNumCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                phoneNumCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                phoneNumCell.setPaddingRight(4);
                table.addCell(emailNumCell);

                PdfPCell cases = new PdfPCell(new Phrase(String.valueOf(lawyer.getCases())));
                phoneNumCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                phoneNumCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                phoneNumCell.setPaddingRight(4);
                table.addCell(cases);

            }
            document.add(table);

            document.close();} catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
