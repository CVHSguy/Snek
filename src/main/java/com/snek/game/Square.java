package com.snek.game;

import javafx.scene.paint.Color;

public class Square {

    private int x;
    private int y;
    private int size;
    private Color color;


    public Square(int x, int y, int size, Color color){
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }

    public Color getColor(){return color;}
    public int getSize(){return this.size;}
    public int getX(){return this.x;}
    public int getY(){return this.y;}


}
