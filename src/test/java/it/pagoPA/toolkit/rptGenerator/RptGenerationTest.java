package it.pagoPA.toolkit.rptGenerator;

import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.pagoPA.toolkit.common.bean.DatiMarcaBolloDigitale;
import it.pagoPA.toolkit.debtPositionGenerator.DebtPositionGeneration;
import it.pagoPA.toolkit.debtPositionGenerator.bean.DebtPosition;
import it.pagoPA.toolkit.debtPositionGenerator.bean.debtPosition.DPPayer;
import it.pagoPA.toolkit.debtPositionGenerator.bean.debtPosition.DPPaymentDetail;
import it.pagoPA.toolkit.debtPositionGenerator.bean.debtPosition.DPSinglePaymentDetail;
import it.pagoPA.toolkit.rptGenerator.bean.RptContainer;
import it.pagoPA.toolkit.rptGenerator.bean.rpt.Rpt;
import it.pagoPA.toolkit.rptGenerator.bean.rpt.RptDatiSingoloVersamento;
import it.pagoPA.toolkit.rptGenerator.bean.rpt.RptDatiVersamento;
import it.pagoPA.toolkit.rptGenerator.bean.rpt.RptDominio;
import it.pagoPA.toolkit.rptGenerator.bean.rpt.RptEnteBeneficiario;
import it.pagoPA.toolkit.rptGenerator.bean.rpt.RptSoggetto;
import it.pagoPA.toolkit.rptGenerator.enumeration.FirmaRicevutaEnum;
import it.pagoPA.toolkit.rptGenerator.enumeration.TipoBolloEnum;
import it.pagoPA.toolkit.rptGenerator.xsd.StAutenticazioneSoggetto;
import it.pagoPA.toolkit.rptGenerator.xsd.StTipoIdentificativoUnivocoPersFG;
import it.pagoPA.toolkit.rptGenerator.xsd.StTipoIdentificativoUnivocoPersG;
import it.pagoPA.toolkit.rptGenerator.xsd.StTipoVersamento;

public class RptGenerationTest {

    String identificativoDominio = "01234567890";
    String identificativoStazioneRichiedente = "idStazioneRichiedente";

    StTipoIdentificativoUnivocoPersFG tipoIdentificativoUnivocoVersante = StTipoIdentificativoUnivocoPersFG.F;
    String codiceIdentificativoUnivocoVersante = "VRDGPP82B02H501A";
    String anagraficaVersante = "VERDI GIUSEPPE";
    String indirizzoVersante = "Via Firenze";
    String civicoVersante = "456";
    String capVersante = "00100";
    String localitaVersante = "Roma";
    String provinciaVersante = "RM";
    String nazioneVersante = "IT";
    String emailVersante = "giuseppe.verdi@gmail.it";

    StTipoIdentificativoUnivocoPersFG tipoIdentificativoUnivocoPagatore = StTipoIdentificativoUnivocoPersFG.F;
    String codiceIdentificativoUnivocoPagatore = "RSSMRA80A01F205X";
    String anagraficaPagatore = "ROSSI MARIO";
    String indirizzoPagatore = "Via Roma";
    String civicoPagatore = "123";
    String capPagatore = "50127";
    String localitaPagatore = "Firenze";
    String provinciaPagatore = "FI";
    String nazionePagatore = "IT";
    String emailPagatore = "mario.rossi@gmail.it";

    StTipoIdentificativoUnivocoPersG tipoIdentificativoUnivocoEnteBeneficiario = StTipoIdentificativoUnivocoPersG.G;
    String codiceIdentificativoUnivocoEnteBeneficiario = "09876543210";
    String denominazioneEnteBeneficiario = "Ente Beneficiario";
    String codiceUnitOperEnteBeneficiario = "codiceUnitOper";
    String denomUnitOperEnteBeneficiario = "DenomUnitOper";
    String indirizzoEnteBeneficiario = "Via Torino";
    String civicoEnteBeneficiario = "789";
    String capEnteBeneficiario = "20100";
    String localitaEnteBeneficiario = "Milano";
    String provinciaEnteBeneficiario = "MI";
    String nazioneEnteBeneficiario = "IT";

    BigDecimal importoTotaleDaVersare = BigDecimal.valueOf(33.33);
    StTipoVersamento tipoVersamento = StTipoVersamento.CP;
    String identificativoUnivocoVersamento = "01234567890123456";
    String ibanAddebito = "IT58C0306914512000000046601";
    String bicAddebito = "BCITITMMXXX";
    FirmaRicevutaEnum firmaRicevuta = FirmaRicevutaEnum.NON_RICHIESTA;

    BigDecimal importoSingoloVersamento1 = BigDecimal.valueOf(11.11);
    BigDecimal importoSingoloVersamento2 = BigDecimal.valueOf(22.22);
    BigDecimal commissioneCaricoPA = BigDecimal.valueOf(1.50);
    String ibanAccredito = "IT73W0306953740100000300003";
    String bicAccredito = "BCITITMMXXX";
    String ibanAppoggio = "IT33I0760114500000041940305";
    String bicAppoggio = "BPPIITRRXXX";
    String credenzialiPagatore = "user:password";
    String iuv = "01234567890123456";
    String descrizioneCausaleVersamento1 = "pagamento Test 1";
    String descrizioneCausaleVersamento2 = "pagamento Test 2";
    String datiSpecificiRiscossione = "9/test";
    Integer ordineVersamento1 = 2;
    Integer ordineVersamento2 = 1;

    TipoBolloEnum tipoBollo = TipoBolloEnum.IMPOSTA_DI_BOLLO;
    String hashDocumento = "aGFzaENvZGUgRG9jdW1lbnRvIE1hcmRhIGRhIEJvbGxv";
    String provinciaResidenza = "FI";

    String versioneOggetto = "1.0";
    StAutenticazioneSoggetto autenticazioneSoggetto = StAutenticazioneSoggetto.N_A;

    String idTenant = "idTenant00001";

    int auxDigit = 3;
    Integer segregationCode = 01;
    String causal = "causale test";
    Date expirationDate = new GregorianCalendar(2222, Calendar.OCTOBER, 03).getTime();

    /**
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {

    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testGenerateAllData() throws Exception {
        RptDominio dominio = RptGeneration.generateDominio(identificativoDominio, identificativoStazioneRichiedente);
        RptSoggetto soggettoVersante = RptGeneration.generateSoggetto(
                RptGeneration.generateIdentificativoUnivocoFG(tipoIdentificativoUnivocoVersante,
                        codiceIdentificativoUnivocoVersante),
                anagraficaVersante, RptGeneration.generateIndirizzo(indirizzoVersante, civicoVersante, capVersante,
                        localitaVersante, provinciaVersante, nazioneVersante),
                emailVersante);
        RptSoggetto soggettoPagatore = RptGeneration.generateSoggetto(
                RptGeneration.generateIdentificativoUnivocoFG(tipoIdentificativoUnivocoPagatore,
                        codiceIdentificativoUnivocoPagatore),
                anagraficaPagatore, RptGeneration.generateIndirizzo(indirizzoPagatore, civicoPagatore, capPagatore,
                        localitaPagatore, provinciaPagatore, nazionePagatore),
                emailPagatore);
        RptEnteBeneficiario enteBeneficiario = RptGeneration.generateEnteBeneficiario(
                RptGeneration.generateIdentificativoUnivocoG(tipoIdentificativoUnivocoEnteBeneficiario,
                        codiceIdentificativoUnivocoEnteBeneficiario),
                denominazioneEnteBeneficiario, codiceUnitOperEnteBeneficiario, denomUnitOperEnteBeneficiario,
                RptGeneration.generateIndirizzo(indirizzoEnteBeneficiario, civicoEnteBeneficiario, capEnteBeneficiario,
                        localitaEnteBeneficiario, provinciaEnteBeneficiario, nazioneEnteBeneficiario));
        RptDatiVersamento datiVersamento = RptGeneration.generateDatiVersamento(importoTotaleDaVersare, tipoVersamento,
                identificativoUnivocoVersamento, ibanAddebito, bicAddebito, firmaRicevuta);
        List<RptDatiSingoloVersamento> datiSingoloVersamentoList = new ArrayList<RptDatiSingoloVersamento>();
        datiSingoloVersamentoList.add(RptGeneration.generateDatiSingoloVersamento(importoSingoloVersamento1,
                commissioneCaricoPA, ibanAccredito, bicAccredito, ibanAppoggio, bicAppoggio, credenzialiPagatore,
                descrizioneCausaleVersamento1, iuv, datiSpecificiRiscossione, null, ordineVersamento1));
        datiSingoloVersamentoList.add(RptGeneration.generateDatiSingoloVersamento(importoSingoloVersamento2,
                commissioneCaricoPA, null, null, ibanAppoggio, bicAppoggio, credenzialiPagatore,
                descrizioneCausaleVersamento2, iuv, datiSpecificiRiscossione,
                RptGeneration.generateDatiMarcaBolloDigitale(tipoBollo, hashDocumento, provinciaResidenza),
                ordineVersamento2));
        Rpt rpt = RptGeneration.generateRptElement(versioneOggetto, dominio, autenticazioneSoggetto, soggettoVersante,
                soggettoPagatore, enteBeneficiario, datiVersamento, datiSingoloVersamentoList);
        RptContainer rptContainer = RptGeneration.generate(idTenant, rpt);
        assertNotNull(rptContainer);
        createRptXmlFile(rptContainer, "src/test/resources/RPT_AllData.txt");
        assertNotNull(rptContainer.getRptXml());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testGenerateMinimumData() throws Exception {
        RptDominio dominio = RptGeneration.generateDominio(identificativoDominio, null);
        RptSoggetto soggettoPagatore = RptGeneration.generateSoggetto(
                RptGeneration.generateIdentificativoUnivocoFG(null, null), anagraficaPagatore, null, null);
        RptEnteBeneficiario enteBeneficiario = RptGeneration
                .generateEnteBeneficiario(
                        RptGeneration.generateIdentificativoUnivocoG(tipoIdentificativoUnivocoEnteBeneficiario,
                                codiceIdentificativoUnivocoEnteBeneficiario),
                        denominazioneEnteBeneficiario, null, null, null);
        RptDatiVersamento datiVersamento = RptGeneration.generateDatiVersamento(importoTotaleDaVersare, tipoVersamento,
                identificativoUnivocoVersamento, null, null, firmaRicevuta);
        List<RptDatiSingoloVersamento> datiSingoloVersamentoList = new ArrayList<RptDatiSingoloVersamento>();
        datiSingoloVersamentoList.add(RptGeneration.generateDatiSingoloVersamento(importoSingoloVersamento1, null,
                ibanAccredito, null, null, null, null, null, iuv, datiSpecificiRiscossione, null, ordineVersamento1));
        datiSingoloVersamentoList.add(RptGeneration.generateDatiSingoloVersamento(importoSingoloVersamento2, null, null,
                null, null, null, null, null, iuv, datiSpecificiRiscossione,
                RptGeneration.generateDatiMarcaBolloDigitale(tipoBollo, hashDocumento, provinciaResidenza),
                ordineVersamento2));
        Rpt rpt = RptGeneration.generateRptElement(versioneOggetto, dominio, autenticazioneSoggetto, null,
                soggettoPagatore, enteBeneficiario, datiVersamento, datiSingoloVersamentoList);
        RptContainer rptContainer = RptGeneration.generate(idTenant, rpt);
        assertNotNull(rptContainer);
        createRptXmlFile(rptContainer, "src/test/resources/RPT_MinimumData.txt");
        assertNotNull(rptContainer.getRptXml());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testGenerateFromDebtPosition() throws Exception {
        DPPayer payer = DebtPositionGeneration.generatePayer(codiceIdentificativoUnivocoPagatore,
                tipoIdentificativoUnivocoPagatore, anagraficaPagatore, indirizzoPagatore, civicoPagatore,
                localitaPagatore, provinciaPagatore, nazionePagatore, capPagatore, emailPagatore, null);
        DPPaymentDetail paymentDetail = DebtPositionGeneration.generatePaymentDetail(identificativoDominio, auxDigit,
                segregationCode, null, null, idTenant, importoTotaleDaVersare, causal, expirationDate,
                datiSpecificiRiscossione, null, null, ibanAddebito, bicAddebito);
        DatiMarcaBolloDigitale datiMarcaBolloDigitale = DebtPositionGeneration.generateDatiMarcaBolloDigitale(tipoBollo,
                hashDocumento, provinciaResidenza);
        List<DPSinglePaymentDetail> singlePaymentDetailList = new LinkedList<DPSinglePaymentDetail>();
        singlePaymentDetailList
                .add(DebtPositionGeneration.generateSinglePaymentsDetail(importoSingoloVersamento1, ordineVersamento1,
                        descrizioneCausaleVersamento1, ibanAccredito, bicAccredito, ibanAppoggio, bicAppoggio, null));
        singlePaymentDetailList.add(DebtPositionGeneration.generateSinglePaymentsDetail(importoSingoloVersamento2,
                ordineVersamento2, descrizioneCausaleVersamento2, null, null, null, null, datiMarcaBolloDigitale));
        DebtPosition debtPosition = DebtPositionGeneration.generate(payer, paymentDetail, singlePaymentDetailList);
        RptEnteBeneficiario enteBeneficiario = RptGeneration.generateEnteBeneficiario(
                RptGeneration.generateIdentificativoUnivocoG(tipoIdentificativoUnivocoEnteBeneficiario,
                        codiceIdentificativoUnivocoEnteBeneficiario),
                denominazioneEnteBeneficiario, codiceUnitOperEnteBeneficiario, denomUnitOperEnteBeneficiario,
                RptGeneration.generateIndirizzo(indirizzoEnteBeneficiario, civicoEnteBeneficiario, capEnteBeneficiario,
                        localitaEnteBeneficiario, provinciaEnteBeneficiario, nazioneEnteBeneficiario));
        RptContainer rptContainer = RptGeneration.generate(idTenant, debtPosition, enteBeneficiario,
                commissioneCaricoPA);
        assertNotNull(rptContainer);
        createRptXmlFile(rptContainer, "src/test/resources/RPT_FromDebtPosition.txt");
        assertNotNull(rptContainer.getRptXml());
    }

    /**
     * 
     * @param rptContainer
     * @param filePath
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void createRptXmlFile(RptContainer rptContainer, String filePath)
            throws FileNotFoundException, IOException {
        OutputStream out = new FileOutputStream(filePath);
        out.write(rptContainer.getRptXml());
        out.close();
    }
}
