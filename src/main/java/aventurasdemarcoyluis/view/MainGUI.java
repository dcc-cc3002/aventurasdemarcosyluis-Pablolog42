package aventurasdemarcoyluis.view;

import aventurasdemarcoyluis.controller.GameController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class MainGUI extends Application {
    private static final String RESOURCE_PATH = "src/main/resources/";
    private final GameController controller = new GameController();
    private final Group root = new Group();
    private Button start;
    private Label pasos;
    private Label phaseLabel;
    private Label ownerLabel;
    private Label opponentLabel;
    private Label winnerLabel;
    private Label actualUnitLabel;
    private Label NormaGoal;
    private Label stars;
    private Label chapter;
    private Label victories;

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception if something goes wrong
     */
    @Override
    public void start(Stage primaryStage) throws Exception {


        primaryStage.setTitle("Las Flipantes Aventuras de Marco y Luis");
        primaryStage.setResizable(false);
        Scene scene= new Scene(root,1280,700);
        var background = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "Background.png")));
        root.getChildren().add(background);




        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
