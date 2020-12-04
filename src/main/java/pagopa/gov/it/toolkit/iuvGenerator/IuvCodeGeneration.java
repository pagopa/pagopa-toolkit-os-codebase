package pagopa.gov.it.toolkit.iuvGenerator;

import pagopa.gov.it.toolkit.iuvGenerator.bean.IuvCodeGenerator;
import pagopa.gov.it.toolkit.iuvGenerator.business.IuvCodeBusiness;

/**
 * Main class for the generation of the IUV code
 */
public class IuvCodeGeneration {

    /**
     * Generates the IUV code
     * 
     * @param auxDigit
     * @param segregationCode
     * @param applicationCode
     * @return String IUV code
     * @throws Exception
     * @see IuvCodeGenerator
     */
    public static String generate(int auxDigit, Integer segregationCode, Integer applicationCode) throws Exception {

        IuvCodeGenerator iuvCodeGenerator = new IuvCodeGenerator.Builder().setAuxDigit(auxDigit)
                .setSegregationCode(segregationCode).setApplicationCode(applicationCode).build();

        IuvCodeBusiness.validate(iuvCodeGenerator);

        return IuvCodeBusiness.generateIUV(auxDigit, segregationCode, applicationCode);
    }
}