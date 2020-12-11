# pagopa-toolkit-os-codebase

<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/pagopa/pagopa-api">
    <img src="resources/media/pagopa-logo.png" alt="Logo" width="80" height="80">
  </a>
</p>


- [pagopa-toolkit-os-codebase](#pagopa-toolkit-os-codebase)
  - [Context](#context)
  - [Prerequisites](#prerequisites)
  - [Feature Areas](#feature-areas)
    - [**IUV Generator**](#iuv-generator)
    - [**DebtPositionGenerator**](#debtpositiongenerator)
    - [**Notice Payment Generator**](#notice-payment-generator)
    - [**RPT Generator**](#rpt-generator)
  - [Future-features-under-development](#future-features-under-development)
  - [Getstarted](#getstarted)
  
  
## Context

This repo is a _open source reference implementation_ for _Electronic payments system for Public Administration_ for [pagoPa](https://www.pagopa.gov.it/it/pagopa/) related to [SANP](https://docs.italia.it/italia/pagopa/pagopa-specifichepagamenti-docs/it/stabile/index.html) specifications.

```
git clone https://github.com/pagopa/pagopa-toolkit-os-codebase
```


## Prerequisites

- [maven](https://maven.apache.org/) 3.x
- [jdk](https://www.oracle.com/it/java/technologies/javase/javase-jdk8-downloads.html) _tested on 1.8.0_231_
- [git](https://git-scm.com/)


## Feature Areas

The following is a list of our current product areas:

### **IUV Generator**

generation of the [IUV](https://docs.italia.it/italia/pagopa/pagopa-codici-docs/it/stabile/_docs/Capitolo2.html#punti-di-generazione-del-codice-iuv) code.

Main operation:

- Iuv generation based on auxDigit, segregationCode and applicationCode values:

	```
	IuvCodeGeneration.generate(auxDigit, segregationCode, applicationCode)
	```

### **DebtPositionGenerator**
generation of a [Debt Position](https://docs.italia.it/italia/pagopa/pagopa-specifichepagamenti-docs/it/stabile/_docs/SANP_2.2_Sez2_Cap02_GestionePosizioneDebitoria.html#) containing all the data indicating:

- Payer:
```
DebtPositionGeneration.generatePayer(uniqueIdentificationCode, uniqueIdentificationType, registry, address, numberStreet, locality, province, nation, postalCode, email, mobile)
```
- Payment Data:
```
(DebtPositionGeneration.generatePaymentDetail(domainIdentifier, auxDigit,segregationCode, applicationCode, iuv, idTenant, totalAmountPayment, causal, expirationDate, specificCollectionData, documentNumber, installmentNumber, debitIban, debitBic)
```
- Single Payment Data List:
```
DebtPositionGeneration.generateSinglePaymentsDetail(amountSinglePayment, orderSinglePayment, causalDescriptionSinglePayment, creditIban, creditBic, supportIban, supportBic, datiMarcaBolloDigitale)
```
Main operation:
- Debt Position generation based on payer, paymentDetail and singlePaymentsDetailList values:
```
DebtPositionGeneration.generate(payer, paymentDetail, singlePaymentsDetailList)
```
Other operations:
- Validation of a self-constructed Debt Position:
```
DebtPositionManagement.validate(debtPosition)
```
- Changing the status of a Debt Position:
```
DebtPositionManagement.makeXXX(debtPosition)
```

### **Notice Payment Generator**
pdf generation of the [Payment Notice](https://docs.italia.it/italia/pagopa/pagopa-specifichepagamenti-docs/it/stabile/_docs/SANP_2.2_Sez3_Cap08_ModelloDati.html#avviso-digitale) starting from a List of _Debt Positions_

Main operation:
- Payment Notice pdf generation based on debtPositionList and creditorInstitution values:
```
PaymentNoticeGeneration.generate(debtPositionList, creditorInstitution, isModello1or2)
```
> NOTE : the list of DebtPosition is recommended it has same Payer informations and same PaymentDetail causal.

### **RPT Generator**
generation of an [RPT](https://docs.italia.it/italia/pagopa/pagopa-specifichepagamenti-docs/it/stabile/_docs/SANP_2.2_Sez3_Cap08_ModelloDati.html#richiesta-di-pagamento-telematica-rpt) (both object and xml) containing all the data indicating:
- Dominio:
```
RptGeneration.generateDominio(identificativoDominio, identificativoStazioneRichiedente)
```
- Soggetto Versante:
```
RptGeneration.generateSoggetto(identificativoUnivoco, anagrafica, indirizzo, email)
```
- Soggetto Pagatore:
```
RptGeneration.generateSoggetto(identificativoUnivoco, anagrafica, indirizzo, email)
```
- Ente Beneficiario:
```
RptGeneration.generateEnteBeneficiario(identificativoUnivoco, denominazione, codiceUnitOper, denomUnitOper, indirizzo)
```
- Dati Versamento:
```
RptGeneration.generateDatiVersamento(importoTotaleDaVersare, tipoVersamento, identificativoUnivocoVersamento, ibanAddebito, bicAddebito, firmaRicevuta)
```
- Dati Singolo Versamento List:
```
RptGeneration.generateDatiSingoloVersamento(importoSingoloVersamento, commissioneCaricoPA, ibanAccredito, bicAccredito, ibanAppoggio, bicAppoggio, credenzialiPagatore, descrizioneCausaleVersamento, iuv, datiSpecificiRiscossione, datiMarcaBolloDigitale, ordineVersamento)
```
Main operations:
- Generation of an RPT object:
```
RptGeneration.generateRptElement(versioneOggetto, dominio, autenticazioneSoggetto, soggettoVersante, soggettoPagatore, enteBeneficiario, datiVersamento, datiSingoloVersamentoList)
```
- Generation based on a RPT object:
```
RptGeneration.generate(idTenant, rpt)
```
- Generation based on a Debt Position:
```
RptGeneration.generate(idTenant, debtPosition, enteBeneficiario, commissioneCaricoPA)
```
Other operations:
- Validation of a self-constructed RPT:
```
RptGeneration.validate(rptContainer)
```
- Changing the status of a RPT
```
RptGeneration.makeXXX(rptContainer)
```

## Future-features-under-development
Input/output layer for generating the PDF of the payment notice and more.


## Getstarted

Developer's guidelines who wants build and run tests present in the repo 🚀 :

to build e run all test typing 

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
> **NOTE** : after this command you'll see some strange change to `resources/application.properties`
> this is necessary to be sure of the uniqueness of generated IUV, you can discard it typing `git checkout -- resources/application.properties`

Code coverage test related to unit test is available [here](http://target/site/jacoco/index.html) 

To build [javadoc](https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html) documentation typing : 

```
mvn javadoc:javadoc
```

and then click [here](http://target/site/apidocs/index.html) to show it :

> NOTE : if you want to run a specific test ( or a subset ), typing example 
```
mvn -Dtest=Iuv* test
```
> in this case, `mvn` will run all test case related to *IUV* that match with _Iuv*_, to more details see [documentation](https://maven.apache.org/plugins-archives/maven-surefire-plugin-2.12.4/examples/single-test.html)

> **NOTE** : after every change run command `mvn checkstyle:checkstyle` see [here](https://maven.apache.org/plugins/maven-checkstyle-plugin/usage.html)