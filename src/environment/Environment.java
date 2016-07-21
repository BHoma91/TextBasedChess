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
 * Environment class creates the board where the pieces are placed as well as sets up the
 * board so that it is ready for the game
 *
 *
 *
 */
public class Environment {
	public static Piece[][] pieces = new Piece[8][8];
	public static int whtNum = 16;
	public static int blkNum = 16;

	public void setBoard() {

		Pawn wP1 = new Pawn("white", "wp", 0, 1);
		pieces[0][1] = wP1;
		Pawn wP2 = new Pawn("white", "wp", 1, 1);
		pieces[1][1] = wP2;
		Pawn wP3 = new Pawn("white", "wp", 2, 1);
		pieces[2][1] = wP3;
		Pawn wP4 = new Pawn("white", "wp", 3, 1);
		pieces[3][1] = wP4;
		Pawn wP5 = new Pawn("white", "wp", 4, 1);
		pieces[4][1] = wP5;
		Pawn wP6 = new Pawn("white", "wp", 5, 1);
		pieces[5][1] = wP6;
		Pawn wP7 = new Pawn("white", "wp", 6, 1);
		pieces[6][1] = wP7;
		Pawn wP8 = new Pawn("white", "wp", 7, 1);
		pieces[7][1] = wP8;

		Rook wR1 = new Rook("white", "wR", 0, 0, false);
		pieces[0][0] = wR1;
		Rook wR2 = new Rook("white", "wR", 7, 0, false);
		pieces[7][0] = wR2;

		Knight wN1 = new Knight("white", "wN", 1, 0);
		pieces[1][0] = wN1;
		Knight wN2 = new Knight("white", "wN", 6, 0);
		pieces[6][0] = wN2;

		Bishop wB1 = new Bishop("white", "wB", 2, 0);
		pieces[2][0] = wB1;
		Bishop wB2 = new Bishop("white", "wB", 5, 0);
		pieces[5][0] = wB2;

		King wK = new King("white", "wK", 4, 0, false, false);
		pieces[4][0] = wK;
		Queen wQ = new Queen("white", "wQ", 3, 0);
		pieces[3][0] = wQ;

		Pawn bP1 = new Pawn("black", "bp", 0, 6);
		pieces[0][6] = bP1;
		Pawn bP2 = new Pawn("black", "bp", 1, 6);
		pieces[1][6] = bP2;
		Pawn bP3 = new Pawn("black", "bp", 2, 6);
		pieces[2][6] = bP3;
		Pawn bP4 = new Pawn("black", "bp", 3, 6);
		pieces[3][6] = bP4;
		Pawn bP5 = new Pawn("black", "bp", 4, 6);
		pieces[4][6] = bP5;
		Pawn bP6 = new Pawn("black", "bp", 5, 6);
		pieces[5][6] = bP6;
		Pawn bP7 = new Pawn("black", "bp", 6, 6);
		pieces[6][6] = bP7;
		Pawn bP8 = new Pawn("black", "bp", 7, 6);
    	pieces[7][6] = bP8;

		Rook bR1 = new Rook("black", "bR", 0, 7, false);
		pieces[0][7] = bR1;
		Rook bR2 = new Rook("black", "bR", 7, 7, false);
		pieces[7][7] = bR2;

		Knight bN1 = new Knight("black", "bN", 1, 7);
		pieces[1][7] = bN1;
		Knight bN2 = new Knight("black", "bN", 6, 7);
		pieces[6][7] = bN2;

		Bishop bB1 = new Bishop("black", "bB", 2, 7);
		pieces[2][7] = bB1;
		Bishop bB2 = new Bishop("black", "bB", 5, 7);
		pieces[5][7] = bB2;

		King bK = new King("black", "bK", 4, 7, false, false);
		pieces[4][7] = bK;
		Queen bQ = new Queen("black", "bQ", 3, 7);
		pieces[3][7] = bQ;

	}


	/**
	 *
	 * printBoard() prints out the board
	 *
	 * @return void
	 *
	 *
	 */


	public void printBoard() {
		System.out.println("");
		for (int j = 7; j >= 0; j--) {
			if (j != 7) {
				System.out.println(" ");
			}
			for (int i = 0; i <= 7; i++) {
				if (pieces[i][j] != null) {
					System.out.print(pieces[i][j].piece + " ");
				}

				if(pieces[i][j] == null) {
					if (j%2!=0) {
						if(i%2==0) {
							System.out.print("## ");
						}
						else {
							System.out.print("   ");
						}
					}
					else {
						if(i%2!=0) {
							System.out.print("## ");
						}
						else {
							System.out.print("   ");
						}

					}
				}
				if (i == 7) {
					System.out.print(j+1);
				}
			}
		}
		System.out.println("");
		System.out.println(" a  b  c  d  e  f  g  h ");
		System.out.println();

	}
}
