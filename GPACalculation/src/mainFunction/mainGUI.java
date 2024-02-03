package mainFunction;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.ScrollPane;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JTextField;
import javax.swing.JButton;


public class mainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField insertCourseAmount;
	private static HashMap<String, Double>  courseGrade;
	public static int courseAmount = 10;
	public static String[] gradeStrings = {"A", "A-", "B+", "B", "B-", "C+", "C", "D", "E"};
	public static double[] gradeValue = {4.0, 3.75, 3.5, 3.0, 2.75, 2.5, 2.0, 1.0, 0.0};	
	public static tableSection[] courses;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		courseGrade = new HashMap<String, Double>();
		for (int i = 0; i < gradeStrings.length; i++) {
			courseGrade.put(gradeStrings[i], gradeValue[i]);
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainGUI frame = new mainGUI();
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public static tableSection[] CreateCoursesGUI(JScrollPane panel)
	{
		tableSection[] temp = new tableSection[courseAmount];
		Rectangle rec = new Rectangle();
		

        JPanel borderlaoutpanel = new JPanel();
        panel.setViewportView(borderlaoutpanel);
        borderlaoutpanel.setLayout(new BorderLayout(0, 0));

        JPanel columnpanel = new JPanel();
        borderlaoutpanel.add(columnpanel, BorderLayout.NORTH);
        columnpanel.setLayout(new GridLayout(0, 1, 0, 1));
        columnpanel.setBackground(Color.gray);
		
		rec.setBounds(0, 0, 100, 50);
		Dimension owoDimension = new Dimension(panel.getSize().width-20,50);
		
		for(int i = 0; i<courseAmount; i++)
		{
            JPanel rowPanel = new JPanel();
            rowPanel.setPreferredSize(owoDimension);
            columnpanel.add(rowPanel);
            rowPanel.setLayout(null);
            temp[i] = new tableSection(rowPanel, i + 1, rec, owoDimension.width, gradeStrings);
            //JButton button = new JButton("New button");
            //button.setBounds(20, 5, 89, 23);
            //rowPanel.add(button);

            if(i%2==0)
                rowPanel.setBackground(SystemColor.inactiveCaptionBorder);
			
	
		}
		return temp;
	}
	public static double CalculateGPA()
	{
		double totalGrade = 0.0;
		int valid = 0;
		for(int i = 0; i<courses.length; i++)
		{
			 String creditTextString = courses[i].inputArea.getText();
			 if(creditTextString.isEmpty())
				 continue;
			 String gradeOut = courses[i].gradeInsert.getSelectedItem().toString();
			 totalGrade += Integer.valueOf(creditTextString) * courseGrade.get(gradeOut);
			 valid += Integer.valueOf(creditTextString);
		}
		DecimalFormat df = new DecimalFormat("#.###"); 
		totalGrade = Double.valueOf(df.format(totalGrade/valid));
		return totalGrade;
	}
	public mainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 805);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("GPA Calculator");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 60));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 11, 576, 61);
		contentPane.add(lblNewLabel);
		
		insertCourseAmount = new JTextField();
		insertCourseAmount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		insertCourseAmount.setBounds(251, 103, 325, 32);
		contentPane.add(insertCourseAmount);
		insertCourseAmount.setColumns(10);
		insertCourseAmount.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				int type = (int)e.getKeyChar();	
				if(type < (int)'0' || type > (int)'9')
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
		
		JLabel lblNewLabel_1 = new JLabel("Course Amount:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 29));
		lblNewLabel_1.setBounds(10, 100, 237, 32);
		contentPane.add(lblNewLabel_1);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnInsert.setBounds(212, 146, 169, 37);

		contentPane.add(btnInsert);
		
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        scrollPane.setBounds(10, 194, 566, 471);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contentPane.add(scrollPane);
		
		
		btnInsert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(insertCourseAmount.getText().isEmpty())
					return;
				courseAmount = Integer.parseInt(insertCourseAmount.getText());
				courses = CreateCoursesGUI(scrollPane);
				
			}
		});
		

		
		JLabel GPADisplay = new JLabel("Your GPA is:");
		GPADisplay.setHorizontalAlignment(SwingConstants.CENTER);
		GPADisplay.setFont(new Font("Tahoma", Font.PLAIN, 29));
		GPADisplay.setBounds(10, 724, 566, 32);
		contentPane.add(GPADisplay);
		

		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCalculate.setBounds(212, 676, 169, 37);
		contentPane.add(btnCalculate);
		btnCalculate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				double GPATemp = CalculateGPA();
				GPADisplay.setText("Your GPA is: " + GPATemp);
				
			}
		});

	}
}
