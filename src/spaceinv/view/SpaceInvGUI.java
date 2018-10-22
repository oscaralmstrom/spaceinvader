package spaceinv.view;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import spaceinv.event.Event;
import spaceinv.event.EventService;
import spaceinv.model.IPositionable;
import spaceinv.model.SpaceInv;
import spaceinv.model.levels.ILevel;
import spaceinv.model.levels.Level0;
import spaceinv.model.statics.OuterSpace;

import java.util.ArrayDeque;
import java.util.Deque;

import static spaceinv.model.SpaceInv.GAME_HEIGHT;
import static spaceinv.model.SpaceInv.GAME_WIDTH;


/*
 * The GUI for the SpaceInv game.
 * See: https://www.youtube.com/watch?v=axlx3o0codc
 *
 * No application logic here just GUI and event handling
 * and input to model
 *
 * Run this to run the game
 *
 */
public class SpaceInvGUI extends Application {


    private SpaceInv spaceInv;          // Reference to the OO model
    private boolean running = false;    // Is game running?
    private Deque<ILevel> levels = new ArrayDeque<>();
    private boolean leftPressed, rightPressed;
    // ------- Keyboard handling ----------------------------------

    private void keyPressed(KeyEvent event) {
        if (!running) {
            return;
        }
        KeyCode kc = event.getCode();
        switch (kc) {
            case LEFT:
                spaceInv.moveGunLeft();
                leftPressed = true;
                break;
            case RIGHT:
                spaceInv.moveGunRight();
                rightPressed = true;
                break;
            case SPACE:
                spaceInv.fireGun();
                break;
            default:  // Nothing
        }
    }

    private void keyReleased(KeyEvent event) {
        if (!running) {
            return;
        }
        KeyCode kc = event.getCode();
        switch (kc) {
            case LEFT:
                leftPressed = false;
                if (rightPressed) {
                    spaceInv.moveGunRight();
                    break;
                }
            case RIGHT:
                rightPressed = false;
                if (leftPressed) {
                    spaceInv.moveGunLeft();
                    break;
                }
                spaceInv.stopGun();
                break;
            default: // Nothing
        }
    }

    // ---- Menu handling -----------------

    private void handleMenu(ActionEvent e) {
        MenuItem mi = (MenuItem) e.getSource();
        switch (mi.getText()) {
            case "New":
                newGame();
                break;
            case "Stop":
                stopGame();
                break;
            case "Exit":
                System.exit(0);
                break;
            case "Play":
                startMusic();
                break;
            case "No Play":
                stopMusic();
            default: // Nothing
        }
    }

    // ---------- Menu actions ---------------------

    private void newGame() {

        levels.clear();
        levels.push(new Level0());
        levels.push(new Level0());

        //spaceInv = // TODO Create the OO model by using a Level parameter

        spaceInv = new SpaceInv(levels.pop());

        renderBackground();
        timer.start();

        running = true;
        leftPressed = rightPressed = false;
    }

    private void stopGame() {
        running = false;
        timer.stop();
    }

    private void startMusic() {
        //TODO
        Assets.INSTANCE.music.play();
    }

    private void stopMusic(){
        //TODO
        Assets.INSTANCE.music.stop();
    }

    private void gameEnded() {
        switch (spaceInv.getGameState()) {
            case RUNNING:
                break;
            case WIN:
                if (!levels.isEmpty()) {
                    spaceInv.newFormation(levels.pop().getFormation());
                    break;
                }
            case LOSE:
                stopGame();
                showResults();
        }
    }

    private void showResults() {
        /*fg.clearRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        for (IPositionable d : spaceInv.getPositionables()) {
            Image i = Assets.INSTANCE.get(d.getClass());
            fg.drawImage(i, d.getX(), d.getY(), d.getWidth(), d.getHeight());
        }*/
        Image i = Assets.INSTANCE.get(OuterSpace.class);
        fg.clearRect(0, 0, GAME_WIDTH, GAME_HEIGHT);    // Clear everything
        fg.drawImage(i, 0, 0, GAME_WIDTH, GAME_HEIGHT);
        fg.setFont(Font.font("Areal", 40));
        fg.setTextAlign(javafx.scene.text.TextAlignment.CENTER);

        if (spaceInv.getGameState() == SpaceInv.GameState.WIN) {
            fg.setFill(Color.GREEN);
            fg.fillText("You won!\nYour score was: " + spaceInv.getPoints(), GAME_WIDTH / 2, GAME_HEIGHT / 2);
        } else {
            fg.setFill(Color.RED);
            fg.fillText("You lose!\nYour score was: " + spaceInv.getPoints(), GAME_WIDTH / 2, GAME_HEIGHT / 2);
        }
    }

    // --- Handling events coming form the model -----

    private void handleModelEvent(Event evt) {

        switch(evt.type)
        {
            case ROCKET_HIT_SHIP:
                Assets.INSTANCE.rocketHitShip.play();
            case ROCKET_LAUNCHED:
                Assets.INSTANCE.smallExplosion.play();
                break;
            case BOMB_DROPPED:
                Assets.INSTANCE.smallExplosion.play();
                break;
            case BOMB_HIT_GUN:
                Assets.INSTANCE.bigExplosion.play();
                break;
            case BOMB_HIT_GROUND:
                Assets.INSTANCE.bigExplosion.play();
                break;
            case SHIP_HIT_GROUND:
                Assets.INSTANCE.bigExplosion.play();
        }
    }

    // ************* Rendering and JavaFX below (nothing to do)  *************

    private void render() {
        fg.clearRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        for (IPositionable d : spaceInv.getPositionables()) {
            Image i = Assets.INSTANCE.get(d.getClass());
            fg.drawImage(i, d.getX(), d.getY(), d.getWidth(), d.getHeight());
        }
        for (IPositionable d : spaceInv.getExplosions()) {
            renderExplosion(d.getX(), d.getY());
        }
        fg.setFill(Assets.INSTANCE.colorFgText);
        fg.setFont(Font.font(Assets.INSTANCE.fontSize));
        fg.fillText(String.valueOf(spaceInv.getPoints()), 50, 50);
        fg.fillText(String.valueOf(spaceInv.getGunHealth()), 50, 70);
    }

    private void renderExplosion(double x, double y) {
        new Explosion().start(x, y, fg);
    }

    private void renderBackground() {
        bg.drawImage(Assets.INSTANCE.background, 0, 0, GAME_WIDTH, GAME_HEIGHT);
    }

    private AnimationTimer timer;
    private GraphicsContext fg;   // Foreground
    private GraphicsContext bg;   // Background
    private SpaceInvMenu menuBar;

    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();

        // Menu
        menuBar = new SpaceInvMenu(this::handleMenu);
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        root.setTop(menuBar);

        // Drawing areas
        Canvas backGround = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        bg = backGround.getGraphicsContext2D();
        Canvas foreGround = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        fg = foreGround.getGraphicsContext2D();

        Pane pane = new Pane(backGround, foreGround);
        root.setCenter(pane);

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                spaceInv.update(now);
                render();
                Event e = EventService.remove();
                if (e != null) {
                    SpaceInvGUI.this.handleModelEvent(e);
                }
                gameEnded();
            }
        };

        bg.drawImage(Assets.INSTANCE.background, 0, 0, GAME_WIDTH, GAME_HEIGHT);

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(this::keyPressed);
        scene.setOnKeyReleased(this::keyReleased);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Space Invaders");

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
