package com.snek.game;


import javafx.scene.paint.Color;

public class Board {


    private int boardSize;
    private int amountOfFields;
    Square[][] squares;


    public Board(int amountOfFields, int boardSize){
        //this.boardSize = amountOfFields*10;
        this.amountOfFields = amountOfFields;
        this.boardSize= boardSize;
        int x = 0;
        int y = 0;
        squares= new Square[getAmountOfFields()][getAmountOfFields()];

        boolean change=true;

        for(int i = 0; i< this.amountOfFields ; i++){

            for(int j = 0;j<this.amountOfFields; j++){

                x = j * this.boardSize /this.amountOfFields;
                y = i * this.boardSize /this.amountOfFields;

                if(j%2==0&&i%2==0||j%2==1&&i%2==1){
                    squares[i][j] = new Square(x,y,this.boardSize/getAmountOfFields(), Color.BEIGE);

                } else {
                    squares[i][j] = new Square(x,y,this.boardSize/getAmountOfFields(), Color.LIGHTGRAY);

                }
            }
        }
    }

    public Square[][] getSquares(){
        return squares;
    }

    public int getBoardSize() {
        return boardSize;
    }
    public int getAmountOfFields() {
        return amountOfFields;
    }

}
