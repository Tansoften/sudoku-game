package implement;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;


public class HomePage extends JFrame implements ActionListener {
    private JButton easy;
    private JButton start;
    private JButton medium;
    private JButton hard;
    private JButton continue_btn;
    private int levelFrag = 0;
    private boolean isThereProgress;

//    enum levelfrag{
//        easy,mediun,hard;
//    }





    HomePage() {
        super();

        this.setTitle("SUDOKU GAME");

        JPanel full = new JPanel();
        full.setBackground(new Color(0, 180, 230));
        full.setBounds(0, 0, 500, 500);

        JPanel headingPanel = new JPanel();
        headingPanel.setBounds(0, 0, 500, 40);
        headingPanel.setBackground(Color.decode("#000080"));
        JPanel jPanel = new JPanel();
        jPanel.setBounds(190, 90, 120, 140);
        jPanel.setBackground(Color.WHITE);

        JLabel heading = new JLabel("WELCOME TO SUDOKU");
        heading.setFont(new Font("tahoma", Font.BOLD, 26));
        heading.setForeground(Color.white);
        heading.setBounds(70, 25, 350, 30);
        this.add(heading);


        JLabel level = new JLabel("LEVEL");
        level.setBounds(370, 90, 100, 30);
        level.setFont(new Font("serif", Font.BOLD, 20));
        level.setForeground(Color.BLACK);
        this.add(level);


        //Add button
        easy = new JButton("EASY");
        easy.setBounds(350, 120, 120, 30);
        easy.setForeground(Color.WHITE);
        easy.setBackground(new Color(0, 180, 230));

        medium = new JButton("MEDIUM");
        medium.setBounds(350, 160, 100, 30);
        medium.setForeground(Color.WHITE);
        medium.setBackground(new Color(0, 180, 230));

        hard = new JButton("HARD");
        hard.setBounds(350, 200, 100, 30);
        hard.setForeground(Color.WHITE);
        hard.setBackground(new Color(0, 180, 230));

        start = new JButton("START");
        start.setBackground(Color.decode("#FFDE03"));
        start.setForeground(Color.BLACK);
        start.setBounds(190, 250, 120, 30);

        //

        continue_btn = new JButton("CONTINUE");
        continue_btn.setBackground(Color.decode("#FFDE03"));
        continue_btn.setForeground(Color.BLACK);
        continue_btn.setBounds(190, 290, 120, 30);


        //add action listerner
        easy.addActionListener(this);

        medium.addActionListener(this);
        hard.addActionListener(this);
        start.addActionListener(this);
        continue_btn.addActionListener(this);

        headingPanel.add(heading);
        jPanel.add(level);
        jPanel.add(easy);
        jPanel.add(medium);
        jPanel.add(hard);

        //Add button to JFrame;
        this.add(start);
        this.add(continue_btn);
        continue_btn.setVisible(false);

        try {
            isThereProgress = SharedData.checkProgress();
        }catch (FileNotFoundException ex){
            System.out.print(ex);
        }

        if(isThereProgress){
            continue_btn.setVisible(true);
        }

        this.add(headingPanel);
        this.add(jPanel);
        this.add(full);

        //


        //set property for jframe
        this.setSize(500, 500);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String args[]) {
        HomePage homePage = new HomePage();
        homePage.setVisible(true);

    }

    public static void clearFile(){
        try{
            File fileBackArray = new File("back_array.txt");
            File file = new File("game_progress.txt");
            File fileDimension = new File("dimension_file.txt");
            FileWriter writer = new FileWriter(file);
            FileWriter writerDimension = new FileWriter(fileDimension);
            FileWriter writeBackArray  = new FileWriter(fileBackArray);
            writerDimension.write("");
            writeBackArray.write("");
            writer.write("");
            SharedData.Try = 0;

        }catch (FileNotFoundException ex){
            System.out.print(ex);
        }catch(IOException ex){
            System.out.print(ex); 
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == easy) {
            levelFrag = 0;
            easy.setBackground(Color.BLACK);
            easy.setEnabled(false);
            medium.setBackground(new Color(0, 180, 230));
            medium.setEnabled(true);
            hard.setBackground(new Color(0, 180, 230));
            hard.setEnabled(true);
            //JOptionPane.showMessageDialog(null, "easy clicked");

        } else if (e.getSource() == medium) {
            levelFrag = 1;
            medium.setEnabled(false);
            medium.setBackground(Color.BLACK);
            easy.setEnabled(true);
            easy.setBackground(new Color(0, 180, 230));
            hard.setBackground(new Color(0, 180, 230));
            hard.setEnabled(true);

           // JOptionPane.showMessageDialog(null, "medium clicked");

        } else if (e.getSource() == hard) {
            levelFrag = 2;
            hard.setEnabled(false);
            hard.setBackground(Color.BLACK);
            medium.setBackground(new Color(0, 180, 230));
            medium.setEnabled(true);
            easy.setBackground(new Color(0, 180, 230));
            easy.setEnabled(true);
           // JOptionPane.showMessageDialog(null, "hard clicked");


        } else if(e.getSource() == continue_btn){
            Main.main(null);
            this.dispose();
        }else {
            //JOptionPane.showMessageDialog(null, "start clicked");
                clearFile();

            this.dispose();
               if (levelFrag == 0) {
                   GenerateSudoku .noToHide = 18;
                   Main.main(null);
                    this.dispose();

                } else if (levelFrag == 1) {
                   GenerateSudoku .noToHide = 36;
                   Main.main(null);
                    this.dispose();
                } else {
                   GenerateSudoku .noToHide = 56;
                    Main.main(null);
                    this.dispose();
                }
        }
    }


}