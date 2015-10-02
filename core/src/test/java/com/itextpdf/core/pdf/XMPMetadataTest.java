package com.itextpdf.core.pdf;

import com.itextpdf.core.testutils.annotations.type.IntegrationTest;
import com.itextpdf.core.xmp.XMPException;
import com.itextpdf.test.ExtendedITextTest;
import com.itextpdf.text.pdf.PdfReader;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(IntegrationTest.class)
public class XMPMetadataTest extends ExtendedITextTest{

    static final public String sourceFolder = "./src/test/resources/com/itextpdf/core/pdf/XmpWriterTest/";
    static final public String destinationFolder = "./target/test/com/itextpdf/core/pdf/XmpWriterTest/";

    @BeforeClass
    static public void beforeClass() {
        createDestinationFolder(destinationFolder);
    }

    @Test
    public void createEmptyDocumentWithXmp() throws IOException, XMPException {
        String filename = "emptyDocumentWithXmp.pdf";
        FileOutputStream fos = new FileOutputStream(destinationFolder +filename);
        PdfWriter writer = new PdfWriter(fos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        pdfDoc.getInfo().setAuthor("Alexander Chingarev").
                setCreator("iText 6").
                setTitle("Empty iText 6 Document");
        pdfDoc.getInfo().getPdfObject().remove(PdfName.CreationDate);
        PdfPage page = pdfDoc.addNewPage();
        page.flush();
        pdfDoc.setXmpMetadata();
        pdfDoc.close();

        com.itextpdf.text.pdf.PdfReader reader = new PdfReader(destinationFolder +filename);
        Assert.assertEquals("Rebuilt", false, reader.isRebuilt());
        Assert.assertEquals(readFile(sourceFolder + "emptyDocumentWithXmp.xml").length, reader.getMetadata().length);
        Assert.assertNotNull(reader.getPageN(1));
        reader.close();

    }

    @Test
    public void createEmptyDocumentWithAbcXmp() throws IOException, XMPException {
        ByteArrayOutputStream fos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(fos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        pdfDoc.getInfo().setAuthor("Alexander Chingarev").
                setCreator("iText 6").
                setTitle("Empty iText 6 Document");
        pdfDoc.getInfo().getPdfObject().remove(PdfName.CreationDate);
        PdfPage page = pdfDoc.addNewPage();
        page.flush();
        pdfDoc.setXmpMetadata("abc".getBytes());
        pdfDoc.close();

        com.itextpdf.text.pdf.PdfReader reader = new PdfReader(new ByteArrayInputStream(fos.toByteArray()));
        Assert.assertEquals("Rebuilt", false, reader.isRebuilt());
        Assert.assertArrayEquals("abc".getBytes(), reader.getMetadata());
        Assert.assertNotNull(reader.getPageN(1));
        reader.close();

    }


}
