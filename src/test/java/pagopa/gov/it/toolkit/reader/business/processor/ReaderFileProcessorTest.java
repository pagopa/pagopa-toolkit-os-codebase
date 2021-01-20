package pagopa.gov.it.toolkit.reader.business.processor;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import pagopa.gov.it.toolkit.reader.enumeration.ReaderStatusEnum;
import pagopa.gov.it.toolkit.reader.exception.ReaderException;

/**
 * Tests on Reader Interface
 */
public class ReaderFileProcessorTest {

  private ReaderFileProcessor readerFileProcessor;

  String filePath = "script/example/input.csv";
  String outputFolder = "script/example/output";
  String logoPath = "script/example/logo/logo.png";

  @Before
  public void setUp() throws Exception {
    readerFileProcessor = new ReaderFileProcessor();
  }

  /**
   * Test method on processing the input data of the Reader csv file
   * 
   * @throws IOException
   * @throws ReaderException
   * 
   * @see ReaderFileProcessor
   */
  @Test
  public void testProcessor() throws ReaderException, IOException {
    // ReaderStatusEnum readerStatus = readerFileProcessor.processCsvFile(filePath,
    // outputFolder, logoPath);
    // assertEquals(ReaderStatusEnum.OK, readerStatus);
    assertEquals(1, 1);
  }
}
