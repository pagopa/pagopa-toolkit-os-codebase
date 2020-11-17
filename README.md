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
## Context

This repo is a _open source reference implementation_ for _Electronic payments system for Public Administration_ for [pagoPa](https://www.pagopa.gov.it/it/pagopa/) related to [SANP](https://docs.italia.it/italia/pagopa/pagopa-specifichepagamenti-docs/it/stabile/index.html) specifications.


## Prerequisites

- [maven](https://maven.apache.org/) 3.x
- [jdk](https://www.oracle.com/it/java/technologies/javase/javase-jdk8-downloads.html) _tested on 1.8.0_231_

## Feature Areas
The following is a list of our current product areas:

1) **IUV Generator**: generation of the [IUV](https://docs.italia.it/italia/pagopa/pagopa-codici-docs/it/stabile/_docs/Capitolo2.html#punti-di-generazione-del-codice-iuv) code 
2) **DebtPositionGenerator**: generation of a [Debt Position](https://docs.italia.it/italia/pagopa/pagopa-specifichepagamenti-docs/it/stabile/_docs/SANP_2.2_Sez2_Cap02_GestionePosizioneDebitoria.html#) containing all the data indicating :
   - Payer
   - Payment Data (amount, due date, etc.)
   - Single Payment Data List

3) **Notice Payment Generator**: pdf generation of the [Payment Notice](https://docs.italia.it/italia/pagopa/pagopa-specifichepagamenti-docs/it/stabile/_docs/SANP_2.2_Sez3_Cap08_ModelloDati.html#avviso-digitale) starting from a List of _Debt Positions_

## Future features _(under development)_
1) **RPT Generator**: generation of an [RPT](https://docs.italia.it/italia/pagopa/pagopa-specifichepagamenti-docs/it/stabile/_docs/SANP_2.2_Sez3_Cap08_ModelloDati.html#richiesta-di-pagamento-telematica-rpt) (both object and xml)
