package part2;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


public class Window extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args){
		new Window();
	}

	class BrowseFiles implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	        JFileChooser c = new JFileChooser();
	        // Demonstrate "Open" dialog:
	        int rVal = c.showOpenDialog(Window.this);
	        textChooseFile.setText(c.getSelectedFile().getAbsolutePath());
	      }
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
	
	public Window(){
	
		initFirstTab();
		initSecondTab();
		
		buttonBrowse.addActionListener(new BrowseFiles());
		
		
		
		tabbedPane.add("Exchange file",firstPan);
		tabbedPane.add("Generate and export",secondPan);
		tabbedPane.setToolTipTextAt(0, "Send File");
		tabbedPane.setToolTipTextAt(1, "Generate a key and export it");
		
		this.setIconImage(new ImageIcon("C:/Users/JAMAL/Desktop/images_secure_transfer_files.jpg").getImage());
		
		add(tabbedPane);
		
		this.setTitle("Security Exchange");
		this.setSize(400,300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		buttonBrowse.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent arg0) {
	          System.out.println("Bouton appuyé !");
	          
	        }        
	      });
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
}
