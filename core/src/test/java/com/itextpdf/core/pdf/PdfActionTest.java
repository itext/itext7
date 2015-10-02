package com.itextpdf.core.pdf;

import com.itextpdf.core.pdf.action.PdfAction;
import com.itextpdf.core.testutils.annotations.type.IntegrationTest;
import com.itextpdf.test.ExtendedITextTest;

import java.io.FileOutputStream;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(IntegrationTest.class)
public class PdfActionTest  extends ExtendedITextTest {

    static final public String sourceFolder = "./src/test/resources/com/itextpdf/core/pdf/PdfActionTest/";
    static final public String destinationFolder = "./target/test/com/itextpdf/core/pdf/PdfActionTest/";

    @BeforeClass
    static public void beforeClass() {
        createDestinationFolder(destinationFolder);
    }

    @Test
    public void actionTest01() throws Exception {
        PdfWriter writer = new PdfWriter(new FileOutputStream(destinationFolder + "actionTest01.pdf"));
        PdfDocument document = createDocument(writer, true);

        document.getCatalog().setOpenAction(PdfAction.createURI(document, "http://itextpdf.com/"));

        document.close();

        System.out.println(String.format("Please open document %s and make sure that you're automatically redirected to %s site.", destinationFolder + "actionTest01.pdf", "http://itextpdf.com"));
    }

    @Test
    public void actionTest02() throws Exception {

        PdfWriter writer = new PdfWriter(new FileOutputStream(destinationFolder + "actionTest02.pdf"));
        PdfDocument document = createDocument(writer, false);

        document.getPage(2).setAdditionalAction(PdfName.O, PdfAction.createURI(document, "http://itextpdf.com/"));

        document.close();

        System.out.println(String.format("Please open document %s at page 2 and make sure that you're automatically redirected to %s site.", destinationFolder + "actionTest02.pdf", "http://itextpdf.com"));

    }

    private PdfDocument createDocument(PdfWriter writer, boolean flushPages) {
        PdfDocument document = new PdfDocument(writer);
        PdfPage p1 = document.addNewPage();
        PdfStream str1 = p1.getFirstContentStream();
        str1.getOutputStream().writeString("1 0 0 rg 100 600 100 100 re f\n");
        if (flushPages)
            p1.flush();
        PdfPage p2 = document.addNewPage();
        PdfStream str2 = p2.getFirstContentStream();
        str2.getOutputStream().writeString("0 1 0 rg 100 600 100 100 re f\n");
        if (flushPages)
            p2.flush();
        PdfPage p3 = document.addNewPage();
        PdfStream str3 = p3.getFirstContentStream();
        str3.getOutputStream().writeString("0 0 1 rg 100 600 100 100 re f\n");
        if (flushPages)
            p3.flush();
        return document;
    }

}
