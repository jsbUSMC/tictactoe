package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

public class TicTacToeGUI extends Application {

    private GridPane createGridPaneLayout() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5,5,5,5));
        grid.setVgap(10);
        grid.setHgap(10);

        /*Canvas backdrop = new Canvas(600,600);
        GraphicsContext gc = backdrop.getGraphicsContext2D();
        Stop[] stops = new Stop[] {new Stop(0, Color.ALICEBLUE), new Stop(1, Color.BLANCHEDALMOND)};
        LinearGradient gradient = new LinearGradient(0,0,1,0,true, CycleMethod.NO_CYCLE, stops);
        gc.setFill(gradient);
        gc.fillRect(0,0,backdrop.getWidth(),backdrop.getHeight());*/


        return grid;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TicTacToe");
        Canvas backdrop = new Canvas(600,600);
        GraphicsContext gc = backdrop.getGraphicsContext2D();
        Stop[] stops = new Stop[] {new Stop(0, Color.ALICEBLUE), new Stop(1, Color.BLANCHEDALMOND)};
        LinearGradient gradient = new LinearGradient(0,0,1,0,true, CycleMethod.NO_CYCLE, stops);
        gc.setFill(gradient);
        gc.fillRect(0,0,backdrop.getWidth(),backdrop.getHeight());
        primaryStage.show();
    }
}
