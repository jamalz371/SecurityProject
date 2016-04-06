package part2;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import part1.AES_128;
import part1.RSA_2048;
import part1.SHA_3;


public class Window extends JFrame{

	private static final long serialVersionUID = 1L;

	public static void main(String[] args){
		new Window();
		/*Window w = new Window();
		try {
			w.encryptFileAES_128();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	class BrowseFiles implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	        JFileChooser c = new JFileChooser();
	        // Open dialog window to browse files
	        int rVal = c.showOpenDialog(Window.this);
	        if (rVal == JFileChooser.APPROVE_OPTION){
	        	textChooseFile.setText(c.getSelectedFile().getAbsolutePath());
	        	System.out.println("path : " + textChooseFile.getText());
	      }
	    }
	}
	
	class BrowseFileSign implements ActionListener {
		public void actionPerformed(ActionEvent e) {
	        JFileChooser c2 = new JFileChooser();
	        // Open dialog window to browse files
	        int rVal2 = c2.showOpenDialog(Window.this);
	        if (rVal2 == JFileChooser.APPROVE_OPTION){
	        	textNameSignature.setText(c2.getSelectedFile().getAbsolutePath());
	      }
	    }
	}
	
	class BrowseEnc implements ActionListener {
		public void actionPerformed(ActionEvent e) {
	        JFileChooser c3 = new JFileChooser();
	        // Open dialog window to browse files
	        int rVal3 = c3.showOpenDialog(Window.this);
	        if (rVal3 == JFileChooser.APPROVE_OPTION){
	        	textEnc.setText(c3.getSelectedFile().getAbsolutePath());
	      }
	    }
	}
	
	class BrowseDec implements ActionListener {
		public void actionPerformed(ActionEvent e) {
	        JFileChooser c4 = new JFileChooser();
	        // Open dialog window to browse files
	        int rVal4 = c4.showOpenDialog(Window.this);
	        if (rVal4 == JFileChooser.APPROVE_OPTION){
	        	textDec.setText(c4.getSelectedFile().getAbsolutePath());
	      }
	    }
	}
	
	class CalculateSign implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String nameChoose = textNameSignature.getText();
			byte[] finRes = readFile(nameChoose);
			byte[] res = SHA_3.digest(finRes);
			textResultSignature.setText(new String(res));
		}
	}
	
	class expAES implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			boxAESCheck = ! boxAESCheck;
		}
	}
	
	class expRSA implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			boxRSACheck = ! boxRSACheck;
		}
	}
	
	class GenerateFile implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(boxAESCheck){
				try {
					exportAES_128();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(boxRSACheck){
				try {
					exportRSA_2048();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
				
		}
	}
	
	class encryptThisAES implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			encAESCheck = ! encAESCheck;
		}
	}
	
	class encryptThisRSA implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			encRSACheck = ! encRSACheck;
		}
	}
	
	class decryptThisAES implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			decAESCheck = ! decAESCheck;
		}
	}
	
	class decryptThisRSA implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			decRSACheck = ! decRSACheck;
		}
	}
	
	class GenerateEncryptedFile implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(encAESCheck){
				String tmpPath = textEnc.getText();
				File encFile = new File("");
				String nameEncryptedFile = encFile.getAbsolutePath() + File.separator + "encrypted_File_AES.txt";
				byte[] getFileContent = readFile(tmpPath);
				SessionKey.setKeyAES();
				SecretKey curKey = SessionKey.getKeyAES();
				byte[] encryptedFileContent = AES_128.encrypt(curKey.getEncoded(),getFileContent);
				writeFile(encryptedFileContent,nameEncryptedFile);
			}
			else if(encRSACheck){
				String tmpPath = textEnc.getText();
				File encFile = new File("");
				String nameEncryptedFile = encFile.getAbsolutePath() + File.separator + "encrypted_File_RSA.txt";
				byte[] getFileContent = readFile(tmpPath);
				try {
					String testContent = new String(getFileContent,"UTF-8");
					System.out.println("Contenu : " + testContent);
				} catch (UnsupportedEncodingException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				Key[] curKeys = SessionKey.getKeysRSA();
				byte[] encryptedFileContent = RSA_2048.encrypt(curKeys[0].getEncoded(), getFileContent);
				myB = encryptedFileContent;
				try {
					String textCipher = new String(encryptedFileContent,"UTF-8");
					writeFile(textCipher.getBytes(),nameEncryptedFile);
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
				
		}
	}
	
	// Used to Contain the encrypted bytes of RSA
	public byte[] myB = null;
	
	class GenerateDecryptedFile implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(decAESCheck){
				String tmpPath = textDec.getText();
				File encFile = new File("");
				String nameEncryptedFile = encFile.getAbsolutePath() + File.separator + "decrypted_File_AES.txt";
				byte[] getFileContent = readFile(tmpPath);
				SecretKey curKey = SessionKey.getKeyAES();
				byte[] decryptedFileContent = AES_128.decrypt(curKey.getEncoded(),getFileContent);
				writeFile(decryptedFileContent,nameEncryptedFile);
			}
			else if(decRSACheck){
				File encFile = new File("");
				String nameEncryptedFile = encFile.getAbsolutePath() + File.separator + "decrypted_File_RSA.txt";
				Key[] curKeys = SessionKey.getKeysRSA();
				byte[] decryptedFileContent = RSA_2048.decrypt(curKeys[1].getEncoded(),myB);
				try {
					String plainRSA = new String(decryptedFileContent,"UTF-8");
					System.out.println("plainRSA : " + plainRSA);
					writeFile(plainRSA.getBytes(),nameEncryptedFile);
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
				
		}
	}
	
	public byte[] readFile(String nf){
		File file = new File(nf);
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(file);
			byte fileContent[] = new byte[(int)file.length()];
			fin.read(fileContent);
			String s = new String(fileContent);
			//System.out.println("File content: " + s);
			return s.getBytes();
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found" + e);
		}
		catch (IOException ioe) {
			System.out.println("Exception while reading file " + ioe);
		}
		finally {
			try {
				if (fin != null) {
					fin.close();
				}
			}
			catch (IOException ioe) {
				System.out.println("Error while closing stream: " + ioe);
			}
		}
		return null;
	}
	
	public static void writeFile(byte[] contentToWrite,String path){
		byte[] content = contentToWrite;
		String pathNameFile = path;
		try {
			FileOutputStream fos = new FileOutputStream(new File(pathNameFile));
			try {
				fos.write(content);
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void exportAES_128() throws IOException{ 
		//File filer = new File("");
		//String path = filer.getAbsolutePath();
		//String sep = a.separator;
	    String pathFull = textNameFile.getText();
	    String emplacement = "C:\\Users\\JAMAL\\Desktop\\" + pathFull;
		FileOutputStream fos = new FileOutputStream(new File(emplacement));
	    String resultKey = AES_128.generateKeyHex();
	    fos.write("Key AES-128 in Hexadecimal format : \r\n\r\n".getBytes());
	    fos.write(resultKey.getBytes());
	    String encodedKey = Base64.getEncoder().encodeToString(resultKey.getBytes());
		fos.write("\r\n\r\nKey AES-128 in string format : \r\n\r\n".getBytes());
		fos.write(encodedKey.getBytes());
	    fos.close();
	}
	
	public void exportRSA_2048() throws IOException{ 
		//File filer = new File("");
		//String path = filer.getAbsolutePath();
		//String sep = a.separator;
		String pathFull = textNameFile.getText();
	    String emplacement = "C:\\Users\\JAMAL\\Desktop\\" + pathFull;
		FileOutputStream fos = new FileOutputStream(new File(emplacement));
		Key k[] = RSA_2048.getKeys();
		fos.write(k[0].toString().getBytes());
		fos.write("\r\n\r\n-----------------------------------------------------".getBytes());
		fos.write("\r\n\r\nPublic key RSA-2048 in Hexadecimal format : \r\n\r\n".getBytes());
		fos.write(RSA_2048.bytesToHexRepresentation(k[0].getEncoded()).getBytes());
	    fos.write("\r\n\r\nPublic key RSA-2048 in string format : \r\n\r\n".getBytes());
	    String encodedKeyPublicRSA = Base64.getEncoder().encodeToString(k[0].getEncoded());
	    fos.write(encodedKeyPublicRSA.getBytes());
	    fos.write("\r\n\r\n-----------------------------------------------------".getBytes());
		fos.write("\r\n\r\nPrivate key RSA-2048 in Hexadecimal format : \r\n\r\n".getBytes());
	    fos.write(RSA_2048.bytesToHexRepresentation(k[1].getEncoded()).getBytes());
	    String encodedKeyPrivateRSA = Base64.getEncoder().encodeToString(k[1].getEncoded());
	    fos.write("\r\n\r\nPrivate key RSA-2048 in string format : \r\n\r\n".getBytes());
	    fos.write(encodedKeyPrivateRSA.getBytes());
	    fos.close();
	}
	
	
	 // Fonction à supprimer
	 /*public byte[] encryptFileAES_128(byte[] keyToUse, String pathToFile) throws IOException{ 
		 String myPath = pathToFile;
		//SecretKey keyK = AES_128.getKey();
		byte[] contentFile = readFile(myPath);
		byte[] encryptedContent = AES_128.encrypt(keyToUse,contentFile);
		return encryptedContent;
	}*/
	 
	 /*public byte[] decryptFileAES_128(String pathToFile) throws IOException{
		String myPath = pathToFile;
		
	 }*/
	 
	
	public void initSessionKeys(){
		SessionKey.setKeyAES();
		SessionKey.setKeysRSA();
	}
	
	private JTabbedPane tabbedPane = new JTabbedPane();

	// The different tabs
	private JPanel firstPan = new JPanel();
	private JPanel secondPan = new JPanel();
	private JPanel thirdPan = new JPanel();
	private JPanel fourthPan = new JPanel();
	private JPanel fifthPan = new JPanel();
	
	GroupLayout layout = new GroupLayout(firstPan);
	GroupLayout layout2 = new GroupLayout(secondPan);
	GroupLayout layout3 = new GroupLayout(thirdPan);
	GroupLayout layout4 = new GroupLayout(fourthPan);
	GroupLayout layout5 = new GroupLayout(fifthPan);
	
	// Elements of the first tab
	private JLabel labelIP= new JLabel("IP : ");
	private JTextField textIP = new JTextField("", 15);
	private JLabel labelPort = new JLabel("Port : ");
	private JTextField textPort = new JTextField("",6);
	private JLabel labelChooseFile = new JLabel("Choose a file to send : ");
	private JTextField textChooseFile = new JTextField("",30);
	private JButton buttonBrowse = new JButton("Browse");
	private JButton buttonSend = new JButton("Send");
	
	// Elements of the second tab
	private JLabel labelChooseName = new JLabel("Choose a name for the file to export : ");
	private JTextField textNameFile = new JTextField("",30);
	private JCheckBox boxRSA = new JCheckBox("RSA-2048");
	private JCheckBox boxAES = new JCheckBox("AES-128");
	private JButton buttonGenerate = new JButton("Generate");
	
	// Elements of the third tab
	private JLabel labelFileSignature = new JLabel("Choose a file to calculate its signature : ");
	private JTextField textNameSignature = new JTextField("",30);
	private JButton butBrowseSignature = new JButton("Browse");
	private JButton buttonCalculateSignature = new JButton("Calculate SHA-3");
	private JLabel labelResultSignature = new JLabel("Signature SHA-3 : ");
	private JTextField textResultSignature = new JTextField("",1024);
	private boolean boxAESCheck = false; 
	private boolean boxRSACheck = false;
	
	//Elements of the fourth tab
	private JLabel labelEnc = new JLabel("Choose the file to encrypt : ");
	private JTextField textEnc = new JTextField("",30);
	private JButton browseEnc = new JButton("Browse");
	private JCheckBox encBoxRSA = new JCheckBox("RSA-2048");
	private JCheckBox encBoxAES = new JCheckBox("AES-128");
	private JButton encryptButton = new JButton("Encrypt");
	private JLabel labelDec = new JLabel("Choose the file to decrypt : ");
	private JTextField textDec = new JTextField("",30);
	private JButton browseDec = new JButton("Browse");
	private JCheckBox decBoxRSA = new JCheckBox("RSA-2048");
	private JCheckBox decBoxAES = new JCheckBox("AES-128");
	private JButton decryptButton = new JButton("Decrypt");
	private boolean encAESCheck = false; 
	private boolean encRSACheck = false;
	private boolean decAESCheck = false; 
	private boolean decRSACheck = false;
	
	//Elements of the fifth tab
	private JLabel labelPortReceive = new JLabel("PORT : ");
	private JTextField textPortReceive = new JTextField("",6);
	private JLabel labelNameFileReceive = new JLabel("Choose a name for the file : ");
	private JTextField textNameReceive = new JTextField("",30);
	private JButton buttonReady = new JButton("Ready");
	
	public Window(){
	
		initFirstTab();
		initSecondTab();
		intThirdTab();
		initFourthTab();
		intFifthTab();
		
		buttonBrowse.addActionListener(new BrowseFiles());
		butBrowseSignature.addActionListener(new BrowseFileSign());
		buttonCalculateSignature.addActionListener(new CalculateSign());
		boxAES.addActionListener(new expAES());
		boxRSA.addActionListener(new expRSA());
		buttonGenerate.addActionListener(new GenerateFile());
		browseEnc.addActionListener(new BrowseEnc());
		encBoxAES.addActionListener(new encryptThisAES());
		encBoxRSA.addActionListener(new encryptThisRSA());
		encryptButton.addActionListener(new GenerateEncryptedFile());
		browseDec.addActionListener(new BrowseDec());
		decBoxAES.addActionListener(new decryptThisAES());
		decBoxRSA.addActionListener(new decryptThisRSA());
		decryptButton.addActionListener(new GenerateDecryptedFile());
		
		tabbedPane.add("Send file",firstPan);
		tabbedPane.add("Generate and export",secondPan);
		tabbedPane.add("Calculate signature SHA-3",thirdPan);
		tabbedPane.add("Encrypt and decrypt files",fourthPan);
		tabbedPane.add("Receive file",fifthPan);
		tabbedPane.setToolTipTextAt(0,"Send your favorite files");
		tabbedPane.setToolTipTextAt(1,"Generate a key and export it");
		tabbedPane.setToolTipTextAt(2,"Calculate the signature SHA-3 of your favorite files");
		tabbedPane.setToolTipTextAt(3,"Encrypt and decrypt your favorite files");
		tabbedPane.setToolTipTextAt(4,"Receive your favorite files");
		
		this.add(tabbedPane);
		this.setIconImage(new ImageIcon("src/image/images_secure_transfer_files.jpg").getImage());
		this.setTitle("Security Exchange Files");
		this.setSize(700,300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	public void initFirstTab(){
		firstPan.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().
	            addComponent(labelIP).addComponent(labelPort).addComponent(labelChooseFile).addComponent(buttonSend));
	    hGroup.addGroup(layout.createParallelGroup().
	            addComponent(textIP).addComponent(textPort).addComponent(textChooseFile).addComponent(buttonSend));
	    layout.setHorizontalGroup(hGroup);
	    hGroup.addGroup(layout.createParallelGroup().
	            addComponent(buttonBrowse));
	    GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
	    vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
	            addComponent(labelIP).addComponent(textIP));
	    vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
	            addComponent(labelPort).addComponent(textPort));
	    vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
	            addComponent(labelChooseFile).addComponent(textChooseFile).addComponent(buttonBrowse));
	    vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
	            addComponent(buttonSend));
	    layout.setVerticalGroup(vGroup);
	}
	
	public void initSecondTab(){
		secondPan.setLayout(layout2);
		layout2.setAutoCreateGaps(true);
		layout2.setAutoCreateContainerGaps(true);
		GroupLayout.SequentialGroup hGroup2 = layout2.createSequentialGroup();
		hGroup2.addGroup(layout2.createParallelGroup().
	            addComponent(labelChooseName).addComponent(boxAES).addComponent(buttonGenerate));
	    hGroup2.addGroup(layout2.createParallelGroup().
	            addComponent(textNameFile).addComponent(boxRSA));
	    layout2.setHorizontalGroup(hGroup2);
	    GroupLayout.SequentialGroup vGroup2 = layout2.createSequentialGroup();
	    vGroup2.addGroup(layout2.createParallelGroup(Alignment.BASELINE).
	            addComponent(labelChooseName).addComponent(textNameFile));
	    vGroup2.addGroup(layout2.createParallelGroup(Alignment.BASELINE).
	            addComponent(boxAES).addComponent(boxRSA));
	    vGroup2.addGroup(layout2.createParallelGroup(Alignment.BASELINE));
	    vGroup2.addGroup(layout2.createParallelGroup(Alignment.BASELINE).
	            addComponent(buttonGenerate));
	    layout2.setVerticalGroup(vGroup2);
	}
	
	public void intThirdTab(){
		thirdPan.setLayout(layout3);
		layout3.setAutoCreateGaps(true);
		layout3.setAutoCreateContainerGaps(true);
		GroupLayout.SequentialGroup hGroup3 = layout3.createSequentialGroup();
		hGroup3.addGroup(layout3.createParallelGroup().
	            addComponent(labelFileSignature).addComponent(buttonCalculateSignature).addComponent(labelResultSignature));
	    hGroup3.addGroup(layout3.createParallelGroup().
	            addComponent(textNameSignature).addComponent(textResultSignature));
	    hGroup3.addGroup(layout3.createParallelGroup().
	            addComponent(butBrowseSignature));
	    layout3.setHorizontalGroup(hGroup3);
	    GroupLayout.SequentialGroup vGroup3 = layout3.createSequentialGroup();
	    vGroup3.addGroup(layout3.createParallelGroup(Alignment.BASELINE).
	            addComponent(labelFileSignature).addComponent(textNameSignature).addComponent(butBrowseSignature));
	    vGroup3.addGroup(layout3.createParallelGroup(Alignment.BASELINE).
	            addComponent(buttonCalculateSignature));
	    vGroup3.addGroup(layout3.createParallelGroup(Alignment.BASELINE));
	    vGroup3.addGroup(layout3.createParallelGroup(Alignment.BASELINE).
	            addComponent(labelResultSignature).addComponent(textResultSignature));
	    vGroup3.addGroup(layout3.createParallelGroup(Alignment.BASELINE));
	    layout3.setVerticalGroup(vGroup3);
	}
	
	public void initFourthTab(){
		fourthPan.setLayout(layout4);
		layout4.setAutoCreateGaps(true);
		layout4.setAutoCreateContainerGaps(true);
		GroupLayout.SequentialGroup hGroup4 = layout4.createSequentialGroup();
		hGroup4.addGroup(layout4.createParallelGroup().
	            addComponent(labelEnc).addComponent(encBoxAES).addComponent(encryptButton).addComponent(labelDec).addComponent(decBoxAES).addComponent(decryptButton));
	    hGroup4.addGroup(layout4.createParallelGroup().
	            addComponent(textEnc).addComponent(encBoxRSA).addComponent(textDec).addComponent(decBoxRSA));
	    hGroup4.addGroup(layout4.createParallelGroup().
	            addComponent(browseEnc).addComponent(browseDec));
	    layout4.setHorizontalGroup(hGroup4);
	    GroupLayout.SequentialGroup vGroup4 = layout4.createSequentialGroup();
	    vGroup4.addGroup(layout4.createParallelGroup(Alignment.BASELINE).
	            addComponent(labelEnc).addComponent(textEnc).addComponent(browseEnc));
	    vGroup4.addGroup(layout4.createParallelGroup(Alignment.BASELINE).
	            addComponent(encBoxAES).addComponent(encBoxRSA));
	    vGroup4.addGroup(layout4.createParallelGroup(Alignment.BASELINE));
	    vGroup4.addGroup(layout4.createParallelGroup(Alignment.BASELINE).
	            addComponent(encryptButton));
	    vGroup4.addGroup(layout4.createParallelGroup(Alignment.BASELINE));
	    vGroup4.addGroup(layout4.createParallelGroup(Alignment.BASELINE).
	            addComponent(labelDec).addComponent(textDec).addComponent(browseDec));
	    vGroup4.addGroup(layout4.createParallelGroup(Alignment.BASELINE));
	    vGroup4.addGroup(layout4.createParallelGroup(Alignment.BASELINE).
	            addComponent(decBoxAES).addComponent(decBoxRSA));
	    vGroup4.addGroup(layout4.createParallelGroup(Alignment.BASELINE));
	    vGroup4.addGroup(layout4.createParallelGroup(Alignment.BASELINE).
	            addComponent(decryptButton));
	    vGroup4.addGroup(layout4.createParallelGroup(Alignment.BASELINE));
	    layout4.setVerticalGroup(vGroup4);
	}
	
	public void intFifthTab(){
		fifthPan.setLayout(layout5);
		layout5.setAutoCreateGaps(true);
		layout5.setAutoCreateContainerGaps(true);
		GroupLayout.SequentialGroup hGroup5 = layout5.createSequentialGroup();
		hGroup5.addGroup(layout5.createParallelGroup().
	            addComponent(labelPortReceive).addComponent(labelNameFileReceive).addComponent(buttonReady));
		hGroup5.addGroup(layout5.createParallelGroup().
	            addComponent(textPortReceive).addComponent(textNameReceive));
	    layout5.setHorizontalGroup(hGroup5);
	    GroupLayout.SequentialGroup vGroup5 = layout5.createSequentialGroup();
	    vGroup5.addGroup(layout5.createParallelGroup(Alignment.BASELINE).
	            addComponent(labelPortReceive).addComponent(textPortReceive));
	    vGroup5.addGroup(layout5.createParallelGroup(Alignment.BASELINE).
	            addComponent(labelNameFileReceive).addComponent(textNameReceive));
	    vGroup5.addGroup(layout5.createParallelGroup(Alignment.BASELINE));
	    vGroup5.addGroup(layout5.createParallelGroup(Alignment.BASELINE).
	            addComponent(buttonReady));
	    layout5.setVerticalGroup(vGroup5);
	}
}
