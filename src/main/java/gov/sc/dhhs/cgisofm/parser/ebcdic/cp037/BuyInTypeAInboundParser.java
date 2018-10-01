package gov.sc.dhhs.cgisofm.parser.ebcdic.cp037;

import gov.sc.dhhs.cgis.domain.MySrs;
import gov.sc.dhhs.cgisofm.writer.ebcdic.cp037.BuyInTypeAOutboundWriter;
import net.sf.JRecord.Details.AbstractLine;
import net.sf.JRecord.IO.AbstractLineReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BuyInTypeAInboundParser extends CP037Parser {

	private static final Logger logger = LoggerFactory.getLogger(BuyInTypeAInboundParser.class);
	private final static String MAPPING_FILE = "jrecord/MYSRS.jrecord.xml";
	private static InputStream copyBookXMLStream;

	public BuyInTypeAInboundParser() throws Exception {
		loadCopyBook();
	}

	private void loadCopyBook() throws Exception {
		try {
			copyBookXMLStream = this.getClass().getClassLoader().getResourceAsStream(MAPPING_FILE);
			if(copyBookXMLStream == null) {
				copyBookXMLStream = new FileInputStream(MAPPING_FILE);
			}
			
			
		} catch (Exception ex) {
			logger.error("Unable to load the copy book");
			throw ex;
		}
	}

	public InputStream getCopyBookXMLStream() {
		return copyBookXMLStream;
	}

	

	public Set<MySrs> parse(InputStream stream) throws CgisOFMParserException {
		Set<MySrs> srsSet = new HashSet<MySrs>();
		String mappingFile = "/Users/yreddy/AnypointStudio/workspace3.9/mulejrecordtest/src/main/resources/jrecord/MYSRS.jrecord.xml";
		
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		logger.info("IN  PARSE : TESTING");
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		

		AbstractLineReader reader = null;
		try {
			reader = getAbstractLineReaderForFileStream(stream, mappingFile);
			logger.debug("After getting the reader");
			AbstractLine fileRecord;

			logger.debug("creating list of SRS records");
			while ((fileRecord = reader.read()) != null) {
				MySrs srs = new MySrs();
				srs.setSrsRetirementCode(fileRecord.getFieldValue("retirementCode").asString());
				srs.setSsnOfTheRetiree(fileRecord.getFieldValue("SSNoftheRetiree").asString());
				srs.setSsnOfPersonReceivingBenefits(fileRecord.getFieldValue("SSNofPerson").asString());
				srs.setPayeeName(fileRecord.getFieldValue("PayeeName").asString());
				srs.setPoaName(fileRecord.getFieldValue("POAName").asString());
				srs.setGrossAmountofBenefit(fileRecord.getFieldValue("GrossAmountofBenefit").asString());
				srsSet.add(srs);
			}

		} catch (Exception ex) {
			logger.error("Error while parsing the records");
			ex.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (Exception ex) {
				logger.error("Unable to close the reader");
				logger.error(ex.getMessage());
			}

		}
		
		logger.error("SRS SET : " + srsSet.toString());
		
		try {
			
			System.out.println("WRITING MYSRS info:");
			System.out.println("------------------------------------------------------");
			System.out.println("------------------------------------------------------");
		
			BuyInTypeAOutboundWriter writer = new BuyInTypeAOutboundWriter();
			String srsFileOut   = "/Users/yreddy/AnypointStudio/workspace3.9/mulejrecordtest/src/main/resources/output/DTAR020out.bin";
			FileOutputStream myoutputStream = new FileOutputStream(srsFileOut);
	

			List<MySrs> mainList = new ArrayList<MySrs>();
			mainList.addAll(srsSet);
			writer.write(mainList, myoutputStream, srsFileOut);
			System.out.println("WRITING COMPLETED" );
			System.out.println("------------------------------------------------------");
			System.out.println("------------------------------------------------------");
			
		} catch (Exception ex) {
			logger.error("Error while writting the records");
			ex.printStackTrace();
		} finally {
			
		
		}
		
		return srsSet;
	}

	
	
	public Set<MySrs> parse(String fileName) throws CgisOFMParserException {
		Set<MySrs> srsSet = new HashSet<MySrs>();
		AbstractLineReader reader = null;
		try {
			reader = getAbstractLineReaderForFile(fileName);
			logger.debug("After getting the reader");
			AbstractLine fileRecord;

			logger.debug("creating list of SRS records");
			while ((fileRecord = reader.read()) != null) {
				MySrs srs = new MySrs();
				srs.setSrsRetirementCode(fileRecord.getFieldValue("retirementCode").asString());
				srs.setSsnOfTheRetiree(fileRecord.getFieldValue("SSNoftheRetiree").asString());
				srs.setSsnOfPersonReceivingBenefits(fileRecord.getFieldValue("SSNofPerson").asString());
				srs.setPayeeName(fileRecord.getFieldValue("PayeeName").asString());
				srs.setPoaName(fileRecord.getFieldValue("POAName").asString());
				srs.setGrossAmountofBenefit(fileRecord.getFieldValue("GrossAmountofBenefit").asString());
				srsSet.add(srs);
			}

		} catch (Exception ex) {
			logger.error("Error while parsing the records");
			ex.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (Exception ex) {
				logger.error("Unable to close the reader");
				logger.error(ex.getMessage());
			}

		}
		logger.error("SRS SET : " + srsSet.toString());
		
		
		return srsSet;
	}

	public static void main(String args[]) {
		System.out.println("In main....");
		try {
			//Parse the mainframe file and retrieve the data into the set of SRS objects
			//FileInputStream inputStream = new FileInputStream("/Users/yreddy/AnypointStudio/workspace3.9/JrecordTest/resource/input/MYSRS_YUG_TEST_DATA");
			String file = "/Users/yreddy/AnypointStudio/workspace3.9/mulejrecordtest/src/main/resources/input/MYSRS_YUG_TEST_DATA";
			FileInputStream inputStream = new FileInputStream(file);
			String srsFileOut   = "/Users/yreddy/AnypointStudio/workspace3.9/mulejrecordtest/src/main/resources/output/DTAR020out.bin";
			FileOutputStream myoutputStream = new FileOutputStream(srsFileOut);

			BuyInTypeAInboundParser srsParser = new BuyInTypeAInboundParser();
			int totalrecords = 0;
			Set<MySrs> mySrsSet = srsParser.parse(file);

			totalrecords = mySrsSet.size();
			System.out.println("Records size: " + totalrecords);
			System.out.println("MYSRS info:");
			System.out.println("------------------------------------------------------");
			System.out.println("------------------------------------------------------");
			System.out.println("" + mySrsSet.toString());
			System.out.println("------------------------------------------------------");
			System.out.println("------------------------------------------------------");
			
			/*
			//BuyInTypeAOutboundWriter writer = new BuyInTypeAOutboundWriter();
			System.out.println("WRITING MYSRS info:");
			System.out.println("------------------------------------------------------");
			System.out.println("------------------------------------------------------");
			//List<MySrs> mainList = new ArrayList<MySrs>();
			//mainList.addAll(mySrsSet);
			//writer.write(mainList, myoutputStream, srsFileOut);
			System.out.println("WRITING COMPLETED" );
			System.out.println("------------------------------------------------------");
			System.out.println("------------------------------------------------------");
			*/
		}
		catch(Exception ex) {
			System.out.println("Exception :: " + ex.getLocalizedMessage());
			ex.printStackTrace();
		}
		System.out.println("End of main....");
	}
}