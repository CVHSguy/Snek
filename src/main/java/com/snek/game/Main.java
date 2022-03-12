package com.snek.game;

public class Main {
    public Main() {
    }

    public static final int amountOfFields = 21;
    public static final int boardSize = 400;

    static Board board = new Board(amountOfFields,boardSize);
    public static Board getBoard(){return board;}


}
