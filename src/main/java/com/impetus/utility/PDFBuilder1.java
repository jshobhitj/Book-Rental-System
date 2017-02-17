package com.impetus.utility;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.impetus.model.BookCatalogue;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

// TODO: Auto-generated Javadoc
/**
 * This view class generates a PDF documents.
 * 
 *
 * 
 */
public class PDFBuilder1 extends AbstractITextPdfView {

    /** The Constant LOGGER. */
    final static Logger LOGGER = Logger.getLogger(PDFBuilder1.class);

    /* (non-Javadoc)
     * @see com.impetus.utility.AbstractITextPdfView#buildPdfDocument(java.util.Map, com.itextpdf.text.Document, com.itextpdf.text.pdf.PdfWriter, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
            PdfWriter writer, HttpServletRequest request,
            HttpServletResponse response) {
        // get data model which is passed by the Spring container

        @SuppressWarnings("unchecked")
        List<BookCatalogue> listBooks = (List<BookCatalogue>) model
                .get("listBooks");
        Timestamp from = (Timestamp) model.get("from");
        Timestamp to = (Timestamp) model.get("to");
        LOGGER.info("Generating PDF for Issue date from: " + from + " to: "
                + to);
        @SuppressWarnings("unchecked")
        List<Long> listBooksCount = (List<Long>) model.get("listBooksCount");

        try {
            doc.add(new Paragraph("Book Summary"));
            doc.add(new Paragraph("Issue Date: from: "
                    + new SimpleDateFormat("dd-MM-yyyy").format(from) + " to: "
                    + new SimpleDateFormat("dd-MM-yyyy").format(to)));
            PdfPTable table = new PdfPTable(8);
            table.setWidthPercentage(100.0f);
            table.setWidths(new float[] { 1.5f, 1.5f, 1.2f, 1.2f, 1.1f, 1.2f,
                    1.2f, 1.1f });
            table.setSpacingBefore(10);

            // define font for table header row
            Font font = FontFactory.getFont(FontFactory.HELVETICA);
            font.setColor(BaseColor.WHITE);

            // define table header cell
            PdfPCell cell = new PdfPCell();
            cell.setBackgroundColor(BaseColor.BLUE);
            cell.setPadding(5);

            // write table header
            cell.setPhrase(new Phrase("Title", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Author", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Count Delivery(Pending)", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Count Delivery(Cancel)", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Count Delivery(Closed)", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Count Return(Pending)", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Count Return(Cancel)", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Count Return(Closed)", font));
            table.addCell(cell);

            // write table row data

            ListIterator<Long> itr = listBooksCount.listIterator();

            for (BookCatalogue aBook : listBooks) {
                table.addCell(aBook.getTitle());
                table.addCell(aBook.getAuthor());
                table.addCell(Long.toString(itr.next()));
                table.addCell(Long.toString(itr.next()));
                table.addCell(Long.toString(itr.next()));
                table.addCell(Long.toString(itr.next()));
                table.addCell(Long.toString(itr.next()));
                table.addCell(Long.toString(itr.next()));
            }
            doc.add(table);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            LOGGER.info(e);
        }

    }

}