package pagopa.gov.it.toolkit.iuvGenerator.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Properties;

/**
 * Management of the generation of the base iuv
 */
public class ApplicationProperties {

    private static final String APPLICATION_PROPERTIES_FILE_PATH = "resources";
    private static final String APPLICATION_PROPERTIES_FILE_NAME = "application.properties";

    private static final String APPLICATION_PROPERTIES_SEQUENCE_VALUE = "sequence.value";

    private static final Properties application = new Properties();

    static {
        if (!getApplicationPropertiesFile().exists()) {
            application.put(APPLICATION_PROPERTIES_SEQUENCE_VALUE, "0");
            writeProperties();
        }

        readProperties();
    }

    /**
     * Reads properties file
     */
    private static void readProperties() {
        try (InputStream input = new FileInputStream(getApplicationPropertiesFile())) {
            application.load(input);

        } catch (IOException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    /**
     * Writes properties file
     */
    private static void writeProperties() {
        try (OutputStream output = new FileOutputStream(getApplicationPropertiesFile())) {
            application.store(output, null);

        } catch (IOException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    /**
     * Get the properties file name with path
     * 
     * @return the properties file name with path
     */
    private static File getApplicationPropertiesFile() {
        return new File(new StringBuffer(APPLICATION_PROPERTIES_FILE_PATH).append("/")
                .append(APPLICATION_PROPERTIES_FILE_NAME).toString());
    }

    /**
     * Synchronized method that gives the next sequence value
     * 
     * @return the next sequence value
     */
    public static synchronized String getSequenceNextValue() {
        String value = application.getProperty(APPLICATION_PROPERTIES_SEQUENCE_VALUE);
        updateSequenceValue(new BigInteger(value).add(BigInteger.ONE).toString());
        return value;
    }

    /**
     * Update the sequence value on properties file
     * 
     * @param nextValue
     *            the next sequence value
     */
    private static void updateSequenceValue(String nextValue) {
        application.setProperty(APPLICATION_PROPERTIES_SEQUENCE_VALUE, nextValue);
        writeProperties();
    }
}
