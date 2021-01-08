package pagopa.gov.it.toolkit.reader;

public class DebtPositionCsvOutput {
	private String line;
	private DebtPositionCsv debtPositionCsv;
	private String iuv;
	private String noticeNumber;
	private String result;
	private String resultDescription;
	
	public DebtPositionCsvOutput(DebtPositionCsv debtPositionCsv, String iuv, String noticeNumber, String result, String resultDescription) {
		super();
		this.debtPositionCsv = debtPositionCsv;
		this.iuv = iuv;
		this.noticeNumber = noticeNumber;
		this.result = result;
		this.resultDescription = resultDescription;
	}
	
	public DebtPositionCsvOutput(String line, String iuv, String noticeNumber, String result, String resultDescription) {
		super();
		this.line = line;
		this.iuv = iuv;
		this.noticeNumber = noticeNumber;
		this.result = result;
		this.resultDescription = resultDescription;
	}
	
	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public DebtPositionCsv getDebtPositionCsv() {
		return debtPositionCsv;
	}
	
	public void setDebtPositionCsv(DebtPositionCsv debtPositionCsv) {
		this.debtPositionCsv = debtPositionCsv;
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
