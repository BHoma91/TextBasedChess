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
 *King class is a subclass of the Piece class where we implement the methods and rules that 
 *are specific to king pieces
 *
 *@param color // the color of the piece
 *@param piece // the type of piece it is
 *@param x_coordinate 
 *@param y_coordinate
 *@param moved // tells us if the king has moved so we can do castle
 *@param check // tells if the king is in check
 */

public class King extends Piece {
	String color;
	String piece;
	int x_coordinate;
	int y_coordinate;
	boolean moved;
	boolean check;

	public King(String color, String piece, int x_coordinate, int y_coordinate, boolean moved, boolean check) {
		super(color, piece, x_coordinate, y_coordinate);
		this.moved = moved;
		this.check = check;
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
		int Dx = x - piece.getX();
		int Dy = y - piece.getY();
		Piece temp = null;
		int O_x = piece.getX();
		int O_y = piece.getY();

		if ((x - piece.getX() == 1 || (x - piece.getX() == -1) || (x - piece.getX() == 0))
				&& ((y - piece.getY() == 1) || (y - piece.getY() == 0) || (y - piece.getY() == -1))) {
			if (piece.color.toLowerCase().equals("white")) {
				if (pieces[x][y] != null) {
					if (pieces[x][y].color.toLowerCase().equals("white")) {
						System.out.println();
						System.err.println("ERROR: Invalid Move");
						return -1;

					}
					temp = pieces[x][y];
					int x_old = piece.getX();
					int y_old = piece.getY();
					pieces[x][y] = piece;
					piece.setX(x);
					piece.setY(y);
					pieces[x_old][y_old] = null;

				} else {
					int x_old = piece.getX();
					int y_old = piece.getY();
					pieces[x][y] = piece;
					piece.setX(x);
					piece.setY(y);
					pieces[x_old][y_old] = null;

				}
			} else {
				if (pieces[x][y] != null) {
					if (pieces[x][y].color.toLowerCase().equals("black")) {
						System.out.println();
						System.err.println("ERROR: Invalid Move");
						return -1;
					}
					temp = pieces[x][y];
					int x_old = piece.getX();
					int y_old = piece.getY();
					pieces[x][y] = piece;
					piece.setX(x);
					piece.setY(y);
					pieces[x_old][y_old] = null;

				} else {
					int x_old = piece.getX();
					int y_old = piece.getY();
					pieces[x][y] = piece;
					piece.setX(x);
					piece.setY(y);
					pieces[x_old][y_old] = null;
					moved = true;

				}
			}
		} else if (Math.abs(Dx) == 2 && Dy == 0) {
			if ((piece.getY() == 0 || piece.getY() == 7) && piece.getX() == 4) {
				if (moved == false && check == false) {
					if (piece.color.toLowerCase().equals("white")) {

						if (pieces[0][0] != null && Dx == -2 && pieces[1][0] == null && pieces[2][0] == null
								&& pieces[3][0] == null) {

							if (pieces[0][0].getPiece().equals("wR")) {
								Rook Rook = (Rook) pieces[0][0];
								if (Rook.getMoved() == false) {
									int x_old = piece.getX();
									int y_old = piece.getY();
									int x_check;

									for (x_check = 3; x_check > 1; x_check--) {
										pieces[x_check][0] = piece;
										piece.setX(x_check);
										piece.setY(0);

										pieces[x_old][y_old] = null;

										for (int i = 0; i < 8; i++) {
											for (int j = 0; j < 8; j++) {
												if (Environment.pieces[j][i] != null) {
													if (Environment.pieces[j][i].getColor().equals("black")) {
														if (Environment.pieces[j][i]
																.check_checker(Environment.pieces[j][i], "wK") == 1) {
															System.out.println(
																	"ERROR: Move results in king being in check");
															pieces[x_old][y_old] = piece;
															piece.setX(x_old);
															piece.setY(y_old);
															pieces[x_check][0] = null;
															return -1;
														}
													}
												}
											}
										}
									}
									
									pieces[x_old][y_old] = piece;
									piece.setX(x_old);
									piece.setY(y_old);
									pieces[x_check][0] = null;

									Rook.setMoved(true);
									Rook.setX(x + 1);
									Rook.setY(0);
									moved = true;
									pieces[x][y] = piece;
									piece.setX(x);
									piece.setY(y);
									pieces[x_old][y_old] = null;
									pieces[0][0] = null;
									pieces[x + 1][0] = Rook;

								}
							}
						}
						if (pieces[7][0] != null && Dx == 2 && pieces[5][0] == null && pieces[6][0] == null) {

							if (pieces[7][0].getPiece().equals("wR")) {
								Rook Rook = (Rook) pieces[7][0];
								if (Rook.getMoved() == false) {
									int x_old = piece.getX();
									int y_old = piece.getY();
									int x_check;

									for (x_check = 5; x_check < 7; x_check++) {
										pieces[x_check][0] = piece;
										piece.setX(x_check);
										piece.setY(0);

										pieces[x_old][y_old] = null;

										for (int i = 0; i < 8; i++) {
											for (int j = 0; j < 8; j++) {
												if (Environment.pieces[j][i] != null) {
													if (Environment.pieces[j][i].getColor().equals("black")) {
														if (Environment.pieces[j][i]
																.check_checker(Environment.pieces[j][i], "wK") == 1) {
															System.out.println(
																	"ERROR: Move results in king being in check");
															pieces[x_old][y_old] = piece;
															piece.setX(x_old);
															piece.setY(y_old);
															pieces[x_check][0] = null;
															return -1;
														}
													}
												}
											}
										}
									}

									pieces[x_old][y_old] = piece;
									piece.setX(x_old);
									piece.setY(y_old);
									pieces[x_check][0] = null;

									Rook.setMoved(true);
									Rook.setX(x - 1);
									Rook.setY(0);
									moved = true;
									pieces[x][y] = piece;
									piece.setX(x);
									piece.setY(y);
									pieces[x_old][y_old] = null;
									pieces[7][0] = null;
									pieces[x - 1][0] = Rook;

								}
							}
						}

					} else {
						if (piece.color.toLowerCase().equals("black")) {

							if (pieces[0][7] != null && Dx == -2 && pieces[1][7] == null && pieces[2][7] == null
									&& pieces[3][7] == null) {
								if (pieces[0][7].getPiece().equals("bR")) {
									Rook Rook = (Rook) pieces[0][7];
									if (Rook.getMoved() == false) {
										int x_old = piece.getX();
										int y_old = piece.getY();
										int x_check;

										for (x_check = 3; x_check > 1; x_check--) {
											pieces[x_check][7] = piece;
											piece.setX(x_check);
											piece.setY(7);

											pieces[x_old][y_old] = null;

											for (int i = 0; i < 8; i++) {
												for (int j = 0; j < 8; j++) {
													if (Environment.pieces[j][i] != null) {
														if (Environment.pieces[j][i].getColor().equals("white")) {
															if (Environment.pieces[j][i].check_checker(
																	Environment.pieces[j][i], "bK") == 1) {
																System.out.println(
																		"ERROR: Move results in king being in check");
																pieces[x_old][y_old] = piece;
																piece.setX(x_old);
																piece.setY(y_old);
																pieces[x_check][7] = null;
																return -1;
															}
														}
													}
												}
											}
										}

										pieces[x_old][y_old] = piece;
										piece.setX(x_old);
										piece.setY(y_old);
										pieces[x_check][7] = null;

										Rook.setMoved(true);
										Rook.setX(x + 1);
										Rook.setY(7);
										moved = true;
										pieces[x][y] = piece;
										piece.setX(x);
										piece.setY(y);
										pieces[x_old][y_old] = null;
										pieces[0][7] = null;
										pieces[x + 1][7] = Rook;

									}

								}
							}
							if (pieces[7][7] != null && Dx == 2 && pieces[5][7] == null && pieces[6][7] == null) {
								if (pieces[7][7].getPiece().equals("bR")) {
									Rook Rook = (Rook) pieces[7][7];
									if (Rook.getMoved() == false) {
										int x_old = piece.getX();
										int y_old = piece.getY();
										int x_check;
										for (x_check = 5; x_check < 7; x_check++) {
											pieces[x_check][7] = piece;
											piece.setX(x_check);
											piece.setY(7);

											pieces[x_old][y_old] = null;

											for (int i = 0; i < 8; i++) {
												for (int j = 0; j < 8; j++) {
													if (Environment.pieces[j][i] != null) {
														if (Environment.pieces[j][i].getColor().equals("white")) {
															if (Environment.pieces[j][i].check_checker(
																	Environment.pieces[j][i], "bK") == 1) {
																System.out.println(
																		"ERROR: Move results in king being in check");
																pieces[x_old][y_old] = piece;
																piece.setX(x_old);
																piece.setY(y_old);
																pieces[x_check][7] = null;
																return -1;
															}
														}
													}
												}
											}
										}

										pieces[x_old][y_old] = piece;
										piece.setX(x_old);
										piece.setY(y_old);
										pieces[x_check][7] = null;

										Rook.setMoved(true);
										Rook.setX(x - 1);
										Rook.setY(7);
										moved = true;
										pieces[x][y] = piece;
										piece.setX(x);
										piece.setY(y);
										pieces[x_old][y_old] = null;
										pieces[7][7] = null;
										pieces[x - 1][7] = Rook;

									}

								}
							}

						}
					}
				} else {
					System.out.println();
					System.err.println("ERROR: Invalid Move");
					return -1;
				}
			}
		} else {
			System.out.println();
			System.err.println("ERROR: Invalid Move");
			return -1;
		}
		String color = piece.getColor().toLowerCase();

		if (color.equals("white")) {
			if (Chess.check_white() > 0) {
				System.out.println("ERROR: Can't move king into check");
				pieces[O_x][O_y] = piece;
				piece.setX(O_x);
				piece.setY(O_y);
				pieces[x][y] = temp;
				return -1;
			}
		} else { // Black
			if (Chess.check_black() > 0) {
				System.out.println("ERROR: Can't move king into check");
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
		this.moved = true;
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
		if (piece.getX() - 1 >= 0) {
			if (pieces[piece.getX() - 1][piece.getY()] != null) {
				if (pieces[piece.getX() - 1][piece.getY()].piece.equals(king)) {
					return 1;
				}
			}
		}
		if (piece.getX() - 1 >= 0 && piece.getY() - 1 >= 0) {
			if (pieces[piece.getX() - 1][piece.getY() - 1] != null) {
				if (pieces[piece.getX() - 1][piece.getY() - 1].piece.equals(king)) {
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
		if (piece.getY() + 1 < 8) {
			if (pieces[piece.getX()][piece.getY() + 1] != null) {
				if (pieces[piece.getX()][piece.getY() + 1].piece.equals(king)) {
					return 1;
				}
			}
		}
		if (piece.getY() - 1 >= 0) {
			if (pieces[piece.getX()][piece.getY() - 1] != null) {
				if (pieces[piece.getX()][piece.getY() - 1].piece.equals(king)) {
					return 1;
				}
			}
		}
		if (piece.getX() + 1 < 8) {
			if (pieces[piece.getX() + 1][piece.getY()] != null) {
				if (pieces[piece.getX() + 1][piece.getY()].piece.equals(king)) {
					return 1;
				}
			}
		}
		if (piece.getX() + 1 < 8 && piece.getY() + 1 < 8) {
			if (pieces[piece.getX() + 1][piece.getY() + 1] != null) {
				if (pieces[piece.getX() + 1][piece.getY() + 1].piece.equals(king)) {
					return 1;
				}
			}
		}
		if (piece.getX() + 1 < 8 && piece.getY() - 1 >= 0) {
			if (pieces[piece.getX() + 1][piece.getY() - 1] != null) {
				if (pieces[piece.getX() + 1][piece.getY() - 1].piece.equals(king)) {
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
		int x = piece.getX();
		int y = piece.getY();
		String color = piece.getColor().toLowerCase();
		Piece temp = null;
		int RValue = 0;

		// Lets test the left side to see if king (our main man) will be in
		// check
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
			pieces[x][y] = null;
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
		} // Lets test the right side to see if king our main will be in check
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
			pieces[x][y] = null;
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
			pieces[x][y] = null;
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
			pieces[x][y - 1] = temp;
			pieces[x][y] = piece;
			if (a == 0) {
				return 0;
			}
		}
		// Test one above the king
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
			pieces[x][y] = null;
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
		// Lets test left up
		if (x - 1 >= 0 && y + 1 <= 7) {
			int a = 0;
			if (pieces[x - 1][y + 1] != null) {
				if (pieces[x - 1][y + 1].getColor().equals(color)) {
					RValue = 1;
					a++;
				}
			}
			temp = pieces[x - 1][y + 1];
			pieces[x - 1][y + 1] = piece;
			pieces[x][y] = null;
			if (color.equals("white")) {
				if (Chess.check_white() > 0) {
					pieces[x][y] = piece;
					pieces[x - 1][y + 1] = temp;
					RValue = 1;
					a++;
				}
			} else { // Black
				if (Chess.check_black() > 0) {
					pieces[x][y] = piece;
					pieces[x - 1][y + 1] = temp;
					RValue = 1;
					a++;
				}
			}
			pieces[x - 1][y + 1] = temp;
			pieces[x][y] = piece;
			if (a == 0) {
				return 0;
			}
		}
		// Lets test the left down side to see if king our main will be in check
		if (x - 1 >= 0 && y - 1 >= 0) {
			int a = 0;
			if (pieces[x - 1][y - 1] != null) {
				if (pieces[x - 1][y - 1].getColor().equals(color)) {
					RValue = 1;
					a++;
				}
			}
			temp = pieces[x - 1][y - 1];
			pieces[x - 1][y - 1] = piece;
			pieces[x][y] = null;
			if (color.equals("white")) {
				if (Chess.check_white() > 0) {
					pieces[x][y] = piece;
					pieces[x - 1][y - 1] = temp;
					RValue = 1;
					a++;
				}
			} else { // Black
				if (Chess.check_black() > 0) {
					pieces[x][y] = piece;
					pieces[x - 1][y - 1] = temp;
					RValue = 1;
					a++;
				}
			}
			pieces[x - 1][y - 1] = temp;
			pieces[x][y] = piece;
			if (a == 0) {
				return 0;
			}
		}
		// Lets test the bottom right down now
		if (x + 1 <= 0 && y - 1 >= 0) {
			int a = 0;
			if (pieces[x - 1][y] != null) {
				if (pieces[x + 1][y - 1].getColor().equals(color)) {
					RValue = 1;
					a++;
				}
			}
			temp = pieces[x + 1][y - 1];
			pieces[x + 1][y - 1] = piece;
			pieces[x][y] = null;
			if (color.equals("white")) {
				if (Chess.check_white() > 0) {
					pieces[x][y] = piece;
					pieces[x + 1][y - 1] = temp;
					RValue = 1;
					a++;
				}
			} else { // Black
				if (Chess.check_black() > 0) {
					pieces[x][y] = piece;
					pieces[x + 1][y - 1] = temp;
					RValue = 1;
					a++;
				}
			}
			pieces[x + 1][y - 1] = temp;
			pieces[x][y] = piece;
			if (a == 0) {
				return 0;
			}
		}
		// Test one above and right for the king
		if (x + 1 < 0 && y + 1 <= 7) {
			int a = 0;
			if (pieces[x - 1][y] != null) {
				if (pieces[x + 1][y + 1].getColor().equals(color)) {
					RValue = 1;
					a++;
				}
			}
			temp = pieces[x + 1][y + 1];
			pieces[x + 1][y + 1] = piece;
			pieces[x][y] = null;
			if (color.equals("white")) {
				if (Chess.check_white() > 0) {
					pieces[x][y] = piece;
					pieces[x + 1][y + 1] = temp;
					RValue = 1;
					a++;
				}
			} else { // Black
				if (Chess.check_black() > 0) {

					pieces[x][y] = piece;
					pieces[x + 1][y + 1] = temp;
					RValue = 1;
					a++;
				}
			}
			pieces[x + 1][y + 1] = temp;
			pieces[x][y] = piece;
			if (a == 0) {
				return 0;
			}
		}
		pieces[x][y] = piece;
		return RValue;
	}

	public void setCheck(boolean b) {
		this.check = b;

	}
}
