package util;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author arthur
 */
public class Report {
    Document report = new Document();
    public static final String DEST = "/home/arthur/pdfTemp.pdf";
    private String title;
    private ArrayList<PdfPTable> tables = new ArrayList<>();
    
    public void addTable(PdfPTable table) {
        tables.add(table);
    }
    public PdfPTable newTable(int ncol) {
        PdfPTable table = new PdfPTable(ncol);
        return table;
    }
    public void createPdf(String dest) throws DocumentException, FileNotFoundException {
        Font bigfont = new Font(Font.FontFamily.HELVETICA, 24);
        Document report = new Document();
        PdfWriter.getInstance(report, new FileOutputStream(dest));
        report.open();
        Paragraph titulo = new Paragraph(this.title, bigfont);
        titulo.setAlignment(Element.ALIGN_CENTER);
        report.add(titulo);
        report.add( Chunk.NEWLINE );
        report.add(new Paragraph(""));
        for (PdfPTable t: tables) {
            report.add(t);
            report.add( Chunk.NEWLINE );
            report.add(new Paragraph("Total de registros:"+ (t.getRows().size()-1)));
        }
        report.close();
    }
    public void open() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", "-c", "sensible-browser " + DEST);
        processBuilder.start();
    }
    public void generate() throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        createPdf(DEST);
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
