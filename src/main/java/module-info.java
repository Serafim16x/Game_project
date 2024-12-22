module com.example.project_game {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.project_game to javafx.fxml;
    exports com.example.project_game;
}