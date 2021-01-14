package pagopa.gov.it.toolkit.reader;

import javax.swing.JFrame;
import javax.swing.JTextField;

import pagopa.gov.it.toolkit.reader.business.ReaderInterfaceBusiness;

public class ReaderInterface {

    public static void main(String[] args) {
        JFrame jFrame = ReaderInterfaceBusiness.createFrame();
        JTextField csvFilePickerText = ReaderInterfaceBusiness.createCsvFilePicker(jFrame);
        JTextField outputFolderPickerText = ReaderInterfaceBusiness.createOutputFolderPicker(jFrame);
        JTextField logoPickerText = ReaderInterfaceBusiness.createLogoPicker(jFrame);
        ReaderInterfaceBusiness.createTemplateDownloaderButton(jFrame);
        ReaderInterfaceBusiness.createConfirmButton(jFrame, csvFilePickerText, outputFolderPickerText, logoPickerText);
        jFrame.setVisible(true);
    }
}
