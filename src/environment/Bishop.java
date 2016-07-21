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
 *Bishop class is a subclass of the Piece class where we implement the methods and rules that
 *are specific to bishop pieces
 *
 *@param color // the color of the piece
 *@param piece // the type of piece it is
 *@param x_coordinate
 *@param y_coordinate
 *
 *
 */

public class Bishop extends Piece {
	String color;
	String piece;
	int x_coordinate;
	int y_coordinate;

	public Bishop(String color, String piece, int x_coordinate, int y_coordinate) {
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
		boolean move = Math.abs(x - piece.getX()) == Math.abs(y - piece.getY());
		Piece temp = pieces[x][y];
		int O_x = piece.getX();
		int O_y = piece.getY();

		if (!move) {
			System.out.println();
			System.err.println("ERROR: Invalid Move");
			return -1;
		} else { // Valid Move Lets Check for anything in the way
			String color = piece.getColor().toLowerCase();
			boolean notvalid = false;
			int Dx = x - piece.getX(); // Need to see if Delta X is pos or neg
										// for movement
			int Dy = y - piece.getY(); // Need to see if Delta Y is pos or neg
										// for movement

			if (pieces[x][y] != null) {
				String color2 = pieces[x][y].getColor().toLowerCase();
				notvalid = color.equals(color2); // if same color not valid
			}

			if (!notvalid) {

				if (Dx < 0 && Dy < 0) { // Move Diagnol down left
					for (int i = 1; i < Math.abs(Dx); i++) {
						if (pieces[piece.getX() - i][piece.getY() - i] != null) {
							System.out.println();
							System.err.println("ERROR: Invalid Move");
							return -1;
						}
					}
				} else if (Dx < 0 && Dy > 0) { // Move Diagnol up left
					for (int i = 1; i < Math.abs(Dx); i++) {
						if (pieces[piece.getX() - i][piece.getY() + i] != null) {
							System.out.println();
							System.err.println("ERROR: Invalid Move");
							return -1;
						}
					}
				} else if (Dx > 0 && Dy < 0) { // Move Diagnol down right
					for (int i = 1; i < Math.abs(Dx); i++) {
						if (pieces[piece.getX() + i][piece.getY() - i] != null) {
							System.out.println();
							System.err.println("ERROR: Invalid Move");
							return -1;
						}
					}
				} else if (Dx > 0 && Dy > 0) { // Move Diagnol up right
					for (int i = 1; i < Math.abs(Dx); i++) {
						if (pieces[piece.getX() + i][piece.getY() + i] != null) {
							System.out.println();
							System.err.println("ERROR: Invalid Move");
							return -1;
						}
					}
				} else {
					System.out.println("Mistake");
				}
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
				piece.setX(O_x);
				piece.setY(O_y);
				pieces[O_x][O_y] = piece;
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
				int y = 1;
				while (piece.getX() - x >= 0 && piece.getY() + y < 8) {
					if (pieces[piece.getX() - x][piece.getY() + y] != null
							&& !pieces[piece.getX() - x][piece.getY() + y].piece.equals(king)) {
						break;
					} else if (pieces[piece.getX() - x][piece.getY() + y] != null
							&& pieces[piece.getX() - x][piece.getY() + y].piece.equals(king)) {
						return 1;
					}
					x++;
					y++;
				}
			case 1:
				int x1 = 1;
				int y1 = 1;
				while (piece.getX() + x1 < 8 && piece.getY() + y1 < 8) {
					if (pieces[piece.getX() + x1][piece.getY() + y1] != null
							&& !pieces[piece.getX() + x1][piece.getY() + y1].piece.equals(king)) {
						break;
					} else if (pieces[piece.getX() + x1][piece.getY() + y1] != null
							&& pieces[piece.getX() + x1][piece.getY() + y1].piece.equals(king)) {
						return 1;
					}
					x1++;
					y1++;
				}
			case 2:
				int x2 = 1;
				int y2 = 1;
				while (piece.getX() - x2 >= 0 && piece.getY() - y2 >= 0) {
					if (pieces[piece.getX() - x2][piece.getY() - y2] != null
							&& !pieces[piece.getX() - x2][piece.getY() - y2].piece.equals(king)) {
						break;
					} else if (pieces[piece.getX() - x2][piece.getY() - y2] != null
							&& pieces[piece.getX() - x2][piece.getY() - y2].piece.equals(king)) {
						return 1;
					}
					x2++;
					y2++;
				}
			case 3:
				int x3 = 1;
				int y3 = 1;
				while (piece.getX() + x3 < 8 && piece.getY() - y3 >= 0) {
					if (pieces[piece.getX() + x3][piece.getY() - y3] != null
							&& !pieces[piece.getX() + x3][piece.getY() - y3].piece.equals(king)) {
						break;
					}
					if (pieces[piece.getX() + x3][piece.getY() - y3] != null
							&& pieces[piece.getX() + x3][piece.getY() - y3].piece.equals(king)) {
						return 1;
					}
					x3++;
					y3++;
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
		String color = piece.getColor();
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

			if (Math.abs(ax - px) == Math.abs(ay - py)) {
				int Dx = ay - py;
				int Dy = ax - px;

				if (Dx < 0 && Dy < 0) { // Move Diagnol down left
					for (int i = 1; i < Math.abs(Dx); i++) {
						if (pieces[piece.getX() - i][piece.getY() - i] != null) {
							return 1;
						}
					}
				} else if (Dx < 0 && Dy > 0) { // Move Diagnol up left
					for (int i = 1; i < Math.abs(Dx); i++) {
						if (pieces[piece.getX() - i][piece.getY() + i] != null) {
							return 1;
						}
					}
				} else if (Dx > 0 && Dy < 0) { // Move Diagnol down right
					for (int i = 1; i < Math.abs(Dx); i++) {
						if (pieces[piece.getX() + i][piece.getY() - i] != null) {
							return 1;
						}
					}
				} else if (Dx > 0 && Dy > 0) { // Move Diagnol up right
					for (int i = 1; i < Math.abs(Dx); i++) {
						if (pieces[piece.getX() + i][piece.getY() + i] != null) {
							return 1;
						}
					}
				}

				return 0;
			}
			return 1;
		}

		if (x > 0 && y > 0 && x < 7 && y < 7) { // We can check all 4 directions
												// saftely
			if (pieces[x + 1][y + 1] == null || pieces[x - 1][y - 1] == null || pieces[x + 1][y + 1] == null
					|| pieces[x - 1][y - 1] == null) {
				return 0;
			} else {
				if (pieces[x + 1][y + 1] != null) {
					String color1 = pieces[x + 1][y + 1].getColor();

					if (color.equals(color1)) {
						RValue = 1;
					} else {
						RValue = 0;
					}
				}

				if (pieces[x + 1][y - 1] != null) {
					String color1 = pieces[x + 1][y - 1].getColor();

					if (color.equals(color1)) {
						RValue = 1;
					} else {
						RValue = 0;
					}
				}
				if (pieces[x - 1][y + 1] != null) {
					String color1 = pieces[x - 1][y + 1].getColor();

					if (color.equals(color1)) {
						RValue = 1;
					} else {
						RValue = 0;
					}

				} else if (pieces[x - 1][y - 1] != null) {
					String color1 = pieces[x - 1][y - 1].getColor();

					if (color.equals(color1)) {
						RValue = 1;
					} else {
						RValue = 0;
					}
				}
			}
		}

		// Can only move diagonal up right
		else if (x == 0 && y == 0) {
			if (pieces[x + 1][y + 1] == null) {
				return 0;
			}
			if (pieces[x + 1][y + 1] != null) {
				String color1 = pieces[x + 1][y + 1].getColor();

				if (color.equals(color1)) {
					RValue = 1;
				} else {
					RValue = 0;
				}
			}
		}
		// Can only move diagonal left up
		else if (x == 7 && y == 0) {
			if (pieces[x - 1][y + 1] == null) {
				return 0;
			}
			if (pieces[x - 1][y + 1] != null) {
				String color1 = pieces[x - 1][y + 1].getColor();

				if (color.equals(color1)) {
					RValue = 1;
				} else {
					RValue = 0;
				}
			}
		}
		// Can only go down and left
		else if (x == 7 && y == 7) {
			if (pieces[x - 1][y - 1] == null) {
				return 0;
			}
			if (pieces[x - 1][y - 1] != null) {
				String color1 = pieces[x - 1][y - 1].getColor();

				if (color.equals(color1)) {
					RValue = 1;
				} else {
					RValue = 0;
				}
			}
		}
		// Can only move diagonal down and right
		else if (x == 0 && y == 7) {
			if (pieces[x + 1][y - 1] == null) {
				return 0;
			}
			if (pieces[x + 1][y - 1] != null) {
				String color1 = pieces[x + 1][y - 1].getColor();

				if (color.equals(color1)) {
					RValue = 1;
				} else {
					RValue = 0;
				}
			}
		}
		// Can only go diagonal right up and right down
		else if (x == 0 && y > 0 && y < 7) {
			if (pieces[x + 1][y + 1] == null || pieces[x + 1][y - 1] == null) {
				return 0;
			}
			if (pieces[x + 1][y + 1] != null) {
				String color1 = pieces[x + 1][y + 1].getColor();

				if (color.equals(color1)) {
					RValue = 1;
				} else {
					RValue = 0;
				}
			} else if (pieces[x + 1][y - 1] != null) {
				String color1 = pieces[x + 1][y - 1].getColor();

				if (color.equals(color1)) {
					RValue = 1;
				} else {
					RValue = 0;
				}
			}
		}
		// Can only go left up and left down
		else if (x == 7 && y > 0 && y < 0) {
			if (pieces[x - 1][y + 1] == null || pieces[x - 1][y - 1] == null) {
				return 0;
			}
			if (pieces[x - 1][y + 1] != null) {
				String color1 = pieces[x - 1][y + 1].getColor();

				if (color.equals(color1)) {
					RValue = 1;
				} else {
					RValue = 0;
				}
			} else if (pieces[x - 1][y - 1] != null) {
				String color1 = pieces[x - 1][y - 1].getColor();

				if (color.equals(color1)) {
					RValue = 1;
				} else {
					RValue = 0;
				}
			}
		}
		// Can only go up left and up right
		else if (x > 0 && x < 7 && y == 0) {
			if (pieces[x + 1][y + 1] == null || pieces[x - 1][y + 1] == null) {
				return 0;
			}
			if (pieces[x + 1][y + 1] != null) {
				String color1 = pieces[x + 1][y + 1].getColor();

				if (color.equals(color1)) {
					RValue = 1;
				} else {
					RValue = 0;
				}
			} else if (pieces[x - 1][y + 1] != null) {
				String color1 = pieces[x + 1][y + 1].getColor();

				if (color.equals(color1)) {
					RValue = 1;
				} else {
					RValue = 0;
				}
			}
		}
		// can only go down right and down left
		else if (x > 0 && x < 7 && y == 7) {
			if (pieces[x + 1][y - 1] == null || pieces[x - 1][y - 1] == null) {
				return 0;
			}
			if (pieces[x + 1][y - 1] != null) {
				String color1 = pieces[x + 1][y - 1].getColor();

				if (color.equals(color1)) {
					RValue = 1;
				} else {
					RValue = 0;
				}
			} else if (pieces[x - 1][y - 1] != null) {
				String color1 = pieces[x + 1][y - 1].getColor();

				if (color.equals(color1)) {
					RValue = 1;
				} else {
					RValue = 0;
				}
			}
		}
		return RValue;
	}
}
