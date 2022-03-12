package com.snek;

import com.snek.game.Board;
import com.snek.game.Main;
import com.snek.game.Snek;
import com.snek.game.Square;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class HelloController  {

    @FXML
    Canvas canvas;

    @FXML
    private Text txScore;

    @FXML
    AnchorPane anchorPane;

    Snek snek;
    int movementOffset = 19;
    int moveX = 19;
    int moveY = 0;
    int movementFrequency = 500;
    int cooldown = 0;

    void setTxScore(int no){
        txScore.setText(Integer.toString(no));
    }

    @FXML
    Button button = new Button();

    @FXML
    public void drawBoard(){

        Board board = Main.getBoard();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GRAY);

        Square[][] squares = board.getSquares();

        for (int i = 0; i< board.getAmountOfFields(); i++){
            for(int j = 0; j<board.getAmountOfFields(); j++){
                Square square= squares[i][j];
                gc.setFill(square.getColor());
                gc.fillRect(square.getX(),square.getY(),square.getSize(),square.getSize());

            }
        }

    }

    public void spawnSnake(){
        snek = new Snek(20);
        Rectangle snake = snek.getSnake();
        anchorPane.getChildren().add(snake);
        snek.moveSnake(190,190);
    }

    private void drawMisc(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int[] snekPos = snek.getPos();
                if(snekPos[0]>=Main.boardSize-1) {
                    snek.moveSnake(0,snekPos[1]+moveY);
                }else if (snekPos[1]>=Main.boardSize-1){
                    snek.moveSnake(snekPos[0] + moveX,0);
                }else snek.moveSnake(snekPos[0] + moveX, snekPos[1] + moveY);

                System.out.println((Main.boardSize-1)/21);

            }
        },0,movementFrequency);

        button.setLayoutX(100);
        button.setLayoutY(450);
        button.setText("Do Something!");
        anchorPane.getChildren().add(button);
    }

    @FXML
    private void initialize(){
        spawnSnake();
        drawBoard();
        drawMisc();

    }

    @FXML
    public void handleOnKeyPressed(KeyEvent e) {
        //java: an enum switch case label must be the unqualified name of an enumeration constant | so cant use switch cases
        if(e.getCode().equals(KeyCode.W)){
            moveX = 0;
            moveY = -(Main.boardSize-1)/21;
        }else if(e.getCode().equals(KeyCode.D)){
            moveX = (Main.boardSize-1)/21;
            moveY = 0;
        }else if(e.getCode().equals(KeyCode.A)){
            moveX = -(Main.boardSize-1)/21;
            moveY = 0;
        }else if(e.getCode().equals(KeyCode.S)){
            moveX = 0;
            moveY = (Main.boardSize-1)/21;
        }
    }
}

/*
TODO stuff
if statement that with a random thing between 1-9 creates a 22% chance that it spawns a food rectangle, the cooldown is so it cant keep spawning food each game tick
* if(feeler>=8 & cooldown>10){
                    testman.setY(38);
                    testman.setX(19);
                    cooldown = 0;
                }
                cooldown++;
                * stuff for spawning food*/