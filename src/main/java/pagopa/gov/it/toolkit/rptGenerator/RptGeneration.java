package pagopa.gov.it.toolkit.rptGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import pagopa.gov.it.toolkit.common.Constants;
import pagopa.gov.it.toolkit.common.bean.DatiMarcaBolloDigitale;
import pagopa.gov.it.toolkit.debtPositionGenerator.bean.DebtPosition;
import pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPSinglePaymentDetail;
import pagopa.gov.it.toolkit.debtPositionGenerator.business.DebtPositionBusiness;
import pagopa.gov.it.toolkit.rptGenerator.bean.RptContainer;
import pagopa.gov.it.toolkit.rptGenerator.bean.rpt.Rpt;
import pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiSingoloVersamento;
import pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiVersamento;
import pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDominio;
import pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptEnteBeneficiario;
import pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptIdentificativoUnivocoFG;
import pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptIdentificativoUnivocoG;
import pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptIndirizzo;
import pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptSoggetto;
import pagopa.gov.it.toolkit.rptGenerator.business.RptBusiness;
import pagopa.gov.it.toolkit.rptGenerator.enumeration.FirmaRicevutaEnum;
import pagopa.gov.it.toolkit.rptGenerator.enumeration.TipoBolloEnum;
import pagopa.gov.it.toolkit.rptGenerator.xsd.StAutenticazioneSoggetto;
import pagopa.gov.it.toolkit.rptGenerator.xsd.StTipoIdentificativoUnivocoPersFG;
import pagopa.gov.it.toolkit.rptGenerator.xsd.StTipoIdentificativoUnivocoPersG;
import pagopa.gov.it.toolkit.rptGenerator.xsd.StTipoVersamento;

/**
 * Main class for the generation of the <code>Rpt</code>
 */
public class RptGeneration {

    /**
     * Generates the component <code>RptDominio</code> of <code>Rpt</code>
     * 
     * @param identificativoDominio
     * @param identificativoStazioneRichiedente
     * @return RptDominio
     * @see RptDominio
     */
    public static RptDominio generateDominio(String identificativoDominio, String identificativoStazioneRichiedente) {

        RptDominio dominio = new RptDominio.Builder().setIdentificativoDominio(identificativoDominio)
                .setIdentificativoStazioneRichiedente(identificativoStazioneRichiedente).build();

        RptBusiness.validateConstraints(dominio);

        return dominio;
    }

    /**
     * Generates the component <code>RptIdentificativoUnivocoFG</code> of
     * <code>Rpt</code>
     * 
     * @param tipoIdentificativoUnivoco
     * @param codiceIdentificativoUnivoco
     * @return RptIdentificativoUnivocoFG
     * @see RptIdentificativoUnivocoFG
     */
    public static RptIdentificativoUnivocoFG generateIdentificativoUnivocoFG(
            StTipoIdentificativoUnivocoPersFG tipoIdentificativoUnivoco, String codiceIdentificativoUnivoco) {

        RptIdentificativoUnivocoFG identificativoUnivocoFG = new RptIdentificativoUnivocoFG.Builder()
                .setCodiceIdentificativoUnivoco(codiceIdentificativoUnivoco)
                .setTipoIdentificativoUnivoco(tipoIdentificativoUnivoco).build();

        RptBusiness.validateConstraints(identificativoUnivocoFG);

        return identificativoUnivocoFG;
    }

    /**
     * Generates the component <code>RptIdentificativoUnivocoG</code> of
     * <code>Rpt</code>
     * 
     * @param tipoIdentificativoUnivoco
     * @param codiceIdentificativoUnivoco
     * @return RptIdentificativoUnivocoG
     * @see RptIdentificativoUnivocoG
     */
    public static RptIdentificativoUnivocoG generateIdentificativoUnivocoG(
            StTipoIdentificativoUnivocoPersG tipoIdentificativoUnivoco, String codiceIdentificativoUnivoco) {

        RptIdentificativoUnivocoG identificativoUnivocoG = new RptIdentificativoUnivocoG.Builder()
                .setCodiceIdentificativoUnivoco(codiceIdentificativoUnivoco)
                .setTipoIdentificativoUnivoco(tipoIdentificativoUnivoco).build();

        RptBusiness.validateConstraints(identificativoUnivocoG);

        return identificativoUnivocoG;
    }

    /**
     * Generates the component <code>RptIndirizzo</code> of <code>Rpt</code>
     * 
     * @param indirizzo
     * @param civico
     * @param cap
     * @param localita
     * @param provincia
     * @param nazione
     * @return RptIndirizzo
     * @see RptIndirizzo
     */
    public static RptIndirizzo generateIndirizzo(String indirizzo, String civico, String cap, String localita,
            String provincia, String nazione) {

        RptIndirizzo indirizzoBean = new RptIndirizzo.Builder().setIndirizzo(indirizzo).setCivico(civico).setCap(cap)
                .setLocalita(localita).setProvincia(provincia).setNazione(nazione).build();

        RptBusiness.validateConstraints(indirizzoBean);

        return indirizzoBean;
    }

    /**
     * Generates the component <code>RptSoggetto</code> of <code>Rpt</code><br/>
     * Used for <code>SoggettoPagatore</code> and for
     * <code>SoggettoVersante</code>
     * 
     * @param identificativoUnivoco
     * @param anagrafica
     * @param indirizzo
     * @param email
     * @return RptSoggetto
     * @see RptSoggetto
     */
    public static RptSoggetto generateSoggetto(RptIdentificativoUnivocoFG identificativoUnivoco, String anagrafica,
            RptIndirizzo indirizzo, String email) {

        RptSoggetto soggetto = new RptSoggetto.Builder().setIdentificativoUnivoco(identificativoUnivoco)
                .setAnagrafica(anagrafica).setIndirizzo(indirizzo).setEmail(email).build();

        RptBusiness.validateConstraints(soggetto);

        return soggetto;
    }

    /**
     * Generates the component <code>RptEnteBeneficiario</code> of
     * <code>Rpt</code>
     * 
     * @param identificativoUnivoco
     * @param denominazione
     * @param codiceUnitOper
     * @param denomUnitOper
     * @param indirizzo
     * @return RptEnteBeneficiario
     * @see RptEnteBeneficiario
     */
    public static RptEnteBeneficiario generateEnteBeneficiario(RptIdentificativoUnivocoG identificativoUnivoco,
            String denominazione, String codiceUnitOper, String denomUnitOper, RptIndirizzo indirizzo) {

        RptEnteBeneficiario enteBeneficiario = new RptEnteBeneficiario.Builder()
                .setIdentificativoUnivoco(identificativoUnivoco).setDenominazione(denominazione)
                .setCodiceUnitOper(codiceUnitOper).setDenomUnitOper(denomUnitOper).setIndirizzo(indirizzo).build();

        RptBusiness.validateConstraints(enteBeneficiario);

        return enteBeneficiario;
    }

    /**
     * Generates the component <code>RptDatiVersamento</code> of
     * <code>Rpt</code><br/>
     * <code>identificativoUnivocoVersamento</code> mandatory, no validation
     * check on it<br/>
     * dataEsecuzionePagamento = new Date()
     * 
     * @param importoTotaleDaVersare
     * @param tipoVersamento
     * @param identificativoUnivocoVersamento
     * @param ibanAddebito
     * @param bicAddebito
     * @param firmaRicevuta
     * @return RptDatiVersamento
     * @see RptDatiVersamento
     */
    public static RptDatiVersamento generateDatiVersamento(BigDecimal importoTotaleDaVersare,
            StTipoVersamento tipoVersamento, String identificativoUnivocoVersamento, String ibanAddebito,
            String bicAddebito, FirmaRicevutaEnum firmaRicevuta) {

        RptDatiVersamento datiVersamento = new RptDatiVersamento.Builder()
                .setImportoTotaleDaVersare(importoTotaleDaVersare).setTipoVersamento(tipoVersamento)
                .setIdentificativoUnivocoVersamento(identificativoUnivocoVersamento).setIbanAddebito(ibanAddebito)
                .setBicAddebito(bicAddebito).setFirmaRicevuta(firmaRicevuta).build();

        RptBusiness.validateConstraints(datiVersamento);

        return datiVersamento;
    }

    /**
     * Generates the component <code>DatiMarcaBolloDigitale</code> of
     * <code>Rpt</code>
     * 
     * @param tipoBollo
     * @param hashDocumento
     * @param provinciaResidenza
     * @return RptDatiMarcaBolloDigitale
     * @see DatiMarcaBolloDigitale
     */
    public static DatiMarcaBolloDigitale generateDatiMarcaBolloDigitale(TipoBolloEnum tipoBollo, String hashDocumento,
            String provinciaResidenza) {

        DatiMarcaBolloDigitale datiMarcaBolloDigitale = new DatiMarcaBolloDigitale.Builder().setTipoBollo(tipoBollo)
                .setHashDocumento(hashDocumento).setProvinciaResidenza(provinciaResidenza).build();

        RptBusiness.validateConstraints(datiMarcaBolloDigitale);

        return datiMarcaBolloDigitale;
    }

    /**
     * Generates the component <code>RptDatiSingoloVersamento</code> of
     * <code>Rpt</code>
     * 
     * @param importoSingoloVersamento
     * @param commissioneCaricoPA
     * @param ibanAccredito
     * @param bicAccredito
     * @param ibanAppoggio
     * @param bicAppoggio
     * @param credenzialiPagatore
     * @param descrizioneCausaleVersamento
     * @param datiSpecificiRiscossione
     * @param datiMarcaBolloDigitale
     * @param ordineVersamento
     * @return RptDatiSingoloVersamento
     * @see RptDatiSingoloVersamento
     */
    public static RptDatiSingoloVersamento generateDatiSingoloVersamento(BigDecimal importoSingoloVersamento,
            BigDecimal commissioneCaricoPA, String ibanAccredito, String bicAccredito, String ibanAppoggio,
            String bicAppoggio, String credenzialiPagatore, String descrizioneCausaleVersamento, String iuv,
            String datiSpecificiRiscossione, DatiMarcaBolloDigitale datiMarcaBolloDigitale, Integer ordineVersamento) {

        RptDatiSingoloVersamento rptDatiSingoloVersamento = new RptDatiSingoloVersamento.Builder()
                .setImportoSingoloVersamento(importoSingoloVersamento).setCommissioneCaricoPA(commissioneCaricoPA)
                .setIbanAccredito(ibanAccredito).setBicAccredito(bicAccredito).setIbanAppoggio(ibanAppoggio)
                .setBicAppoggio(bicAppoggio).setCredenzialiPagatore(credenzialiPagatore)
                .setDescrizioneCausaleVersamento(descrizioneCausaleVersamento).setIuv(iuv)
                .setDatiSpecificiRiscossione(datiSpecificiRiscossione).setDatiMarcaBolloDigitale(datiMarcaBolloDigitale)
                .setOrdineVersamento(ordineVersamento).build();

        RptBusiness.validateConstraints(rptDatiSingoloVersamento);

        RptBusiness.createAgidCausal(rptDatiSingoloVersamento);

        return rptDatiSingoloVersamento;
    }

    /**
     * Generates the <code>Rpt</code> based on its component
     * 
     * @param versioneOggetto
     * @param dominio
     * @param identificativoMessaggioRichiesta
     * @param dataOraMessaggioRichiesta
     * @param autenticazioneSoggetto
     * @param soggettoVersante
     * @param soggettoPagatore
     * @param enteBeneficiario
     * @param datiVersamento
     * @param datiSingoloVersamentoList
     * @return Rpt
     * @see Rpt
     */
    public static Rpt generateRptElement(String versioneOggetto, RptDominio dominio,
            StAutenticazioneSoggetto autenticazioneSoggetto, RptSoggetto soggettoVersante, RptSoggetto soggettoPagatore,
            RptEnteBeneficiario enteBeneficiario, RptDatiVersamento datiVersamento,
            List<RptDatiSingoloVersamento> datiSingoloVersamentoList) {

        Rpt rpt = new Rpt.Builder().setVersioneOggetto(versioneOggetto).setDominio(dominio)
                .setAutenticazioneSoggetto(autenticazioneSoggetto).setSoggettoVersante(soggettoVersante)
                .setSoggettoPagatore(soggettoPagatore).setEnteBeneficiario(enteBeneficiario)
                .setDatiVersamento(datiVersamento).setDatiSingoloVersamentoList(datiSingoloVersamentoList).build();

        RptBusiness.validateConstraints(rpt);

        return rpt;
    }

    /**
     * Generates the <code>RptContainer</code> from the single data
     * 
     * @param idTenant
     * @param rpt
     * @return RptContainer
     * @throws Exception
     * @see Rpt
     * @see RptContainer
     */
    public static RptContainer generate(String idTenant, Rpt rpt) throws Exception {

        RptContainer rptContainer = new RptContainer.Builder().setIdTenant(idTenant).setRpt(rpt).build();

        RptBusiness.validate(rptContainer);

        RptBusiness.generateRptXml(rptContainer);

        return rptContainer;
    }

    /**
     * Generates the <code>RptContainer</code> from <code>DebtPosition</code>
     * 
     * @param idTenant
     * @param debtPosition
     * @return
     * @throws Exception
     * @see DebtPosition
     * @see RptContainer
     */
    public static RptContainer generate(String idTenant, DebtPosition debtPosition,
            RptEnteBeneficiario enteBeneficiario, BigDecimal commissioneCaricoPA) throws Exception {

        DebtPositionBusiness.validate(debtPosition);
        Rpt rpt = createRptFromDebtPosition(debtPosition, enteBeneficiario, commissioneCaricoPA);

        return generate(idTenant, rpt);
    }

    /**
     * From <code>DebtPosition</code> to <code>RptContainer</code> Transformer
     * 
     * @param debtPosition
     * @param enteBeneficiario
     * @param commissioneCaricoPA
     * @return Rpt
     * @see DebtPosition
     * @see RptContainer
     */
    private static Rpt createRptFromDebtPosition(DebtPosition debtPosition, RptEnteBeneficiario enteBeneficiario,
            BigDecimal commissioneCaricoPA) {
        RptDominio dominio = RptGeneration.generateDominio(debtPosition.getPaymentDetail().getDomainIdentifier(), null);
        RptSoggetto soggetto = RptGeneration.generateSoggetto(
                RptGeneration.generateIdentificativoUnivocoFG(debtPosition.getPayer().getUniqueIdentificationType(),
                        debtPosition.getPayer().getUniqueIdentificationCode()),
                debtPosition.getPayer().getRegistry(),
                RptGeneration.generateIndirizzo(debtPosition.getPayer().getAddress(),
                        debtPosition.getPayer().getNumberStreet(), debtPosition.getPayer().getPostalCode(),
                        debtPosition.getPayer().getLocality(), debtPosition.getPayer().getProvince(),
                        debtPosition.getPayer().getNation()),
                debtPosition.getPayer().getEmail());
        RptDatiVersamento datiVersamento = RptGeneration.generateDatiVersamento(
                debtPosition.getPaymentDetail().getTotalAmountPayment(),
                StTipoVersamento.fromValue(Constants.TIPO_VERSAMENTO_DEFAULT), debtPosition.getPaymentDetail().getIuv(),
                debtPosition.getPaymentDetail().getDebitIban(), debtPosition.getPaymentDetail().getDebitBic(),
                FirmaRicevutaEnum.NON_RICHIESTA);
        List<RptDatiSingoloVersamento> datiSingoloVersamentoList = new ArrayList<RptDatiSingoloVersamento>();
        for (DPSinglePaymentDetail singlePaymentDetail : debtPosition.getSinglePaymentDetailList()) {
            datiSingoloVersamentoList.add(RptGeneration.generateDatiSingoloVersamento(
                    singlePaymentDetail.getAmountSinglePayment(), commissioneCaricoPA,
                    singlePaymentDetail.getCreditIban(), singlePaymentDetail.getCreditBic(),
                    singlePaymentDetail.getSupportIban(), singlePaymentDetail.getSupportBic(),
                    Constants.CREDENZIALI_PAGATORE_DEFAULT, singlePaymentDetail.getCausalDescriptionSinglePayment(),
                    debtPosition.getPaymentDetail().getIuv(),
                    debtPosition.getPaymentDetail().getSpecificCollectionData(),
                    singlePaymentDetail.getDatiMarcaBolloDigitale(), singlePaymentDetail.getOrderSinglePayment()));
        }
        return RptGeneration.generateRptElement(Constants.VERSIONE_OGGETTO_DEFAULT, dominio,
                StAutenticazioneSoggetto.N_A, soggetto, soggetto, enteBeneficiario, datiVersamento,
                datiSingoloVersamentoList);
    }
}