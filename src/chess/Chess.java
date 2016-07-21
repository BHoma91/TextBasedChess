
/**
  * @author Benjamin Homa
  * @author Karl Capili
  **/

package chess;

import java.util.Scanner;
import environment.*;

/**
 * Our chess program uses object-oriented programming by using super and subclasses to implement
 * the pieces. EX: A pawn, king, knight..etc are pieces and so they will have similar fields
 * as well as similar methods.
 **/

public class Chess {


	/**
	 *
	 * @param args
	 * Main Function Program starts here
	 * Game will be played here in the while loop. Here moves will be asked for
	 * Check will be tested to see if any pieces are in check and if any piece can move
	 * The game will end here as well when checkmate becomes true.
	 */
	public static void main(String args[]) {
		Environment Board = new Environment();
		Board.setBoard();
		Board.printBoard();

		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		Boolean Turn = true; // true is whites turn, false is black
		String dPlayer = "";
		String temp = "";
		int dCounter = 0;
		String lmove = "a0 a7";
		Boolean staleMate = false;
		Boolean wKCheck = false;
		Boolean bKCheck = false;

		while (true) { // loop that runs as long as the game is running
			String Player;
			staleMate = isStaleMate();
			Player = Turn == true ? "White's Move: " : " Black's Move: ";

			if (Turn && wKCheck && staleMate) {
				System.out.println("Check Mate: Black Wins");
				break;
			} else if (!Turn && bKCheck && staleMate) {
				System.out.println("Check Mate: White Wins");
				break;
			}

			if (staleMate == true) {
				System.out.println("Stale Mate, Draw!");
				break;
			}

			System.out.println(Player);
			String move = in.nextLine();

			String response = Rules.move_maker(move, Turn, lmove);
			if (response != null) {
				if (response.equals("resign")) {
					break;
				} else if (response.equals("draw")) {
					System.out.println("draw"); 
					dPlayer = Player;
					if (dPlayer.equals(temp)) {
						dCounter = 0;
					}
					temp = dPlayer;
					dCounter++;
					if (dCounter == 2) {
						break;
					}
				}
			}
			Board.printBoard();
			if (check_black() > 0) {
				for (int i1 = 0; i1 < 8; i1++) {
					for (int j1 = 0; j1 < 8; j1++) {
						if (Environment.pieces[j1][i1] instanceof King) {
							King bK = (King) Environment.pieces[j1][i1];
							bK.setCheck(true);
							bKCheck = true;
						}
					}
				}
			} else {
				bKCheck = false;
			}

			if (check_white() > 0) {
				for (int i1 = 0; i1 < 8; i1++) {
					for (int j1 = 0; j1 < 8; j1++) {
						if (Environment.pieces[j1][i1] instanceof King) {
							King wK = (King) Environment.pieces[j1][i1];
							wK.setCheck(true);
							wKCheck = true;
						}
					}
				}
			} else {
				wKCheck = false;
			}

			if (bKCheck == true) {
				System.out.println("check");
			} else if (wKCheck == true) {
				System.out.println("check");
			}

			if (Turn == true && response != null) {
				Turn = false;
				if (move.equals("draw?")) {
					// Don't change lmove
				} else if (move.equals("draw")) {
					// seriosuly do nothing
				} else {
					lmove = move;
				}
			} else if (response != null) {
				Turn = true;
				if (move.equals("draw?")) {
					// Don't change lmove
				} else if (move.equals("draw")) {
					// seriosuly do nothing
				} else {
					lmove = move;
				}
			}
		}
		System.out.println("Good Game");
	}



	/**
	 *
	 * @return true if there is no valid moves for player to make
	 * if King is in check then it will be checkmate instead of stalemate
	 * @return false: This occurs when there is a free move or a move that will
	 * take the king out of check
	 */
	private static Boolean isStaleMate() {
		int counter = 0;
		int counter1 = 0;
		String color = "gray";
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (Environment.pieces[j][i] != null) {
					Piece piece = Environment.pieces[j][i];
					color = piece.getColor();
					int response = piece.canMove(piece);

					if (response == 1 && color.equals("white")) {
						counter++;
					} else if (response == 1) {
						counter1++;
					}
					if (color.equals("white")) {
						if (counter == Environment.whtNum) {
							return true;
						}

					} else {
						if (counter1 == Environment.blkNum) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}


	/**
	 *
	 * @return count: If count is over one that means the black king is in check
	 */
	public static int check_black() {
		int count = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (Environment.pieces[j][i] != null) {
					if (Environment.pieces[j][i].getColor().equals("white")) {
						if (Environment.pieces[j][i].check_checker(Environment.pieces[j][i], "bK") == 1) {
							count++;
						}
					}
				}
			}
		}
		return count;
	}

	/**
	 *
	 * @return count: If count is over one that means the white king is in check
	 */
	public static int check_white() {
		int count = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (Environment.pieces[j][i] != null) {
					if (Environment.pieces[j][i].getColor().equals("black")) {
						if (Environment.pieces[j][i].check_checker(Environment.pieces[j][i], "wK") == 1) {
							count++;
						}
					}
				}
			}
		}
		return count;
	}

}
