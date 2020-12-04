package it.pagoPA.toolkit.rptGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import it.pagoPA.toolkit.common.Constants;
import it.pagoPA.toolkit.common.bean.DatiMarcaBolloDigitale;
import it.pagoPA.toolkit.debtPositionGenerator.bean.DebtPosition;
import it.pagoPA.toolkit.debtPositionGenerator.bean.debtPosition.DPSinglePaymentDetail;
import it.pagoPA.toolkit.debtPositionGenerator.business.DebtPositionBusiness;
import it.pagoPA.toolkit.rptGenerator.bean.RptContainer;
import it.pagoPA.toolkit.rptGenerator.bean.rpt.Rpt;
import it.pagoPA.toolkit.rptGenerator.bean.rpt.RptDatiSingoloVersamento;
import it.pagoPA.toolkit.rptGenerator.bean.rpt.RptDatiVersamento;
import it.pagoPA.toolkit.rptGenerator.bean.rpt.RptDominio;
import it.pagoPA.toolkit.rptGenerator.bean.rpt.RptEnteBeneficiario;
import it.pagoPA.toolkit.rptGenerator.bean.rpt.RptIdentificativoUnivocoFG;
import it.pagoPA.toolkit.rptGenerator.bean.rpt.RptIdentificativoUnivocoG;
import it.pagoPA.toolkit.rptGenerator.bean.rpt.RptIndirizzo;
import it.pagoPA.toolkit.rptGenerator.bean.rpt.RptSoggetto;
import it.pagoPA.toolkit.rptGenerator.business.RptBusiness;
import it.pagoPA.toolkit.rptGenerator.enumeration.FirmaRicevutaEnum;
import it.pagoPA.toolkit.rptGenerator.enumeration.TipoBolloEnum;
import it.pagoPA.toolkit.rptGenerator.xsd.StAutenticazioneSoggetto;
import it.pagoPA.toolkit.rptGenerator.xsd.StTipoIdentificativoUnivocoPersFG;
import it.pagoPA.toolkit.rptGenerator.xsd.StTipoIdentificativoUnivocoPersG;
import it.pagoPA.toolkit.rptGenerator.xsd.StTipoVersamento;

/**
 * RptGeneration
 */
public class RptGeneration {

    /**
     * Generate the Dominio
     * 
     * @param identificativoDominio
     * @param identificativoStazioneRichiedente
     * @return RptDominio
     */
    public static RptDominio generateDominio(String identificativoDominio, String identificativoStazioneRichiedente) {

        return new RptDominio.Builder().setIdentificativoDominio(identificativoDominio)
                .setIdentificativoStazioneRichiedente(identificativoStazioneRichiedente).build();

    }

    /**
     * Generate the IdentificativoUnivoco type F-G
     * 
     * @param tipoIdentificativoUnivoco
     * @param codiceIdentificativoUnivoco
     * @return RptIdentificativoUnivocoFG
     */
    public static RptIdentificativoUnivocoFG generateIdentificativoUnivocoFG(
            StTipoIdentificativoUnivocoPersFG tipoIdentificativoUnivoco, String codiceIdentificativoUnivoco) {

        return new RptIdentificativoUnivocoFG.Builder().setCodiceIdentificativoUnivoco(codiceIdentificativoUnivoco)
                .setTipoIdentificativoUnivoco(tipoIdentificativoUnivoco).build();
    }

    /**
     * Generate the IdentificativoUnivoco type G
     * 
     * @param tipoIdentificativoUnivoco
     * @param codiceIdentificativoUnivoco
     * @return RptIdentificativoUnivocoG
     */
    public static RptIdentificativoUnivocoG generateIdentificativoUnivocoG(
            StTipoIdentificativoUnivocoPersG tipoIdentificativoUnivoco, String codiceIdentificativoUnivoco) {

        return new RptIdentificativoUnivocoG.Builder().setCodiceIdentificativoUnivoco(codiceIdentificativoUnivoco)
                .setTipoIdentificativoUnivoco(tipoIdentificativoUnivoco).build();
    }

    /**
     * Generate the Indirizzo
     * 
     * @param indirizzo
     * @param civico
     * @param cap
     * @param localita
     * @param provincia
     * @param nazione
     * @return RptIndirizzo
     */
    public static RptIndirizzo generateIndirizzo(String indirizzo, String civico, String cap, String localita,
            String provincia, String nazione) {

        return new RptIndirizzo.Builder().setIndirizzo(indirizzo).setCivico(civico).setCap(cap).setLocalita(localita)
                .setProvincia(provincia).setNazione(nazione).build();
    }

    /**
     * Generate the Soggetto
     * 
     * @param identificativoUnivoco
     * @param anagrafica
     * @param indirizzo
     * @param email
     * @return RptSoggetto
     */
    public static RptSoggetto generateSoggetto(RptIdentificativoUnivocoFG identificativoUnivoco, String anagrafica,
            RptIndirizzo indirizzo, String email) {

        return new RptSoggetto.Builder().setIdentificativoUnivoco(identificativoUnivoco).setAnagrafica(anagrafica)
                .setIndirizzo(indirizzo).setEmail(email).build();
    }

    /**
     * Generate the EnteBeneficiario
     * 
     * @param identificativoUnivoco
     * @param denominazione
     * @param codiceUnitOper
     * @param denomUnitOper
     * @param indirizzo
     * @return RptEnteBeneficiario
     */
    public static RptEnteBeneficiario generateEnteBeneficiario(RptIdentificativoUnivocoG identificativoUnivoco,
            String denominazione, String codiceUnitOper, String denomUnitOper, RptIndirizzo indirizzo) {

        return new RptEnteBeneficiario.Builder().setIdentificativoUnivoco(identificativoUnivoco)
                .setDenominazione(denominazione).setCodiceUnitOper(codiceUnitOper).setDenomUnitOper(denomUnitOper)
                .setIndirizzo(indirizzo).build();
    }

    /**
     * Generate the DatiVersamento N.B.: iuv mandatory, no IUV validation check
     * N.B.: dataEsecuzionePagamento = new Date()
     * 
     * @param importoTotaleDaVersare
     * @param tipoVersamento
     * @param identificativoUnivocoVersamento
     * @param ibanAddebito
     * @param bicAddebito
     * @param firmaRicevuta
     * @return RptDatiVersamento
     */
    public static RptDatiVersamento generateDatiVersamento(BigDecimal importoTotaleDaVersare,
            StTipoVersamento tipoVersamento, String identificativoUnivocoVersamento, String ibanAddebito,
            String bicAddebito, FirmaRicevutaEnum firmaRicevuta) {

        return new RptDatiVersamento.Builder().setImportoTotaleDaVersare(importoTotaleDaVersare)
                .setTipoVersamento(tipoVersamento).setIdentificativoUnivocoVersamento(identificativoUnivocoVersamento)
                .setIbanAddebito(ibanAddebito).setBicAddebito(bicAddebito).setFirmaRicevuta(firmaRicevuta).build();
    }

    /**
     * Generate the DatiMarcaBolloDigitale
     * 
     * @param tipoBollo
     * @param hashDocumento
     * @param provinciaResidenza
     * @return RptDatiMarcaBolloDigitale
     */
    public static DatiMarcaBolloDigitale generateDatiMarcaBolloDigitale(TipoBolloEnum tipoBollo, String hashDocumento,
            String provinciaResidenza) {

        return new DatiMarcaBolloDigitale.Builder().setTipoBollo(tipoBollo).setHashDocumento(hashDocumento)
                .setProvinciaResidenza(provinciaResidenza).build();
    }

    /**
     * Generate the DatiSingoloVersamento
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

        RptBusiness.createAgidCausal(rptDatiSingoloVersamento);

        return rptDatiSingoloVersamento;
    }

    /**
     * Generate the Rpt element
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
     */
    public static Rpt generateRptElement(String versioneOggetto, RptDominio dominio,
            StAutenticazioneSoggetto autenticazioneSoggetto, RptSoggetto soggettoVersante, RptSoggetto soggettoPagatore,
            RptEnteBeneficiario enteBeneficiario, RptDatiVersamento datiVersamento,
            List<RptDatiSingoloVersamento> datiSingoloVersamentoList) {

        return new Rpt.Builder().setVersioneOggetto(versioneOggetto).setDominio(dominio)
                .setAutenticazioneSoggetto(autenticazioneSoggetto).setSoggettoVersante(soggettoVersante)
                .setSoggettoPagatore(soggettoPagatore).setEnteBeneficiario(enteBeneficiario)
                .setDatiVersamento(datiVersamento).setDatiSingoloVersamentoList(datiSingoloVersamentoList).build();
    }

    /**
     * Generate the Rpt Container from Single Data
     * 
     * @param idTenant
     * @param rpt
     * @return RptContainer
     * @throws Exception
     */
    public static RptContainer generate(String idTenant, Rpt rpt) throws Exception {

        RptContainer rptContainer = new RptContainer.Builder().setIdTenant(idTenant).setRpt(rpt).build();

        RptBusiness.validate(rptContainer);

        RptBusiness.generateRptXml(rptContainer);

        return rptContainer;
    }

    /**
     * Generate the Rpt Container from DebtPosition
     * 
     * @param idTenant
     * @param debtPosition
     * @return
     * @throws Exception
     */
    public static RptContainer generate(String idTenant, DebtPosition debtPosition,
            RptEnteBeneficiario enteBeneficiario, BigDecimal commissioneCaricoPA) throws Exception {

        DebtPositionBusiness.validate(debtPosition);
        Rpt rpt = createRptFromDebtPosition(debtPosition, enteBeneficiario, commissioneCaricoPA);

        return generate(idTenant, rpt);
    }

    /**
     * From DebtPosition to RptContainer Transformer
     * 
     * @param debtPosition
     * @return
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