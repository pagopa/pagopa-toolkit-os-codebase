package pagopa.gov.it.toolkit.reader.bean;

public class CsvOutputLine {

    private String textInputLine;
    private CsvInputLine csvInputLine;
    private String iuv;
    private String noticeNumber;
    private String result;
    private String resultDescription;

    public CsvOutputLine(String textInputLine, CsvInputLine debtPositionCsv, String iuv, String noticeNumber,
            String result, String resultDescription) {
        super();
        this.textInputLine = textInputLine;
        this.csvInputLine = debtPositionCsv;
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
