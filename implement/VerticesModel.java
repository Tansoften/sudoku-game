package implement;

public class VerticesModel {
    private int verticeX, verticeY;

    VerticesModel(){
        this.verticeX = -1;
        this.verticeY = -1;
    }

    public void setVerticeX(int x){
        this.verticeX = x;
    }

    public void setVerticeY(int y){
        this.verticeY = y;
    }

    public int getVerticeX(){
        return this.verticeX;
    }

    public int getVerticeY(){
        return this.verticeY;
    }
}