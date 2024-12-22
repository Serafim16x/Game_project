package com.example.project_game;

import javafx.application.Application; // Библиотека для создания JavaFX приложений.
import javafx.fxml.FXMLLoader; // Библиотека для загрузки FXML файлов.
import javafx.scene.Scene; // Библиотека для создания сцен в JavaFX.
import javafx.scene.input.KeyCode; // Библиотека для работы с клавишами в JavaFX.
import javafx.stage.Stage; // Библиотека для работы со сценами в JavaFX.
import javafx.stage.StageStyle; // Библиотека для стилей окон в JavaFX.

import java.io.IOException; // Библиотека для обработки исключений ввода/вывода.

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Загружаем FXML файл и создаем сцену.
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 994, 542);
        stage.initStyle(StageStyle.UNDECORATED); // Устанавливаем стиль окна без рамки.
        stage.setScene(scene); // Устанавливаем сцену для окна.

        // Обработка нажатия клавиш.
        scene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.SPACE && !HelloController.jump) // Если нажата клавиша пробела и игрок не прыгает.
                HelloController.jump = true; // Устанавливаем флаг прыжка.

            if(e.getCode() == KeyCode.A) // Если нажата клавиша "A".
                HelloController.left = true; // Устанавливаем флаг движения влево.

            if(e.getCode() == KeyCode.D) // Если нажата клавиша "D".
                HelloController.right = true; // Устанавливаем флаг движения вправо.
        });

        // Обработка отпускания клавиш.
        scene.setOnKeyReleased(e -> {
            if(e.getCode() == KeyCode.A) // Если отпущена клавиша "A".
                HelloController.left = false; // Сбрасываем флаг движения влево.

            if(e.getCode() == KeyCode.D) // Если отпущена клавиша "D".
                HelloController.right = false; // Сбрасываем флаг движения вправо.

            if(e.getCode() == KeyCode.ESCAPE) // Если нажата клавиша "Escape".
                HelloController.isPause = !HelloController.isPause; // Переключаем флаг паузы.
        });

        stage.show(); // Показываем окно.
    }

    public static void main(String[] args) {
        launch(); // Запускаем JavaFX приложение.
    }
}