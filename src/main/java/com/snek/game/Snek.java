package com.snek.game;

import javafx.scene.shape.Rectangle;

public class Snek {

    int size;

    Rectangle rect = new Rectangle();

    public Snek(int size){
        this.size =size;

    }
    public Rectangle getSnake(){
        rect.setWidth(size);
        rect.setHeight(size);
        return rect;
    }

    public void moveSnake(int X,int Y){
        rect.setX(X);
        rect.setY(Y);
    }

    public int[] getPos(){
        int[] pos = new int[2];
        pos[0]= (int) rect.getX();
        pos[1]= (int) rect.getY();
        return pos;
    }


}
