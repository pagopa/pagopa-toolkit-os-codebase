package it.pagoPA.toolkit.rptGenerator.exception;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.pagoPA.toolkit.rptGenerator.RptGeneration;
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

/**
 *
 */
public class ValidationExceptionTest {

    RptDominio dominio;
    RptSoggetto soggettoVersante;
    RptSoggetto soggettoPagatore;
    RptEnteBeneficiario enteBeneficiario;
    RptDatiVersamento datiVersamento;
    List<RptDatiSingoloVersamento> datiSingoloVersamentoList = new ArrayList<RptDatiSingoloVersamento>();

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
    StTipoVersamento tipoVersamento = StTipoVersamento.AD;
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
    Integer ordineVersamento1 = 1;
    Integer ordineVersamento2 = 2;

    TipoBolloEnum tipoBollo = TipoBolloEnum.IMPOSTA_DI_BOLLO;
    String hashDocumento = "aGFzaENvZGUgRG9jdW1lbnRvIE1hcmRhIGRhIEJvbGxv";
    String provinciaResidenza = "FI";

    String versioneOggetto = "1.0";
    StAutenticazioneSoggetto autenticazioneSoggetto = StAutenticazioneSoggetto.N_A;

    String idTenant = "idTenant00001";

    String invalid_codiceIdentificativoUnivocoPagatore = "12312312312";
    BigDecimal invalid_importoTotaleDaVersare = BigDecimal.ONE;

    /**
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        dominio = RptGeneration.generateDominio(identificativoDominio, identificativoStazioneRichiedente);
        soggettoVersante = RptGeneration.generateSoggetto(
                RptGeneration.generateIdentificativoUnivocoFG(tipoIdentificativoUnivocoVersante,
                        codiceIdentificativoUnivocoVersante),
                anagraficaVersante, RptGeneration.generateIndirizzo(indirizzoVersante, civicoVersante, capVersante,
                        localitaVersante, provinciaVersante, nazioneVersante),
                emailVersante);
        soggettoPagatore = RptGeneration.generateSoggetto(
                RptGeneration.generateIdentificativoUnivocoFG(tipoIdentificativoUnivocoPagatore,
                        codiceIdentificativoUnivocoPagatore),
                anagraficaPagatore, RptGeneration.generateIndirizzo(indirizzoPagatore, civicoPagatore, capPagatore,
                        localitaPagatore, provinciaPagatore, nazionePagatore),
                emailPagatore);
        enteBeneficiario = RptGeneration.generateEnteBeneficiario(
                RptGeneration.generateIdentificativoUnivocoG(tipoIdentificativoUnivocoEnteBeneficiario,
                        codiceIdentificativoUnivocoEnteBeneficiario),
                denominazioneEnteBeneficiario, codiceUnitOperEnteBeneficiario, denomUnitOperEnteBeneficiario,
                RptGeneration.generateIndirizzo(indirizzoEnteBeneficiario, civicoEnteBeneficiario, capEnteBeneficiario,
                        localitaEnteBeneficiario, provinciaEnteBeneficiario, nazioneEnteBeneficiario));
        datiVersamento = RptGeneration.generateDatiVersamento(importoTotaleDaVersare, tipoVersamento,
                identificativoUnivocoVersamento, ibanAddebito, bicAddebito, firmaRicevuta);
        datiSingoloVersamentoList.add(RptGeneration.generateDatiSingoloVersamento(importoSingoloVersamento1,
                commissioneCaricoPA, ibanAccredito, bicAccredito, ibanAppoggio, bicAppoggio, credenzialiPagatore,
                descrizioneCausaleVersamento1, iuv, datiSpecificiRiscossione, null, ordineVersamento1));
        datiSingoloVersamentoList.add(RptGeneration.generateDatiSingoloVersamento(importoSingoloVersamento2,
                commissioneCaricoPA, null, null, ibanAppoggio, bicAppoggio, credenzialiPagatore,
                descrizioneCausaleVersamento2, iuv, datiSpecificiRiscossione,
                RptGeneration.generateDatiMarcaBolloDigitale(tipoBollo, hashDocumento, provinciaResidenza),
                ordineVersamento2));
    }

    /**
     * 
     * @throws Exception
     */
    @Test(expected = ValidationException.class)
    public void testValidate() throws Exception {
        RptDominio invalid_Dominio = RptGeneration.generateDominio(null, identificativoStazioneRichiedente);
        Rpt rpt = RptGeneration.generateRptElement(versioneOggetto, invalid_Dominio, autenticazioneSoggetto,
                soggettoVersante, soggettoPagatore, enteBeneficiario, datiVersamento, datiSingoloVersamentoList);
        RptGeneration.generate(idTenant, rpt);
    }

    /**
     * 
     * @throws Exception
     */
    @Test(expected = ValidationException.class)
    public void testInvalidSoggettoUniqueIdentificationType() throws Exception {
        RptSoggetto invalid_SoggettoPagatore = RptGeneration
                .generateSoggetto(RptGeneration.generateIdentificativoUnivocoFG(tipoIdentificativoUnivocoPagatore,
                        invalid_codiceIdentificativoUnivocoPagatore), anagraficaPagatore, null, null);
        Rpt rpt = RptGeneration.generateRptElement(versioneOggetto, dominio, autenticazioneSoggetto, null,
                invalid_SoggettoPagatore, enteBeneficiario, datiVersamento, datiSingoloVersamentoList);
        RptGeneration.generate(idTenant, rpt);
    }

    /**
     * 
     * @throws Exception
     */
    @Test(expected = ValidationException.class)
    public void testInvalidIbanAddebito() throws Exception {
        RptDatiVersamento invalid_datiVersamento = RptGeneration.generateDatiVersamento(importoTotaleDaVersare,
                tipoVersamento, identificativoUnivocoVersamento, null, bicAddebito, firmaRicevuta);
        Rpt rpt = RptGeneration.generateRptElement(versioneOggetto, dominio, autenticazioneSoggetto, null,
                soggettoPagatore, enteBeneficiario, invalid_datiVersamento, datiSingoloVersamentoList);
        RptGeneration.generate(idTenant, rpt);
    }

    /**
     * 
     * @throws Exception
     */
    @Test(expected = ValidationException.class)
    public void testInvalidSinglePaymentsDetailList() throws Exception {
        List<RptDatiSingoloVersamento> invalid_datiSingoloVersamentoList = new ArrayList<RptDatiSingoloVersamento>();
        RptDatiSingoloVersamento datiSingoloVersamento = RptGeneration.generateDatiSingoloVersamento(
                importoSingoloVersamento1, commissioneCaricoPA, ibanAccredito, bicAccredito, ibanAppoggio, bicAppoggio,
                credenzialiPagatore, descrizioneCausaleVersamento1, iuv, datiSpecificiRiscossione, null,
                ordineVersamento1);
        for (int i = 0; i < 10; i++) {
            invalid_datiSingoloVersamentoList.add(datiSingoloVersamento);
        }
        Rpt rpt = RptGeneration.generateRptElement(versioneOggetto, dominio, autenticazioneSoggetto, null,
                soggettoPagatore, enteBeneficiario, datiVersamento, invalid_datiSingoloVersamentoList);
        RptGeneration.generate(idTenant, rpt);
    }

    /**
     * 
     * @throws Exception
     */
    @Test(expected = ValidationException.class)
    public void testInvalidAmounts() throws Exception {
        RptDatiVersamento invalid_datiVersamento = RptGeneration.generateDatiVersamento(invalid_importoTotaleDaVersare,
                tipoVersamento, identificativoUnivocoVersamento, ibanAddebito, bicAddebito, firmaRicevuta);
        Rpt rpt = RptGeneration.generateRptElement(versioneOggetto, dominio, autenticazioneSoggetto, null,
                soggettoPagatore, enteBeneficiario, invalid_datiVersamento, datiSingoloVersamentoList);
        RptGeneration.generate(idTenant, rpt);
    }

    /**
     * 
     * @throws Exception
     */
    @Test(expected = ValidationException.class)
    public void testInvalidIbanAccredito() throws Exception {
        List<RptDatiSingoloVersamento> invalid_datiSingoloVersamentoList = new ArrayList<RptDatiSingoloVersamento>();
        invalid_datiSingoloVersamentoList.add(RptGeneration.generateDatiSingoloVersamento(importoSingoloVersamento1,
                commissioneCaricoPA, ibanAccredito, bicAccredito, ibanAppoggio, bicAppoggio, credenzialiPagatore,
                descrizioneCausaleVersamento1, iuv, datiSpecificiRiscossione, null, ordineVersamento1));
        invalid_datiSingoloVersamentoList.add(RptGeneration.generateDatiSingoloVersamento(importoSingoloVersamento2,
                commissioneCaricoPA, ibanAccredito, bicAccredito, ibanAppoggio, bicAppoggio, credenzialiPagatore,
                descrizioneCausaleVersamento2, iuv, datiSpecificiRiscossione,
                RptGeneration.generateDatiMarcaBolloDigitale(tipoBollo, hashDocumento, provinciaResidenza),
                ordineVersamento2));
        Rpt rpt = RptGeneration.generateRptElement(versioneOggetto, dominio, autenticazioneSoggetto, null,
                soggettoPagatore, enteBeneficiario, datiVersamento, invalid_datiSingoloVersamentoList);
        RptGeneration.generate(idTenant, rpt);
    }
}
