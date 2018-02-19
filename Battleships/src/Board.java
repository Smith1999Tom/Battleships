
public class Board {

	Ship[][] board;
	
	/**
	 * 
	 * @param width Width and height of the board(square)
	 */
	public void createBoard(int width)
	{
		
		//Check if board size parameters are between 8 and 12.
		boolean validParameters = false;
		if(width > 7)
			validParameters = true;
		
		//If no board currently exists, create one and return it.
		if(validParameters)
			board = new Ship[width][width];
		else
			board = new Ship[0][0];
	}
	
	
	/**
	 * Displays the current state of the board on screen
	 */
	public void displayBoard()
	{
		InputOutput.displayBoard(this);
	}
	
	/**
	 * Initializes the ships to be placed, and passes them into the placeRandomShip method
	 */
	public void placeShips()
	{
		Ship battleship = new Ship(4);
		Ship cruiser = new Ship(3);
		Ship destroyer = new Ship(2);
		Ship submarine = new Ship(1);
		
		//Considered using a for loop, but it wouldn't simplify things that much.
		placeRandomShip(battleship);
		placeRandomShip(cruiser);
		placeRandomShip(cruiser);
		placeRandomShip(destroyer);
		placeRandomShip(destroyer);
		placeRandomShip(destroyer);
		placeRandomShip(submarine);
		placeRandomShip(submarine);
		placeRandomShip(submarine);
		placeRandomShip(submarine);

	}
	
	/**
	 * Places the ships in random places on the board
	 * @param placeShip Ship to be placed
	 */
	public void placeRandomShip(Ship placeShip)
	{
		
		boolean shipPlaced = false;
		
		//Determine the ships rotation
		float randomRotation = (float)Math.random();
		int rotation = Math.round(randomRotation);
		
		
		while(!shipPlaced)
		{
			
			try
			{
				//Generate random x and y coordinates, from 0-10
				float randomY = (float)Math.random() * 9;
				int yCoord = Math.round(randomY);
				float randomX = (float)Math.random() * 9;
				int xCoord = Math.round(randomX);
				
				//Ship to act as a buffer. If the ship placement becomes invalid half way
				//through placement, this saves me from having to undo all previouse placements
				Ship[] bufferShip = new Ship[placeShip.getLength()];
				
				
				//0 for horizontal
				if(rotation == 0)
				{
					//For the length of the ship
					for(int i = 0; i < placeShip.getLength(); i ++)
					{
						//If the board is empty, place the ship. Else, throw an exception.
						if(this.board[yCoord][xCoord+i] == null)
							bufferShip[i] = new Ship(placeShip.getLength());	
						else
							throw new NullPointerException();							
					}
					shipPlaced = true;
					
					for(int i = 0; i < placeShip.getLength(); i ++)
					{
						//Place the buffer on the board
						this.board[yCoord][xCoord+i] = bufferShip[i];	
					}
				}
				//Exact same as above, just for vertical rotation
				else
				{
					
					for(int i = 0; i < placeShip.getLength(); i ++)
					{
						if(this.board[yCoord+i][xCoord] == null)
							bufferShip[i] = new Ship(placeShip.getLength());	
						else
							throw new NullPointerException();							
					}
					shipPlaced = true;
					
					for(int i = 0; i < placeShip.getLength(); i ++)
					{
						this.board[yCoord+i][xCoord] = bufferShip[i];	
					}
				}
			}
			catch(ArrayIndexOutOfBoundsException e)
			{}
			catch(NullPointerException e)
			{}
		}
	}
	
	/**
	 * Writes empty lines to console
	 */
	public static void clearBoard()
	{
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}
}
