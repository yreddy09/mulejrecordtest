package gov.sc.dhhs.cgisofm.writer.ebcdic.cp037;

import net.sf.JRecord.Common.RecordException;
import net.sf.JRecord.Details.AbstractLine;
import net.sf.JRecord.Details.LayoutDetail;
import net.sf.JRecord.Details.Line;
import net.sf.JRecord.External.CopybookLoader;
import net.sf.JRecord.External.RecordEditorXmlLoader;
import net.sf.JRecord.IO.AbstractLineWriter;
import net.sf.JRecord.IO.LineIOProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.sc.dhhs.cgis.domain.MySrs;
import gov.sc.dhhs.cgis.domain.utils.FillerUtil;
import gov.sc.dhhs.cgisofm.writer.CgisOFMWriterException;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class BuyInTypeAOutboundWriter { //implements CgisOFMWriter<SCDEWTPLReport> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

    private static final Logger logger = LoggerFactory.getLogger(BuyInTypeAOutboundWriter.class);


    private final static String MAPPING_FILE = "/Users/yreddy/AnypointStudio/workspace3.9/mulejrecordtest/src/main/resources/jrecord/MYSRS.jrecord.xml";
    private String srsFileOut   = "/Users/yreddy/AnypointStudio/workspace3.9/mulejrecordtest/src/main/resource/output/DTAR020out.bin";

    private static InputStream copyBookXMLStream;

    public BuyInTypeAOutboundWriter() throws Exception {
        loadCopyBook();
    }

    private void loadCopyBook() throws Exception {
        copyBookXMLStream = this.getClass().getClassLoader().getResourceAsStream(MAPPING_FILE);
		if(copyBookXMLStream == null) {
			copyBookXMLStream = new FileInputStream(MAPPING_FILE);
		}
    }

    public void write(List<MySrs> domainObjects, OutputStream outputStream, String file) throws CgisOFMWriterException {

        AbstractLineWriter writer = null;
        try {
            logger.debug("Reading file {}", MAPPING_FILE);
            //RecordEditorXmlLoader loader = new RecordEditorXmlLoader();
            CopybookLoader loader = new RecordEditorXmlLoader();
            //LayoutDetail layout = loader.loadCopyBook(copyBookXMLStream, "SCDEWTPLReport").asLayoutDetail();
            LayoutDetail layout = loader.loadCopyBook(MAPPING_FILE, 0, 0, "", 0, 0, null).asLayoutDetail();
            int fileStructure = layout.getFileStructure();
            logger.info("fileStructure {}", fileStructure);
            writer = LineIOProvider.getInstance().getLineWriter(fileStructure);
            //writer.open(outputStream);
            writer.open(file);
            for (MySrs srsObject: domainObjects){
                AbstractLine reportRecord = new Line(layout);
 
                reportRecord.getFieldValue("RetirementCode").set(srsObject.getSrsRetirementCode());
                reportRecord.getFieldValue("SSNoftheRetiree").set(srsObject.getSsnOfTheRetiree());
                reportRecord.getFieldValue("SSNofPerson").set(srsObject.getSsnOfPersonReceivingBenefits());
                reportRecord.getFieldValue("PayeeName").set(srsObject.getPayeeName());
                reportRecord.getFieldValue("POAName").set(srsObject.getPoaName());
                reportRecord.getFieldValue("GrossAmountofBenefit").set(srsObject.getGrossAmountofBenefit());
                writer.write(reportRecord);
            }
            writer.close();
        } catch (IOException ioe) {

        } catch (RecordException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
