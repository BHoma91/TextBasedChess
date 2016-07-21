/**
 *
 *
 * @author Karl Capili
 * @author Ben Homa
 *
 *
 */

package environment;


/**
 *
 *
 * Superclass for the individual pieces
 *
 *@param color // the color of the piece
 *@param piece // the type of piece it is
 *@param x_coordinate
 *@param y_coordinate
 *
 *
 */
public class Piece extends Environment
{
	String color;
	String piece;
	int x_coordinate;
	int y_coordinate;

	public Piece(String color, String piece, int x_coordinate, int y_coordinate)
	{
		this.color = color;
		this.piece = piece;
		this.x_coordinate = x_coordinate;
		this.y_coordinate = y_coordinate;
	}


	public Piece() {
		// TODO Auto-generated constructor stub
	}

	public void setColor(String color)
	{
		this.color = color;
	}

	public String getColor()
	{
		return color;
	}

	public void setPiece(String piece)
	{
		this.piece = piece;
	}

	public String getPiece()
	{
		return piece;
	}

	public void setX(int x_coordinate)
	{
		this.x_coordinate = x_coordinate;
	}

	public int getX()
	{
		return x_coordinate;
	}

	public void setY(int y_coordinate)
	{
		this.y_coordinate = y_coordinate;
	}

	public int getY()
	{
		return y_coordinate;
	}

	/**
	 *
	 *
	 * Move checks if the move is valid as well as performs the move if it is valid
	 *
	 * @param piece // the piece that is doing the moving
	 * @param x // the x_coordinate where the piece wants to move to
	 * @param y // the y_coorinate where the piece wants to move to
	 *
	 * @return int if the move is successful 0 is returned if it is invalid -1 is returned
	 *
	 *
	 */

	public int move(Piece piece, int x, int y)
	{
		/*
		 * method is overridden
		 */
		return 0;
	}

	/**
	 *
	 *
	 * check_checker is the method that tests whether the given piece puts the opposing king into check
	 *
	 * @param piece \\ the piece to check if it puts the opposing king into check
	 * @param king \\ the string value for what the king is
	 * @return int \\ returns 1 if the piece puts the opposing king into check and 0 if not
	 *
	 *
	 */

	public int check_checker(Piece piece, String king)
	{
		/*
		 * method is overridden
		 */
		return 0;
	}

	/**
	 *
	 *
	 * canMove is the method to test if the piece canMove to fix a check. If there are no pieces
	 * that can move then it is checkmate
	 *
	 * @param piece \\ the piece to check if it can move
	 * @return int \\return 1 if it cannot move 0 if it can
	 *
	 */

	public int canMove(Piece piece) {


		return 0;
	}
}
