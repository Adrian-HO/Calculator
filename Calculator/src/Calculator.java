/* Program: My summative Calculator
   Description: Can add, subtract, multiply, divide, square root, and
   power of two, with decimals and turn on and off.
   Name: Adrian Ho
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Calculator {
    private JPanel calculatorPanel;
    private JTextField txtDisplay;
    private JButton[] numberButtons;
    private JButton btnPlus, btnMinus, btnMultiply, btnDivide;
    private JButton btnPosNeg, btnDecimal, btnEqual, btnClear;
    private JButton btnSqRoot, btnPower, btnOnOff, btnExit;
    private JLabel lblImage;
    
    // Colors for customization - keeping original blue theme
    private Color backgroundColor = new Color(173, 216, 230); // Light blue (original)
    private Color buttonColor = new Color(255, 255, 255);     // White buttons for better contrast
    private Color textColor = new Color(25, 25, 112);         // Midnight blue
    
    // Variables for calculations
    private double total1 = 0.0;
    private double total2 = 0.0;
    private char mathOperator;
    private boolean calculatorOn = true;

    public Calculator() {
        // Create the main panel with BorderLayout for main sections
        calculatorPanel = new JPanel();
        calculatorPanel.setLayout(new BorderLayout(0, 15)); // Add spacing between sections
        calculatorPanel.setBackground(backgroundColor);
        calculatorPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Add padding around all edges
        
        // Create display
        txtDisplay = new JTextField();
        txtDisplay.setFont(new Font("Arial", Font.PLAIN, 24));
        txtDisplay.setHorizontalAlignment(JTextField.RIGHT);
        txtDisplay.setEditable(false);
        txtDisplay.setBackground(Color.WHITE);
        txtDisplay.setForeground(textColor);
        txtDisplay.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(5, 5, 5, 5),
            BorderFactory.createLineBorder(Color.LIGHT_GRAY)
        ));
        calculatorPanel.add(txtDisplay, BorderLayout.NORTH);
        
        // Create the main button panel with a more complex layout
        JPanel mainButtonPanel = new JPanel();
        mainButtonPanel.setLayout(new BorderLayout(20, 20)); // Increased spacing between major sections
        mainButtonPanel.setBackground(backgroundColor);
        
        // Create the number pad panel (left side) - adjusted for more square buttons
        JPanel numberPadPanel = new JPanel();
        numberPadPanel.setLayout(new GridLayout(4, 3, 8, 8)); // Keep same grid but will adjust button size
        numberPadPanel.setBackground(backgroundColor);
        numberPadPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20)); // Add padding around numpad
        numberPadPanel.setPreferredSize(new Dimension(220, 220)); // Makes the number pad area larger and more square
        
        // Create operator panel (right side)
        JPanel operatorPanel = new JPanel();
        operatorPanel.setLayout(new GridLayout(5, 1, 8, 8)); // 5 rows, 1 column with spacing
        operatorPanel.setBackground(backgroundColor);
        operatorPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around operators
        
        // Create function panel (top)
        JPanel functionPanel = new JPanel();
        functionPanel.setLayout(new GridLayout(1, 4, 8, 8)); // 1 row, 4 columns with spacing
        functionPanel.setBackground(backgroundColor);
        functionPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 15, 10)); // Add more bottom padding
        
        // Create bottom panel (for equals, exit buttons)
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 1, 8, 8)); // Changed to 1 column for exit button
        bottomPanel.setBackground(backgroundColor);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40)); // Wide margins for exit button
        
        // Create image panel with more spacing
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(backgroundColor);
        imagePanel.setBorder(BorderFactory.createEmptyBorder(25, 0, 10, 0)); // Add more top padding
        
        // Create the image label
        lblImage = new JLabel();
        imagePanel.add(lblImage);
        
        // Create number buttons 0-9 with larger, more square appearance
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setBackground(buttonColor);
            numberButtons[i].setForeground(Color.BLACK);
            numberButtons[i].setFont(new Font("Arial", Font.BOLD, 18)); // Larger font
            numberButtons[i].setMargin(new Insets(10, 10, 10, 10)); // Add internal padding to make buttons larger
            // Make buttons look more square
            numberButtons[i].setPreferredSize(new Dimension(60, 60));
            final int num = i;
            numberButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String current = txtDisplay.getText();
                    txtDisplay.setText(current + num);
                }
            });
        }
        
        // Create operation buttons
        btnPlus = createOperatorButton("+");
        btnMinus = createOperatorButton("-");
        btnMultiply = createOperatorButton("*");
        btnDivide = createOperatorButton("/");
        
        // Make the decimal and +/- buttons match the number buttons
        btnDecimal = createFunctionButton(".");
        btnDecimal.setFont(new Font("Arial", Font.BOLD, 18)); // Larger font
        btnDecimal.setMargin(new Insets(10, 10, 10, 10)); // Add internal padding
        btnDecimal.setPreferredSize(new Dimension(60, 60)); // Make it square like number buttons
        btnDecimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtDisplay.getText().contains(".")) {
                    txtDisplay.setText(txtDisplay.getText() + ".");
                }
            }
        });
        
        btnPosNeg = createFunctionButton("+/-");
        btnPosNeg.setFont(new Font("Arial", Font.BOLD, 18)); // Larger font
        btnPosNeg.setMargin(new Insets(10, 10, 10, 10)); // Add internal padding
        btnPosNeg.setPreferredSize(new Dimension(60, 60)); // Make it square like number buttons
        btnPosNeg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double value = Double.parseDouble(txtDisplay.getText());
                    value = -value;
                    txtDisplay.setText(String.valueOf(value));
                } catch (Exception ex) {
                    txtDisplay.setText("Error");
                }
            }
        });
        
        btnPower = createFunctionButton("x²");
        btnPower.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double value = Double.parseDouble(txtDisplay.getText());
                    double result = Math.pow(value, 2);
                    txtDisplay.setText(String.valueOf(result));
                } catch (Exception ex) {
                    txtDisplay.setText("Error");
                }
            }
        });
        
        btnSqRoot = createFunctionButton("√");
        btnSqRoot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double value = Double.parseDouble(txtDisplay.getText());
                    if (value >= 0) {
                        double result = Math.sqrt(value);
                        txtDisplay.setText(String.valueOf(result));
                    } else {
                        txtDisplay.setText("Invalid Input");
                    }
                } catch (Exception ex) {
                    txtDisplay.setText("Error");
                }
            }
        });
        
        btnEqual = createFunctionButton("=");
        btnEqual.setBackground(new Color(0, 128, 0)); // Green for equal button
        btnEqual.setForeground(Color.BLACK);
        btnEqual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double secondValue = Double.parseDouble(txtDisplay.getText());
                    switch (mathOperator) {
                        case '+':
                            total2 = total1 + secondValue;
                            break;
                        case '-':
                            total2 = total1 - secondValue;
                            break;
                        case '*':
                            total2 = total1 * secondValue;
                            break;
                        case '/':
                            if (secondValue != 0) {
                                total2 = total1 / secondValue;
                            } else {
                                txtDisplay.setText("Cannot divide by zero");
                                return;
                            }
                            break;
                    }
                    txtDisplay.setText(String.valueOf(total2));
                    total1 = 0;
                } catch (Exception ex) {
                    txtDisplay.setText("Error");
                }
            }
        });
        
        btnClear = createFunctionButton("C");
        btnClear.setBackground(new Color(255, 99, 71)); // Tomato red for clear button
        btnClear.setForeground(Color.BLACK);
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtDisplay.setText("");
                total1 = 0;
                total2 = 0;
            }
        });
        
        btnOnOff = createFunctionButton("Off");
        btnOnOff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculatorOn = !calculatorOn;
                if (calculatorOn) {
                    btnOnOff.setText("Off");
                    enableAllButtons(true);
                } else {
                    btnOnOff.setText("On");
                    enableAllButtons(false);
                    txtDisplay.setText("");
                    btnOnOff.setEnabled(true); // Keep On/Off button enabled
                }
            }
        });
        
        btnExit = createFunctionButton("Exit");
        btnExit.setBackground(new Color(139, 0, 0)); // Dark red for exit button
        btnExit.setForeground(Color.BLACK);
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        // Add buttons to the panels in calculator layout
        // Function panel (top row)
        functionPanel.add(btnOnOff);
        functionPanel.add(btnClear);
        functionPanel.add(btnPower);
        functionPanel.add(btnSqRoot);
        
        // Number pad panel (main number grid)
        // Row 1
        numberPadPanel.add(numberButtons[7]);
        numberPadPanel.add(numberButtons[8]);
        numberPadPanel.add(numberButtons[9]);
        
        // Row 2
        numberPadPanel.add(numberButtons[4]);
        numberPadPanel.add(numberButtons[5]);
        numberPadPanel.add(numberButtons[6]);
        
        // Row 3
        numberPadPanel.add(numberButtons[1]);
        numberPadPanel.add(numberButtons[2]);
        numberPadPanel.add(numberButtons[3]);
        
        // Row 4
        numberPadPanel.add(numberButtons[0]);
        numberPadPanel.add(btnDecimal);
        numberPadPanel.add(btnPosNeg);
        
        // Operator panel (right side)
        operatorPanel.add(btnDivide);
        operatorPanel.add(btnMultiply);
        operatorPanel.add(btnMinus);
        operatorPanel.add(btnPlus);
        // btnEqual is now in its own panel
        
        // Bottom panel - only contains exit button now
        bottomPanel.add(btnExit);
        
        // Create a separate panel for the equals button
        JPanel equalsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        equalsPanel.setBackground(backgroundColor);
        equalsPanel.add(btnEqual);
        
        // Create a wrapper panel for the number pad and equals button
        JPanel numberWrapper = new JPanel(new BorderLayout(10, 10));
        numberWrapper.setBackground(backgroundColor);
        numberWrapper.add(numberPadPanel, BorderLayout.CENTER);
        numberWrapper.add(equalsPanel, BorderLayout.SOUTH);
        
        // Assemble all panels
        mainButtonPanel.add(functionPanel, BorderLayout.NORTH);
        mainButtonPanel.add(numberWrapper, BorderLayout.CENTER);
        mainButtonPanel.add(operatorPanel, BorderLayout.EAST);
        mainButtonPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        calculatorPanel.add(mainButtonPanel, BorderLayout.CENTER);
        calculatorPanel.add(imagePanel, BorderLayout.SOUTH);
        
        // Load the image
        loadImage("waves.jpg");
    }
    
    private JButton createOperatorButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(buttonColor); // Make all buttons white to match second image
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mathOperator = text.charAt(0);
                    total1 = Double.parseDouble(txtDisplay.getText());
                    txtDisplay.setText("");
                } catch (Exception ex) {
                    txtDisplay.setText("Error");
                }
            }
        });
        return button;
    }
    
    private JButton createFunctionButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(buttonColor); // Make all buttons white to match second image
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }
    
    private void enableAllButtons(boolean enable) {
        for (int i = 0; i < 10; i++) {
            numberButtons[i].setEnabled(enable);
        }
        btnPlus.setEnabled(enable);
        btnMinus.setEnabled(enable);
        btnMultiply.setEnabled(enable);
        btnDivide.setEnabled(enable);
        btnDecimal.setEnabled(enable);
        btnPosNeg.setEnabled(enable);
        btnPower.setEnabled(enable);
        btnSqRoot.setEnabled(enable);
        btnEqual.setEnabled(enable);
        btnClear.setEnabled(enable);
        btnExit.setEnabled(enable);
        txtDisplay.setEnabled(enable);
    }
    
    public JPanel getCalculatorPanel() {
        return calculatorPanel;
    }
    
    // Method to load and display the image
    private void loadImage(String imagePath) {
        try {
            // Try to load from file system first
            ImageIcon icon = new ImageIcon(imagePath);
            
            // If the image couldn't be loaded (icon.getIconWidth() <= 0), try loading from resources
            if (icon.getIconWidth() <= 0) {
                URL imageURL = getClass().getResource("/" + imagePath);
                if (imageURL != null) {
                    icon = new ImageIcon(imageURL);
                } else {
                    System.out.println("Could not find image: " + imagePath);
                    return;
                }
            }
            
            // Resize the image if needed
            Image img = icon.getImage();
            Image resizedImg = img.getScaledInstance(100, 60, Image.SCALE_SMOOTH);
            icon = new ImageIcon(resizedImg);
            
            lblImage.setIcon(icon);
        } catch (Exception e) {
            System.out.println("Error loading image: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        try {
            // Set system look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Enhanced Calculator");
                Calculator calculator = new Calculator();
                
                frame.setContentPane(calculator.getCalculatorPanel());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(520, 728); // set size of popup calculator
                frame.setVisible(true);
            }
        });
    }
}