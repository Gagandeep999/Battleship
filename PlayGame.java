/**
 * Author : Gagandeep Singh
 * Date	  : 18 September 2017	 		
 * Purpose:	Simulate a game of Battleships between the user and computer
 */

import java.util.Random;
import java.util.Scanner;

public class PlayGame extends Position{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner keyboard = new Scanner(System.in);
		Random rand=new Random();
		System.out.println("Hi, let’s play Battleship!\n");
		Position[][] pos = new Position[8][8];
		Position posObj = new Position();
		Win winObj = new Win();
		
		/*intialise each object of the grid using the constructor*/
		for(int i=0;i<=7;i++)
			for(int j=0;j<=7;j++)
				pos[i][j] = new Position();
		
		String coordinates;//variable to store the input from the user in 'A1' form
		int numberOfUserGrenade = 0;	//counter for the number of user grenade
		int numberOfUserShips = 0;		//counter for the number of user ships
		int numberOfComputerShips=0;	//counter for the number of computer ships
		int numberOfComputerGrenade=0;	//counter for the number of computer grenade
		String userRocketPosition;
		final int totalNumberOfShips = 6;//maximum number of ships allowed in the game
		final int totalNumberOfGrenades = 4;//maximum number of grenades allowed in the game
		boolean userTurn=true, computerTurn = true; //variables used to skip a turn if player hits a grenade
		int userTurnCounter=0, computerTurnCounter=0;
		
		/**
		 * Taking input from the user in "A1" form and then converting it into array locations which serves the purpose of checking
		 * if the input is valid and also accessing different locations in the array.
		 * After validity of the input is confirmed the corresponding location is assigned the type as "Ship" or "Grenade" and owner as "User" or "Computer".
		 * A counter keeps track of the number of ships entered and decreases it whenever one player hits any of other player ship.
		 */
		//taking input form the user for his ship positions
		do{
			System.out.print("Enter the coordinates of your ship #"+(numberOfUserShips+1) +":");
			coordinates = keyboard.next().toUpperCase();
			
			if(posObj.chkCoordinate(coordinates))
			{
				if(pos[posObj.row(coordinates)][posObj.column(coordinates)].getType() == "null"){
					pos[posObj.row(coordinates)][posObj.column(coordinates)].setType("S");
					pos[posObj.row(coordinates)][posObj.column(coordinates)].setOwner("S");
					numberOfUserShips++;
				}
				else{
					System.out.println("sorry, coordinates already used. try again.");
				}
			}
		}while(numberOfUserShips<totalNumberOfShips);
		System.out.println();
		
		//taking input from the user for his grenade position
		do{
			System.out.print("Enter the coordinates of your grenade #"+(numberOfUserGrenade+1) +":");
			coordinates = keyboard.next().toUpperCase();
			
			if(posObj.chkCoordinate(coordinates))
			{
				if(pos[posObj.row(coordinates)][posObj.column(coordinates)].getType() == "null"){
					pos[posObj.row(coordinates)][posObj.column(coordinates)].setType("G");
					pos[posObj.row(coordinates)][posObj.column(coordinates)].setOwner("G");
					numberOfUserGrenade++;
				}
				else{
					System.out.println("sorry, coordinates already used. try again.");
				}			
			}
		}while(numberOfUserGrenade<totalNumberOfGrenades);
		
		/**
		 * Computer's ship and grenade locations are placed on the grid using a random function. Firstly a random grid location is generated
		 * and then that location is checked if there is no ship or grenade at that location then. After all the checks type and owner is set.
		 */
		//setting up computer ships using the random function
		do{
			int randomRow = rand.nextInt(8);
            int randomCol = rand.nextInt(8); 
            if(pos[randomRow][randomCol].getType() == "null")
            {
            	pos[randomRow][randomCol].setType("s");
            	pos[randomRow][randomCol].setOwner("s");
            	numberOfComputerShips++;
            }
		}while(numberOfComputerShips<totalNumberOfShips);
		
		//setting up computer grenades using the random function
		do{
			int randomRow = rand.nextInt(8);
            int randomCol = rand.nextInt(8); 
            if(pos[randomRow][randomCol].getType() == "null")
            {
            	pos[randomRow][randomCol].setType("g");
            	pos[randomRow][randomCol].setOwner("g");
            	numberOfComputerGrenade++;
            }
		}while(numberOfComputerGrenade<totalNumberOfGrenades);
		
		System.out.println("\nOK, the computer placed its ships and grenades at random. Let’s play.");
		
		/**
		 * The game starts by taking an input for the rocket from the user checking the validity of the coordinates, then it checks if the
		 * coordinate belongs to the computer, then it performs another check if it's a ship or grenade. In case of ship, number of ships get
		 * reduced and the coordinate's isCalled value is set to true. Same thing happens if computer hits user's ship. When one of the player
		 * hits a grenade they lose a turn and this continues till one of the player sinks all the ships of his opponent.
		 */
		//simulating game
		do
		{
			if(userTurn){
				System.out.print("\nposition of your/user rocket:");
				userRocketPosition = keyboard.next().toUpperCase();
				int rowUser = posObj.row(userRocketPosition);
				int columnUser = posObj.column(userRocketPosition);
				if(posObj.chkCoordinate(userRocketPosition))
				{
					if(!pos[rowUser][columnUser].getIsCalled())
					{
						if((pos[rowUser][columnUser].getOwner() == "Computer")){
							if(pos[rowUser][columnUser].getType() == "Ship"){
							numberOfComputerShips--;
							pos[rowUser][columnUser].setIsCalled(true);
							pos[rowUser][columnUser].setDisplayValue("S");
							System.out.print("ship hit.");
							if((numberOfComputerShips==0)){
								System.out.println("  user wins!");
								winObj.ifWin(pos);
								break;
							}
							}
							if(pos[rowUser][columnUser].getType() == "Grenade"){
							userTurn = false;
							pos[rowUser][columnUser].setIsCalled(true);
							pos[rowUser][columnUser].setDisplayValue("G");
							System.out.print("boom! grenade.");
							}
						}
						if((pos[rowUser][columnUser].getOwner() == "none")){
							pos[rowUser][columnUser].setIsCalled(true);
							pos[rowUser][columnUser].setDisplayValue("*");
							System.out.print("nothing.");
						}
						if((pos[rowUser][columnUser].getOwner() == "User")){
							if(pos[rowUser][columnUser].getType() == "Ship"){
								numberOfUserShips--;
								pos[rowUser][columnUser].setIsCalled(true);
								pos[rowUser][columnUser].setDisplayValue("s");
								System.out.print("ship hit.");
								if((numberOfUserShips==0)){
									System.out.println("  computer wins!");
									winObj.ifWin(pos);
									break;
								}
								}
								if(pos[rowUser][columnUser].getType() == "Grenade"){
								userTurn = false;
								pos[rowUser][columnUser].setIsCalled(true);
								pos[rowUser][columnUser].setDisplayValue("s");
								System.out.print("boom! grenade.");
								}
						}
					}
					else
					{
						System.out.println("position already called.");
					}
				}
				else
				{
					System.out.println("sorry, coordinates outside the grid. try again.");
				}
				posObj.showBoard(pos);
			}
			
			if(computerTurn==false)
				computerTurnCounter++;
			if(computerTurnCounter==2){
				computerTurn = true;
				computerTurnCounter = 0;
			}
			
			if(computerTurn){
				System.out.print("\nposition of my/computer rocket: ");
				int rowComp = rand.nextInt(8);
				int colComp = rand.nextInt(8);
				char displayRowComp = (char)(rowComp+65);
				char displayColcomp = (char)(colComp+49);
				System.out.println(displayRowComp+""+displayColcomp);
				
					if(!pos[rowComp][colComp].getIsCalled()){
						if((pos[rowComp][colComp].getOwner() == "User")){
							if(pos[rowComp][colComp].getType() == "Ship"){
							numberOfUserShips--;
							pos[rowComp][colComp].setDisplayValue("s");
							pos[rowComp][colComp].setIsCalled(true);
							System.out.print("ship hit.");
							if((numberOfUserShips==0)){
								System.out.println("  computer wins!");
								winObj.ifWin(pos);
								break;
							}
							}
							if(pos[rowComp][colComp].getType() == "Grenade"){
							computerTurn = false;
							pos[rowComp][colComp].setDisplayValue("g");
							pos[rowComp][colComp].setIsCalled(true);
							System.out.print("boom! grenade.");
							}
						}
						if((pos[rowComp][colComp].getOwner() == "none"))
						{
							pos[rowComp][colComp].setIsCalled(true);
							pos[rowComp][colComp].setDisplayValue("*");
							System.out.print("nothing.");
						}
						if((pos[rowComp][colComp].getOwner() == "Computer")){
							if(pos[rowComp][colComp].getType() == "Ship"){
								numberOfComputerShips--;
								pos[rowComp][colComp].setDisplayValue("S");
								pos[rowComp][colComp].setIsCalled(true);
								System.out.print("ship hit.");
								if((numberOfComputerShips==0)){
									System.out.println("  user wins!");
									winObj.ifWin(pos);
									break;
								}
								}
								if(pos[rowComp][colComp].getType() == "Grenade"){
								computerTurn = false;
								pos[rowComp][colComp].setDisplayValue("G");
								pos[rowComp][colComp].setIsCalled(true);
								System.out.print("boom! grenade.");
								}
						}
					}
					else
					{
						System.out.println("position already called.");
					}
					posObj.showBoard(pos);
				}	
			
			if(userTurn==false)
				userTurnCounter++;
			if(userTurnCounter==2){
				userTurn = true;
				userTurnCounter = 0;
			}
			
		}while((numberOfUserShips!=0)&&(numberOfComputerShips!=0));
	}
}
