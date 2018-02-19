import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.io.FileOutputStream;
import java.io.PrintWriter;



public class InputOutput {

	/**
	 * Displays the state of the board
	 * @param aBoard A Board object
	 */
	public static void displayBoard(Board aBoard)
	{
		for(int p = 0; p < aBoard.board.length; p++)
		{
			for(int q = 0; q < aBoard.board.length; q++)
			{
				try
				{
					
					Ship tempShip = aBoard.board[p][q];
					if(tempShip.getDestroyed() == true)
						System.out.print("x ");
					else
						System.out.print("- ");
					
				}
				catch(NullPointerException e)
				{
					System.out.print("- ");
				}
				
				
				
			}
			System.out.println();
		}
	}
	
	/**
	 * Gets the users guess, in the form of A1
	 * @return String User guess
	 */
	public static String getUserGuess()
	{
		Scanner sc = new Scanner(System.in);
		String userGuess = sc.nextLine();
		return userGuess;
	}
	
	/**
	 * Saves the game state to a text file
	 * @param aBoard The current board
	 */
	public static void saveGame(Board aBoard)
	{
		
		FileOutputStream outputStream  = null;
		PrintWriter printWriter = null;
		
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		
		try
		{
			
			outputStream = new FileOutputStream(input + ".txt");
			printWriter = new PrintWriter(outputStream);
			
			String userLine = null;
			//Write each line from the user to file.
			
			for(int p = 0; p < 10; p ++)
			{
				for(int q = 0; q < 10; q ++)
				{
					if(aBoard.board[p][q] != null)
					{
						userLine = Character.toString(aBoard.board[p][q].getDisplay());
						
					}
					else
					{
						userLine = "-";
					}
					printWriter.print(userLine);
					
				}
				printWriter.println();
			}
			
			
			
			
				
			
			printWriter.close();
			outputStream.close();
			
			
		}
		catch(IOException e)
		{
			System.out.println("Error in writing to file " + e);
		}
	}
	
	/**
	 * Loads a save state from a text file. Writes it to a board
	 * @return Board with loaded values.
	 */
	public static Board loadGame()
	{
		
		Scanner sc = new Scanner(System.in);
		
		FileReader filereader = null;
		BufferedReader buffreader = null;
		Board outputBoard = new Board();
		outputBoard.createBoard(10);
		
		try
		{
			System.out.println("Enter file name to read from");
			String input = sc.nextLine();
			
			
			
			filereader = new FileReader(input +".txt");
			buffreader = new BufferedReader(filereader);
			
			String outputLine = buffreader.readLine();
			
			//Display each line of data from the file in the console
			for(int p = 0; p < 10; p ++)
			{
				char[] outputChar = outputLine.toCharArray();
				for(int q = 0; q < 10; q ++)
				{
					if(outputChar[q] == '-')
					{
						outputBoard.board[p][q] = null;
					}
					else if(outputChar[q] == 'S')
					{
						Ship outputShip = new Ship(1);
						outputBoard.board[p][q] = outputShip;
						
					}
					else if(outputChar[q] == 'D')
					{
						Ship outputShip = new Ship(2);
						outputBoard.board[p][q] = outputShip;
					}
					else if(outputChar[q] == 'C')
					{
						Ship outputShip = new Ship(3);
						outputBoard.board[p][q] = outputShip;
					}
					else if(outputChar[q] == 'B')
					{
						Ship outputShip = new Ship(4);
						outputBoard.board[p][q] = outputShip;
					}
					
				}
					
				outputLine = buffreader.readLine();
			}
			
			filereader.close();
			buffreader.close();
			
		}
		catch(IOException e)
		{
			System.out.println("Error reading file " + e);
		}
		
		outputBoard.displayBoard();
		return outputBoard;
	}
	
}
