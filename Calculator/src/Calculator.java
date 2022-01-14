import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Calculator implements ActionListener {

// Declaring everything we need 
	
	JFrame frame;
	JTextField textfield;
	JButton[] numberButtons = new JButton[10]; 
	JButton[] functionButtons = new JButton[9]; // creating an array for the + - * / etc.
	JButton addButton, subButton, mulButton, divButton; // naming the function buttons
	JButton decButton, equButton, delButton, clrButton, negButton; // naming the extra functions buttons
	JPanel panel; 
	
	Font myFont = new Font("Ink Free",Font.BOLD,30); // Declaring a font
	
	double num1 = 0, num2 = 0, result =0;
	char operator;
		
// moving on to the calculator	
	Calculator(){
		
		frame = new JFrame("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,550);
		frame.setLayout(null);
		
		// working on the text field
		textfield = new JTextField();
		textfield.setBounds(50, 25, 300, 50); // (x, y, width, height)
		textfield.setFont(myFont); // setting the font
		textfield.setEditable(false); // to stop updating the text box we created above. 
		
		// working on adding the buttons
		addButton = new JButton("+");
		subButton = new JButton("-");
		mulButton = new JButton("*");
		divButton = new JButton("/");
		decButton = new JButton(".");
		equButton = new JButton("=");
		delButton = new JButton("Del");
		clrButton = new JButton("Clr");
		negButton = new JButton("+/-");
		// assigning the function to a button from the array
		functionButtons[0] = addButton;
		functionButtons[1] = subButton;
		functionButtons[2] = mulButton;
		functionButtons[3] = divButton;
		functionButtons[4] = decButton;
		functionButtons[5] = equButton;
		functionButtons[6] = delButton;
		functionButtons[7] = clrButton;
		functionButtons[8] = negButton;
		
		// for loop that will loop 8 times to set the font and add the ActionListener.
		for(int i = 0; i<9; i++) {
			functionButtons[i].addActionListener(this);
			functionButtons[i].setFont(myFont);
			functionButtons[i].setFocusable(false);
		}
		
		// for loop for the numbers
		for(int i = 0; i<10; i++) {
			numberButtons[i] = new JButton(String.valueOf(i));
			numberButtons[i].addActionListener(this);
			numberButtons[i].setFont(myFont);
			numberButtons[i].setFocusable(false);
				
		}
		
		delButton.setBounds(150,430,100,50);
		clrButton.setBounds(250,430,100,50);
		negButton.setBounds(50, 430, 100, 50);
		
		
		
		// working on the Grid
		panel = new JPanel();
		panel.setBounds(50,100,300,300);
		panel.setLayout(new GridLayout(4,4,10,10));
		// panel.setBackground(Color.GRAY); // to add color to the grid
		
		// adding the numbers now. 
		panel.add(numberButtons[1]); // starting top left to bottom right 
		panel.add(numberButtons[2]);
		panel.add(numberButtons[3]);
		panel.add(addButton);
		panel.add(numberButtons[4]);
		panel.add(numberButtons[5]);
		panel.add(numberButtons[6]);
		panel.add(subButton);
		panel.add(numberButtons[7]);
		panel.add(numberButtons[8]);
		panel.add(numberButtons[9]);
		panel.add(mulButton);
		panel.add(decButton);
		panel.add(numberButtons[0]);
		panel.add(equButton);
		panel.add(divButton);

		
		frame.add(panel);
		frame.add(negButton);
		frame.add(delButton);
		frame.add(clrButton);
		frame.add(textfield);		
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		
		Calculator calc = new Calculator();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// adding functionality to the buttons using for loops
		
		for(int i = 0; i < 10; i++) {
			if(e.getSource() == numberButtons[i]) {
				textfield.setText(textfield.getText().concat(String.valueOf(i))); // adding the functions to the numerical buttons
			}
		}
		// adding functionality to the decimal button. 
		if(e.getSource() == decButton) {
			textfield.setText(textfield.getText().concat("."));
		}
		
		// adding functionality to the add button. 
		if(e.getSource() == addButton) {
			num1 = Double.parseDouble(textfield.getText());
			operator = '+';
			textfield.setText(""); // clears the text field. 
		}
		// adding functionality to the sub button 
		if(e.getSource() == subButton) {
			num1 = Double.parseDouble(textfield.getText());
			operator = '-';
			textfield.setText(""); // clears the text field. 
		}
		
		// adding functionality to the multiply button 
		if(e.getSource() == mulButton) {
			num1 = Double.parseDouble(textfield.getText());
			operator = '*';
			textfield.setText(""); // clears the text field. 
		}
		
		// adding functionality to the divide button. 
		if(e.getSource() == divButton) {
			num1 = Double.parseDouble(textfield.getText());
			operator = '/';
			textfield.setText(""); // clears the text field. 
		}
		// adding functionality to the decimal point. 
		if(e.getSource() == equButton) {
			num2 = Double.parseDouble(textfield.getText());
			
			switch(operator) {
			case'+':
				result = num1 + num2;
				break;
			case'-':
				result = num1 - num2;
				break;
			case'*':
				result = num1 * num2;
				break;
			case'/':
				result = num1 / num2;
				break;
			}
			
			textfield.setText(String.valueOf(result));
			num1 = result;
		}
		 	// functionality for the clear button
			if(e.getSource() == clrButton) {
				textfield.setText(""); // clears the text field. 
			}
			// functionality for the delete button
			if(e.getSource() == delButton) {
				String string = textfield.getText();
				textfield.setText(""); // clears the text field. 
				for(int i = 0; i < string.length()-1; i++) {
					textfield.setText(textfield.getText()+string.charAt(i));
				}
			}
			
			// functionality for the negative button
			if(e.getSource() == negButton) {
				double temp = Double.parseDouble(textfield.getText()); // takes whatever value in the text field and assigns it to temp
				temp*=-1;
				textfield.setText(String.valueOf(temp));
			}
	}
}
