package implement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Saving{

    public void saveGameProgress(int dimension) {
        File file = new File("game_progress.txt");
        File dimensionFile = new File("dimension_file.txt");
        String contents = "";
       // String dimensionContent = "";
        try {
            FileWriter writer = new FileWriter(file);
            FileWriter writerDimension = new FileWriter(dimensionFile);
            for(int row = 0; row < dimension; ++row) {
                for(int col = 0; col < dimension; ++col) {
                    if(col != (dimension-1)) {
                        if(GUIComponet.bt[row][col].getText().isBlank() || GUIComponet.bt[row][col].getText().isEmpty()) {
                            contents = contents.concat("0 ");
                        }else {
                            contents = contents.concat(GUIComponet.bt[row][col].getText()+" ");
                        }

                    }else {
                        if(GUIComponet.bt[row][col].getText().isBlank() || GUIComponet.bt[row][col].getText().isEmpty()) {
                            contents = contents.concat("0");
                        }else {
                            contents = contents.concat(GUIComponet.bt[row][col].getText());
                        }
                    }
                }

                if(row != (dimension - 1)) {
                    contents = contents.concat("\n");
                }
            }
            writerDimension.write(String.valueOf(GenerateSudoku .noToHide));
            writerDimension.close();
            writer.write("");
            writer.write(contents);
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }



}