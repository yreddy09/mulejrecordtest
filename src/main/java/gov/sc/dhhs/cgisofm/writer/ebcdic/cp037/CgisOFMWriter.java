package gov.sc.dhhs.cgisofm.writer.ebcdic.cp037;

import java.io.OutputStream;
import java.util.List;

import gov.sc.dhhs.cgisofm.writer.CgisOFMWriterException;

public interface CgisOFMWriter<T> {

    public void writetoFile(List<T> domainObjects, String fileNamePath) throws CgisOFMWriterException;
    public void write(List<T> domainObjects, OutputStream outputStream) throws CgisOFMWriterException;
}
