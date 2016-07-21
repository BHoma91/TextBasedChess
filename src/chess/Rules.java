
/**
 *
 * @author Benjamin Homa
 * @author Karl Capili
 */



package chess;

import static environment.Environment.pieces;
import environment.*;

/**
 * Rules class is used to ensure that moves are valid to the rules of chess
 **/
public class Rules {
	String last;


	/**
	 *
	 * @param input this will be the first charachter of the first and second command
	 * example is e2 d4 is called then this will get the number value for e and d. These
	 * numbers will represent the X values of where the piece is now and where we want it to go
	 * @return the number at the end of the function so we can use it to move a piece
	 */
	public static int get_X(String input) {
		int x;
		switch (input.charAt(0)) {
		case 'a':
			x = 0;
			break;
		case 'b':
			x = 1;
			break;
		case 'c':
			x = 2;
			break;
		case 'd':
			x = 3;
			break;
		case 'e':
			x = 4;
			break;
		case 'f':
			x = 5;
			break;
		case 'g':
			x = 6;
			break;
		case 'h':
			x = 7;
			break;
		default:
			x = -100;
		}
		return x;
	}



	/**
	 *
	 * @param input this will be the first charachter of the first and second command
	 * example is e2 d4 is called then this will get the number value for 2 and 4. These
	 * numbers will represent the Y values of where the piece is now and where we want it to go
	 * @return the number at end of function so we can use it as the Y value and move the piece
	 */
	public static int get_Y(String input) {
		int y;

		switch (input.charAt(1)) {
		case '1':
			y = 0;
			break;
		case '2':
			y = 1;
			break;
		case '3':
			y = 2;
			break;
		case '4':
			y = 3;
			break;
		case '5':
			y = 4;
			break;
		case '6':
			y = 5;
			break;
		case '7':
			y = 6;
			break;
		case '8':
			y = 7;
			break;
		default:
			y = -100;
		}
		return y;
	}


	/**
	 *
	 * @param input: This string holds the command for the current move
	 * @param turn: This boolean value is true if white player's turn and false if black players turn
	 * @param lt: This string will hold the value of the last turn. If e2 e4 was played then e7 e5: Lt will
	 * equal e2 e4
	 * @return
	 */

	public static String move_maker(String input, Boolean turn, String lt) {
		String[] in = input.split(" ");
		String[] lin = lt.split(" ");
		int x = 0, bx = 0;
		int y = 0, by = 0;
		int lx = 0, llx = 0; // X's from last turn
		int ly = 0, lly = 0;
		; // Y's from last turn
		int check;

		Piece piece = new Piece();
		Piece pawnpiece = new Piece();
		if (in.length < 2) {
			if (in[0].toLowerCase().equals("draw?") || (in[0].toLowerCase().equals("draw"))) {
				return "draw";
			} else if (in[0].toLowerCase().equals("resign")) {
				return "resign";
			} else {
				System.out.println("Need More Than 1 Argument");
				return null;
			}
		}

		if (in[0].length() != 2 || in[1].length() != 2)
		{
			System.out.println("ERROR: Invalid Input");
			return null;
		}

		for (int i = 0; i < in.length; i++) {
			if (i == 0) {
				bx = get_X(in[i]);
				by = get_Y(in[i]);
				llx = get_X(lin[i]);
				lly = get_Y(lin[i]);
				if (bx >= 0 && bx <= 7 && by >= 0 && by <= 7) {
					piece = pieces[bx][by];
					if (piece != null) {
						if (piece.getColor().toLowerCase().equals("black") && turn == true) {
							System.out.println("Can't Move Black Piece");
							return null;
						} else if (piece.getColor().toLowerCase().equals("white") && turn == false) {
							System.out.println("Can't Move White Piece");
							return null;
						}
					}
				} else
				{
					System.out.println("Illegal move, try again");
					return null;
				}
			} else if (i == 1) {
				x = get_X(in[i]);
				y = get_Y(in[i]);
				lx = get_X(lin[i]);
				ly = get_Y(lin[i]);
				if (piece == null) {
					System.out.println("No Piece In Select Spot");
					return null;
				}
				if (Math.abs(lx - llx) == 0 && Math.abs(ly - lly) == 2) { // Something
																			// last
																			// turn
																			// went
																			// 2
																			// spaces
																			// (Rook/Pawn/Queen)
					pawnpiece = pieces[lx][ly];
					if (pawnpiece != null) { // Shouldn't be an issue since were
												// checking something from LAST
												// TURN!
						if (pawnpiece.getPiece().equals("bp")) { // We got a
																	// black
																	// pawn
							if (piece.getPiece().equals("wp")) { // This turn we
																	// have a
																	// white
																	// pawn
								if (Math.abs(x - lx) == 0 && (y - ly) == 1) { // EN
																				// PASSANT!
									pieces[piece.getX()][piece.getY()] = null;
									pieces[x][y] = piece;
									pieces[lx][ly] = null;
									piece.setX(x);
									piece.setY(y);
									System.out.println("EN PASSANT YOU PEASENT");
									return "done";
								}
							}
						} else if (pawnpiece.getPiece().equals("wp")) { // We
																		// got a
																		// white
																		// pawn
							if (piece.getPiece().equals("bp")) { // This turn a
																	// black
																	// pawn is
																	// moving
								if (Math.abs(x - lx) == 0 && (y - ly) == -1) { // EN
																				// PASSANT!
									pieces[piece.getX()][piece.getY()] = null;
									pieces[x][y] = piece;
									pieces[lx][ly] = null;
									piece.setX(x);
									piece.setY(y);
									System.out.println("EN PASSANT YOU PEASENT");
									return "done";
								}
							}
						}
					}
				}
				if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
					check = piece.move(piece, x, y); // we move

					switch (check) {
					case (0):
						break;
					case (-1):
						return null;
					case (2): // case is used to determine if white player promotes white pawn
						if (in.length == 3) {
							if (in[2].toLowerCase().equals("n")) {
								Knight wN = new Knight("white", "wN", x, y);
								pieces[x][y] = wN;
								break;
							} else if (in[2].toLowerCase().equals("r")) {
								Rook wR = new Rook("white", "wR", x, y, true);
								pieces[x][y] = wR;
								break;
							} else if (in[2].toLowerCase().equals("b")) {
								Bishop wB = new Bishop("white", "wB", x, y);
								pieces[x][y] = wB;
								break;
							}
						} else {
							Queen wQ = new Queen("white", "wQ", x, y);
							pieces[x][y] = wQ;
							break;
						}
					case (3): //case is used to determine if black player promotes black pawn
						if (in.length == 3) {
							if (in[2].toLowerCase().equals("n")) {
								Knight bN = new Knight("black", "bN", x, y);
								pieces[x][y] = bN;
								break;
							} else if (in[2].toLowerCase().equals("r")) {
								Rook bR = new Rook("black", "bR", x, y, true);
								pieces[x][y] = bR;
								break;
							} else if (in[2].toLowerCase().equals("b")) {
								Bishop bB = new Bishop("black", "bB", x, y);
								pieces[x][y] = bB;
								break;
							}
						} else {
							Queen bQ = new Queen("black", "bQ", x, y);
							pieces[x][y] = bQ;
							break;
						}
					}
				} else {
					System.out.println("Selection Out of Bonds");
					return null;
				}
			}
		}
		return "done";
	}
}
