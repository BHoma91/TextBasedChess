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
 *Pawn class is a subclass of the Piece class where we implement the methods and rules that
 *are specific to pawn pieces
 *
 *@param color // the color of the piece
 *@param piece // the type of piece it is
 *@param x_coordinate
 *@param y_coordinate
 *
 *
 */

public class Pawn extends Piece {
	String color;
	String piece;
	int x_coordinate;
	int y_coordinate;
	boolean passing;

	public Pawn(String color, String piece, int x_coordinate, int y_coordinate) {
		super(color, piece, x_coordinate, y_coordinate);
		passing = false;
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
		Piece temp = pieces[x][y];
		int O_x = piece.getX();
		int O_y = piece.getY();

		if (((piece.getY() == 1) || (piece.getY() == 6)) && (x - piece.getX() == 0)) {
			if (piece.getColor().toLowerCase().equals("white")) {
				if (((y - piece.getY() == 2) && pieces[x][y] != null && pieces[x][y - 1] != null)
						|| ((y - piece.getY() == 1) && pieces[x][y] != null)
						|| ((y - piece.getY() != 2) && (y - piece.getY() != 1))) {
					System.out.println();
					System.err.println("ERROR: Invalid Move");
					return -1;
				} else {
					int x_old = piece.getX();
					int y_old = piece.getY();
					pieces[x][y] = piece;
					piece.setX(x);
					piece.setY(y);
					pieces[x_old][y_old] = null;

					if (piece.getY() == 7) {
						return 2;
					}
				}
			} else {
				if (((y - piece.getY() == -2) && pieces[x][y] != null && pieces[x][y + 1] != null)
						|| ((y - piece.getY() == -1) && pieces[x][y] != null)
						|| ((y - piece.getY() != -2) && (y - piece.getY() != -1))) {
					System.out.println();
					System.err.println("ERROR: Invalid Move");
					return -1;
				} else {
					int x_old = piece.getX();
					int y_old = piece.getY();
					pieces[x][y] = piece;
					piece.setX(x);
					piece.setY(y);
					pieces[x_old][y_old] = null;

					if (piece.getY() == 0) {
						return 3;
					}
				}
			}
		} else if (((piece.getY() != 1) || (piece.getY() != 6)) && (x - piece.getX() == 0)) {
			if (piece.getColor().toLowerCase().equals("white")) {
				if (pieces[x][y] != null || (y - piece.getY() != 1)) {
					System.out.println();
					System.err.println("ERROR: Invalid Move");
					return -1;
				} else {
					int x_old = piece.getX();
					int y_old = piece.getY();
					pieces[x][y] = piece;
					piece.setX(x);
					piece.setY(y);
					pieces[x_old][y_old] = null;
				}
			} else {
				if (pieces[x][y] != null || (y - piece.getY() != -1)) {
					System.out.println(piece.getY());
					System.out.println();
					System.err.println("ERROR: Invalid Move");
					return -1;
				} else {
					int x_old = piece.getX();
					int y_old = piece.getY();
					pieces[x][y] = piece;
					piece.setX(x);
					piece.setY(y);
					pieces[x_old][y_old] = null;
				}
			}
		} else if (x - piece.getX() == 1 || (x - piece.getX() == -1)) {
			if (piece.getColor().toLowerCase().equals("white")) {
				if (y - piece.getY() == 1) {
					if (pieces[x][y] != null) {
						if (pieces[x][y].color.toLowerCase().equals("white")) {
							System.out.println();
							System.err.println("ERROR: Invalid Move");
							return -1;
						} else {
							int x_old = piece.getX();
							int y_old = piece.getY();
							pieces[x][y] = piece;
							piece.setX(x);
							piece.setY(y);
							pieces[x_old][y_old] = null;
						}
					} else {
						System.out.println();
						System.err.println("ERROR: Invalid Move");
						return -1;
					}
				} else {
					System.out.println();
					System.out.println(y - piece.getY());
					System.err.println("ERROR: Invalid Move");
					return -1;
				}
			} else {
				if (y - piece.getY() == -1) {
					if (pieces[x][y] != null) {
						if (pieces[x][y].color.toLowerCase().equals("black")) {
							System.out.println();
							System.err.println("ERROR: Invalid Move");
							return -1;
						} else {
							int x_old = piece.getX();
							int y_old = piece.getY();
							pieces[x][y] = piece;
							piece.setX(x);
							piece.setY(y);
							pieces[x_old][y_old] = null;
						}
					} else {
						System.out.println();
						System.err.println("ERROR: Invalid Move");
						return -1;
					}
				} else {
					System.out.println();
					System.err.println("ERROR: Invalid Move");
					return -1;
				}
			}
		} else {
			System.out.println();
			System.err.println("Error: Invalid Move");
			return -1;
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
		if (piece.getColor().toLowerCase().equals("white")) {
			if (piece.getX() + 1 < 8 && piece.getY() + 1 < 8) {
				if (pieces[piece.getX() + 1][piece.getY() + 1] != null) {
					if (pieces[piece.getX() + 1][piece.getY() + 1].piece.equals(king)) {
						return 1;
					}
				}
			}
			if (piece.getX() - 1 >= 0 && piece.getY() + 1 < 8) {
				if (pieces[piece.getX() - 1][piece.getY() + 1] != null) {
					if (pieces[piece.getX() - 1][piece.getY() + 1].piece.equals(king)) {
						return 1;
					}
				}
			}
		} else {
			if (piece.getX() + 1 < 8 && piece.getY() - 1 >= 0) {
				if (pieces[piece.getX() + 1][piece.getY() - 1] != null) {
					if (pieces[piece.getX() + 1][piece.getY() - 1].piece.equals(king)) {
						return 1;
					}
				}
				if (piece.getX() - 1 >= 0 && piece.getY() - 1 >= 0) {
					if (pieces[piece.getX() - 1][piece.getY() - 1] != null) {
						if (pieces[piece.getX() - 1][piece.getY() - 1].piece.equals(king)) {
							return 1;
						}
					}
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
		int Rvalue = 0;
		int x = piece.getX();
		int y = piece.getY();
		String color = piece.getColor();
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

			int dx = Math.abs(ax - px);
			int dy = Math.abs(ay - py);

			if (dx == 1 && dy == 1) {
				return 0;
			}
			return 1;

		}

		if (color.equals("white")) {
			if (pieces[x][y + 1] == null) {
				return 0;
			}
			if (x + 1 < 8) {
				if (pieces[x + 1][y + 1] == null) {
					Rvalue = 1;
				}
			}
			if (x - 1 >= 0) {
				if (pieces[x - 1][y + 1] == null) {
					Rvalue = 1;
				}
			}
			if (x + 1 < 8 && x - 1 >= 0) {
				if (pieces[x + 1][y + 1] != null || pieces[x - 1][y + 1] != null) {
					if (pieces[x + 1][y + 1] != null && pieces[x + 1][y + 1].getColor().equals("black")) {
						return 0;
					} else if (pieces[x - 1][y + 1] != null && pieces[x - 1][y + 1].getColor().equals("black")) {
						return 0;
					}
				}
			} else if (x == 0) {
				if (pieces[x + 1][y + 1] != null) {
					if (pieces[x + 1][y + 1] != null && pieces[x + 1][y + 1].getColor().equals("black")) {
						return 0;
					}
				}
			} else if (x == 7) {
				if (pieces[x - 1][y + 1] != null) {
					if (pieces[x - 1][y + 1] != null && pieces[x - 1][y + 1].getColor().equals("black")) {
						return 0;
					}
				}
			}
		} else {
			if (pieces[x][y - 1] == null) {
				return 0;
			}
			if (x + 1 < 8) {
				if (pieces[x + 1][y - 1] == null) {
					Rvalue = 1;
				}
			}
			if (x - 1 >= 0) {
				if (pieces[x - 1][y - 1] == null) {
					Rvalue = 1;
				}
			}

			if (x + 1 < 8 && x - 1 >= 0) {
				if (pieces[x + 1][y - 1] != null || pieces[x - 1][y - 1] != null) {
					if (pieces[x + 1][y - 1] != null && pieces[x + 1][y - 1].getColor().equals("black")) {
						return 0;
					} else if (pieces[x - 1][y - 1] != null && pieces[x - 1][y - 1].getColor().equals("black")) {
						return 0;
					}
				}
			} else if (x == 7) {
				if (pieces[x - 1][y - 1] != null) {
					if (pieces[x - 1][y - 1] != null && pieces[x - 1][y - 1].getColor().equals("black")) {
						return 0;
					}
				}
			} else if (x == 0) {
				if (pieces[x + 1][y - 1] != null) {
					if (pieces[x + 1][y - 1] != null && pieces[x + 1][y - 1].getColor().equals("black")) {
						return 0;
					}
				}
			}
		}

		return Rvalue;
	}
}
