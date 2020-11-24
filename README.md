# PagoPa-toolkit-os

<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/pagopa/pagopa-api">
    <img src="resources/media/pagopa-logo.png" alt="Logo" width="80" height="80">
  </a>
</p>


- [PagoPa-toolkit-os](#pagopa-toolkit-os)
  - [Context](#context)
  - [Prerequisites](#prerequisites)
  - [Feature Areas](#feature-areas)
  - [Future features _(under development)_](#future-features-under-development)
  - [Getstarted](#getstarted)
  
  
## Context

This repo is a _open source reference implementation_ for _Electronic payments system for Public Administration_ for [pagoPa](https://www.pagopa.gov.it/it/pagopa/) related to [SANP](https://docs.italia.it/italia/pagopa/pagopa-specifichepagamenti-docs/it/stabile/index.html) specifications.

```
git clone https://github.com/pagopa/pagopa-toolkit-os-codebase
```


## Prerequisites

- [maven](https://maven.apache.org/) 3.x
- [jdk](https://www.oracle.com/it/java/technologies/javase/javase-jdk8-downloads.html) _tested on 1.8.0_231_
- [git] (https://git-scm.com/)


## Feature Areas

The following is a list of our current product areas:

1) **IUV Generator**: generation of the [IUV](https://docs.italia.it/italia/pagopa/pagopa-codici-docs/it/stabile/_docs/Capitolo2.html#punti-di-generazione-del-codice-iuv) code.
Main operation: 
   - IuvCodeGeneration.generate(auxDigit, segregationCode, applicationCode) --> Iuv generation based on auxDigit, segregationCode and applicationCode values.

2) **DebtPositionGenerator**: generation of a [Debt Position](https://docs.italia.it/italia/pagopa/pagopa-specifichepagamenti-docs/it/stabile/_docs/SANP_2.2_Sez2_Cap02_GestionePosizioneDebitoria.html#) containing all the data indicating :
   - Payer (DebtPositionGeneration.generatePayer(uniqueIdentificationCode, uniqueIdentificationType, registry, address, numberStreet, locality, province, nation, postalCode, email, mobile));
   - Payment Data (DebtPositionGeneration.generatePaymentDetail(domainIdentifier, auxDigit,segregationCode, applicationCode, iuv, idTenant, totalAmountPayment, causal, expirationDate, specificCollectionData, documentNumber, installmentNumber, debitIban, debitBic));
   - Single Payment Data List (DebtPositionGeneration.generateSinglePaymentsDetail(amountSinglePayment, orderSinglePayment, causalDescriptionSinglePayment, creditIban, creditBic, supportIban, supportBic, datiMarcaBolloDigitale).
Main operation: 
   - DebtPositionGeneration.generate(payer, paymentDetail, singlePaymentsDetailList) --> Debt Position generation based on payer, paymentDetail and singlePaymentsDetailList values.
Other operations:
   - DebtPositionManagement.validate(debtPosition) --> Validation of a self-constructed Debt Position;
   - DebtPositionManagement.makeXXX(debtPosition) --> Changing the status of a Debt Position.

3) **Notice Payment Generator**: pdf generation of the [Payment Notice](https://docs.italia.it/italia/pagopa/pagopa-specifichepagamenti-docs/it/stabile/_docs/SANP_2.2_Sez3_Cap08_ModelloDati.html#avviso-digitale) starting from a List of _Debt Positions_
Main operation: 
   - PaymentNoticeGeneration.generate(debtPositionList, creditorInstitution) --> Payment Notice pdf generation based on debtPositionList and creditorInstitution values. N.B.: debtPositionList is recommended it has same Payer informations and same PaymentDetail causal.

4) **RPT Generator**: generation of an [RPT](https://docs.italia.it/italia/pagopa/pagopa-specifichepagamenti-docs/it/stabile/_docs/SANP_2.2_Sez3_Cap08_ModelloDati.html#richiesta-di-pagamento-telematica-rpt) (both object and xml) containing all the data indicating :
   - Dominio (RptGeneration.generateDominio(identificativoDominio, identificativoStazioneRichiedente));
   - Soggetto Versante (RptGeneration.generateSoggetto(identificativoUnivoco, anagrafica, indirizzo, email));
   - Soggetto Pagatore (RptGeneration.generateSoggetto(identificativoUnivoco, anagrafica, indirizzo, email));
   - Ente Beneficiario (RptGeneration.generateEnteBeneficiario(identificativoUnivoco, denominazione, codiceUnitOper, denomUnitOper, indirizzo));
   - Dati Versamento (RptGeneration.generateDatiVersamento(importoTotaleDaVersare, tipoVersamento, identificativoUnivocoVersamento, ibanAddebito, bicAddebito, firmaRicevuta));
   - Dati Singolo Versamento List (RptGeneration.generateDatiSingoloVersamento(importoSingoloVersamento, commissioneCaricoPA, ibanAccredito, bicAccredito, ibanAppoggio, bicAppoggio, credenzialiPagatore, descrizioneCausaleVersamento, iuv, datiSpecificiRiscossione, datiMarcaBolloDigitale, ordineVersamento)).
Main operations: 
   - RptGeneration.generateRptElement(versioneOggetto, dominio, autenticazioneSoggetto, soggettoVersante, soggettoPagatore, enteBeneficiario, datiVersamento, datiSingoloVersamentoList) --> Generation of an RPT object
   - RptGeneration.generate(idTenant, rpt) --> Generation based on a RPT object.
   - RptGeneration.generate(idTenant, debtPosition, enteBeneficiario, commissioneCaricoPA) --> Generation based on a Debt Position.
Other operations:
   - RptGeneration.validate(rptContainer) --> Validation of a self-constructed RPT;
   - RptGeneration.makeXXX(rptContainer) --> Changing the status of a RPT

## Future features _(under development)_



##  Getstarted

Developer's guidelines who wants build and run tests present in the repo 🚀 :

1. to build e run all test typing 

```
mvn install -X -U
```

after that if all rights, you will see something like that below 👍

```
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  11.334 s
[INFO] Finished at: 2020-11-18T11:39:24+01:00
[INFO] ------------------------------------------------------------------------
```
to build [javadoc](https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html) documentation typing : 

```
mvn javadoc:javadoc
```

and then click [here](file://target/site/apidocs/index.html) to show it.

> NOTE : if you want to run a specific test ( or a subset ), typing example 
```
mvn -Dtest=Iuv* test
```
> in this case, `mvn` will run all test case related to *IUV* that match with _Iuv*_
> 
> to more details see [documentation](https://maven.apache.org/plugins-archives/maven-surefire-plugin-2.12.4/examples/single-test.html)

