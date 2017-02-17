package com.impetus.utility;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.impetus.model.BookRentalLog;
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
 * This view class generates a PDF document.
 * 
 */
public class PDFBuilder extends AbstractITextPdfView {
    
    /** The Constant LOGGER. */
    final static Logger LOGGER = Logger.getLogger(PDFBuilder.class);

    /* (non-Javadoc)
     * @see com.impetus.utility.AbstractITextPdfView#buildPdfDocument(java.util.Map, com.itextpdf.text.Document, com.itextpdf.text.pdf.PdfWriter, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
            PdfWriter writer, HttpServletRequest request,
            HttpServletResponse response) {
        // get data model which is passed by the Spring container

        @SuppressWarnings("unchecked")
        List<BookRentalLog> listBooks = (List<BookRentalLog>) model
                .get("listBooks");
        Timestamp from = (Timestamp) model.get("from");
        Timestamp to = (Timestamp) model.get("to");
        LOGGER.info("Generating PDF for Issue date from: " + from + " to: "
                + to);
        try {
            doc.add(new Paragraph("Book List"));
            doc.add(new Paragraph("Issue Date: from: "
                    + new SimpleDateFormat("dd-MM-yyyy").format(from) + " to: "
                    + new SimpleDateFormat("dd-MM-yyyy").format(to)));
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100.0f);
            table.setWidths(new float[] { 2.0f, 2.0f, 2.0f, 2.0f, 2.0f });

            table.setSpacingBefore(10);

            // define font for table header row
            Font font = FontFactory.getFont(FontFactory.HELVETICA);
            font.setColor(BaseColor.WHITE);

            // define table header cell
            PdfPCell cell = new PdfPCell();
            cell.setBackgroundColor(BaseColor.BLUE);
            cell.setPadding(5);

            // write table header
            cell.setPhrase(new Phrase("Book Title", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Author", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Category", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("User ID", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Issue Date", font));
            table.addCell(cell);

            // write table row data
            for (BookRentalLog aBook : listBooks) {
                table.addCell(aBook.getBookCatalogue().getTitle());
                table.addCell(aBook.getBookCatalogue().getAuthor());
                table.addCell(aBook.getBookCatalogue().getCategory());
                table.addCell(aBook.getUserDetails().getUserId());
                table.addCell(new SimpleDateFormat("dd-MM-yyyy").format(aBook
                        .getIssueDate()));
            }
            doc.add(table);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            LOGGER.info(e);
        }
    }

}