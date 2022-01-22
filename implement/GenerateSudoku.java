package implement;

import java.util.Random;

class Loading implements Runnable {
	public void run() {

	}
}

public class GenerateSudoku {
	private int sudoku[][];
	private int dimensions, subDimensions;
	private int edgeRowStart, edgeColStart, edgeRowEnd, edgeColEnd;
	int numbers[];
	public static int noToHide ;

	public GenerateSudoku () {

		boolean isBuilt = false;

		do {
			dimensions = 9;
			buildSudoku(dimensions);
			isBuilt = fillSudoku(dimensions);
		} while (isBuilt);

	}
	public int getDimension(){return dimensions;}


	private void fillPickingNumbers() {
		Random randNum = new Random();
		int num = 0;
		for (int index = 0; index < numbers.length; ++index) {

			if(index == 0) {
				num = randNum.nextInt(dimensions) + 1;
				numbers[index] = num;
			} else {
				boolean isRepeated = false;

				do {
					num = randNum.nextInt(dimensions) + 1;
					for (int iterate = 0; iterate < index; ++iterate) {
						if (numbers[iterate] == num) {
							isRepeated = true;
							break;
						} else if ((iterate + 1) == index) {
							isRepeated = false;
						}
					}

				} while (isRepeated);

				numbers[index] = num;
				//System.out.print(num+" ");
			}
			//System.out.print(num + " ");
		}
	}

	private boolean checkRepeatition(int currentRow, int currentCol, int edgeRowStart, int edgeColStart, int edgeRowEnd, int edgeColEnd, int num, int subDimensions) {
		boolean report = false;
		//checkRow(currentRow, currentCol, num)  || checkCol(currentRow, currentCol, num) || checkSubSudoku(edgeRowStart, edgeColStart, edgeRowEnd, edgeColEnd, num)

		if (checkRow(currentRow, currentCol, num) || checkCol(currentRow, currentCol, num) || checkSubSudoku(edgeRowStart, edgeColStart, edgeRowEnd, edgeColEnd, num)) {
			report = true;

		}

		return report;
	}

	private boolean checkSubSudoku(int edgeRowStart, int edgeColStart, int edgeRowEnd, int edgeColEnd, int num) {
		//System.out.println("Checking sub sudoku...");
		boolean report = false;

		for (int row = edgeRowStart; row <= edgeRowEnd; ++row) {
			for (int col = edgeColStart; col <= edgeColEnd; ++col) {
				if (sudoku[row][col] == num) {
					report = true;
					//System.out.println("Sub sudoku: " + num + " repeated.");
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
				}
			}
		}

		return report;
	}

	private boolean checkCol(int currentRow, int currentCol, int num) {
		//System.out.println("Checking column...");

		boolean report = false;
		for (int index = 0; index < currentRow; ++index) {
			if (sudoku[index][currentCol] == num) {
				//System.out.println("Col:" + num + " repeated.");
				report = true;
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				break;
			}
		}

		return report;
	}

	private boolean checkRow(int currentRow, int currentCol, int num) {
		//System.out.println("Checking row...");
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		boolean report = false;
		for (int index = 0; index < currentCol; ++index) {
			if (sudoku[currentRow][index] == num) {
				//System.out.println("Row:" + num + " repeated.");
				report = true;
				break;
			}
		}
		return report;
	}

	private boolean fillSudoku(int dimensions) {
		fillPickingNumbers();
		//System.out.println("Filling sudoku...");

		int num = 0;
		subDimensions = (int) Math.round(Math.sqrt(dimensions));
		edgeRowStart = 0;
		edgeColStart = 0;
		edgeRowEnd = subDimensions - 1;
		edgeColEnd = subDimensions - 1;
		boolean hasFailed = false;
		while (true) {

			for (int row = 0; row < sudoku.length; ++row) {
				//System.out.println("Filling" + (row + 1) + " row.");
				if (row == (edgeRowEnd + 1)) {
					edgeRowStart += subDimensions;
					edgeRowEnd += subDimensions;
				}

				for (int col = 0; col < sudoku[row].length; ++col) {
					//System.out.println("Filling" + (col + 1) + " column.");
					if (col == (edgeColEnd + 1)) {
						edgeColStart += subDimensions;
						edgeColEnd += subDimensions;//System.out.println("x="+row+", y="+col);System.out.println("Sudoku:Starts ("+edgeRowStart+","+edgeColStart+") Ends("+edgeRowEnd+","+edgeColEnd+")");
					}

					if (row == 0 && col == 0) {
						num = numbers[0];
						sudoku[row][col] = num;
					} else {
						fillPickingNumbers();
						int index = 0;
						boolean isNumRepeated = false;

						do {
							if (index == numbers.length) {
								//System.out.println("Failed, number repeated!");
								hasFailed = true;
								break;
							} else {
								num = numbers[index];
							}

							isNumRepeated = checkRepeatition(row, col, edgeRowStart, edgeColStart, edgeRowEnd, edgeColEnd, num, subDimensions);
							if (isNumRepeated) {
								++index;
							}

						} while (isNumRepeated);

						if (!hasFailed) {
							sudoku[row][col] = num;
						}

					}
					//showSudoku();

					if (hasFailed) {
						break;
					}

				}
				if (hasFailed) {
					break;
				}
				edgeColStart = 0;
				edgeColEnd = subDimensions - 1;
			}
			break;
		}

		if (hasFailed) {
			//System.out.println("Failed filling sudoku...");
			return true;
		} else {
			//System.out.println("Done filling sudoku...");
			return false;
		}


	}

	private void buildSudoku(int dimensions) {
		sudoku = new int[dimensions][dimensions];
		numbers = new int[dimensions];
	}

	public void hideCell() {
		Random randNum = new Random();

		for (int i = 0; i < noToHide; i++) {
			int row = randNum.nextInt(dimensions);
			int col = randNum.nextInt(dimensions);
			if (sudoku[col][row] != 0) {
				sudoku[col][row] = 0;
			} else {
				i--;
			}
		}


	}
	public int returnNum(int r,int c)
	{
		return sudoku[r][c];
	}
	public int [][] returnSudokuArray(){
		return sudoku;
	}
}