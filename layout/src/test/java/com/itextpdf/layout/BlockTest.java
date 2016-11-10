package com.itextpdf.layout;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.xobject.PdfImageXObject;
import com.itextpdf.kernel.utils.CompareTool;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.Property;
import com.itextpdf.test.ExtendedITextTest;
import com.itextpdf.test.annotations.type.IntegrationTest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;

@Category(IntegrationTest.class)
public class BlockTest extends ExtendedITextTest {

    public static final String sourceFolder = "./src/test/resources/com/itextpdf/layout/BlockTest/";
    public static final String destinationFolder = "./target/test/com/itextpdf/layout/BlockTest/";

    @BeforeClass
    public static void beforeClass() {
        createDestinationFolder(destinationFolder);
    }

    @Test
    public void blockWithSetHeightProperties01() throws IOException, InterruptedException {
        String outFileName = destinationFolder + "blockWithSetHeightProperties01.pdf";
        String cmpFileName = sourceFolder + "cmp_blockWithSetHeightProperties01.pdf";
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(outFileName));

        String textByron =
                "When a man hath no freedom to fight for at home,\n" +
                        "    Let him combat for that of his neighbours;\n" +
                        "Let him think of the glories of Greece and of Rome,\n" +
                        "    And get knocked on the head for his labours.\n" +
                        "\n" +
                        "To do good to Mankind is the chivalrous plan,\n" +
                        "    And is always as nobly requited;\n" +
                        "Then battle for Freedom wherever you can,\n" +
                        "    And, if not shot or hanged, you'll get knighted.";

        Document doc = new Document(pdfDocument);

        Paragraph p = new Paragraph(textByron);
        for (int i = 0; i < 10; i++) {
            p.add(textByron);
        }
        p.setBorder(new SolidBorder(0.5f));

        doc.add(new Paragraph("Default layout:"));
        doc.add(p);
        doc.add(new AreaBreak());

        doc.add(new Paragraph("Paragraph's height is set shorter than needed:"));
        p.setHeight(1300);
        doc.add(p);
        doc.add(new AreaBreak());

        doc.add(new Paragraph("Paragraph's min height is set shorter than needed:"));
        p.deleteOwnProperty(Property.HEIGHT);
        p.deleteOwnProperty(Property.MAX_HEIGHT);
        p.deleteOwnProperty(Property.MIN_HEIGHT);
        p.setMinHeight(1300);
        doc.add(p);
        doc.add(new AreaBreak());

        doc.add(new Paragraph("Paragraph's max height is set shorter than needed:"));
        p.deleteOwnProperty(Property.HEIGHT);
        p.deleteOwnProperty(Property.MAX_HEIGHT);
        p.deleteOwnProperty(Property.MIN_HEIGHT);
        p.setMaxHeight(1300);
        doc.add(p);
        doc.add(new AreaBreak());

        doc.add(new Paragraph("Paragraph's height is set bigger than needed:"));
        p.deleteOwnProperty(Property.HEIGHT);
        p.deleteOwnProperty(Property.MAX_HEIGHT);
        p.deleteOwnProperty(Property.MIN_HEIGHT);
        p.setHeight(2500);
        doc.add(p);
        doc.add(new AreaBreak());

        doc.add(new Paragraph("Paragraph's min height is set bigger than needed:"));
        p.deleteOwnProperty(Property.HEIGHT);
        p.deleteOwnProperty(Property.MAX_HEIGHT);
        p.deleteOwnProperty(Property.MIN_HEIGHT);
        p.setMinHeight(2500);
        doc.add(p);
        doc.add(new AreaBreak());

        doc.add(new Paragraph("Paragraph's max height is set bigger than needed:"));
        p.deleteOwnProperty(Property.HEIGHT);
        p.deleteOwnProperty(Property.MAX_HEIGHT);
        p.deleteOwnProperty(Property.MIN_HEIGHT);
        p.setMaxHeight(2500);
        doc.add(p);

        doc.close();

        Assert.assertNull(new CompareTool().compareByContent(outFileName, cmpFileName, destinationFolder, "diff"));
    }

    @Test
    public void blockWithSetHeightProperties02() throws IOException, InterruptedException {
        String outFileName = destinationFolder + "blockWithSetHeightProperties02.pdf";
        String cmpFileName = sourceFolder + "cmp_blockWithSetHeightProperties02.pdf";
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(outFileName));

        String textByron =
                "When a man hath no freedom to fight for at home,\n" +
                        "    Let him combat for that of his neighbours;\n" +
                        "Let him think of the glories of Greece and of Rome,\n" +
                        "    And get knocked on the head for his labours.\n" +
                        "\n" +
                        "To do good to Mankind is the chivalrous plan,\n" +
                        "    And is always as nobly requited;\n" +
                        "Then battle for Freedom wherever you can,\n" +
                        "    And, if not shot or hanged, you'll get knighted.";

        Document doc = new Document(pdfDocument);

        Paragraph p = new Paragraph(textByron);
        Div div = new Div();
        div.setBorder(new SolidBorder(Color.RED, 2));
        for (int i = 0; i < 5; i++) {
            div.add(p);
        }
        PdfImageXObject xObject = new PdfImageXObject(ImageDataFactory.create(sourceFolder + "Desert.jpg"));
        Image image = new Image(xObject, 300);
        div.add(image);

        doc.add(new Paragraph("Default layout:"));
        doc.add(div);
        doc.add(new AreaBreak());

        doc.add(new Paragraph("Div's height is set shorter than needed:"));
        div.setHeight(1000);
        doc.add(div);
        doc.add(new AreaBreak());

        doc.add(new Paragraph("Div's min height is set shorter than needed:"));
        div.deleteOwnProperty(Property.HEIGHT);
        div.deleteOwnProperty(Property.MAX_HEIGHT);
        div.deleteOwnProperty(Property.MIN_HEIGHT);
        div.setMinHeight(1000);
        doc.add(div);
        doc.add(new AreaBreak());

        doc.add(new Paragraph("Div's max height is set shorter than needed:"));
        div.deleteOwnProperty(Property.HEIGHT);
        div.deleteOwnProperty(Property.MAX_HEIGHT);
        div.deleteOwnProperty(Property.MIN_HEIGHT);
        div.setMaxHeight(1000);
        doc.add(div);
        doc.add(new AreaBreak());

        doc.add(new Paragraph("Div's height is set bigger than needed:"));
        div.deleteOwnProperty(Property.HEIGHT);
        div.deleteOwnProperty(Property.MAX_HEIGHT);
        div.deleteOwnProperty(Property.MIN_HEIGHT);
        div.setHeight(2500);
        doc.add(div);
        doc.add(new AreaBreak());

        doc.add(new Paragraph("Div's min height is set bigger than needed:"));
        div.deleteOwnProperty(Property.HEIGHT);
        div.deleteOwnProperty(Property.MAX_HEIGHT);
        div.deleteOwnProperty(Property.MIN_HEIGHT);
        div.setMinHeight(2500);
        doc.add(div);
        doc.add(new AreaBreak());

        doc.add(new Paragraph("Div's max height is set bigger than needed:"));
        div.deleteOwnProperty(Property.HEIGHT);
        div.deleteOwnProperty(Property.MAX_HEIGHT);
        div.deleteOwnProperty(Property.MIN_HEIGHT);
        div.setMaxHeight(2500);
        doc.add(div);

        doc.close();

        Assert.assertNull(new CompareTool().compareByContent(outFileName, cmpFileName, destinationFolder, "diff"));
    }
}