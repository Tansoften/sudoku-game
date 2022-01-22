package implement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends JFrame {

    boolean isRepeated = false;
    boolean doneFilled = false;
    InspectInput inspectInput = new InspectInput();
    int dimension;
    GenerateSudoku  generateSudoku;
    static JFrame jfr;
    private JPanel panel;
    private Font font;

    public static void main(String[] args) {
        new Main();
        //main.setVisible(true);
    }

    public Main(){
        initialize();
    }

   private void initialize() {
        SharedData.repsInCol = new ArrayList<VerticesModel>();
        SharedData.repsInRow = new ArrayList<VerticesModel>();
        SharedData.repsInSubSudoku = new ArrayList<VerticesModel>();
       generateSudoku = new GenerateSudoku ();
        dimension = generateSudoku.getDimension();
       // Main m = new Main();
       generateSudoku.hideCell();


        jfr = new JFrame();
        jfr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfr.setResizable(false);
        jfr.setSize(500, 800);
        jfr.setTitle("SUDOKU GAME");
        //jfr.setMaximumSize(new Dimension(500, 800));

        jfr.setLayout(new GridLayout(2, 3));
        JButton reset_btn = new JButton("RESET");
        JButton new_btn = new JButton("NEW");
        JButton menu_btn = new JButton("MENU");
        panel = new JPanel();
        JPanel buttons_panel = new JPanel();
        

        panel.setSize(500, 800);


        panel.setLayout(new GridLayout(generateSudoku.getDimension(), generateSudoku.getDimension()));

        GUIComponet.bt = new JTextField[generateSudoku.getDimension()][generateSudoku.getDimension()];

        //set grid items' fonts
        font = new Font("", Font.BOLD, 20);

        GUIComponet.y = dimension;
        GUIComponet.x = dimension;

       boolean hasData = false;
        try{
            hasData = SharedData.checkProgress();
        }catch (FileNotFoundException ex){
            System.out.print(ex);
        }

        if(hasData){
            SharedData.loadBackArray();
            SharedData.loadNumbers();
            GenerateSudoku.noToHide = SharedData.loadNoToHide();
            loadGame();
        }else{
            SharedData.Try =0;
            newGame();
            saveBackArray();
        }

        reset_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                resetSudoku();

            }
        });
        new_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                newSudoku();
                saveBackArray();
            }
        });
        menu_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                goToHome();
            }
        });
        new Decoration(dimension).startDecoration();
        //decoration.startDecoration();
        buttons_panel.add(reset_btn);
        buttons_panel.add(new_btn);
        buttons_panel.add(menu_btn);
        jfr.add(panel);
       jfr.add(buttons_panel);

        jfr.setVisible(true);


    }

    private void saveBackArray(){
        try{
            File file = new File("back_array.txt");
            FileWriter writer = new FileWriter(file);
            String numbers = "";

            for(int row = 0; row < generateSudoku.returnSudokuArray().length; ++row){
                for(int col = 0; col < generateSudoku.returnSudokuArray()[row].length; ++col){
                    String sNumber = String.valueOf(generateSudoku.returnSudokuArray()[row][col]);
                    if(col != (generateSudoku.returnSudokuArray()[row].length-1)){
                        numbers = numbers.concat(sNumber+" ");
                    }else{
                        numbers = numbers.concat(sNumber);
                    }
                }
                if(row != (generateSudoku.returnSudokuArray().length-1)){
                    numbers = numbers.concat("\n");
                }
            }

            writer.write(numbers);
            writer.close();
        }catch (FileNotFoundException ex){
            System.out.print(ex);
        }catch (IOException ex){
            System.out.print(ex);
        }
    }

    void loadGame(){

      for (int j = 0; j < generateSudoku.getDimension(); j++) {
            for (int i = 0; i < generateSudoku.getDimension(); i++) {

                System.out.print(Integer.parseInt(SharedData.numbers[j][i])+ " ");
                if (Integer.parseInt(SharedData.numbers[j][i]) != 0) {
                    GUIComponet.bt[j][i] = new JTextField();

                    GUIComponet.bt[j][i].setText(SharedData.numbers[j][i]);
                    GUIComponet.bt[j][i].setForeground(Color.black);



                } else {
                    GUIComponet.bt[j][i] = new JTextField(String.valueOf(" "));
                    setTxt(j, i, generateSudoku.getDimension());
                    //Check repetition of the progress game
//                    try{
//                        inspectInput.checkRepetition(j,i,Integer.parseInt(SharedData.numbers[j][i]),dimension);
//                    }
//                    catch (Exception e)
//                    {
//                        System.out.println(e);
//                    }


                }

                if (Integer.parseInt(SharedData.backArray[j][i]) != 0 )
                {
                    GUIComponet.bt[j][i].setEditable(false);
                }
                else
                {
                    GUIComponet.bt[j][i].setEditable(true);
                    setTxt(j,i,dimension);
                }


                // bt[j][i].setEditable(false);
                GUIComponet.bt[j][i].setFont(font);

                GUIComponet.bt[j][i].setHorizontalAlignment(JTextField.CENTER);
                GUIComponet.bt[j][i].setForeground(Color.black);
                panel.add(GUIComponet.bt[j][i]);

            }

        }
    }

    private void newGame(){
        for (int j = 0; j < generateSudoku.getDimension(); j++) {
            for (int i = 0; i < generateSudoku.getDimension(); i++) {


                if (generateSudoku.returnNum(j, i) != 0) {
                    GUIComponet.bt[j][i] = new JTextField(String.valueOf(generateSudoku.returnNum(j, i)));
                    GUIComponet.bt[j][i].setForeground(Color.black);
                    GUIComponet.bt[j][i].setEditable(false);


                } else {
                    GUIComponet.bt[j][i] = new JTextField(String.valueOf(" "));
                    setTxt(j, i, generateSudoku.getDimension());

                }


                // bt[j][i].setEditable(false);
                GUIComponet.bt[j][i].setFont(font);

                GUIComponet.bt[j][i].setHorizontalAlignment(JTextField.CENTER);
                GUIComponet.bt[j][i].setForeground(Color.black);
                panel.add(GUIComponet.bt[j][i]);
            }


        }
    }

    public static void goToHome(){
        jfr.dispose();
        HomePage.main(null);
    }

    void setTxt(int r, int c, int dimension) {
        GUIComponet.bt[r][c].addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                GUIComponet.bt[r][c].setText("");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                GUIComponet.verticeX = c;
                GUIComponet.verticeY = r;

                try {
                    int num = Integer.parseInt(GUIComponet.bt[r][c].getText());
                    //Verify if num is greater than 0
                    isNotZero(num);
                    // checkCol(dimension, c, check);
                    //checkRow(r, dimension, check);
                    //checkSubSudoku(r, c, dimension, check);
                    //
                    startInspection(r, c, num, dimension);

                } catch (Exception E) {
                    //JOptionPane.showMessageDialog(null,E);
                    //System.out.print(E);
                    GUIComponet.bt[r][c].setText("");
                }

                new Decoration(dimension).startDecoration();
                Saving saving = new Saving();
                saving.saveGameProgress(dimension);

            }
        });

    }

    private void startInspection(int row, int col, int num, int dimension) {

        isRepeated = inspectInput.checkRepetition(row, col, num, dimension);
        doneFilled = inspectInput.isFilled(dimension);
        //m.out.print(SharedData.repsInCol.size()+", "+SharedData.repsInRow.size()+", "+SharedData.repsInSubSudoku.size());

        if(SharedData.repsInCol.size() != 0 || SharedData.repsInRow.size() != 0 || SharedData.repsInSubSudoku.size() !=0){
            isRepeated = true;

           // System.out.print(SharedData.repsInCol.size()+", "+SharedData.repsInRow.size()+", "+SharedData.repsInSubSudoku.size());
        }
        if (doneFilled && isRepeated) {
             JOptionPane.showMessageDialog(null, "Puzzle is not solved!");
        } else if (doneFilled && !isRepeated) {
            //decoration.startDecoration();
             JOptionPane.showMessageDialog(null, "Congrats! you have solved the puzzle!");
            HomePage.clearFile();
             Main.goToHome();
             jfr.dispose();
        }
    }

    void isNotZero(int num) throws Exception {
        if (num == 0) {
            throw new Exception();
        }
    }

   static void resetSudoku() {
        SharedData.loadBackArray();
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (Integer.parseInt(SharedData.backArray[r][c]) == 0) {
                    GUIComponet.bt[r][c].setText(" ");
                } else {
                    GUIComponet.bt[r][c].setText(String.valueOf(SharedData.backArray[r][c]));
                }
            }
        }
        //System.out.println("Row: "+GUIComponet.EvenOrOddRow+", Col: "+GUIComponet.EvenOrOddCol);

        SharedData.repsInCol.clear();
        SharedData.repsInRow.clear();
        SharedData.repsInSubSudoku.clear();

        new Decoration(9).startDecoration();

    }

    void newSudoku() {
        generateSudoku = new GenerateSudoku ();
        generateSudoku.hideCell();
        for (int r = 0; r < dimension; r++) {
            for (int c = 0; c < dimension; c++) {
                if (generateSudoku.returnSudokuArray()[r][c] == 0) {
                    GUIComponet.bt[r][c].setText(" ");
                    setTxt(r, c, generateSudoku.getDimension());
                    GUIComponet.bt[r][c].setEditable(true);
                } else {
                    GUIComponet.bt[r][c].setText(String.valueOf(generateSudoku.returnSudokuArray()[r][c]));
                    GUIComponet.bt[r][c].setEditable(false);
                    //System.out.println(imp.returnSudokuArray()[r][c]);
                }
            }
        }

        SharedData.repsInCol.clear();
        SharedData.repsInRow.clear();
        SharedData.repsInSubSudoku.clear();

        new Decoration(dimension).startDecoration();

    }
}