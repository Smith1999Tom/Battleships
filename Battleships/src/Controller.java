import java.math.*;
import java.util.Scanner;

/**
 * Controller class. Manages over all other classes.
 * @author Tom Smith
 *
 */
public class Controller {

	//TODO mention in report
	//Use an array of boards, to allow easier implementation of multiplayer
	public static Board[] userBoard = new Board[2];
	
	public static void main(String args[])
	{
		
		int menuChoice = menu();
		
		if(menuChoice == 1)	//new game
		{
			Board bufferBoard = new Board();

		
			//Create test board
			bufferBoard.createBoard(10);
			//bufferBoard.testFillBoard();
			
			bufferBoard.placeShips();
			
			
			bufferBoard.displayBoard();
			
			userBoard[0] = bufferBoard;
			
			gameLoop();
		}
		else if(menuChoice == 2)
		{
			userBoard[0] = InputOutput.loadGame();
			gameLoop();
		}
		
		
		
		
		
		
		
	}
	
	/**
	 * Main game loop. Repeats until game has ended.
	 */
	public static void gameLoop()
	{
		int score = 0;
		boolean victory = false;
		
		while(!victory)
		{
			//Display board
			Board.clearBoard();
			userBoard[0].displayBoard();
			System.out.println("You have hit " + score + " ships.");
			
			
			boolean validInput = false;
			int[] processedInput = new int[2];
			
			//Get and validate input
			do
			{
				String rawInput = InputOutput.getUserGuess();
				
				processedInput = processUserInput(rawInput);
				if(processedInput[0] != -1)
					validInput = true;
				
			}while(!validInput);

			
			//If user hits ship, update board and increment score.
			processHit(processedInput);
			//Score is local to gameLoop(), so increment it outside method.
			try
			{
				if(userBoard[0].board[processedInput[0]][processedInput[1]].getDestroyed())
				score += 1;
			}
			catch(NullPointerException e)
			{
				
			}
			
			
			victory = checkVictory();
			
			
			
		}
		

	}
	
	
	
	/**
	 * Method to process the users input, and convert it into usable coordinates.
	 * @param stringInput Raw input from user
	 * @return int[] Processed user input
	 */
	public static int[] processUserInput(String stringInput)
	{
		String[] coord1 = new String[] {"A","B","C","D","E","F","G","H","I","J"};
		String[] coord2 = new String[] {"1","2","3","4","5","6","7","8","9","10"};
		
		String xCoord = stringInput.substring(0, 1);
		String yCoord = stringInput.substring(1);
		
		int[] processedInput = new int[2];
		boolean validInput = false;
		
		//If users xCoord is a valid x coordinate, get ready to return it.
		for(int i = 0; i < coord1.length; i++)
		{
			if(xCoord.equalsIgnoreCase(coord1[i]))
			{
				validInput = true;
				//Since 2d array does y first, x value has index of 1 not 0
				processedInput[1] = i;
			}
		}
		
		//If users yCoord is a valid y coordinate, get ready to return it.
		if(validInput)
		{
			validInput = false;
			for(int i = 0; i < coord2.length; i ++)
			{
				if(yCoord.equalsIgnoreCase(coord2[i]))
				{
					validInput = true;
					processedInput[0] = i;
				}
			}
					
		}
		
		if(stringInput.equalsIgnoreCase("save"))
			InputOutput.saveGame(userBoard[0]);
		else if(stringInput.equalsIgnoreCase("load"))
			userBoard[0] = InputOutput.loadGame();
		
		
		
		//If both x and y are valid, return them. Else return (-1,-1)
		if(validInput)
			return processedInput;
		else
		{
			int[] returnInt = new int[] {-1,-1};
			return returnInt;
		}
		
		
		
		
	}
	
	/**
	 * 	Method to determine whether the user has hit a ship. 
	 * @param intInput Input entered by user. First value is y coord, second is x.
	 */
	public static void processHit(int[] intInput)
	{
		Ship shipTemp = new Ship();
		if(userBoard[0].board[intInput[0]][ intInput[1]] != null)
		{
			shipTemp = userBoard[0].board[intInput[0]][intInput[1]];
			shipTemp.setDestroyed();
			userBoard[0].board[intInput[0]][intInput[1]] = shipTemp;
			System.out.println("Hit!");
		}	
		else
			System.out.println("Miss...");
		
		
		
	}

	/**
	 * Method to check if victory has occured (all enemy ships destroyed)
	 * @return Player has won
	 */
	public static boolean checkVictory()
	{
		
		boolean victory = true;
		for(int p = 0; p < userBoard[0].board.length; p ++)
		{
			for(int q = 0; q < userBoard[0].board.length; q ++)
			{
				
				try
				{
					Ship tempShip = userBoard[0].board[p][q];
					if(!tempShip.getDestroyed())
						victory = false;
					
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					
				}
				catch(NullPointerException e)
				{
					
				}
			}
		}
		
		if(victory)
			System.out.println("Victory!");
		return victory;
		
	}
	
	/**
	 * Method to destroy most ships on the board, to allow testing of the victory condition.
	 * For dev use only, not called in the actual game.
	 */
	public static void fakeVictory()
	{
		
		for(int p = 0; p < 9; p++)
		{
			for(int q = 0; q < 9; q ++)
			{
				int[] coordinates = new int[] {p, q};
				processHit(coordinates);
					
			}
		}
		
	}
	
	/**
	 * Displays a menu
	 * @return choice selected by user
	 */
	public static int menu()
	{
		System.out.println("Tom's Battleships game");
		System.out.println("");
		System.out.println("New game - 1");
		System.out.println("Load game - 2");
		
		int choice = -1;
		Scanner sc = new Scanner(System.in);
		
		while((choice != 1) && (choice != 2))
		{
			System.out.println("Enter a choice - 1 or 2");
			if(sc.hasNextInt())
				choice = sc.nextInt();
			else
				choice = -1;
		}
		
		return choice;
	}
	
	
	
	
}
