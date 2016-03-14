package part2;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;

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
		//new Window();
		Window w = new Window();
		try {
			w.encryptFileAES_128();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// POUR ENVOYER LA CLE IL FAUT ENVOYER LES BYTES DE LA CLE ET DONC FAIRE CA :
	
		//key.toString().getBytes()
	
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
			try {
				exportAES_128();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
			System.out.println("File content: " + s);
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
	
	public void exportAES_128() throws IOException{ 
		//String current = new java.io.File( "." ).getCanonicalPath();
	   // System.out.println("Current dir:"+current);
	    String pathFull = textNameFile.getText();
		//SecretKey key = AES_128.getKey();
		//FileOutputStream fos = new FileOutputStream(new File(pathFull));
	    String resultKey = AES_128.generateKeyHex();
	    //fos.write(resultKey.getBytes());
	    System.out.println(resultKey);
	}
	
	public void exportRSA_2048() throws IOException{ 
		//String current = new java.io.File( "." ).getCanonicalPath();
	   // System.out.println("Current dir:"+current);
	    String pathFull = textNameFile.getText();
	    Key[] keys = RSA_2048.getKeys();
		FileOutputStream fos = new FileOutputStream(new File(pathFull));
		//System.out.println("RSA : " + keys.toString());
		//fos.write(keys.toString().getBytes());
	}
	
	
	 
	 public void encryptFileAES_128() throws IOException{ 

		//String pathFull = textNameFile.getText();
		//byte[] in = readFile(pathFull);
		//Boolean checkExport = false;
		SecretKey key = AES_128.getKey();
		System.out.println(key.toString().getBytes());
		/*try{
			byte[] cipher = AES_128.encrypt(key.getEncoded(),in);
			System.out.println("Cipher text :"+ new String(cipher,"UTF-8"));
			
			byte[] plainText = AES_128.decrypt(key.getEncoded(),cipher);
			System.out.println("Plain text :"+new String(plainText,"UTF-8"));
		}
		
		catch(Exception e){
			e.printStackTrace();
		}*/
	}
	
	private JTabbedPane tabbedPane = new JTabbedPane();

	// The different tabs
	private JPanel firstPan = new JPanel();
	private JPanel secondPan = new JPanel();
	private JPanel thirdPan = new JPanel();
	
	GroupLayout layout = new GroupLayout(firstPan);
	GroupLayout layout2 = new GroupLayout(secondPan);
	GroupLayout layout3 = new GroupLayout(thirdPan);
	
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
	private JLabel labelFileSignature = new JLabel("Choose a file to calculate his signature : ");
	private JTextField textNameSignature = new JTextField("",30);
	private JButton butBrowseSignature = new JButton("Browse");
	private JButton buttonCalculateSignature = new JButton("Calculate SHA-3");
	private JLabel labelResultSignature = new JLabel("Signature SHA-3 : ");
	private JTextField textResultSignature = new JTextField("",1024);
	
	public Window(){
	
		initFirstTab();
		initSecondTab();
		intThirdTab();
		buttonBrowse.addActionListener(new BrowseFiles());
		butBrowseSignature.addActionListener(new BrowseFileSign());
		buttonCalculateSignature.addActionListener(new CalculateSign());
		boxAES.addActionListener(new expAES());
		
		tabbedPane.add("Exchange file",firstPan);
		tabbedPane.add("Generate and export",secondPan);
		tabbedPane.add("Calculate signature SHA-3",thirdPan);
		tabbedPane.setToolTipTextAt(0, "Send File");
		tabbedPane.setToolTipTextAt(1, "Generate a key and export it");
		tabbedPane.setToolTipTextAt(2, "Calculate the signature SHA-3 of your favorite files");
		
		this.setIconImage(new ImageIcon("C:/Users/JAMAL/Desktop/images_secure_transfer_files.jpg").getImage());
		
		add(tabbedPane);
		
		this.setTitle("Security Exchange");
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
}
