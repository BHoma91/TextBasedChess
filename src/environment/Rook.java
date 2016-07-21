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
 *Rook class is a subclass of the Piece class where we implement the methods and rules that
 *are specific to rook pieces
 *
 *@param color // the color of the piece
 *@param piece // the type of piece it is
 *@param x_coordinate
 *@param y_coordinate
 *@moved // tells us if the rook has been for castling purposes
 *
 */

public class Rook extends Piece {
	String color;
	String piece;
	int x_coordinate;
	int y_coordinate;
	boolean moved;

	public Rook(String color, String piece, int x_coordinate, int y_coordinate, boolean moved) {
		super(color, piece, x_coordinate, y_coordinate);
		this.moved = moved;
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
		boolean move = false;
		Piece temp = null;
		int O_x = piece.getX();
		int O_y = piece.getY();

		if (piece.getX() - x != 0 && piece.getY() - y == 0 || piece.getX() - x == 0 && piece.getY() - y != 0) {
			move = true;
		}

		if (!move) {
			System.out.println();
			System.err.println("ERROR: Invalid Move");
			return -1;
		}

		else { // Check if okay
			String color = piece.getColor().toLowerCase();
			boolean notvalid = false;
			int Dx = x - piece.getX(); // Need to see if Delta X is pos or neg
										// movement
			int Dy = y - piece.getY(); // Need to see if Delta Y is pos or neg
										// for movement

			if (pieces[x][y] != null) {
				String color2 = pieces[x][y].getColor().toLowerCase();
				notvalid = color.equals(color2); // if same color not valid
			}

			if (!notvalid) { // Lets try to move

				if (Dx < 0 && Dy == 0) { // Move Left
					for (int i = 1; i < Math.abs(Dx); i++) {
						if (pieces[piece.getX() - i][piece.getY()] != null) {
							System.out.println();
							System.err.println("ERROR: Invalid Move");
							return -1;
						}
					}
				} else if (Dx == 0 && Dy > 0) { // Move Up
					for (int i = 1; i < Math.abs(Dy); i++) {
						if (pieces[piece.getX()][piece.getY() + i] != null) {
							System.out.println();
							System.err.println("ERROR: Invalid Move");
							return -1;
						}
					}
				} else if (Dx > 0 && Dy == 0) { // Move Right
					for (int i = 1; i < Dx; i++) {
						if (pieces[piece.getX() + i][piece.getY()] != null) {
							System.out.println();
							System.err.println("ERROR: Invalid Move");
							return -1;
						}
					}
				} else if (Dx == 0 && Dy < 0) { // Move Down
					for (int i = 1; i < Math.abs(Dy); i++) {
						if (pieces[piece.getX()][piece.getY() - i] != null) {
							System.out.println();
							System.err.println("ERROR: Invalid Move");
							return -1;
						}
					}
				} else {
					System.out.println("My bad made mistake");
				}
				temp = pieces[x][y];
				pieces[piece.getX()][piece.getY()] = null;
				pieces[x][y] = piece;
				piece.setX(x);
				piece.setY(y);
				moved = true;
			}

			else {
				System.out.println();
				System.err.println("ERROR: Invalid Move");
				return -1;
			}
		}
		String color = piece.getColor().toLowerCase();

		if (color.equals("white")) {
			if (Chess.check_white() > 0) {
				System.out.println("Invalid Move: Your king is in check");
				pieces[O_x][O_y] = piece;
				piece.setX(O_x);
				piece.setY(O_y);
				pieces[x][y] = temp;
				return -1;
			}
		} else { // Black
			if (Chess.check_black() > 0) {
				System.out.println("Invalid Move: Your king is in check");
				pieces[O_x][O_y] = piece;
				piece.setX(O_x);
				piece.setY(O_y);
				pieces[x][y] = temp;
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
	 * @param moved boolean for if rook moved yet or not
	 */
	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	/**
	 *
	 * @return the value of moved
	 */
	public boolean getMoved() {
		return moved;
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
	public int check_checker(Piece piece, String king) {
		for (int i = 0; i < 4; i++) {
			switch (i) {
			case 0:
				int x = 1;
				while (piece.getX() + x < 8) {
					if (pieces[piece.getX() + x][piece.getY()] != null
							&& !pieces[piece.getX() + x][piece.getY()].piece.equals(king)) {
						break;
					} else if (pieces[piece.getX() + x][piece.getY()] != null
							&& pieces[piece.getX() + x][piece.getY()].piece.equals(king)) {
						return 1;
					}
					x++;
				}
			case 1:
				int y = 1;
				while (piece.getY() + y < 8) {
					if (pieces[piece.getX()][piece.getY() + y] != null
							&& !pieces[piece.getX()][piece.getY() + y].piece.equals(king)) {
						break;
					} else if (pieces[piece.getX()][piece.getY() + y] != null
							&& pieces[piece.getX()][piece.getY() + y].piece.equals(king)) {
						return 1;
					}
					y++;
				}
			case 2:
				int x1 = 1;
				while (piece.getX() - x1 >= 0) {
					if (pieces[piece.getX() - x1][piece.getY()] != null
							&& !pieces[piece.getX() - x1][piece.getY()].piece.equals(king)) {
						break;
					} else if (pieces[piece.getX() - x1][piece.getY()] != null
							&& pieces[piece.getX() - x1][piece.getY()].piece.equals(king)) {
						return 1;
					}
					x1++;
				}
			case 3:
				int y1 = 1;
				while (piece.getY() - y1 >= 0) {
					if (pieces[piece.getX()][piece.getY() - y1] != null
							&& !pieces[piece.getX()][piece.getY() - y1].piece.equals(king)) {
						break;
					}
					if (pieces[piece.getX()][piece.getY() - y1] != null
							&& pieces[piece.getX()][piece.getY() - y1].piece.equals(king)) {
						return 1;
					}
					y1++;
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
		int x = piece.getX();
		int y = piece.getY();
		String color = piece.getColor().toLowerCase();
		Piece temp = null;
		int RValue = 0;
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
			int px = piece.getX();
			int py = piece.getY();
			int Dx = ax - px;
			int Dy = ay - py;

			if (ax - px != 0 && py - ay == 0) { // He can take him vertically
				 if (Dx == 0 && Dy > 0) { // Move Up
					for (int i = 1; i < Math.abs(Dy); i++) {
						if (pieces[piece.getX()][piece.getY() + i] != null) {
							return 1;
						}
					}
				} else if (Dx == 0 && Dy < 0) { // Move Down
					for (int i = 1; i < Math.abs(Dy); i++) {
						if (pieces[piece.getX()][piece.getY() - i] != null) {
							return 1;
						}
					}
				}

				return 0;
			} else if (ax - px == 0 && py - ay != 0) { // Can take him left or right
				if (Dx < 0 && Dy == 0) { // Move Left
					for (int i = 1; i < Math.abs(Dx); i++) {
						if (pieces[piece.getX() - i][piece.getY()] != null) {
							return 1;
						}
					}
				}
				 else if (Dx > 0 && Dy == 0) { // Move Right
						for (int i = 1; i < Dx; i++) {
							if (pieces[piece.getX() + i][piece.getY()] != null) {
								return 1;
							}
						}
					}

				return 0;
			}
			return 1;
		}

		if (x - 1 >= 0) {
			int a = 0;
			if (pieces[x - 1][y] != null) {
				if (pieces[x - 1][y].getColor().equals(color)) {
					RValue = 1;
					a++;
				}
			}
			temp = pieces[x - 1][y];
			pieces[x - 1][y] = piece;
			if (color.equals("white")) {
				if (Chess.check_white() > 0) {
					pieces[x][y] = piece;
					pieces[x - 1][y] = temp;
					RValue = 1;
					a++;
				}
			} else { // Black
				if (Chess.check_black() > 0) {
					pieces[x][y] = piece;
					pieces[x - 1][y] = temp;
					RValue = 1;
					a++;
				}
			}
			pieces[x][y] = piece;
			pieces[x - 1][y] = temp;
			if (a == 0) {
				return 0;
			}
		}
		if (x + 1 <= 7) {
			int a = 0;
			// test if spot is open
			if (pieces[x + 1][y] != null) {
				if (pieces[x + 1][y].getColor().equals(color)) {
					RValue = 1;
					a++;
				}
			}
			temp = pieces[x + 1][y];
			pieces[x + 1][y] = piece;
			if (color.equals("white")) {
				if (Chess.check_white() > 0) {
					pieces[x][y] = piece;
					pieces[x + 1][y] = temp;
					RValue = 1;
					a++;
				}
			} else { // Black
				if (Chess.check_black() > 0) {
					pieces[x][y] = piece;
					pieces[x + 1][y] = temp;
					RValue = 1;
					a++;
				}
			}
			pieces[x][y] = piece;
			pieces[x + 1][y] = temp;
			if (a == 0) {
				return 0;
			}
		}
		// Lets test the bottom down now
		if (y - 1 >= 0) {
			int a = 0;
			if (pieces[x][y - 1] != null) {
				if (pieces[x][y - 1].getColor().equals(color)) {
					RValue = 1;
					a++;
				}
			}
			temp = pieces[x][y - 1];
			pieces[x][y - 1] = piece;
			if (color.equals("white")) {
				if (Chess.check_white() > 0) {
					pieces[x][y] = piece;
					pieces[x][y - 1] = temp;
					RValue = 1;
					a++;
				}
			} else { // Black
				if (Chess.check_black() > 0) {
					pieces[x][y] = piece;
					pieces[x][y - 1] = temp;
					RValue = 1;
					a++;
				}
			}
			pieces[x][y] = piece;
			pieces[x][y - 1] = temp;
			if (a == 0) {
				return 0;
			}
		}
		// Test one above the Rook
		if (y + 1 <= 7) {
			int a = 0;
			if (pieces[x][y + 1] != null) {
				if (pieces[x][y + 1].getColor().equals(color)) {
					RValue = 1;
					a++;
				}
			}
			temp = pieces[x][y + 1];
			pieces[x][y + 1] = piece;
			if (color.equals("white")) {
				if (Chess.check_white() > 0) {
					pieces[x][y] = piece;
					pieces[x][y + 1] = temp;
					RValue = 1;
					a++;
				}
			} else { // Black
				if (Chess.check_black() > 0) {
					pieces[x][y] = piece;
					pieces[x][y + 1] = temp;
					RValue = 1;
					a++;
				}
			}
			pieces[x][y] = piece;
			pieces[x][y + 1] = temp;
			if (a == 0) {
				return 0;
			}
		}

		pieces[x][y] = piece;
		return RValue;
	}
}