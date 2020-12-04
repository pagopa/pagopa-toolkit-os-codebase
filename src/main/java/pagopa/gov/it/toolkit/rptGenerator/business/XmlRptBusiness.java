package pagopa.gov.it.toolkit.rptGenerator.business;

import java.io.File;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.namespace.QName;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import pagopa.gov.it.toolkit.rptGenerator.bean.RptContainer;
import pagopa.gov.it.toolkit.rptGenerator.bean.RptContainerUpdater;
import pagopa.gov.it.toolkit.rptGenerator.bean.rpt.Rpt;
import pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiSingoloVersamento;
import pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiVersamento;
import pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDominio;
import pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptEnteBeneficiario;
import pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptSoggetto;
import pagopa.gov.it.toolkit.rptGenerator.constants.RptConstants;
import pagopa.gov.it.toolkit.rptGenerator.exception.XmlRptGeneratorException;
import pagopa.gov.it.toolkit.rptGenerator.xsd.CtDatiMarcaBolloDigitale;
import pagopa.gov.it.toolkit.rptGenerator.xsd.CtDatiSingoloVersamentoRPT;
import pagopa.gov.it.toolkit.rptGenerator.xsd.CtDatiVersamentoRPT;
import pagopa.gov.it.toolkit.rptGenerator.xsd.CtDominio;
import pagopa.gov.it.toolkit.rptGenerator.xsd.CtEnteBeneficiario;
import pagopa.gov.it.toolkit.rptGenerator.xsd.CtIdentificativoUnivocoPersonaFG;
import pagopa.gov.it.toolkit.rptGenerator.xsd.CtIdentificativoUnivocoPersonaG;
import pagopa.gov.it.toolkit.rptGenerator.xsd.CtRichiestaPagamentoTelematico;
import pagopa.gov.it.toolkit.rptGenerator.xsd.CtSoggettoPagatore;
import pagopa.gov.it.toolkit.rptGenerator.xsd.CtSoggettoVersante;

/**
 * Business logic class with all methods to generate the rpt in XML format
 */
public class XmlRptBusiness {

    /**
     * Construction of the bean to generate the rpt in XML format
     * 
     * @param rptContainer
     * @throws Exception
     * @see RptContainer
     * @see pagopa.gov.it.toolkit.rptGenerator.xsd.ObjectFactory
     */
    public static void generateRptXml(RptContainer rptContainer) throws Exception {
        CtRichiestaPagamentoTelematico ctRichiestaPagamentoTelematico = new CtRichiestaPagamentoTelematico();
        GregorianCalendar calendar = new GregorianCalendar();
        try {
            Rpt rpt = rptContainer.getRpt();
            RptDominio rptDominio = rpt.getDominio();
            RptSoggetto rptSoggettoVersante = rpt.getSoggettoVersante();
            RptSoggetto rptSoggettoPagatore = rpt.getSoggettoPagatore();
            RptEnteBeneficiario rptEnteBeneficiario = rpt.getEnteBeneficiario();
            RptDatiVersamento rptDatiVersamento = rpt.getDatiVersamento();
            List<RptDatiSingoloVersamento> datiSingoloVersamentoList = rpt.getDatiSingoloVersamentoList();

            CtDominio ctDominio = new CtDominio();
            ctDominio.setIdentificativoDominio(rptDominio.getIdentificativoDominio());
            ctDominio.setIdentificativoStazioneRichiedente(rptDominio.getIdentificativoStazioneRichiedente());
            ctRichiestaPagamentoTelematico.setDominio(ctDominio);

            if (rptSoggettoVersante != null) {
                CtSoggettoVersante ctSoggettoVersante = new CtSoggettoVersante();
                CtIdentificativoUnivocoPersonaFG ctIdentificativoUnivocoPersonaFGVersante = new CtIdentificativoUnivocoPersonaFG();
                ctIdentificativoUnivocoPersonaFGVersante.setCodiceIdentificativoUnivoco(
                        rptSoggettoVersante.getIdentificativoUnivoco().getCodiceIdentificativoUnivoco());
                ctIdentificativoUnivocoPersonaFGVersante.setTipoIdentificativoUnivoco(
                        rptSoggettoVersante.getIdentificativoUnivoco().getTipoIdentificativoUnivoco());
                ctSoggettoVersante.setIdentificativoUnivocoVersante(ctIdentificativoUnivocoPersonaFGVersante);
                ctSoggettoVersante.setAnagraficaVersante(rptSoggettoVersante.getAnagrafica());
                if (rptSoggettoVersante.getIndirizzo() != null) {
                    ctSoggettoVersante.setIndirizzoVersante(rptSoggettoVersante.getIndirizzo().getIndirizzo());
                    ctSoggettoVersante.setCivicoVersante(rptSoggettoVersante.getIndirizzo().getCivico());
                    ctSoggettoVersante.setCapVersante(rptSoggettoVersante.getIndirizzo().getCap());
                    ctSoggettoVersante.setLocalitaVersante(rptSoggettoVersante.getIndirizzo().getLocalita());
                    ctSoggettoVersante.setProvinciaVersante(rptSoggettoVersante.getIndirizzo().getProvincia());
                    ctSoggettoVersante.setNazioneVersante(rptSoggettoVersante.getIndirizzo().getNazione());
                }
                ctSoggettoVersante.setEMailVersante(rptSoggettoVersante.getEmail());
                ctRichiestaPagamentoTelematico.setSoggettoVersante(ctSoggettoVersante);
            }

            CtSoggettoPagatore ctSoggettoPagatore = new CtSoggettoPagatore();
            CtIdentificativoUnivocoPersonaFG ctIdentificativoUnivocoPersonaFG = new CtIdentificativoUnivocoPersonaFG();
            ctIdentificativoUnivocoPersonaFG.setCodiceIdentificativoUnivoco(
                    rptSoggettoPagatore.getIdentificativoUnivoco().getCodiceIdentificativoUnivoco());
            ctIdentificativoUnivocoPersonaFG.setTipoIdentificativoUnivoco(
                    rptSoggettoPagatore.getIdentificativoUnivoco().getTipoIdentificativoUnivoco());
            ctSoggettoPagatore.setIdentificativoUnivocoPagatore(ctIdentificativoUnivocoPersonaFG);
            ctSoggettoPagatore.setAnagraficaPagatore(rptSoggettoPagatore.getAnagrafica());
            if (rptSoggettoPagatore.getIndirizzo() != null) {
                ctSoggettoPagatore.setIndirizzoPagatore(rptSoggettoPagatore.getIndirizzo().getIndirizzo());
                ctSoggettoPagatore.setCivicoPagatore(rptSoggettoPagatore.getIndirizzo().getCivico());
                ctSoggettoPagatore.setCapPagatore(rptSoggettoPagatore.getIndirizzo().getCap());
                ctSoggettoPagatore.setLocalitaPagatore(rptSoggettoPagatore.getIndirizzo().getLocalita());
                ctSoggettoPagatore.setProvinciaPagatore(rptSoggettoPagatore.getIndirizzo().getProvincia());
                ctSoggettoPagatore.setNazionePagatore(rptSoggettoPagatore.getIndirizzo().getNazione());
            }
            ctSoggettoPagatore.setEMailPagatore(rptSoggettoPagatore.getEmail());
            ctRichiestaPagamentoTelematico.setSoggettoPagatore(ctSoggettoPagatore);

            CtEnteBeneficiario ctEnteBeneficiario = new CtEnteBeneficiario();
            CtIdentificativoUnivocoPersonaG ctIdentificativoUnivocoPersonaG = new CtIdentificativoUnivocoPersonaG();
            ctIdentificativoUnivocoPersonaG.setCodiceIdentificativoUnivoco(
                    rptEnteBeneficiario.getIdentificativoUnivoco().getCodiceIdentificativoUnivoco());
            ctIdentificativoUnivocoPersonaG.setTipoIdentificativoUnivoco(
                    rptEnteBeneficiario.getIdentificativoUnivoco().getTipoIdentificativoUnivoco());
            ctEnteBeneficiario.setIdentificativoUnivocoBeneficiario(ctIdentificativoUnivocoPersonaG);
            ctEnteBeneficiario.setDenominazioneBeneficiario(rptEnteBeneficiario.getDenominazione());
            ctEnteBeneficiario.setCodiceUnitOperBeneficiario(rptEnteBeneficiario.getCodiceUnitOper());
            ctEnteBeneficiario.setDenomUnitOperBeneficiario(rptEnteBeneficiario.getDenomUnitOper());
            if (rptEnteBeneficiario.getIndirizzo() != null) {
                ctEnteBeneficiario.setIndirizzoBeneficiario(rptEnteBeneficiario.getIndirizzo().getIndirizzo());
                ctEnteBeneficiario.setCivicoBeneficiario(rptEnteBeneficiario.getIndirizzo().getCivico());
                ctEnteBeneficiario.setCapBeneficiario(rptEnteBeneficiario.getIndirizzo().getCap());
                ctEnteBeneficiario.setLocalitaBeneficiario(rptEnteBeneficiario.getIndirizzo().getLocalita());
                ctEnteBeneficiario.setProvinciaBeneficiario(rptEnteBeneficiario.getIndirizzo().getProvincia());
                ctEnteBeneficiario.setNazioneBeneficiario(rptEnteBeneficiario.getIndirizzo().getNazione());
            }
            ctRichiestaPagamentoTelematico.setEnteBeneficiario(ctEnteBeneficiario);

            CtDatiVersamentoRPT ctDatiVersamentoRPT = new CtDatiVersamentoRPT();
            calendar.setTime(rptDatiVersamento.getDataEsecuzionePagamento());
            ctDatiVersamentoRPT
                    .setDataEsecuzionePagamento(DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar));
            ctDatiVersamentoRPT.setImportoTotaleDaVersare(
                    rptDatiVersamento.getImportoTotaleDaVersare().setScale(2, BigDecimal.ROUND_HALF_UP));
            ctDatiVersamentoRPT.setTipoVersamento(rptDatiVersamento.getTipoVersamento());
            ctDatiVersamentoRPT
                    .setIdentificativoUnivocoVersamento(rptDatiVersamento.getIdentificativoUnivocoVersamento());
            ctDatiVersamentoRPT.setCodiceContestoPagamento(rptDatiVersamento.getCodiceContestoPagamento());
            ctDatiVersamentoRPT.setIbanAddebito(rptDatiVersamento.getIbanAddebito());
            ctDatiVersamentoRPT.setBicAddebito(rptDatiVersamento.getBicAddebito());
            ctDatiVersamentoRPT.setFirmaRicevuta(String.valueOf(rptDatiVersamento.getFirmaRicevuta().value()));

            datiSingoloVersamentoList.sort(Comparator.comparing(RptDatiSingoloVersamento::getOrdineVersamento));
            for (RptDatiSingoloVersamento rptDatiSingoloVersamento : datiSingoloVersamentoList) {
                CtDatiSingoloVersamentoRPT ctDatiSingoloVersamentoRPT = new CtDatiSingoloVersamentoRPT();
                ctDatiSingoloVersamentoRPT.setImportoSingoloVersamento(
                        rptDatiSingoloVersamento.getImportoSingoloVersamento().setScale(2, BigDecimal.ROUND_HALF_UP));
                ctDatiSingoloVersamentoRPT.setCommissioneCaricoPA(
                        rptDatiSingoloVersamento.getCommissioneCaricoPA() != null ? rptDatiSingoloVersamento
                                .getCommissioneCaricoPA().setScale(2, BigDecimal.ROUND_HALF_UP) : null);
                ctDatiSingoloVersamentoRPT.setIbanAccredito(rptDatiSingoloVersamento.getIbanAccredito());
                ctDatiSingoloVersamentoRPT.setBicAccredito(rptDatiSingoloVersamento.getBicAccredito());
                ctDatiSingoloVersamentoRPT.setIbanAppoggio(rptDatiSingoloVersamento.getIbanAppoggio());
                ctDatiSingoloVersamentoRPT.setBicAppoggio(rptDatiSingoloVersamento.getBicAppoggio());
                ctDatiSingoloVersamentoRPT.setCredenzialiPagatore(rptDatiSingoloVersamento.getCredenzialiPagatore());
                ctDatiSingoloVersamentoRPT.setCausaleVersamento(rptDatiSingoloVersamento.getCausaleVersamento());
                ctDatiSingoloVersamentoRPT
                        .setDatiSpecificiRiscossione(rptDatiSingoloVersamento.getDatiSpecificiRiscossione());
                if (rptDatiSingoloVersamento.getDatiMarcaBolloDigitale() != null) {
                    CtDatiMarcaBolloDigitale ctDatiMarcaBolloDigitale = new CtDatiMarcaBolloDigitale();
                    ctDatiMarcaBolloDigitale
                            .setTipoBollo(rptDatiSingoloVersamento.getDatiMarcaBolloDigitale().getTipoBollo().value());
                    ctDatiMarcaBolloDigitale.setHashDocumento(rptDatiSingoloVersamento.getDatiMarcaBolloDigitale()
                            .getHashDocumento().getBytes(StandardCharsets.UTF_8));
                    ctDatiMarcaBolloDigitale.setProvinciaResidenza(
                            rptDatiSingoloVersamento.getDatiMarcaBolloDigitale().getProvinciaResidenza());
                    ctDatiSingoloVersamentoRPT.setDatiMarcaBolloDigitale(ctDatiMarcaBolloDigitale);
                }
                ctDatiVersamentoRPT.getDatiSingoloVersamento().add(ctDatiSingoloVersamentoRPT);
            }

            ctRichiestaPagamentoTelematico.setDatiVersamento(ctDatiVersamentoRPT);

            ctRichiestaPagamentoTelematico.setVersioneOggetto(rpt.getVersioneOggetto());
            ctRichiestaPagamentoTelematico
                    .setIdentificativoMessaggioRichiesta(rpt.getIdentificativoMessaggioRichiesta());
            calendar.setTime(rpt.getDataOraMessaggioRichiesta());
            ctRichiestaPagamentoTelematico
                    .setDataOraMessaggioRichiesta(DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar));
            ctRichiestaPagamentoTelematico.setAutenticazioneSoggetto(rpt.getAutenticazioneSoggetto());

            RptContainerUpdater.setRptXml(rptContainer, xmlRptGeneration(ctRichiestaPagamentoTelematico));

        } catch (Exception e) {
            throw new XmlRptGeneratorException(e.getMessage());
        }
    }

    /**
     * Marshal's operation to generate the rpt in XML format
     * 
     * @param ctRichiestaPagamentoTelematico the bean to marshal
     * @return byte array of the rpt in XML format
     * @throws JAXBException
     * @throws PropertyException
     * @throws SAXException
     */
    private static byte[] xmlRptGeneration(CtRichiestaPagamentoTelematico ctRichiestaPagamentoTelematico)
            throws JAXBException, PropertyException, SAXException {
        JAXBContext jaxbContext = JAXBContext.newInstance(CtRichiestaPagamentoTelematico.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter sw = new StringWriter();
        JAXBElement<CtRichiestaPagamentoTelematico> jaxbElement = new JAXBElement<CtRichiestaPagamentoTelematico>(
                new QName(RptConstants.XSD_RPT_NAMESPACE, RptConstants.XSD_RPT_LOCAL_PART),
                CtRichiestaPagamentoTelematico.class, ctRichiestaPagamentoTelematico);
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        marshaller.setSchema(schemaFactory.newSchema(new File(RptConstants.XSD_RPT_PATH)));
        marshaller.marshal(jaxbElement, sw);
        return sw.toString().getBytes(StandardCharsets.UTF_8);
    }
}
