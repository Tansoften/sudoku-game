package implement;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class InspectInput {


    private boolean checkCol(int currentRow, int currentCol, int num, int verticeY) {
        //System.out.println("Checking column...");
       // System.out.println(currentCol);
        boolean report = false;

        for (int index = 0; index < currentRow; ++index) {

            if (!(GUIComponet.bt[index][currentCol].getText().isBlank() || GUIComponet.bt[index][currentCol].getText().isEmpty())) {


                if(!(verticeY == index) ){
                    if (Integer.parseInt(GUIComponet.bt[index][currentCol].getText()) == num) {

                        //GUIComponet.bt[index][currentCol].setBackground(Color.GRAY);
                        //GUIComponet.bt[index][currentCol].setForeground(Color.RED);
                        VerticesModel repInCol = new VerticesModel();
                        repInCol.setVerticeY(index);
                        repInCol.setVerticeX(currentCol);
                        SharedData.repsInCol.add(repInCol);

                       // System.out.println("Col:" + num + " repeated.");
                        report = true;
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
                        // break;


                    }else{
                        for(int i = 0; i < SharedData.repsInCol.size(); ++i){
                            if(SharedData.repsInCol.get(i).getVerticeY() == index && SharedData.repsInCol.get(i).getVerticeX() == currentCol){
                                //JOptionPane.showMessageDialog(null, SharedData.repsInCol.get(i).getVerticeY());
                                SharedData.repsInCol.remove(SharedData.repsInCol.get(i));
                            }
                        }
                    }

                }

            }
        }//JOptionPane.showMessageDialog(null, report);

        return report;
    }

    private boolean checkRow(int currentRow, int currentCol, int num, int verticeX ) {
       // System.out.println("Checking row...");
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        boolean report = false;
        for (int index = 0; index < currentCol; ++index) {
            if (!(index == verticeX)){
                if (!(GUIComponet.bt[currentRow][index].getText().isBlank() || GUIComponet.bt[currentRow][index].getText().isEmpty())) {
                    if (Integer.parseInt(GUIComponet.bt[currentRow][index].getText()) == num) {
                       // System.out.println("Row:" + num + " repeated.");
                       // GUIComponet.bt[currentRow][index].setBackground(Color.GRAY);
                        //GUIComponet.bt[currentRow][index].setForeground(Color.RED);

                        VerticesModel repInRow = new VerticesModel();
                        repInRow.setVerticeY(currentRow);
                        repInRow.setVerticeX(index);
                        SharedData.repsInRow.add(repInRow);

                        report = true;

                    }else{
                        for(int i = 0; i < SharedData.repsInRow.size(); ++i){
                            if(SharedData.repsInRow.get(i).getVerticeY() == currentRow && SharedData.repsInRow.get(i).getVerticeX() == index){
                                //JOptionPane.showMessageDialog(null, SharedData.repsInCol.get(i).getVerticeY());
                                SharedData.repsInRow.remove(SharedData.repsInRow.get(i));
                            }
                        }
                    }

                }
            }



        }
        return report;
    }

    private boolean checkSubSudoku(int row, int col, int dimensions, int num) {
        boolean report = false;
        //System.out.println("Checking sub sudoku...");

        int subDimensions = (int) Math.round(Math.sqrt(dimensions));
        int edgeRowStart = 0;
        int edgeColStart = 0;
        int edgeRowEnd = subDimensions - 1;
        int edgeColEnd = subDimensions - 1;
        for (int r = 0; r <= row; r++) {
            if (r == (edgeRowEnd + 1)) {
                edgeRowStart += subDimensions;
                edgeRowEnd += subDimensions;


            }
            int c;
            for (c = 0; c <= col; c++) {
                if (c == (edgeColEnd + 1)) {
                    edgeColStart += subDimensions;
                    edgeColEnd += subDimensions;
                    // System.out.println(edgeColStart+","+edgeColEnd);
                }
            }
            if (c == col && r == row) {
                //System.out.println("hello");
                break;
            }
            // edgeColStart = 0;
            // edgeColEnd = subDimensions - 1;
        }
      //  System.out.print("index " + row + ", " + col + " \n" + "Edge Start " + edgeRowStart + ", " + edgeColStart + " \n" + "Edge End " + edgeRowEnd + ", " + edgeColEnd);

        for (int r = edgeRowStart; r <= edgeRowEnd; ++r) {
            for (int c = edgeColStart; c <= edgeColEnd; ++c) {
                // r represent verticeY of received number, c represent verticeX of received number
                if (!(row == r && col ==c))
                {
                    if (!(GUIComponet.bt[r][c].getText().isBlank() || GUIComponet.bt[r][c].getText().isEmpty())) {
                        if (Integer.parseInt(GUIComponet.bt[r][c].getText()) == num) {
                           // GUIComponet.bt[r][c].setBackground(Color.GRAY);
                            //GUIComponet.bt[r][c].setForeground(Color.RED);

                            VerticesModel repInSubSudoku = new VerticesModel();
                            repInSubSudoku.setVerticeY(r);
                            repInSubSudoku.setVerticeX(c);
                            SharedData.repsInSubSudoku.add(repInSubSudoku);

                            report = true;
                            // System.out.println("Col:" + num + " repeated.");
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
                        }else{
                            for(int i = 0; i < SharedData.repsInSubSudoku.size(); ++i){
                                if(SharedData.repsInSubSudoku.get(i).getVerticeY() == r && SharedData.repsInSubSudoku.get(i).getVerticeX() == c){
                                    //JOptionPane.showMessageDialog(null, SharedData.repsInCol.get(i).getVerticeY());
                                    SharedData.repsInSubSudoku.remove(SharedData.repsInSubSudoku.get(i));
                                }
                            }
                        }
                    }
                }


            }
        }
        //System.out.print(report);
        return report;
    }

     boolean checkRepetition(int r, int c, int num, int dimension) {
        boolean report = false;

        boolean quadrantStatus = checkSubSudoku(r, c, dimension, num);
        boolean rowStatus = checkRow(r, dimension, num ,c);
        boolean colStatus = checkCol(dimension, c, num, r);

        new Decoration(dimension).startDecoration();

        if (colStatus || rowStatus || quadrantStatus) {
            SharedData.Try++;
            if(SharedData.Try > 2)
            {
                JOptionPane.showMessageDialog(null, "Game is Over");
                HomePage.clearFile();
                Main.goToHome();
                SharedData.Try = 0;
            }
            report = true;
        }
        else
        {
            SharedData.Try =0;
            //GUIComponet.bt[GUIComponet.verticeY][GUIComponet.verticeX].setBackground(new Color(0, 184, 230));
        }
        //System.out.print(report);
        return report;
    }
     boolean isFilled(int dimension) {
        boolean filled = true;
        for (int r = 0; r < dimension; r++) {
            for (int c = 0; c < dimension; c++) {
                if (GUIComponet.bt[r][c].getText().isBlank() || GUIComponet.bt[r][c].getText().isEmpty()) {
                    filled = false;

                }
            }
        }
        return filled;
    }

}
