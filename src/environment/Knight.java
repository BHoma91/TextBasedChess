/**
 *
 *
 * @author Karl Capili
 * @author Ben Homa
 *
 *
 */

package environment;

import chess.Chess;


/**
 *
 *
 *
 *Knight class is a subclass of the Piece class where we implement the methods and rules that
 *are specific to knight pieces
 *
 *@param color // the color of the piece
 *@param piece // the type of piece it is
 *@param x_coordinate
 *@param y_coordinate
 *
 *
 */

public class Knight extends Piece {
	String color;
	String piece;
	int x_coordinate;
	int y_coordinate;

	public Knight(String color, String piece, int x_coordinate, int y_coordinate) {
		super(color, piece, x_coordinate, y_coordinate);
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

	@Override
	public int move(Piece piece, int x, int y) {
		int Dx = Math.abs(piece.getX() - x);
		int Dy = Math.abs(piece.getY() - y);
		Boolean move = false;
		Piece temp = pieces[x][y];
		int O_x = piece.getX();
		int O_y = piece.getY();

		if (Dx == 2 && Dy == 1 || Dx == 1 && Dy == 2) {
			move = true;
		}
		if (!move) {
			System.out.println();
			System.err.println("ERROR: Invalid Move");
			return -1;
		} else {
			String color = piece.getColor().toLowerCase();
			boolean notvalid = false;

			if (pieces[x][y] != null) {
				String color2 = pieces[x][y].getColor().toLowerCase();
				notvalid = color.equals(color2); // if same color not valid
			}
			if (!notvalid) {
				pieces[piece.getX()][piece.getY()] = null;
				pieces[x][y] = piece;
				piece.setX(x);
				piece.setY(y);
			}
			else {
				System.out.println();
				System.err.println("ERROR: Invalid Move");
				return -1;
			}
		}
		String color = piece.getColor().toLowerCase();

		if(color.equals("white")) {
			if(Chess.check_white() > 0){
				System.out.println("Invalid Move: Your king is in check");
				pieces[O_x][O_y] = piece;
				piece.setX(O_x);
				piece.setY(O_y);
				pieces[x][y]=temp;
				return -1;
			}
		}
		else {                  //Black
			if(Chess.check_black() > 0){
				System.out.println("Invalid Move: Your king is in check");
				pieces[O_x][O_y] = piece;
				piece.setX(O_x);
				piece.setY(O_y);
				pieces[x][y]=temp;
				return -1;
			}
		}
		if (temp != null) {
			if (temp.getColor().toLowerCase().equals("white")) {
				whtNum--;
			} else {
				blkNum--;
			}
		}
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


	@Override
	public int check_checker(Piece piece, String king)
	{
		if(piece.getX() - 2 >= 0 && piece.getY() + 1 < 8)
		{
			if(pieces[piece.getX() - 2][piece.getY() + 1] != null)
			{
				if(pieces[piece.getX() - 2][piece.getY() + 1].piece.equals(king))
				{
					return 1;
				}
			}
		}
		if(piece.getX() - 2 >= 0 && piece.getY() - 1 >= 0)
		{
			if(pieces[piece.getX() - 2][piece.getY() - 1] != null)
			{
				if(pieces[piece.getX() - 2][piece.getY() - 1].piece.equals(king))
				{
					return 1;
				}
			}
		}
		if(piece.getX() - 1 >= 0 && piece.getY() + 2 < 8)
		{
			if(pieces[piece.getX() - 1][piece.getY() + 2] != null)
			{
				if(pieces[piece.getX() - 1][piece.getY() + 2].piece.equals(king))
				{
					return 1;
				}
			}
		}
		if(piece.getX() + 1 < 8 && piece.getY() + 2 < 8)
		{
			if(pieces[piece.getX() + 1][piece.getY() + 2] != null)
			{
				if(pieces[piece.getX() + 1][piece.getY() + 2 ].piece.equals(king))
				{
					return 1;
				}
			}
		}
		if(piece.getX() + 2 < 8 && piece.getY() + 1 < 8)
		{
			if(pieces[piece.getX() + 2][piece.getY() + 1] != null)
			{
				if(pieces[piece.getX() + 2][piece.getY() + 1].piece.equals(king))
				{
					return 1;
				}
			}
		}
		if(piece.getX() + 2 < 8 && piece.getY() - 1 >= 0)
		{
			if(pieces[piece.getX() + 2][piece.getY() - 1] != null)
			{
				if(pieces[piece.getX() + 2][piece.getY() - 1].piece.equals(king))
				{
					return 1;
				}
			}
		}
		if(piece.getX() - 1 >= 0 && piece.getY() - 2 >= 0)
		{
			if(pieces[piece.getX() - 1][piece.getY() - 2] != null)
			{
				if(pieces[piece.getX() - 1][piece.getY() - 2].piece.equals(king))
				{
					return 1;
				}
			}
		}
		if(piece.getX() + 1 < 8 && piece.getY() - 2 >= 0)
		{
			if(pieces[piece.getX() + 1][piece.getY() - 2] != null)
			{
				if(pieces[piece.getX() + 1][piece.getY() - 2].piece.equals(king))
				{
					return 1;
				}
			}
		}

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
		int RValue = 0;
		String color = piece.getColor().toLowerCase();
		Piece attack[] = new Piece[8];
		int z = 0;

		if (color.equals("white")) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (Environment.pieces[j][i] != null) {
						if (Environment.pieces[j][i].getColor().equals("black")) {
							if (Environment.pieces[j][i].check_checker(Environment.pieces[j][i], "wK") == 1) {
								attack[z] = pieces[j][i];
								z++;
							}
						}
					}
				}
			}
		} else {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (Environment.pieces[j][i] != null) {
						if (Environment.pieces[j][i].getColor().equals("white")) {
							if (Environment.pieces[j][i].check_checker(Environment.pieces[j][i], "bK") == 1) {
								attack[z] = pieces[j][i];
								z++;
							}
						}
					}
				}

			}
		}

		if (z > 2) {
			return 1;
		} else if (z == 1) {
			Piece attacker = attack[0];
			int ax = attacker.getX();
			int ay = attacker.getY();
			int Dx = Math.abs(piece.getX() - ax);
			int Dy = Math.abs(piece.getY() - ay);


			if (Dx == 2 && Dy == 1 || Dx == 1 && Dy == 2) {
				return 0;
			}
			return 1;

		}

		return RValue;

	}
}