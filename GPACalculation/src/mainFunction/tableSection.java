package mainFunction;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class tableSection  {
	public JLabel currentLabel;
	public JTextArea inputArea;
	public JComboBox gradeInsert;
	tableSection(JPanel parentJPanel, int panelAmount, Rectangle rec, 
			int offset, String[] gradeTemp) 
	{

		currentLabel = new JLabel("Course " + panelAmount );
		currentLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		currentLabel.setBounds(rec);
		parentJPanel.add(currentLabel);

		inputArea = new JTextArea();
		inputArea.setFont(new Font("Tahoma", Font.PLAIN, 20));
		inputArea.setBounds(offset/2 - rec.height, rec.y, rec.width, rec.height);
		parentJPanel.add(inputArea);
		inputArea.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				int owo = (int)e.getKeyChar();
				if(owo <(int)'0' || owo>(int)'9')
					e.consume();
					
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		gradeInsert = new JComboBox(new DefaultComboBoxModel(gradeTemp));
		//System.out.println(parentJPanel.getSize().width);
		gradeInsert.setBounds(offset - rec.width, rec.y, rec.width, rec.height);
		parentJPanel.add(gradeInsert);
	}
	
}
