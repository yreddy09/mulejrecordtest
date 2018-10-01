package gov.sc.dhhs.cgisofm.writer;


@SuppressWarnings("serial")
public class CgisOFMWriterException extends Exception {
    public CgisOFMWriterException() {
    }

    public CgisOFMWriterException(String s) {
        super(s);
    }

    public CgisOFMWriterException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public CgisOFMWriterException(Throwable throwable) {
        super(throwable);
    }
}
