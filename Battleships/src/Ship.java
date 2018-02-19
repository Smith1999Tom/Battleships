
public class Ship {

	private int length;
	private boolean destroyed;
	private char display;			//What letter to use to represent the ship.
	
	
	
	Ship()       
	{
		length =-1;
		destroyed = false;
		display = 'E';
	}
	
	Ship(int aLength)
	{
		length = aLength;
		destroyed = false;
		display = 'O';
		
		
		switch(length)
		{
		case 1:
			display = 'S';
			break;
		case 2:
			display = 'D';
			break;
		case 3:
			display = 'C';
			break;
		case 4:
			display = 'B';
			break;
		
		}
		
		
	}
	

	
	public char getDisplay()
	{
		return this.display;
	}
	
	public boolean getDestroyed()
	{
		return this.destroyed;
	}
	
	public void setDestroyed()
	{
		//Sets ship to destroyed
		this.destroyed = true;
	}
	
	public int getLength()
	{
		return this.length;
	}
	
	
	
	
}
