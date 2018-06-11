package com.mohsen;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;


class MyTextArea extends JPanel{

	private static final long serialVersionUID = -5865148269057884357L;
	JTextArea tx;
	MyTextArea(JTextArea tx){
		this .tx=tx;
		setLayout(new BorderLayout());
		add(new JScrollPane(tx),BorderLayout.CENTER) ;
	}
}
public class SwingContainerDemo {

	static PrintWriter writer ;
	static JFrame mainFrame;
	static JLabel Enter_Directory_Path;
	static JButton startBtn;
	static JButton output;
	static JTextField Enter_Directory_Path_txtField;
	static JTextField Output_File_Location_txtField;
	static JTextArea status;
	static MyTextArea statusScroll;
	static  JPanel panel3;
	static  JPanel panel2;
	static  JPanel panel4;
	static String dirName;
	static JCheckBox includeSubDirectories;
	static JCheckBox includeDirectories;

	public static void main(String[] args) {

		mainFrame = new JFrame("DirList Generator v1.0 created by mohsen");
		mainFrame.setSize(600,300);
		mainFrame.setLayout(new GridLayout(5, 1));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Enter_Directory_Path = new JLabel("Enter Directory Path ",Label.LEFT);   

		output =new JButton("Choose...");

		Enter_Directory_Path_txtField = new JTextField();
		Enter_Directory_Path_txtField.setEditable(false);

		Output_File_Location_txtField = new JTextField();
		Output_File_Location_txtField.setEditable(false);

		includeSubDirectories = new JCheckBox("List files in all sub directories");
		includeDirectories = new JCheckBox("Include Forlder Names too");

		status=new JTextArea();
		status.setEditable(false);
		statusScroll=new MyTextArea(status);

		panel2=new JPanel();
		panel2.setLayout(new FlowLayout());
		panel2.add(Enter_Directory_Path);
		panel2.add(output);

		startBtn =new JButton("Start");
		startBtn.setPreferredSize(new Dimension(250,40));
		panel3=new JPanel();
		panel3.setLayout(new FlowLayout());
		panel3.add(startBtn);

		panel4=new JPanel();
		panel4.add(includeSubDirectories);
		panel4.add(includeDirectories);

		mainFrame.add(panel2);
		mainFrame.add(Enter_Directory_Path_txtField);
		mainFrame.add(panel3);
		mainFrame.add(panel4);
		mainFrame.add(statusScroll);
		mainFrame.setVisible(true);
		output.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				jfc.setDialogTitle("Choose a directory to list all files : ");
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				int returnValue = jfc.showDialog(null, null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					if (jfc.getSelectedFile().isDirectory()) {
						Enter_Directory_Path_txtField.setText(jfc.getSelectedFile()+"");
					}
				}
			}
		});
		
		startBtn.addActionListener(new ActionListener() {	

			@Override
			public void actionPerformed(ActionEvent e) {
				dirName=Enter_Directory_Path_txtField.getText();
				File temp = null;
				try {
					temp = File.createTempFile("list", ".txt");
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				
				final String fileName=temp.getAbsolutePath();
				if(dirName.equals("")){
					status.setText("Please input the directory path ^\n");	
				}
				else {
					Thread t1=new Thread(new Runnable(){
						@Override
						public void run() {
							status.setText(new SimpleDateFormat("yyyy/MM/dd - HH:mm:ss").format(Calendar.getInstance().getTime())+" Process Started, Please wait ...\n");
						}
					});
					Thread t2=new Thread(new Runnable(){
						@Override
						public void run() {
							try {
								writer= new PrintWriter(fileName, "UTF-8");
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							} catch (UnsupportedEncodingException e1) {
								e1.printStackTrace();
							}
							File dir = new File(dirName);  
							if(includeSubDirectories.isSelected())
								listRecursive(dir);
							else
								list(dir);
							writer.close();
							status.setText(new SimpleDateFormat("yyyy/MM/dd - HH:mm:ss").format(Calendar.getInstance().getTime())+" Process Finished successfully. \n"+
									"The file created on : "+fileName+"\n\n");	
							Runtime runtime = Runtime.getRuntime();
							try {
								Process process = runtime.exec("C:\\Windows\\notepad.exe "+fileName);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					});
					t1.start();
					t2.start();
					
					
				}
			}
		});
	}
	public static void listRecursive(File dir) {
		if (dir.isDirectory()) {
			File[] items = dir.listFiles();
			if(items!=null)
				for (File item : items) {
					if(includeDirectories.isSelected()){
						writer.println(item.getAbsoluteFile());
						writer.flush();
					}else{
						if(!item.isDirectory()){
							writer.println(item.getAbsoluteFile());
							writer.flush();
						}
					}
					if (item.isDirectory()) 
						listRecursive(item);  // Recursive call
				}
		}
	}
	public static void list(File dir) {
		if (dir.isDirectory()) {
			File[] items = dir.listFiles();
			if(items!=null)
				for (File item : items) {
					if(includeDirectories.isSelected()){
						writer.println(item.getAbsoluteFile());
						writer.flush();
					}else
						if(!item.isDirectory()){
							writer.println(item.getAbsoluteFile());
							writer.flush();
						}
				}
		}
	}
}