module com.snek {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.snek to javafx.fxml;
    exports com.snek;
    exports com.snek.game;
    opens com.snek.game to javafx.fxml;
}