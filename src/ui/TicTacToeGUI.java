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

    private GridPane createGridPaneLayout(double width, double height) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(0,0,0,0));
        grid.setVgap(10);
        grid.setHgap(10);

        Canvas backdrop = new Canvas(width, height);
        GraphicsContext gc = backdrop.getGraphicsContext2D();
        Color deepBlue = Color.rgb(5,3,37);
        Color altBlue = Color.rgb(0,43,82);
        Color deepPurple = Color.rgb(43,28,51);
        Stop[] stops = new Stop[] {new Stop(0, deepBlue), new Stop(0.4, altBlue), new Stop(0.8, deepPurple)};
        LinearGradient gradient = new LinearGradient(0,0,1,0,true, CycleMethod.NO_CYCLE, stops);
        gc.setFill(gradient);
        gc.fillRect(0,0,backdrop.getWidth(),backdrop.getHeight());

        grid.getChildren().add(backdrop);


        return grid;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TicTacToe");
        GridPane grid = createGridPaneLayout(primaryStage.getMinWidth(),primaryStage.getMinHeight());
        primaryStage.setScene(new Scene(grid, 600, 600));
        primaryStage.show();
    }
}
