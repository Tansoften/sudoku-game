package implement;

import javax.swing.*;
import java.awt.*;

public class Decoration {

    int subDimensions;
    int edgeRowEnd;
    int edgeColEnd;
    int dimensions;

    Decoration(int dimensions) {
        this.dimensions = dimensions;
        subDimensions = (int) Math.round(Math.sqrt(dimensions));
        edgeRowEnd = subDimensions - 1;
        edgeColEnd = subDimensions - 1;
    }

    private void decorateSudoku() {

        for (int row = 0; row < dimensions; row++) {
            if (row == (edgeRowEnd + 1)) {
                edgeRowEnd += subDimensions;
               GUIComponet.EvenOrOddRow++;
            }
            for (int col = 0; col < dimensions; col++) {
                if (col == (edgeColEnd + 1)) {
                    edgeColEnd += subDimensions;//System.out.println("x="+row+", y="+col);System.out.println("Sudoku:Starts ("+edgeRowStart+","+edgeColStart+") Ends("+edgeRowEnd+","+edgeColEnd+")");
                    GUIComponet.EvenOrOddCol++;
                }
                if ((GUIComponet.EvenOrOddCol % 2 == 1 && GUIComponet.EvenOrOddRow % 2 == 1) || (GUIComponet.EvenOrOddCol % 2 == 0 && GUIComponet.EvenOrOddRow % 2 == 0)) {

                    GUIComponet.bt[row][col].setBackground(new Color(0, 184, 230));
                } else {
                    GUIComponet.bt[row][col].setBackground(new Color(230, 230, 230));
                }
                GUIComponet.bt[row][col].setForeground(Color.black);
               // System.out.println(col + ", " + edgeColEnd);
               // System.out.println("EvenOrOdd " + GUIComponet.EvenOrOddCol + " " + GUIComponet.EvenOrOddRow);

            }
            edgeColEnd = subDimensions - 1;
            GUIComponet.EvenOrOddCol = 1;
        }
    }

//    private void decorateUserInput() {
//        for (int row = 0; row < dimensions; row++) {
//            for (int col = 0; col < dimensions; col++) {
//                if (GUIComponet.bt[row][col].getText().isEmpty() || GUIComponet.bt[row][col].getText().isBlank()) {
//                    //GUIComponet.bt[row][col].setBackground(new Color(179, 240, 255));
//                }
//
//            }
//        }
//    }

    public void highlightRepeatedNumbers(){
        for(int i = 0; i < SharedData.repsInCol.size(); ++i){
            //JOptionPane.showMessageDialog(null, SharedData.repsInCol.get(i).getVerticeY());
            GUIComponet.bt[SharedData.repsInCol.get(i).getVerticeY()][SharedData.repsInCol.get(i).getVerticeX()].setForeground(Color.red);
        }

        for(int j = 0; j < SharedData.repsInRow.size(); ++j){
            //JOptionPane.showMessageDialog(null, SharedData.repsInCol.get(i).getVerticeY());
            GUIComponet.bt[SharedData.repsInRow.get(j).getVerticeY()][SharedData.repsInRow.get(j).getVerticeX()].setForeground(Color.red);
        }

        for(int j = 0; j < SharedData.repsInSubSudoku.size(); ++j){
            //JOptionPane.showMessageDialog(null, SharedData.repsInCol.get(i).getVerticeY());
            GUIComponet.bt[SharedData.repsInSubSudoku.get(j).getVerticeY()][SharedData.repsInSubSudoku.get(j).getVerticeX()].setForeground(Color.red);
        }
    }

    public void startDecoration() {
        decorateSudoku();
        highlightRepeatedNumbers();
        //decorateUserInput();
    }
}
