package it.pagoPA.toolkit.rptGenerator;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.pagoPA.toolkit.rptGenerator.bean.RptContainer;
import it.pagoPA.toolkit.rptGenerator.bean.rpt.Rpt;
import it.pagoPA.toolkit.rptGenerator.bean.rpt.RptDatiSingoloVersamento;
import it.pagoPA.toolkit.rptGenerator.bean.rpt.RptDatiVersamento;
import it.pagoPA.toolkit.rptGenerator.bean.rpt.RptDominio;
import it.pagoPA.toolkit.rptGenerator.bean.rpt.RptEnteBeneficiario;
import it.pagoPA.toolkit.rptGenerator.bean.rpt.RptSoggetto;
import it.pagoPA.toolkit.rptGenerator.enumeration.FirmaRicevutaEnum;
import it.pagoPA.toolkit.rptGenerator.enumeration.RptStatusEnum;
import it.pagoPA.toolkit.rptGenerator.enumeration.TipoBolloEnum;
import it.pagoPA.toolkit.rptGenerator.xsd.StAutenticazioneSoggetto;
import it.pagoPA.toolkit.rptGenerator.xsd.StTipoIdentificativoUnivocoPersG;
import it.pagoPA.toolkit.rptGenerator.xsd.StTipoVersamento;

public class RptManagementTest {

    RptContainer rptContainer;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        String identificativoDominio = "01234567890";
        String anagraficaPagatore = "ROSSI MARIO";
        StTipoIdentificativoUnivocoPersG tipoIdentificativoUnivocoEnteBeneficiario = StTipoIdentificativoUnivocoPersG.G;
        String codiceIdentificativoUnivocoEnteBeneficiario = "09876543210";
        String denominazioneEnteBeneficiario = "Ente Beneficiario";
        BigDecimal importoTotaleDaVersare = BigDecimal.valueOf(33.33);
        StTipoVersamento tipoVersamento = StTipoVersamento.CP;
        String identificativoUnivocoVersamento = "01234567890123456";
        FirmaRicevutaEnum firmaRicevuta = FirmaRicevutaEnum.NON_RICHIESTA;
        BigDecimal importoSingoloVersamento1 = BigDecimal.valueOf(11.11);
        BigDecimal importoSingoloVersamento2 = BigDecimal.valueOf(22.22);
        String ibanAccredito = "IT73W0306953740100000300003";
        String iuv = "01234567890123456";
        String datiSpecificiRiscossione = "9/test";
        Integer ordineVersamento1 = 1;
        Integer ordineVersamento2 = 2;
        TipoBolloEnum tipoBollo = TipoBolloEnum.IMPOSTA_DI_BOLLO;
        String hashDocumento = "aGFzaENvZGUgRG9jdW1lbnRvIE1hcmRhIGRhIEJvbGxv";
        String provinciaResidenza = "FI";
        String versioneOggetto = "1.0";
        StAutenticazioneSoggetto autenticazioneSoggetto = StAutenticazioneSoggetto.N_A;
        String idTenant = "idTenant00001";

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
        rptContainer = RptGeneration.generate(idTenant, rpt);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testValidate() throws Exception {
        RptManagement.validate(rptContainer);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testMakePayable() throws Exception {
        assertEquals(RptManagement.makeSent(rptContainer).getRptStatus(), RptStatusEnum.SENT);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testMakeNotPayable() throws Exception {
        assertEquals(RptManagement.makeReceivedRT(rptContainer).getRptStatus(), RptStatusEnum.RECEIVED_RT);
    }
}
