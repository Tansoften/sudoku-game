package implement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SharedData {
    static ArrayList<VerticesModel> repsInRow;
    static ArrayList<VerticesModel> repsInCol;
    static ArrayList<VerticesModel> repsInSubSudoku;
    static String numbers[][];
    static String backArray[][];
    static int dimension = 9;
    static  int Try= 0;
    static public boolean checkProgress() throws FileNotFoundException {
        boolean report = false;

        File file = new File("game_progress.txt");

        Scanner readFile = new Scanner(file);

        if(readFile.hasNext()){
            report = true;
        }

        return report;
    }

    static public void loadBackArray(){
        try{
            File file = new File("back_array.txt");
            Scanner readFile = new Scanner(file);

            backArray = new String[dimension][dimension];

            while(readFile.hasNext()){
                for(int row = 0; row < dimension; ++row){
                    for(int col = 0; col < dimension; ++col){
                        backArray[row][col] = readFile.next();
                    }
                }
            }

        }catch (FileNotFoundException fileNotFoundException){
            System.out.print(fileNotFoundException);
        }catch (IOException ioException){
            System.out.print(ioException);
        }
    }



    static public void loadNumbers(){

        try{
            File file = new File("game_progress.txt");
            Scanner readFile = new Scanner(file);

            numbers = new String[dimension][dimension];

            while(readFile.hasNext()){
                for(int row = 0; row < dimension; ++row){
                    for(int col = 0; col < dimension; ++col){
                        numbers[row][col] = readFile.next();
                    }
                }
            }

        }catch (FileNotFoundException fileNotFoundException){
            System.out.print(fileNotFoundException);
        }catch (IOException ioException){
            System.out.print(ioException);
        }
    }
    static  public int loadNoToHide() {
        int noToHide = 0;
        try {
            File file = new File("dimension_file.txt");
            Scanner readFile = new Scanner(file);

             noToHide = readFile.nextInt();

//            while(readFile.hasNext()){
//                for(int row = 0; row < dimension; ++row){
//                    for(int col = 0; col < dimension; ++col){
//                        numbers[row][col] = readFile.next();
//                    }
//                }
//            }

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.print(fileNotFoundException);
        } catch (IOException ioException) {
            System.out.print(ioException);
        }
        return noToHide;
    }
}
