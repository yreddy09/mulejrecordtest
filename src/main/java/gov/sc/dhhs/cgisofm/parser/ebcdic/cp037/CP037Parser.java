package gov.sc.dhhs.cgisofm.parser.ebcdic.cp037;

import net.sf.JRecord.JRecordInterface1;
import net.sf.JRecord.Common.Constants;
import net.sf.JRecord.Details.LayoutDetail;
import net.sf.JRecord.External.RecordEditorXmlLoader;
import net.sf.JRecord.IO.AbstractLineReader;
import net.sf.JRecord.IO.AbstractLineWriter;
import net.sf.JRecord.IO.LineIOProvider;
import net.sf.JRecord.Numeric.ICopybookDialects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public abstract class CP037Parser {
	private static final Logger logger = LoggerFactory.getLogger(CP037Parser.class);

	public CP037Parser() {
	}

	/**
	 * This method is used for reading the FB file format
	 */
	protected AbstractLineReader getAbstractLineReaderForFile(String file) throws Exception {
		String mappingFile = "/Users/yreddy/AnypointStudio/workspace3.9/mulejrecordtest/src/main/resources/jrecord/MYSRS.jrecord.xml";
		logger.info("Reading file {}", mappingFile);
		RecordEditorXmlLoader loader = new RecordEditorXmlLoader();
		// LayoutDetail layout = loader.loadCopyBook(getCopyBookXMLStream(),
		// "Wizard_Employer").asLayoutDetail();
		LayoutDetail layout = loader.loadCopyBook(mappingFile, 0, 0, "", 0, 0, null).asLayoutDetail();

		// For XML copybooks, get the file structure from layout
		int fileStructure = layout.getFileStructure();

		logger.info("fileStructure {}", fileStructure);
		// BinaryLineReader reader = new BinaryLineReader();
		//AbstractLineReader reader = LineIOProvider.getInstance().getLineReader(fileStructure);
		AbstractLineReader reader = JRecordInterface1.SCHEMA_XML.newIOBuilder(mappingFile).setDefaultFont("CP037").newReader(file);

		if (null == reader) {
			logger.info("reader is null!!!!!!");
			System.out.println("reader is null!!!!!!");
		}

		//reader.open(employerFileStream, layout);
		reader.open(file, layout);
		//reader.open(file);

		return reader;
	}

	/**
	 * This method is used for reading the FB fileStream format
	 */
	protected AbstractLineReader getAbstractLineReaderForFileStream(InputStream employerFileStream, String mappingFile) throws Exception {
		logger.info("Reading file {}", mappingFile);
		RecordEditorXmlLoader loader = new RecordEditorXmlLoader();
		LayoutDetail layout = loader.loadCopyBook(mappingFile, 0, 0, "", 0, 0, null).asLayoutDetail();

		// For XML copybooks, get the file structure from layout
		int fileStructure = layout.getFileStructure();

		logger.info("fileStructure {}", fileStructure);
		// LineIOProvider.getInstance().getLineReader(fileStructure);
		AbstractLineReader reader = JRecordInterface1.SCHEMA_XML.newIOBuilder(mappingFile).setDefaultFont("CP037").newReader(employerFileStream);

		if (null == reader) {
			logger.info("reader is null!!!!!!");
		}

		reader.open(employerFileStream, layout);


		return reader;
	}

	/**
	 * This method is used for reading the VB file format
	 */
	protected AbstractLineReader getAbstractLineReaderForVBFile(InputStream employerFileStream, String mappingFile)
			throws Exception {
		logger.info("Reading file {}", mappingFile);
		RecordEditorXmlLoader loader = new RecordEditorXmlLoader();
		LayoutDetail layout = loader.loadCopyBook(getCopyBookXMLStream(), "Wizard_Employer").asLayoutDetail();
		/* with XML copybooks, get the file structure from layout */
		int fileStructure = Constants.IO_VB;
		// int fileStructure = layout.getFileStructure();
		logger.info("fileStructure {}", fileStructure);
		// BinaryLineReader reader = new BinaryLineReader();
		AbstractLineReader reader = LineIOProvider.getInstance().getLineReader(fileStructure);
		if (null == reader) {
			logger.info("reader is null!!!!!!");
		}
		// AbstractLineWriter writer =
		// LineIOProvider.getInstance().getLineWriter(fileStructure);
		reader.open(employerFileStream, layout);
		// writer.open(salesFileOut);
		return reader;
	}

	public abstract InputStream getCopyBookXMLStream();

}