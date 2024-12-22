package com.example.project_game;

import java.net.URL; // Библиотека для работы с URL.
import java.util.ResourceBundle; // Библиотека для работы с ресурсами, например, для интернационализации.

import javafx.animation.*; // Библиотека для работы с анимациями в JavaFX.
import javafx.fxml.FXML; // Библиотека для работы с аннотациями FXML в JavaFX.
import javafx.scene.control.Button; // Библиотека для работы с кнопками в JavaFX.
import javafx.scene.image.ImageView; // Библиотека для работы с изображениями в JavaFX.
import javafx.scene.control.Label; // Библиотека для работы с метками в JavaFX.
import javafx.util.Duration; // Библиотека для работы с продолжительностью времени в JavaFX.

public class HelloController {

    @FXML
    private ResourceBundle resources; // ResourceBundle для интернационализации.

    @FXML
    private URL location; // URL для поиска FXML файла.

    @FXML
    private ImageView bg1, bg2, bg3, bg4, player, enemy1, enemy2, Heard1, Heard2, Heard3; // Объекты ImageView для элементов игры.

    @FXML
    private Label LabelLouse, DistanceLabel, resultLabel; // Объекты Label для отображения информации.

    @FXML
    private Button ButtonnewGame, ButtonExit, ButtonContinue; // Объекты Button для управления игрой.

    private final int BG_WIDTH = 994; // Константа для ширины фона.

    private ParallelTransition parallelTransition; // ParallelTransition для одновременной анимации нескольких узлов.
    private TranslateTransition enemyTransition; // TranslateTransition для перемещения врагов.

    public static boolean jump = false; // Статическая переменная для проверки прыжка игрока.
    public static boolean right = false; // Статическая переменная для проверки движения вправо.
    public static boolean left = false; // Статическая переменная для проверки движения влево.
    public static boolean isPause = false; // Статическая переменная для проверки паузы в игре.

    private int playerSpeed = 6, jumpDownSpeed = 7; // Переменные для скорости игрока и скорости падения.
    private int lives = 3; // Переменная для отслеживания количества жизней игрока.
    private double distance = 0; // Переменная для отслеживания пройденного расстояния.

    private int speedIncrementDistance = 100; // Порог расстояния для увеличения скорости.
    private double bestDistance = 0; // Переменная для отслеживания лучшего расстояния.

    // Метод для продолжения игры.
    private void resumeGame() {
        isPause = false; // Снимаем паузу.
        playerSpeed = 6; // Восстанавливаем скорость игрока.
        jumpDownSpeed = 7; // Восстанавливаем скорость падения.
        parallelTransition.play(); // Запускаем параллельную анимацию.
        enemyTransition.play(); // Запускаем анимацию врага.
        ButtonnewGame.setVisible(false); // Скрываем кнопку новой игры.
        ButtonExit.setVisible(false); // Скрываем кнопку выхода.
        ButtonContinue.setVisible(false); // Скрываем кнопку продолжения.
    }

    // Метод для перезапуска игры.
    private void restartGame() {
        LabelLouse.setVisible(false); // Скрываем метку проигрыша.
        playerSpeed = 6; // Восстанавливаем скорость игрока.
        jumpDownSpeed = 7; // Восстанавливаем скорость падения.
        parallelTransition.playFromStart(); // Запускаем параллельную анимацию с начала.
        enemyTransition.playFromStart(); // Запускаем анимацию врага с начала.
        if (distance > bestDistance) { // Проверяем, побили ли мы лучший результат.
            bestDistance = distance; // Обновляем лучший результат.
            resultLabel.setText(String.format("Best Distance: %.2f m", bestDistance)); // Обновляем текст метки результата.
        }
        lives = 4; // Восстанавливаем количество жизней.
        Heard1.setVisible(true); // Показываем первое сердце.
        Heard2.setVisible(true); // Показываем второе сердце.
        Heard3.setVisible(true); // Показываем третье сердце.
        DistanceLabel.setVisible(true); // Показываем метку расстояния.
        distance = 0; // Сбрасываем расстояние.
        DistanceLabel.setText("Distance: 0 m"); // Обновляем текст метки расстояния.
        ButtonnewGame.setVisible(false); // Скрываем кнопку новой игры.
        ButtonExit.setVisible(false); // Скрываем кнопку выхода.
        ButtonContinue.setVisible(false); // Скрываем кнопку продолжения.
        parallelTransition.play(); // Запускаем параллельную анимацию.
        enemyTransition.play(); // Запускаем анимацию врага.
    }

    // Метод для потери жизни.
    private void loseLife() {
        if (lives == 3) { // Если у игрока 3 жизни.
            Heard1.setVisible(false); // Скрываем первое сердце.
            playerSpeed = 6; // Восстанавливаем скорость игрока.
            jumpDownSpeed = 7; // Восстанавливаем скорость падения.
            parallelTransition.playFromStart(); // Запускаем параллельную анимацию с начала.
            enemyTransition.playFromStart(); // Запускаем анимацию врага с начала.
        } else if (lives == 2) { // Если у игрока 2 жизни.
            Heard2.setVisible(false); // Скрываем второе сердце.
            playerSpeed = 6; // Восстанавливаем скорость игрока.
            jumpDownSpeed = 7; // Восстанавливаем скорость падения.
            parallelTransition.playFromStart(); // Запускаем параллельную анимацию с начала.
            enemyTransition.playFromStart(); // Запускаем анимацию врага с начала.
        } else if (lives == 1) { // Если у игрока 1 жизнь.
            Heard3.setVisible(false); // Скрываем третье сердце.
            playerSpeed = 6; // Восстанавливаем скорость игрока.
            jumpDownSpeed = 7; // Восстанавливаем скорость падения.
            parallelTransition.playFromStart(); // Запускаем параллельную анимацию с начала.
            enemyTransition.playFromStart(); // Запускаем анимацию врага с начала.
        } else if (lives == 0) { // Если у игрока 0 жизней.
            LabelLouse.setVisible(true); // Показываем метку проигрыша.
            playerSpeed = 0; // Останавливаем игрока.
            jumpDownSpeed = 0; // Останавливаем падение.
            parallelTransition.pause(); // Ставим параллельную анимацию на паузу.
            enemyTransition.pause(); // Ставим анимацию врага на паузу.

            PauseTransition pause = new PauseTransition(Duration.seconds(2)); // Создаем паузу на 2 секунды.
            pause.setOnFinished(event -> restartGame()); // После паузы перезапускаем игру.
            pause.play(); // Запускаем паузу.
        }
        lives--; // Уменьшаем количество жизней.
    }

    // AnimationTimer для обработки игрового цикла.
    AnimationTimer timer = new AnimationTimer() {

        private long lastUpdate = 0; // Переменная для отслеживания времени последнего обновления.

        @Override
        public void handle(long now) {
            if (lastUpdate > 0) { // Если время последнего обновления больше 0.
                double elapsedSeconds = (now - lastUpdate) / 1_000_000_000.0; // Вычисляем прошедшее время в секундах.
                distance += elapsedSeconds * playerSpeed; // Увеличиваем пройденное расстояние.
                DistanceLabel.setText(String.format("Distance: %.2f m", distance)); // Обновляем текст метки расстояния.

                if (distance >= speedIncrementDistance) { // Если пройденное расстояние больше порога увеличения скорости.
                    playerSpeed++; // Увеличиваем скорость игрока.
                    jumpDownSpeed++; // Увеличиваем скорость падения.
                    speedIncrementDistance += 100; // Увеличиваем порог для следующего увеличения скорости.
                }
            }
            lastUpdate = now; // Обновляем время последнего обновления.

            if (jump && player.getLayoutY() > 300f)
                player.setLayoutY(player.getLayoutY() - playerSpeed); // Перемещаем игрока вверх при прыжке.
            else if (player.getLayoutY() <= 440f) {
                jump = false; // Останавливаем прыжок.
                player.setLayoutY(player.getLayoutY() + jumpDownSpeed); // Перемещаем игрока вниз.
            }

            if (right && player.getLayoutX() < 100f)
                player.setLayoutX(player.getLayoutX() + playerSpeed); // Перемещаем игрока вправо.

            if (left && player.getLayoutX() > 20f)
                player.setLayoutX(player.getLayoutX() - playerSpeed); // Перемещаем игрока влево.

            if (isPause && !ButtonnewGame.isVisible()) { // Если игра на паузе и кнопка новой игры не видна.
                playerSpeed = 0; // Останавливаем скорость игрока.
                jumpDownSpeed = 0; // Останавливаем скорость падения.
                parallelTransition.pause(); // Ставим параллельную анимацию на паузу.
                enemyTransition.pause(); // Ставим анимацию врага на паузу.
                ButtonnewGame.setVisible(true); // Показываем кнопку новой игры.
                ButtonExit.setVisible(true); // Показываем кнопку выхода.
                ButtonContinue.setVisible(true); // Показываем кнопку продолжения.
            } else if (!isPause && ButtonnewGame.isVisible()) { // Если игра не на паузе и кнопка новой игры видна.
                playerSpeed = 6; // Восстанавливаем скорость игрока.
                jumpDownSpeed = 7; // Восстанавливаем скорость падения.
                parallelTransition.play(); // Запускаем параллельную анимацию.
                enemyTransition.play(); // Запускаем анимацию врага.
                ButtonnewGame.setVisible(false); // Скрываем кнопку новой игры.
                ButtonExit.setVisible(false); // Скрываем кнопку выхода.
                ButtonContinue.setVisible(false); // Скрываем кнопку продолжения.
            } else if (!isPause && ButtonContinue.isVisible()) { // Если игра не на паузе и кнопка продолжения видна.
                playerSpeed = 6; // Восстанавливаем скорость игрока.
                jumpDownSpeed = 7; // Восстанавливаем скорость падения.
                parallelTransition.play(); // Запускаем параллельную анимацию.
                enemyTransition.play(); // Запускаем анимацию врага.
                ButtonnewGame.setVisible(false); // Скрываем кнопку новой игры.
                ButtonExit.setVisible(false); // Скрываем кнопку выхода.
                ButtonContinue.setVisible(false); // Скрываем кнопку продолжения.
            }

            if (player.getBoundsInParent().intersects(enemy1.getBoundsInParent()) || player.getBoundsInParent().intersects(enemy2.getBoundsInParent())) {
                loseLife(); // Проверяем пересечение игрока с врагами и вызываем метод потери жизни.
            }
        }
    };

    @FXML
    void initialize() {
        ButtonnewGame.getStyleClass().add("custom-button"); // Применяем CSS класс к кнопке новой игры.
        ButtonExit.getStyleClass().add("custom-button"); // Применяем CSS класс к кнопке выхода.
        ButtonContinue.getStyleClass().add("custom-button"); // Применяем CSS класс к кнопке продолжения.

        resultLabel.setText("Best Distance: 0 m"); // Инициализируем метку результата с лучшим расстоянием.

        ButtonnewGame.setOnAction(event -> restartGame()); // Устанавливаем действие для кнопки новой игры.
        ButtonExit.setOnAction(event -> System.exit(0)); // Устанавливаем действие для кнопки выхода.
        ButtonContinue.setOnAction(event -> resumeGame()); // Устанавливаем действие для кнопки продолжения.

        // Создаем и настраиваем анимацию для первого фона.
        TranslateTransition bgOneTransition = new TranslateTransition(Duration.millis(5000), bg1);
        bgOneTransition.setFromX(0);
        bgOneTransition.setToX(BG_WIDTH * -1);
        bgOneTransition.setInterpolator(Interpolator.LINEAR);
        bgOneTransition.setCycleCount(Animation.INDEFINITE);

        // Создаем и настраиваем анимацию для второго фона.
        TranslateTransition bgTwoTransition = new TranslateTransition(Duration.millis(5000), bg2);
        bgTwoTransition.setFromX(0);
        bgTwoTransition.setToX(BG_WIDTH * -1);
        bgTwoTransition.setInterpolator(Interpolator.LINEAR);
        bgTwoTransition.setCycleCount(Animation.INDEFINITE);

        // Создаем и настраиваем анимацию для третьего фона.
        TranslateTransition bgThreeTransition = new TranslateTransition(Duration.millis(5000), bg3);
        bgThreeTransition.setFromX(0);
        bgThreeTransition.setToX(BG_WIDTH * -1);
        bgThreeTransition.setInterpolator(Interpolator.LINEAR);
        bgThreeTransition.setCycleCount(Animation.INDEFINITE);

        // Создаем и настраиваем анимацию для четвертого фона.
        TranslateTransition bgFourTransition = new TranslateTransition(Duration.millis(5000), bg4);
        bgFourTransition.setFromX(0);
        bgFourTransition.setToX(BG_WIDTH * -1);
        bgFourTransition.setInterpolator(Interpolator.LINEAR);
        bgFourTransition.setCycleCount(Animation.INDEFINITE);

        // Создаем и настраиваем анимацию для второго врага.
        TranslateTransition newEnemyTransition = new TranslateTransition(Duration.millis(3500), enemy2);
        newEnemyTransition.setFromX(0);
        newEnemyTransition.setToX(BG_WIDTH * -1 - 100);
        newEnemyTransition.setInterpolator(Interpolator.LINEAR);
        newEnemyTransition.setCycleCount(Animation.INDEFINITE);
        newEnemyTransition.play();

        // Создаем и настраиваем анимацию для первого врага.
        enemyTransition = new TranslateTransition(Duration.millis(3500), enemy1);
        enemyTransition.setFromX(0);
        enemyTransition.setToX(BG_WIDTH * -1 - 100);
        enemyTransition.setInterpolator(Interpolator.LINEAR);
        enemyTransition.setCycleCount(Animation.INDEFINITE);
        enemyTransition.play();

        // Создаем и настраиваем параллельную анимацию для всех фонов.
        parallelTransition = new ParallelTransition(bgOneTransition, bgTwoTransition,  bgThreeTransition, bgFourTransition);
        parallelTransition.setCycleCount(Animation.INDEFINITE);
        parallelTransition.play();

        enemyTransition.setCycleCount(Animation.INDEFINITE);
        enemyTransition.play();

        timer.start(); // Запускаем AnimationTimer для игрового цикла.
    }
}









