package ch.hftm.model;

public abstract class Piece {
     private Color color;
     private int x;
     private int y;

     public Piece(Color color, int x, int y){
        this.color = color;
        this.x = x;
        this.y = y;
     }
}
