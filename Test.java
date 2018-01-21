package graphicalBattleShip;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class BShip extends JFrame implements ActionListener{
	public static final int HEIGHT = 350;
	public static final int WIDTH = 400;
	
	public static final int ROWS = 8;
	public static final int COLUMNS = 8;
	
	public static final int numOfButtons = 64;
	
	JTextField text;
	PrintWriter pw = null;
	Scanner sc = null;
	Scanner kb = new Scanner(System.in);
	BufferedReader br = null;
	
	GridButtons buttons[][];
	
	JLabel label;
	JButton button;
	private static JLabel lblPlayer_1;
	private static JLabel lblPlayer_2;
	private static JTextField player1NewName;
	private static JTextField player2NewName;
	private static JFrame changeNameWindow;
	private static JFrame inputWindow;
	private static JTextField txtInputData;
	private static int row;
	private static int column;
	
	public void changeName(){
		changeNameWindow = new JFrame("Change name Window");
		changeNameWindow.setSize(300, 100);
		changeNameWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		changeNameWindow.setLayout(new GridLayout(3, 2));
		JLabel player1Name = new JLabel("Enter Player1 Name: ");
		player1NewName = new JTextField("xyz",30);
		JLabel player2Name = new JLabel("Enter Player2 Name: ");
		player2NewName = new JTextField("abc",30);
		JButton okChangeName = new JButton("OK");
		okChangeName.setActionCommand("okChangeName");
		okChangeName.addActionListener(this);
		changeNameWindow.add(player1Name);
		changeNameWindow.add(player1NewName);
		changeNameWindow.add(player2Name);
		changeNameWindow.add(player2NewName);
		changeNameWindow.add(okChangeName);
		changeNameWindow.setVisible(true);
	}
	
	public void setPositions(String displayLabel, String buttonName){
		inputWindow = new JFrame("Enter Positions");
		inputWindow.setSize(700, 100);
		inputWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		inputWindow.setLayout(new GridLayout(3, 1));
		label = new JLabel(displayLabel);
		txtInputData = new JTextField("eg \"11,22,33...\"",20);
		button = new JButton("OK");
		button.addActionListener(this);
		button.setActionCommand(buttonName);
		inputWindow.add(label);
		inputWindow.add(txtInputData);
		inputWindow.add(button);
		inputWindow.setVisible(true);	
	}
	
	public void player1Ships(){
		String a = "Please pass the system to Player_1. Enter the positions of your 10 ships.";
		String b = "Make sure to enter them in the same format as provided otherwise the game will quit!!";
		String displayLabel = a+b;
		String buttonName = "Player 1 ships";
		setPositions(displayLabel, buttonName);

	}
	public void player1Grenade(){
		
	}
	public void player2Ships() {
		String a = "Please pass the system to Player_2. Enter the positions of your 10 ships.";
		String b = "Make sure to enter them in the same format as provided otherwise the game will quit!!";
		String displayLabel = a+b;
		String buttonName = "Player 2 ships";
		setPositions(displayLabel, buttonName);
	}
	public void player2Grenade(){
		
	}
	
	
	public BShip(){
		super();
		setTitle("BattleShip");
		setSize(WIDTH, HEIGHT);
		setLocation(200, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		JMenuBar bar1 = new JMenuBar();
		JMenu file_menu = new JMenu("File");
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.addActionListener(this);
		JMenuItem changePlayerName = new JMenuItem("Change Player Name");
		changePlayerName.addActionListener(this);
		JMenuItem exitGame = new JMenuItem("Exit Game");
		exitGame.addActionListener(this);
		
		file_menu.add(newGame);
		file_menu.add(changePlayerName);
		file_menu.add(exitGame);
		
		bar1.add(file_menu);
		
		JMenu help = new JMenu("Help");
		JMenuItem howToPlay = new JMenuItem("How to Play");
		howToPlay.addActionListener(this);
		JMenuItem credits = new JMenuItem("Credits");
		credits.addActionListener(this);
		
		help.add(howToPlay);
		help.add(credits);
		
		bar1.add(help);
		
		this.setJMenuBar(bar1);
		
		JPanel pnlPlayer1 = new JPanel();
		pnlPlayer1.setLayout(new GridLayout(2, 1));
		lblPlayer_1 = new JLabel("Player_1");
		JTextField player_1Score = new JTextField("0",1);
		pnlPlayer1.add(lblPlayer_1);
		pnlPlayer1.add(player_1Score);
		JPanel pnlPlayer2 = new JPanel();
		pnlPlayer2.setLayout(new GridLayout(2, 1));
		lblPlayer_2 = new JLabel("Player_2");
		JTextField player_2Score = new JTextField("0",1);
		pnlPlayer2.add(lblPlayer_2);
		pnlPlayer2.add(player_2Score); 
		
		JPanel scores = new JPanel();
		scores.setLayout(new BorderLayout());
		scores.add(pnlPlayer1,BorderLayout.WEST);
		scores.add(pnlPlayer2,BorderLayout.EAST);
		
		JLabel instructionsLabel = new JLabel("To start the game click the \"File\" on top and click \"New Game\"");
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(2, 1));
		northPanel.add(instructionsLabel);
		northPanel.add(scores);
		
		this.add(northPanel,BorderLayout.NORTH);
		
		JButton exitButton = new JButton("Exit Game");
		exitButton.addActionListener(this);
		this.add(exitButton,BorderLayout.SOUTH);
		
		buttons = new GridButtons[ROWS][COLUMNS];
		
		for(int i=0;i<ROWS;i++){
			for(int j=0;j<COLUMNS;j++){
				buttons[i][j] = new GridButtons();//"["+i+"]"+"["+j+"]"
				buttons[i][j].setText("["+i+"]"+"["+j+"]");
				buttons[i][j].addActionListener(this);
			}
		}
		JPanel centrePanel = new JPanel();
		centrePanel.setLayout(new GridLayout(ROWS, COLUMNS));
		for(int i=0;i<ROWS;i++){
			for(int j=0;j<COLUMNS;j++){
				centrePanel.add(buttons[i][j]);
			}
		}
		this.add(centrePanel,BorderLayout.CENTER);
		
	}


	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if(s.equals("How to Play")){
			Scanner sc = null; JTextArea display = new JTextArea();String text;
			JFrame window  = new JFrame("ReadMe.txt");
			window.setSize(800, 400);
			window.setLocation(100, 100);
			window.setLayout(new GridLayout());
			
			try {
				sc = new Scanner(new FileInputStream("abc.txt"));
				text = sc.nextLine();
				System.out.println(text);
				while(sc.hasNext()){
					display.append(text);
					display.append("\n");
					System.out.println(text+"\n");
					text = sc.nextLine();
				}
			} catch (FileNotFoundException e1) {
				System.out.println("FileNotFound Exception");
				System.exit(0);
			}
			sc.close();
			JScrollPane pane = new JScrollPane(display);
			pane.getHorizontalScrollBar();
			JPanel p1 = new JPanel(new GridLayout());
			p1.add(pane);
			window.add(p1,BorderLayout.CENTER);
			window.setVisible(true);
		}
		if(s.equals("Exit Game")){
			System.exit(0);
		}
		if(s.equals("exitButton")){
			System.exit(0);
		}
		if(s.equals("Change Player Name")){
			changeName();
		}
		if(s.equals("okChangeName")){
			lblPlayer_1.setText(player1NewName.getText());
			lblPlayer_2.setText(player2NewName.getText());
			changeNameWindow.dispose();
		}

		if(s.equals(" ")){
			System.out.println("Pressed");
		}
		if(s.equals("New Game")){
			player1Ships();
		}
		if(s.equals("Player 1 ships")){
			System.out.println("player 1 ships ok button ");
			String[] eachPosition = txtInputData.getText().split(",");
			for(int i=0;i<eachPosition.length;i++){
				row = (eachPosition[i].trim().charAt(0)) - 48;
				System.out.println(row);
				column = (eachPosition[i].trim().charAt(1)) - 48;
				System.out.println(column);
				buttons[row][column].setType("S-P1");
				buttons[row][column].setPlayer("S-P1");
				buttons[row][column].setIsCalled(true);
			}
			inputWindow.dispose();
			player2Ships();
		}
		if(s.equals("Player 2 ships")){
			boolean isCalled = true;
			String gridPositions = txtInputData.getText();
			String[] eachPosition = gridPositions.split(",");
			
				for(int i=0;i<eachPosition.length;i++){
					row = (eachPosition[i].trim().charAt(0)) - 48;
					column = (eachPosition[i].trim().charAt(1)) - 48;
					if(buttons[row][column].getIsCalled()==false){
						buttons[row][column].setType("S-P2");
						buttons[row][column].setPlayer("S-P2");
						buttons[row][column].setIsCalled(true);
					}
					else{
						isCalled = false;
					}
				}
				if(isCalled == false){
					JFrame error = new JFrame("Error Window");
					error.setSize(800, 100);
					error.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					error.setLayout(new GridLayout(2, 1));
					JLabel errLabel = new JLabel("Sorry!! one of the positions you entered was already selected by Player_1. Please enter again!!");
					error.add(errLabel);
					JButton errorButton = new JButton("OK");
					errorButton.addActionListener(this);
					errorButton.setActionCommand("errorButton");
					error.add(errorButton);
					error.setVisible(true);
			}
			inputWindow.dispose();
		}
		if(s.equals("errorButton")){
			player2Ships();
		}
	
	}
}


public class Test {
	public static void main(String args[]){
		BShip w1 = new BShip();
		w1.setVisible(true);
	}
}
