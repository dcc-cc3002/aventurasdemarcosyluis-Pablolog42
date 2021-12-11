package aventurasdemarcoyluis.view;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.phases.characterPhases.WaitSelectTurnTypePhase;
import aventurasdemarcoyluis.model.EntityType;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class MainGUI extends Application {
    private static final String RESOURCE_PATH = "src/main/resources/";
    private GameController controller;
    private final Group root = new Group();
    private TextArea mainTextArea;

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


        TextField entradaNombre = new TextField();
        entradaNombre.setPromptText("Ingrese su nombre...");


        Button button1 = new Button("Seleccionar");

        button1.setOnAction(event -> {
            String playerName = entradaNombre.getText();
            controller = new GameController(playerName);

            button1.setDisable(true);
            startUpSequence();
            }
        );


        primaryStage.setTitle("Las Flipantes Aventuras de Marco y Luis");
        primaryStage.setResizable(false);
        Scene scene= new Scene(root,1280,700);
        var background = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "Background.png")));
        root.getChildren().add(background);


        GridPane gridPane = new GridPane();
        gridPane.setHgap(30);
        gridPane.setVgap(30);

        mainTextArea = new TextArea();


        Button button2 = new Button("1");
        Button button3 = new Button("2");
        Button button4 = new Button("3");
        Button button5 = new Button("4");
        Button button6 = new Button("5");



        int dx = 1;

        int buttonBarHeight = 14;

        gridPane.add(entradaNombre,2,3,3,1);
        gridPane.add(button1,5,3);

        gridPane.add(mainTextArea, 1+dx, 4, 15, 10);
        gridPane.add(button2, 1+dx, buttonBarHeight, 1, 1);
        gridPane.add(button3, 2+dx, buttonBarHeight, 1, 1);
        gridPane.add(button4, 3+dx, buttonBarHeight, 1, 1);
        gridPane.add(button5, 4+dx, buttonBarHeight, 1, 1);
        gridPane.add(button6, 5+dx, buttonBarHeight, 1, 1);


        root.getChildren().add(gridPane);





        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startUpSequence() {
        controller.runFirstBattle();

        mainTextArea.appendText("Bienvenid@, " + controller.getPlayer().getPlayerName() + "!");
        controller.toNextPhase(new WaitSelectTurnTypePhase(controller));

        mainTextArea.appendText("------------------------------------ \n" );
        mainTextArea.appendText("Sus personajes principales son: \n");
        mainTextArea.appendText(controller.getPlayerMainCharacter(EntityType.MARCO).toString() + "\n");
        mainTextArea.appendText(controller.getPlayerMainCharacter(EntityType.LUIS).toString() + "\n");
        mainTextArea.appendText("------------------------------------ \n");

        mainTextArea.appendText("Su baúl de items es: \n");
        mainTextArea.appendText(controller.getPlayer().getPlayerVault().toString() + "\n");

        mainTextArea.appendText("Usted se encuentra con los siguientes enemigos salvajes: \n");
        mainTextArea.appendText(controller.getEnemyList().toString());

        selectTurnTypeSequence();

    }

    private void selectTurnTypeSequence() {
        String personajePrincipal = "Usted está jugando este turno con: " + controller.getCurrentTurnMainCharacter();

        String inicioSelectionTurno = "\n" +
                                        "-";
    }

}
