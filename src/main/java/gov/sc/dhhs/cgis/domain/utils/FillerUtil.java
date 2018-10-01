package gov.sc.dhhs.cgis.domain.utils;


public class FillerUtil {

    private final static int MAXFILL = 100;
    private final static String SPACE = " ";
    private static final StringBuffer filler = fillBuffer(MAXFILL);

    private static StringBuffer fillBuffer(int fillMax) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < fillMax; i++) {
            if (i == 0) {
                sb.append("");
            } else {
                sb.append(SPACE);
            }
        }
        return sb;  //To change body of created methods use File | Settings | File Templates.
    }

    public static String getFillSpace(int value) {
        if (MAXFILL <= value){
            throw new IllegalArgumentException("Fill value cannot be greater than "+(MAXFILL-1));
        }
        return filler.substring(0,value);
    }

}
