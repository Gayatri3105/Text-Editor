package notepad;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Notepad extends JFrame implements ActionListener{

	JTextArea area;
	JScrollPane pane;
	String text;
	
	Notepad()
	{
		setTitle("Notepad Application");
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("text editor.png"));
		setIconImage(icon.getImage());
		
		JMenuBar menubar = new JMenuBar();
		
		JMenu file = new JMenu("File");
		
		JMenuItem newdoc = new JMenuItem("New");
		newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		newdoc.addActionListener(this);
		
		JMenuItem open = new JMenuItem("Open");
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		open.addActionListener(this);
		
		JMenuItem save = new JMenuItem("Save");
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		save.addActionListener(this);
		
		JMenuItem print = new JMenuItem("Print");
		print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		print.addActionListener(this);
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
		exit.addActionListener(this);
		
		file.add(newdoc);
		file.add(open);
		file.add(save);
		file.add(print);
		file.add(exit);
		
		
		JMenu edit = new JMenu("Edit");
		
		JMenuItem undo = new JMenuItem("Undo");
		undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		undo.addActionListener(this);
		
		JMenuItem copy = new JMenuItem("Copy");
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		copy.addActionListener(this);
		
		JMenuItem paste = new JMenuItem("Paste");
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		paste.addActionListener(this);
		
		JMenuItem cut = new JMenuItem("Cut");
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		cut.addActionListener(this);
		
		JMenuItem delete = new JMenuItem("Delete");
		delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		delete.addActionListener(this);
		
		JMenuItem selectall = new JMenuItem("Select All");
		selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		selectall.addActionListener(this);
		
		edit.add(undo);
		edit.add(copy);
		edit.add(paste);
		edit.add(cut);
		edit.add(delete);
		edit.add(selectall);
		
		JMenu help = new JMenu("Help");
		
		JMenuItem about = new JMenuItem("About");
		about.addActionListener(this);
		
		help.add(about);
		
		menubar.add(file);
		menubar.add(edit);
		menubar.add(help);
		
		//add(menubar);
		setJMenuBar(menubar);
		
		area = new JTextArea();
		area.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 18));
		
		pane = new JScrollPane(area);
		pane.setBorder(BorderFactory.createEmptyBorder());
		add(pane, BorderLayout.CENTER);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getActionCommand().equals("New"))
		{
			area.setText("");
			
		}else if(ae.getActionCommand().equals("Open")) 
		{
			JFileChooser chooser = new JFileChooser();
			chooser.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt");
			chooser.addChoosableFileFilter(restrict);
			
			int action = chooser.showOpenDialog(this);
			if(action != JFileChooser.APPROVE_OPTION)
			{
				return;
			}
			
			File file = chooser.getSelectedFile();
			try {
				
				BufferedReader reader = new BufferedReader(new FileReader(file));
				area.read(reader, null);
				
			}catch(Exception e) {}
			
			
		}else if(ae.getActionCommand().equals("Save"))
		{
			
			JFileChooser saveas = new JFileChooser();
			saveas.setApproveButtonText("Save");
			int action = saveas.showOpenDialog(this);
			if(action != JFileChooser.APPROVE_OPTION)
			{
				return;
			}
			
			File filename = new File(saveas.getSelectedFile() + ".txt");
			BufferedWriter outFile = null;
			try {
				outFile = new BufferedWriter(new FileWriter(filename));
				area.write(outFile);
			}catch(Exception e) {}
			
		}else if(ae.getActionCommand().equals("Print")){
			try {
				
				area.print();
				
			}catch (Exception e) {}
			
		}else if(ae.getActionCommand().equals("Exit")){
			
			System.exit(0);
			
		}else if(ae.getActionCommand().equals("Copy")) {
			
			text = area.getSelectedText();
			
		}else if(ae.getActionCommand().equals("Paste")) {
			
			area.insert(text, area.getCaretPosition());
			
		}else if(ae.getActionCommand().equals("Cut")) {
			
			text = area.getSelectedText();
			area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
			
		}else if(ae.getActionCommand().equals("Select All")) {
			
			area.selectAll();
			
		}else if(ae.getActionCommand().equals("About")) {
			
			new About().setVisible(true);
			
		}
	}
	
	public static void main(String[] args) {
		
		new Notepad().setVisible(true);

	}

}
