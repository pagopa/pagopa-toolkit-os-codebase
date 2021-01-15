package pagopa.gov.it.toolkit.reader.bean;

import java.math.BigDecimal;
import java.util.Date;

import pagopa.gov.it.toolkit.rptGenerator.enumeration.TipoBolloEnum;
import pagopa.gov.it.toolkit.rptGenerator.xsd.StTipoIdentificativoUnivocoPersFG;

public class CsvInputLine {
    private String textInputLine;

    // Iuv Generation
    private Integer domainAuxDigit;
    private Integer domainSegregationCode;
    private Integer domainApplicationCode;

    // Domain (Creditor Institution)
    private String domainFiscalCode;
    private String domainName;
    private String domainOperationalUnitCode;
    private String domainOperationalUnitName;
    private String domainAddress;
    private String domainNumberStreet;
    private String domainLocality;
    private String domainProvince;
    private String domainPostalCode;
    private String domainNation;
    private String domainSector;
    private String domainCbillCode;
    private String domainPostalAccountHolder;
    private String domainPostalAccountNumber;
    private String domainPostalAuthorizationCode;
    private String domainInfo;
    private String domainWebsite;
    private byte[] domainLogo;

    // Payer
    private String payerUniqueIdentificationCode;
    private StTipoIdentificativoUnivocoPersFG payerUniqueIdentificationType;
    private String payerRegistry;
    private String payerAddress;
    private String payerNumberStreet;
    private String payerLocality;
    private String payerProvince;
    private String payerPostalCode;
    private String payerNation;
    private String payerEmail;
    private String payerMobile;

    // Payment Detail
    private String tenantId;
    private BigDecimal totalAmountPayment;
    private String causal;
    private Date expirationDate;
    private String specificCollectionData;
    private String documentNumber;
    private Integer installmentNumber;
    private String debitIban;
    private String debitBic;
    private String causalDescriptionSinglePayment;
    private String creditIban;
    private String creditBic;
    private String supportIban;
    private String supportBic;
    private TipoBolloEnum tipoBollo;
    private String documentHash;
    private String residenceProvince;
    private Boolean isModello1Or2;

    private String iuv;
    private String noticeNumber;

    public CsvInputLine() {
        super();
    }

    public String getPayerUniqueIdentificationCode() {
        return payerUniqueIdentificationCode;
    }

    public void setPayerUniqueIdentificationCode(String payerUniqueIdentificationCode) {
        this.payerUniqueIdentificationCode = payerUniqueIdentificationCode;
    }

    public StTipoIdentificativoUnivocoPersFG getPayerUniqueIdentificationType() {
        return payerUniqueIdentificationType;
    }

    public void setPayerUniqueIdentificationType(StTipoIdentificativoUnivocoPersFG payerUniqueIdentificationType) {
        this.payerUniqueIdentificationType = payerUniqueIdentificationType;
    }

    public String getPayerRegistry() {
        return payerRegistry;
    }

    public void setPayerRegistry(String payerRegistry) {
        this.payerRegistry = payerRegistry;
    }

    public String getPayerAddress() {
        return payerAddress;
    }

    public void setPayerAddress(String payerAddress) {
        this.payerAddress = payerAddress;
    }

    public String getPayerNumberStreet() {
        return payerNumberStreet;
    }

    public void setPayerNumberStreet(String payerNumberStreet) {
        this.payerNumberStreet = payerNumberStreet;
    }

    public String getPayerLocality() {
        return payerLocality;
    }

    public void setPayerLocality(String payerLocality) {
        this.payerLocality = payerLocality;
    }

    public String getPayerProvince() {
        return payerProvince;
    }

    public void setPayerProvince(String payerProvince) {
        this.payerProvince = payerProvince;
    }

    public String getPayerNation() {
        return payerNation;
    }

    public void setPayerNation(String payerNation) {
        this.payerNation = payerNation;
    }

    public String getPayerPostalCode() {
        return payerPostalCode;
    }

    public void setPayerPostalCode(String payerPostalCode) {
        this.payerPostalCode = payerPostalCode;
    }

    public String getPayerEmail() {
        return payerEmail;
    }

    public void setPayerEmail(String payerEmail) {
        this.payerEmail = payerEmail;
    }

    public String getPayerMobile() {
        return payerMobile;
    }

    public void setPayerMobile(String payerMobile) {
        this.payerMobile = payerMobile;
    }

    public String getDomainFiscalCode() {
        return domainFiscalCode;
    }

    public void setDomainFiscalCode(String domainFiscalCode) {
        this.domainFiscalCode = domainFiscalCode;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getDomainOperationalUnitCode() {
        return domainOperationalUnitCode;
    }

    public void setDomainOperationalUnitCode(String domainOperationalUnitCode) {
        this.domainOperationalUnitCode = domainOperationalUnitCode;
    }

    public String getDomainOperationalUnitName() {
        return domainOperationalUnitName;
    }

    public void setDomainOperationalUnitName(String domainOperationalUnitName) {
        this.domainOperationalUnitName = domainOperationalUnitName;
    }

    public String getDomainAddress() {
        return domainAddress;
    }

    public void setDomainAddress(String domainAddress) {
        this.domainAddress = domainAddress;
    }

    public String getDomainNumberStreet() {
        return domainNumberStreet;
    }

    public void setDomainNumberStreet(String domainNumberStreet) {
        this.domainNumberStreet = domainNumberStreet;
    }

    public String getDomainPostalCode() {
        return domainPostalCode;
    }

    public void setDomainPostalCode(String domainPostalCode) {
        this.domainPostalCode = domainPostalCode;
    }

    public String getDomainLocality() {
        return domainLocality;
    }

    public void setDomainLocality(String domainLocality) {
        this.domainLocality = domainLocality;
    }

    public String getDomainProvince() {
        return domainProvince;
    }

    public void setDomainProvince(String domainProvince) {
        this.domainProvince = domainProvince;
    }

    public String getDomainNation() {
        return domainNation;
    }

    public void setDomainNation(String domainNation) {
        this.domainNation = domainNation;
    }

    public String getDomainSector() {
        return domainSector;
    }

    public void setDomainSector(String domainSector) {
        this.domainSector = domainSector;
    }

    public String getDomainCbillCode() {
        return domainCbillCode;
    }

    public void setDomainCbillCode(String domainCbillCode) {
        this.domainCbillCode = domainCbillCode;
    }

    public String getDomainPostalAccountHolder() {
        return domainPostalAccountHolder;
    }

    public void setDomainPostalAccountHolder(String domainPostalAccountHolder) {
        this.domainPostalAccountHolder = domainPostalAccountHolder;
    }

    public String getDomainPostalAccountNumber() {
        return domainPostalAccountNumber;
    }

    public void setDomainPostalAccountNumber(String domainPostalAccountNumber) {
        this.domainPostalAccountNumber = domainPostalAccountNumber;
    }

    public String getDomainPostalAuthorizationCode() {
        return domainPostalAuthorizationCode;
    }

    public void setDomainPostalAuthorizationCode(String domainPostalAuthorizationCode) {
        this.domainPostalAuthorizationCode = domainPostalAuthorizationCode;
    }

    public Integer getDomainAuxDigit() {
        return domainAuxDigit;
    }

    public void setDomainAuxDigit(Integer domainAuxDigit) {
        this.domainAuxDigit = domainAuxDigit;
    }

    public Integer getDomainSegregationCode() {
        return domainSegregationCode;
    }

    public void setDomainSegregationCode(Integer domainSegregationCode) {
        this.domainSegregationCode = domainSegregationCode;
    }

    public Integer getDomainApplicationCode() {
        return domainApplicationCode;
    }

    public void setDomainApplicationCode(Integer domainApplicationCode) {
        this.domainApplicationCode = domainApplicationCode;
    }

    public String getDomainInfo() {
        return domainInfo;
    }

    public void setDomainInfo(String domainInfo) {
        this.domainInfo = domainInfo;
    }

    public String getDomainWebsite() {
        return domainWebsite;
    }

    public void setDomainWebsite(String domainWebsite) {
        this.domainWebsite = domainWebsite;
    }

    public byte[] getDomainLogo() {
        return domainLogo;
    }

    public void setDomainLogo(byte[] domainLogo) {
        this.domainLogo = domainLogo;
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

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public BigDecimal getTotalAmountPayment() {
        return totalAmountPayment;
    }

    public void setTotalAmountPayment(BigDecimal totalAmountPayment) {
        this.totalAmountPayment = totalAmountPayment;
    }

    public String getCausal() {
        return causal;
    }

    public void setCausal(String causal) {
        this.causal = causal;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getSpecificCollectionData() {
        return specificCollectionData;
    }

    public void setSpecificCollectionData(String specificCollectionData) {
        this.specificCollectionData = specificCollectionData;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Integer getInstallmentNumber() {
        return installmentNumber;
    }

    public void setInstallmentNumber(Integer installmentNumber) {
        this.installmentNumber = installmentNumber;
    }

    public String getDebitIban() {
        return debitIban;
    }

    public void setDebitIban(String debitIban) {
        this.debitIban = debitIban;
    }

    public String getDebitBic() {
        return debitBic;
    }

    public void setDebitBic(String debitBic) {
        this.debitBic = debitBic;
    }

    public String getCausalDescriptionSinglePayment() {
        return causalDescriptionSinglePayment;
    }

    public void setCausalDescriptionSinglePayment(String causalDescriptionSinglePayment) {
        this.causalDescriptionSinglePayment = causalDescriptionSinglePayment;
    }

    public String getCreditIban() {
        return creditIban;
    }

    public void setCreditIban(String creditIban) {
        this.creditIban = creditIban;
    }

    public String getCreditBic() {
        return creditBic;
    }

    public void setCreditBic(String creditBic) {
        this.creditBic = creditBic;
    }

    public String getSupportIban() {
        return supportIban;
    }

    public void setSupportIban(String supportIban) {
        this.supportIban = supportIban;
    }

    public String getSupportBic() {
        return supportBic;
    }

    public void setSupportBic(String supportBic) {
        this.supportBic = supportBic;
    }

    public TipoBolloEnum getTipoBollo() {
        return tipoBollo;
    }

    public void setTipoBollo(TipoBolloEnum tipoBollo) {
        this.tipoBollo = tipoBollo;
    }

    public String getDocumentHash() {
        return documentHash;
    }

    public void setDocumentHash(String documentHash) {
        this.documentHash = documentHash;
    }

    public String getResidenceProvince() {
        return residenceProvince;
    }

    public void setResidenceProvince(String residenceProvince) {
        this.residenceProvince = residenceProvince;
    }

    public Boolean getIsModello1Or2() {
        return isModello1Or2;
    }

    public void setIsModello1Or2(Boolean isModello1Or2) {
        this.isModello1Or2 = isModello1Or2;
    }

    public String getTextInputLine() {
        return textInputLine;
    }

    public void setTextInputLine(String textInputLine) {
        this.textInputLine = textInputLine;
    }
}
