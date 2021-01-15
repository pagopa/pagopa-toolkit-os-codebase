package pagopa.gov.it.toolkit.reader.business;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import pagopa.gov.it.toolkit.reader.business.processor.ReaderFileProcessor;
import pagopa.gov.it.toolkit.reader.business.template.ReaderTemplateFileHandler;
import pagopa.gov.it.toolkit.reader.constants.ReaderInterfaceConstants;
import pagopa.gov.it.toolkit.reader.enumeration.ReaderStatus;
import pagopa.gov.it.toolkit.reader.exception.ReaderException;

/**
 * Creation of GUI components
 */
public class ReaderInterfaceBusiness {

    /**
     * Creates the GUI frame
     * 
     * @return JFrame
     */
    public static JFrame createFrame() {
        JFrame jFrame = createFrame(ReaderInterfaceConstants.RI_FRAME_TITLE, ReaderInterfaceConstants.RI_FRAME_WIDTH,
                ReaderInterfaceConstants.RI_FRAME_HEIGHT);
        return jFrame;
    }

    /**
     * Creates the GUI csv file picker
     * 
     * @param jFrame
     *            GUI frame
     * @return JTextField
     */
    public static JTextField createCsvFilePicker(JFrame jFrame) {
        JLabel csvFilePickerLabel = createLabel(ReaderInterfaceConstants.RI_CSV_FILE_PICKER_LABEL,
                ReaderInterfaceConstants.RI_CSV_FILE_PICKER_LABEL_X_COORDINATE,
                ReaderInterfaceConstants.RI_CSV_FILE_PICKER_LABEL_Y_COORDINATE,
                ReaderInterfaceConstants.RI_CSV_FILE_PICKER_LABEL_WIDTH,
                ReaderInterfaceConstants.RI_CSV_FILE_PICKER_LABEL_HEIGHT);
        jFrame.add(csvFilePickerLabel);
        JTextField csvFilePickerText = createTextField(ReaderInterfaceConstants.RI_CSV_FILE_PICKER_TEXT_X_COORDINATE,
                ReaderInterfaceConstants.RI_CSV_FILE_PICKER_TEXT_Y_COORDINATE,
                ReaderInterfaceConstants.RI_CSV_FILE_PICKER_TEXT_WIDTH,
                ReaderInterfaceConstants.RI_CSV_FILE_PICKER_TEXT_HEIGHT);
        jFrame.add(csvFilePickerText);
        JButton csvFilePickerButton = createButton(ReaderInterfaceConstants.RI_CSV_FILE_PICKER_BUTTON,
                ReaderInterfaceConstants.RI_CSV_FILE_PICKER_BUTTON_X_COORDINATE,
                ReaderInterfaceConstants.RI_CSV_FILE_PICKER_BUTTON_Y_COORDINATE,
                ReaderInterfaceConstants.RI_CSV_FILE_PICKER_BUTTON_WIDTH,
                ReaderInterfaceConstants.RI_CSV_FILE_PICKER_BUTTON_HEIGHT);
        jFrame.add(csvFilePickerButton);
        csvFilePickerButton.addActionListener(createActionListenerForFilePickerButton(jFrame, csvFilePickerText,
                ReaderInterfaceConstants.RI_CSV_FILE_PICKER_BUTTON_DESCRIPTION,
                ReaderInterfaceConstants.RI_CSV_FILE_PICKER_ACCEPTED_EXT));
        return csvFilePickerText;
    }

    /**
     * Creates the GUI output folder where to create RPT and PaymentNotice files
     * 
     * @param jFrame
     *            GUI frame
     * @return JTextField
     */
    public static JTextField createOutputFolderPicker(JFrame jFrame) {
        JLabel outputFolderPickerLabel = createLabel(ReaderInterfaceConstants.RI_OUTPUT_FOLDER_LABEL,
                ReaderInterfaceConstants.RI_OUTPUT_FOLDER_LABEL_X_COORDINATE,
                ReaderInterfaceConstants.RI_OUTPUT_FOLDER_LABEL_Y_COORDINATE,
                ReaderInterfaceConstants.RI_OUTPUT_FOLDER_LABEL_WIDTH,
                ReaderInterfaceConstants.RI_OUTPUT_FOLDER_LABEL_HEIGHT);
        jFrame.add(outputFolderPickerLabel);
        JTextField outputFolderPickerText = createTextField(ReaderInterfaceConstants.RI_OUTPUT_FOLDER_TEXT_X_COORDINATE,
                ReaderInterfaceConstants.RI_OUTPUT_FOLDER_TEXT_Y_COORDINATE,
                ReaderInterfaceConstants.RI_OUTPUT_FOLDER_TEXT_WIDTH,
                ReaderInterfaceConstants.RI_OUTPUT_FOLDER_TEXT_HEIGHT);
        jFrame.add(outputFolderPickerText);
        JButton outputFolderPickerButton = createButton(ReaderInterfaceConstants.RI_OUTPUT_FOLDER_BUTTON,
                ReaderInterfaceConstants.RI_OUTPUT_FOLDER_BUTTON_X_COORDINATE,
                ReaderInterfaceConstants.RI_OUTPUT_FOLDER_BUTTON_Y_COORDINATE,
                ReaderInterfaceConstants.RI_OUTPUT_FOLDER_BUTTON_WIDTH,
                ReaderInterfaceConstants.RI_OUTPUT_FOLDER_BUTTON_HEIGHT);
        jFrame.add(outputFolderPickerButton);
        outputFolderPickerButton
                .addActionListener(createActionListenerForFolderPickerButton(jFrame, outputFolderPickerText));
        return outputFolderPickerText;
    }

    /**
     * Creates the GUI logo picker
     * 
     * @param jFrame
     *            GUI frame
     * @return JTextField
     */
    public static JTextField createLogoPicker(JFrame jFrame) {
        JLabel logoPickerLabel = createLabel(ReaderInterfaceConstants.RI_LOGO_PICKER_LABEL,
                ReaderInterfaceConstants.RI_LOGO_PICKER_LABEL_X_COORDINATE,
                ReaderInterfaceConstants.RI_LOGO_PICKER_LABEL_Y_COORDINATE,
                ReaderInterfaceConstants.RI_LOGO_PICKER_LABEL_WIDTH,
                ReaderInterfaceConstants.RI_LOGO_PICKER_LABEL_HEIGHT);
        jFrame.add(logoPickerLabel);
        JTextField logoPickerText = createTextField(ReaderInterfaceConstants.RI_LOGO_PICKER_TEXT_X_COORDINATE,
                ReaderInterfaceConstants.RI_LOGO_PICKER_TEXT_Y_COORDINATE,
                ReaderInterfaceConstants.RI_LOGO_PICKER_TEXT_WIDTH,
                ReaderInterfaceConstants.RI_LOGO_PICKER_TEXT_HEIGHT);
        jFrame.add(logoPickerText);
        JButton logoPickerButton = createButton(ReaderInterfaceConstants.RI_LOGO_PICKER_BUTTON,
                ReaderInterfaceConstants.RI_LOGO_PICKER_BUTTON_X_COORDINATE,
                ReaderInterfaceConstants.RI_LOGO_PICKER_BUTTON_Y_COORDINATE,
                ReaderInterfaceConstants.RI_LOGO_PICKER_BUTTON_WIDTH,
                ReaderInterfaceConstants.RI_LOGO_PICKER_BUTTON_HEIGHT);
        jFrame.add(logoPickerButton);
        logoPickerButton.addActionListener(createActionListenerForFilePickerButton(jFrame, logoPickerText,
                ReaderInterfaceConstants.RI_LOGO_PICKER_BUTTON_DESCRIPTION,
                ReaderInterfaceConstants.RI_LOGO_PICKER_ACCEPTED_EXT));
        return logoPickerText;
    }

    /**
     * Creates the GUI templates downloader button
     * 
     * @param jFrame
     *            GUI frame
     */
    public static void createTemplateDownloaderButton(JFrame jFrame) {
        JButton templateDownloaderButton = createButton(ReaderInterfaceConstants.RI_TEMPLATE_DOWNLOADER_BUTTON,
                ReaderInterfaceConstants.RI_TEMPLATE_DOWNLOADER_BUTTON_X_COORDINATE,
                ReaderInterfaceConstants.RI_TEMPLATE_DOWNLOADER_BUTTON_Y_COORDINATE,
                ReaderInterfaceConstants.RI_TEMPLATE_DOWNLOADER_BUTTON_WIDTH,
                ReaderInterfaceConstants.RI_TEMPLATE_DOWNLOADER_BUTTON_HEIGHT);
        jFrame.add(templateDownloaderButton);
        templateDownloaderButton.addActionListener(createActionListenerForTemplatesDownloaderButton(jFrame));
    }

    /**
     * Creates the GUI confirm button
     * 
     * @param jFrame
     *            GUI frame
     * @param csvFilePickerText
     *            csv input picker component
     * @param outputFolderPickerText
     *            output folder picker component
     * @param logoPickerText
     *            logo picker component
     */
    public static void createConfirmButton(JFrame jFrame, JTextField csvFilePickerText,
            JTextField outputFolderPickerText, JTextField logoPickerText) {
        JButton confirmButton = createButton(ReaderInterfaceConstants.RI_CONFIRM_BUTTON,
                ReaderInterfaceConstants.RI_CONFIRM_BUTTON_X_COORDINATE,
                ReaderInterfaceConstants.RI_CONFIRM_BUTTON_Y_COORDINATE,
                ReaderInterfaceConstants.RI_CONFIRM_BUTTON_WIDTH, ReaderInterfaceConstants.RI_CONFIRM_BUTTON_HEIGHT);
        jFrame.add(confirmButton);
        confirmButton.addActionListener(createActionListenerForConfirmButton(jFrame, csvFilePickerText,
                outputFolderPickerText, logoPickerText));
    }

    private static JFrame createFrame(String text, int width, int height) {
        JFrame jFrame = new JFrame(text);
        jFrame.setSize(width, height);
        jFrame.setLayout(null);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        return jFrame;
    }

    private static JLabel createLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        return label;
    }

    private static JTextField createTextField(int x, int y, int width, int height) {
        JTextField text = new JTextField();
        text.setBounds(x, y, width, height);
        text.setEditable(false);
        text.setBackground(Color.WHITE);
        return text;
    }

    private static JButton createButton(String text, int x, int y, int width, int height) {
        JButton btnPickCsvFile = new JButton(text);
        btnPickCsvFile.setBounds(x, y, width, height);
        return btnPickCsvFile;
    }

    private static ActionListener createActionListenerForFilePickerButton(JFrame jFrame, JTextField textField,
            String description, String acceptedExtension) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.addChoosableFileFilter(new FileFilter() {
                    @Override
                    public String getDescription() {
                        return description;
                    }

                    @Override
                    public boolean accept(File f) {
                        if (f.isDirectory()) {
                            return true;
                        }
                        return f.getName().toLowerCase().endsWith(acceptedExtension);
                    }
                });
                int returnValue = jFileChooser.showOpenDialog(jFrame);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String fileName = jFileChooser.getSelectedFile().getAbsolutePath();
                    textField.setText(fileName);
                }
            }
        };
    }

    private static ActionListener createActionListenerForFolderPickerButton(JFrame jFrame, JTextField outputFolder) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnValue = jFileChooser.showOpenDialog(jFrame);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String folderPath = jFileChooser.getSelectedFile().getAbsolutePath();
                    outputFolder.setText(folderPath);
                }
            }
        };
    }

    private static ActionListener createActionListenerForTemplatesDownloaderButton(JFrame jFrame) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnValue = jFileChooser.showOpenDialog(jFrame);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String folderPath = jFileChooser.getSelectedFile().getAbsolutePath();
                    ReaderTemplateFileHandler readerTemplateFileHandler = new ReaderTemplateFileHandler();
                    try {
                        readerTemplateFileHandler.moveFilesToFolder(folderPath);
                        String resultMessage = ReaderInterfaceConstants.RI_TEMPLATE_DOWNLOADER_SUCCESS_MSG + folderPath;
                        JOptionPane.showMessageDialog(jFrame, resultMessage, ReaderInterfaceConstants.MESSAGE_TITLE,
                                JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(jFrame, ReaderInterfaceConstants.RI_TEMPLATE_DOWNLOADER_ERROR_MSG,
                                ReaderInterfaceConstants.MESSAGE_TITLE, JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        };
    }

    private static ActionListener createActionListenerForConfirmButton(JFrame jFrame, JTextField csvFilePicker,
            JTextField outputFolder, JTextField logoPicker) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReaderFileProcessor readerFileProcessor = new ReaderFileProcessor();
                ReaderStatus readerStatus = null;
                try {
                    readerStatus = readerFileProcessor.processCsvFile(csvFilePicker.getText(), outputFolder.getText(),
                            logoPicker.getText());
                } catch (ReaderException readerException) {
                    JOptionPane.showMessageDialog(jFrame, readerException.getMessage(),
                            ReaderInterfaceConstants.MESSAGE_TITLE, JOptionPane.ERROR_MESSAGE);
                } catch (IOException ioException) {
                    readerStatus = ReaderStatus.KO;
                }
                if (readerStatus != null) {
                    switch (readerStatus) {
                    case OK:
                        JOptionPane.showMessageDialog(jFrame, ReaderInterfaceConstants.RI_CONFIRM_SUCCESS_MSG,
                                ReaderInterfaceConstants.MESSAGE_TITLE, JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case WARNING:
                        JOptionPane.showMessageDialog(jFrame, ReaderInterfaceConstants.RI_CONFIRM_WARNING_MSG,
                                ReaderInterfaceConstants.MESSAGE_TITLE, JOptionPane.WARNING_MESSAGE);
                        break;
                    case KO:
                        JOptionPane.showMessageDialog(jFrame, ReaderInterfaceConstants.RI_CONFIRM_ERROR_MSG,
                                ReaderInterfaceConstants.MESSAGE_TITLE, JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                }
            }
        };
    }
}
