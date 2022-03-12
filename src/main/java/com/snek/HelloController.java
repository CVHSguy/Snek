package com.snek;

import com.snek.game.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;


import java.util.*;

public class HelloController  {

    @FXML
    Canvas canvas;

    @FXML
    private Text txScore;

    @FXML
    AnchorPane anchorPane;

    Snek snek;
    Food food;
    int moveX = 0;
    int moveY = 0;
    int movementFrequency = 500;
    int cooldown = 0;

    String heading = "";

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
        snek = new Snek((Main.boardSize-1)/Main.amountOfFields);
        Rectangle snake = snek.getSnake();
        anchorPane.getChildren().add(snake);
        snek.moveSnake(190,190);
    }
    public void spawnFood(){
        //currently i use the food rectangle as a ghetto tail just to test, the random numbers below randomize its spawnpoint on the map, although there is no check yet whether it spawns on the snake.
        //the sout is just to test if the random numbers are within range, they still need to be +1 to be perfect.
        food = new Food((Main.boardSize-1)/Main.amountOfFields);
        Rectangle asd = food.getFood();
        anchorPane.getChildren().add(asd);
        Random rn = new Random();
        int randomX = rn.nextInt(Main.amountOfFields);
        int randomY = rn.nextInt(Main.amountOfFields);
        System.out.println(randomX+" and "+randomY);
        int randomspawn = rn.nextInt(10);
    }

    private void Gameloop(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int[] snekPos = snek.getPos();
                //right to left
                if(snekPos[0]>Main.boardSize-(moveX*2) & Objects.equals(heading, "right")) {
                    snek.moveSnake(0, snekPos[1] + moveY);
                    //left to right
                }else if (snekPos[0]==0 & Objects.equals(heading, "left")){
                    snek.moveSnake(Main.boardSize+moveX, snekPos[1] + moveY);
                    //down to up
                }else if (snekPos[1]>Main.boardSize-(moveY*2) & heading=="down") {
                    snek.moveSnake(snekPos[0] + moveX, 0);
                    //up to down
                }else if(snekPos[1]==0 & heading=="up"){
                    snek.moveSnake(snekPos[0]+moveX, Main.boardSize+moveY);
                    //normal movement
                }else snek.moveSnake(snekPos[0] + moveX, snekPos[1] + moveY);
                food.moveFood(snekPos[0],snekPos[1]);
                
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
        spawnFood();
        drawBoard();
        Gameloop();


    }

    @FXML
    public void handleOnKeyPressed(KeyEvent e) {
        //java: an enum switch case label must be the unqualified name of an enumeration constant | so cant use switch cases
        if(e.getCode().equals(KeyCode.W)){
            moveX = 0;
            moveY = -(Main.boardSize-1)/Main.amountOfFields;
            heading = "up";
        }else if(e.getCode().equals(KeyCode.D)){
            moveX = (Main.boardSize-1)/Main.amountOfFields;
            moveY = 0;
            heading = "right";
        }else if(e.getCode().equals(KeyCode.A)){
            moveX = -(Main.boardSize-1)/Main.amountOfFields;
            moveY = 0;
            heading = "left";
        }else if(e.getCode().equals(KeyCode.S)){
            moveX = 0;
            moveY = (Main.boardSize-1)/Main.amountOfFields;
            heading = "down";
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

//food.moveFood(randomX*((Main.boardSize-1)/Main.amountOfFields), randomY*((Main.boardSize-1)/Main.amountOfFields));