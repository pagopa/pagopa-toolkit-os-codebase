package pagopa.gov.it.toolkit.reader.bean;

/**
 * CSV output line Bean
 */
public class CsvOutputLine {

    private String textInputLine;
    private CsvInputLine csvInputLine;
    private String iuv;
    private String noticeNumber;
    private String result;
    private String resultDescription;

    /**
     * Public constructor
     * 
     * @param textInputLine
     *            input line in text format
     * @param csvInputLine
     *            bean instance of input line
     * @param iuv
     * @param noticeNumber
     * @param result
     *            outcome of the operation ("OK" or "KO")
     * @param resultDescription
     *            description of any error
     * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPaymentDetail
     */
    public CsvOutputLine(String textInputLine, CsvInputLine csvInputLine, String iuv, String noticeNumber,
            String result, String resultDescription) {
        super();
        this.textInputLine = textInputLine;
        this.csvInputLine = csvInputLine;
        this.iuv = iuv;
        this.noticeNumber = noticeNumber;
        this.result = result;
        this.resultDescription = resultDescription;
    }

    public String getTextInputLine() {
        return textInputLine;
    }

    public void setTextInputLine(String textInputLine) {
        this.textInputLine = textInputLine;
    }

    public CsvInputLine getCsvInputLine() {
        return csvInputLine;
    }

    public void setDebtPositionCsv(CsvInputLine csvInputLine) {
        this.csvInputLine = csvInputLine;
    }

    public String getIuv() {
        return iuv;
    }

    public void setIuv(String iuv) {
        this.iuv = iuv;
    }

    public String getNoticeNumber() {
        return noticeNumber;
    }

    public void setNoticeNumber(String noticeNumber) {
        this.noticeNumber = noticeNumber;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultDescription() {
        return resultDescription;
    }

    public void setResultDescription(String resultDescription) {
        this.resultDescription = resultDescription;
    }
}
