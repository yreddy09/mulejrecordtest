package gov.sc.dhhs.cgisofm.parser.ebcdic.cp037;


@SuppressWarnings("serial")
public class CgisOFMParserException extends Exception{

    public CgisOFMParserException() {
    }

    public CgisOFMParserException(String s) {
        super(s);
    }


    public CgisOFMParserException(String s, Throwable throwable, ExceptionType exceptionType) {
        super(s, throwable);
        this.exceptionType = exceptionType;
    }


    public CgisOFMParserException(Throwable throwable) {
        super(throwable);
    }

    public Object getValidLoadList() {
        return validLoadList;
    }

    public void setValidLoadList(Object validLoadList) {
        this.validLoadList = validLoadList;
    }
    private ExceptionType exceptionType;

    public ExceptionType getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    public enum ExceptionType{
        PARSER,SYSTEM
    }
    
    private Object validLoadList;


}
