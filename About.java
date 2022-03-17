package notepad;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class About extends JFrame implements ActionListener{
	JButton b1;
	
	About()
	{
		setBounds(350,150, 600, 500);
		
		setLayout(null);
		
		JLabel l1 = new JLabel("<html>About Notepad<br><br>Notepad is a word processing program,<br>which allows changing of text in computer file.<br>Notepad is a simple text editor for basic text editing program,<br>which enables computer user to create text documents.</html>");
		l1.setBounds(150, 130, 400, 200);
		add(l1);
		l1.setFont(new Font("SAN_SERIF", Font.PLAIN, 17));
		
		b1 = new JButton("Ok");
		b1.setBounds(410, 400, 80, 25);
		b1.addActionListener(this);
		add(b1);
	}
	
	public void actionPerformed(ActionEvent ae) {
		
		this.setVisible(false);
	}
	
	public static void main(String[] args) {
		
		new Notepad().setVisible(true);;

	}

}
