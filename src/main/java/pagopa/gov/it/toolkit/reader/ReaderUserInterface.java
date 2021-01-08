package pagopa.gov.it.toolkit.reader;

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

public class ReaderUserInterface {
	private static final String MESSAGE_TITLE = "Esito operazione";
	
	public static void main(String[] args) {
		JFrame jFrame = new JFrame("Caricamento posizioni debitorie da file CSV");
		jFrame.setSize(705, 400);
		jFrame.setLayout(null);
		jFrame.setResizable(false);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblCsvFile = new JLabel("File posizioni debtiorie (.csv):");
		lblCsvFile.setBounds(10, 10, 350, 50);
		jFrame.add(lblCsvFile);
		
		JTextField txtCsvFile = new JTextField();
		txtCsvFile.setBounds(10, 50, 550, 30);
		txtCsvFile.setEditable(false);
		txtCsvFile.setBackground(Color.WHITE);
		jFrame.add(txtCsvFile);
		
		JButton btnPickCsvFile = new JButton("Scegli file");
		btnPickCsvFile.setBounds(570, 50, 115, 30);
		jFrame.add(btnPickCsvFile);
		
		JLabel lblOutputFolder = new JLabel("Cartella di destinazione:");
		lblOutputFolder.setBounds(10, 100, 350, 50);
		jFrame.add(lblOutputFolder);
		
		JTextField txtOutputFolder = new JTextField();
		txtOutputFolder.setBounds(10, 140, 550, 30);
		txtOutputFolder.setEditable(false);
		txtOutputFolder.setBackground(Color.WHITE);
		jFrame.add(txtOutputFolder);
		
		JButton btnPickOutputFolder = new JButton("Scegli cartella");
		btnPickOutputFolder.setBounds(570, 140, 115, 30);
		jFrame.add(btnPickOutputFolder);
		
		JLabel lblLogo = new JLabel("File logo ente (.png, dimensione consigliata: 30x30mm):");
		lblLogo.setBounds(10, 190, 350, 50);
		jFrame.add(lblLogo);
		
		JTextField txtLogo = new JTextField();
		txtLogo.setBounds(10, 230, 550, 30);
		txtLogo.setEditable(false);
		txtLogo.setBackground(Color.WHITE);
		jFrame.add(txtLogo);
		
		JButton btnPickLogo = new JButton("Scegli file");
		btnPickLogo.setBounds(570, 230, 115, 30);
		jFrame.add(btnPickLogo);
		
		JButton btnDownloadCsvTemplate = new JButton("<html>Scarica<br/>template</html>");
		btnDownloadCsvTemplate.setBounds(245, 300, 100, 50);
		jFrame.add(btnDownloadCsvTemplate);
		
		JButton btnConfirm = new JButton("Conferma");
		btnConfirm.setBounds(355, 300, 100, 50);
		jFrame.add(btnConfirm);
		
		jFrame.setVisible(true);
		
		btnPickCsvFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.addChoosableFileFilter(new FileFilter() {
					@Override
					public String getDescription() {
						return "File CSV (*.csv)";
					}
					
					@Override
					public boolean accept(File f) {
						if(f.isDirectory()) {
							return true;
						}
						return f.getName().toLowerCase().endsWith(".csv");
					}
				});
				int returnValue = jFileChooser.showOpenDialog(jFrame);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					String fileName = jFileChooser.getSelectedFile().getAbsolutePath();
					txtCsvFile.setText(fileName);
				}
			}
		});
		
		btnPickOutputFolder.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnValue = jFileChooser.showOpenDialog(jFrame);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					String folderPath = jFileChooser.getSelectedFile().getAbsolutePath();
					txtOutputFolder.setText(folderPath);
				}
			}
		});
		
		btnPickLogo.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.addChoosableFileFilter(new FileFilter() {
					@Override
					public String getDescription() {
						return "File PNG (*.png)";
					}
					
					@Override
					public boolean accept(File f) {
						if(f.isDirectory()) {
							return true;
						}
						return f.getName().toLowerCase().endsWith(".png");
					}
				});
				int returnValue = jFileChooser.showOpenDialog(jFrame);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					String fileName = jFileChooser.getSelectedFile().getAbsolutePath();
					txtLogo.setText(fileName);
				}
			}
		});
		
		btnDownloadCsvTemplate.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnValue = jFileChooser.showOpenDialog(jFrame);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					String folderPath = jFileChooser.getSelectedFile().getAbsolutePath();
					ReaderTemplateCsvFileHandler readerTemplateCsvFileHandler = new ReaderTemplateCsvFileHandler();
					try {
						readerTemplateCsvFileHandler.moveFileToFolder(folderPath);
						String resultMessage = "Il template è stato stato scaricato e posizionato con successo nella seguente cartella:\n" + folderPath;
						JOptionPane.showMessageDialog(jFrame, resultMessage, MESSAGE_TITLE, JOptionPane.INFORMATION_MESSAGE);
					} catch(Exception exception) {
						JOptionPane.showMessageDialog(jFrame, "Si è verificato un errore durante il download del template", MESSAGE_TITLE, JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		btnConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ReaderProcessFile readerProcessFile = new ReaderProcessFile();
				ReaderStatus readerStatus = null;
				
				try {					
					readerStatus = readerProcessFile.processCsvFile(txtCsvFile.getText(), txtOutputFolder.getText(), txtLogo.getText());
				} catch(ReaderException readerException) {
					JOptionPane.showMessageDialog(jFrame, readerException.getMessage(), MESSAGE_TITLE, JOptionPane.ERROR_MESSAGE);
				} catch(IOException ioException) {
					readerStatus = ReaderStatus.KO;
				}
				
				if(readerStatus != null) {
					switch(readerStatus) {
						case OK:
							JOptionPane.showMessageDialog(jFrame, "Il caricamento è stato completato con successo", MESSAGE_TITLE, JOptionPane.INFORMATION_MESSAGE);
							break;
						case WARNING:
							JOptionPane.showMessageDialog(jFrame, "Il caricamento è stato completato con successo, ma alcune posizioni non sono state caricate.\n"
									+ "Consultare l’esito per ulteriori informazioni", MESSAGE_TITLE, JOptionPane.WARNING_MESSAGE);
							break;
						case KO:
							JOptionPane.showMessageDialog(jFrame, "Il caricamento è terminato in errore. Ripetere nuovamente l’operazione", MESSAGE_TITLE, 
									JOptionPane.ERROR_MESSAGE);
							break;
					}
				}
			}
		});
	}
}
