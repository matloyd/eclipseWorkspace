package com.mohsen;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CopyOfs {
	static PrintWriter writer ;
	static String fileName;
	static String dirName;

	public static void main(String[] args) {

		JFrame mainFrame=new JFrame("DirList Generator v1.0 created by mohsen");
		
		JPanel  panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		 panel.setLayout(layout);
		
		
		
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setSize(400, 100);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton startBtn =new JButton("Start >>>");
		
		JLabel lbl1=new JLabel("Source path : ");
		JLabel lbl2=new JLabel("Destination file name : ");		
		
		
		final JTextField textFileName = new JTextField();
		final JTextField textDirName = new JTextField();
		
		mainFrame.add(textFileName,BorderLayout.NORTH);
		mainFrame.add(startBtn,BorderLayout.SOUTH);
		
		mainFrame.add(lbl1,BorderLayout.CENTER);
		mainFrame.add(lbl2,BorderLayout.CENTER);
		
		mainFrame.add(textDirName,BorderLayout.CENTER);
		mainFrame.setVisible(true);

		startBtn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				fileName=textDirName.getText();
				dirName=textFileName.getText();
				try {
					writer= new PrintWriter("E:\\"+fileName+".txt", "UTF-8");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				File dir = new File(dirName.replace("/","//"));  
				listRecursive(dir);
				writer.close();
				System.out.println("created successfully");				
			}
		});

	}

	public static void listRecursive(File dir) {
		if (dir.isDirectory()) {
			File[] items = dir.listFiles();
			if(items!=null)
				for (File item : items) {
					if(!item.isDirectory()){
						writer.println(item.getAbsoluteFile());
						writer.flush();
					}
					if (item.isDirectory()) 
						listRecursive(item);  // Recursive call
				}
		}
	}


}
