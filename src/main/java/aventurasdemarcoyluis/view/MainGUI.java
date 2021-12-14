package aventurasdemarcoyluis.view;

import aventurasdemarcoyluis.backend.controller.GameController;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.backend.controller.phases.PhaseType;
import aventurasdemarcoyluis.backend.controller.phases.characterPhases.WaitSelectItemPhase;
import aventurasdemarcoyluis.backend.controller.phases.characterPhases.WaitSelectTurnTypePhase;
import aventurasdemarcoyluis.backend.controller.turns.TurnType;
import aventurasdemarcoyluis.backend.model.EntityType;
import aventurasdemarcoyluis.backend.model.items.ItemType;
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

import javax.swing.*;
import java.io.FileInputStream;

public class MainGUI extends Application {
    private static final String RESOURCE_PATH = "src/main/resources/";
    private GameController controller;
    private final Group root = new Group();
    private TextArea mainTextArea;


    public static Label phaseLabel = new Label("Phase not selected");

    private Button start;
    private Label pasos;

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


        Button selectButton = new Button("Seleccionar");

        selectButton.setOnAction(event -> {
                    String playerName = entradaNombre.getText();
                    controller = new GameController(playerName);

                    selectButton.setDisable(true);
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


        Button button1 = new Button("Boton 1");
        Button button2 = new Button("Boton 2");
        Button button3 = new Button("Boton 3");
        Button button4 = new Button("Boton 4");
        Button button5 = new Button("Boton 5");
        Button button6 = new Button("Boton 6");




        // HANDLER DE BOTONES
        // se hace el handling de lo presionado, acorde a la fase en la que se encuente el controlador.

        button1.setOnAction(event -> {
                    switch (controller.getCurrentPhase().getType()){
                        case WAITSELECTTURNTYPEPHASE -> {
                            // en este caso, el boton 1 selecciona el turno de item
                            try {
                                controller.getCurrentPhase().selectTurnKind(TurnType.ITEM);
                                mainTextArea.appendText("\n Item Turn Selected!");

                                mainTextArea.appendText("\n Please Select which item you want " + controller.getCurrentTurnMainCharacter().getType() + " to use from the vault:");
                                mainTextArea.appendText("\n" + controller.getPlayer().getPlayerVault().toString());

                                mainTextArea.appendText("\n 1. Use HoneySyrup      2. Use Red Mushroom");


                                System.out.println(controller.getCurrentPhase().getType());
                            } catch (InvalidSelectionException e) {
                                e.printStackTrace();
                            }
                        }

                        // In this phase, button 1 behaves as a Use HoneySyrup button
                        case WAITSELECTITEMPHASE -> {
                            try {
                                controller.getCurrentPhase().selectItem(ItemType.HONEYSYRUP);
                                mainTextArea.appendText("\n Honey Syrup selected successfully!");

                                // this should be the use item phase.
                                controller.getCurrentPhase().useSelectedItem();
                                mainTextArea.appendText("\n " + controller.getCurrentTurnMainCharacter().getType() + "'s new stats are:" );
                                mainTextArea.appendText("\n"+ controller.getCurrentTurnMainCharacter());

                                mainTextArea.appendText("\n ** TURNO DE " + controller.getCurrentTurnMainCharacter().getType() + " FINALIZADO **" );

                                //this should be the finish turn Phase, and
                                controller.getCurrentPhase().toNextPhase(controller.getCurrentPhase().calculateNextPhaseAfterTurnFinished());


                            } catch (InvalidSelectionException e) {
                                // imprimo el error y no hago nada.
                                mainTextArea.appendText("\n" + e.getMessage());
                                e.printStackTrace();
                            }

                        }

                        case WAITSELECTATTACKTYPEPHASE -> {

                        }


                        case WAITSELECTENEMYTOBEATTACKEDPHASE -> {

                        }

                        default -> {}


                    }



                }
        );
        button2.setOnAction(event -> {
            switch (controller.getCurrentPhase().getType()){

                case WAITSELECTTURNTYPEPHASE -> {
                    // en este caso, el boton 2 selecciona el turno de ataque
                    try {
                        controller.getCurrentPhase().selectTurnKind(TurnType.ATTACK);
                        mainTextArea.appendText("\n Attack Turn Selected!");

                        // imprimo la lista de enemigos.
                        mainTextArea.appendText("\n Please Select which enemy you want " + controller.getCurrentTurnMainCharacter().getType() + " to attack:");
                        mainTextArea.appendText("\n" + controller.getEnemyList().toString());


                        mainTextArea.appendText("\n Select the button with the enemy number to attack from the list: ");


                    } catch (InvalidSelectionException e) {
                        e.printStackTrace();
                    }
                }


                // In this phase, button 2 behaves as a Use RedMooshroom button
                case WAITSELECTITEMPHASE -> {
                    try {
                        controller.getCurrentPhase().selectItem(ItemType.REDMUSHROOM);
                        mainTextArea.appendText("\n Red Mushroom selected succesfully!");

                    } catch (InvalidSelectionException e) {
                        // imprimo el error y no hago nada.
                        mainTextArea.appendText("\n" + e.getMessage());
                        e.printStackTrace();
                    }

                }

            }
                }
        );
        button3.setOnAction(event -> {
                }
        );
        button4.setOnAction(event -> {
                }
        );
        button5.setOnAction(event -> {
                }
        );

        button6.setOnAction(event -> {
                }
        );






        int dx = 1;

        int buttonBarHeight = 14;

        phaseLabel.setText("Fase No seleccionada");

        gridPane.add(entradaNombre,2,3,3,1);
        gridPane.add(selectButton,5,3);

        gridPane.add(mainTextArea, 1+dx, 4, 15, 10);
        gridPane.add(button1, 1+dx, buttonBarHeight, 1, 1);
        gridPane.add(button2, 2+dx, buttonBarHeight, 1, 1);
        gridPane.add(button3, 3+dx, buttonBarHeight, 1, 1);
        gridPane.add(button4, 4+dx, buttonBarHeight, 1, 1);
        gridPane.add(button5, 5+dx, buttonBarHeight, 1, 1);
        gridPane.add(button6, 6+dx, buttonBarHeight, 1, 1);

        gridPane.add(phaseLabel, 7,3);


        root.getChildren().add(gridPane);







        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startUpSequence() {

        controller.runFirstBattle();

        phaseLabel.setText(controller.getCurrentPhase().getType().toString());


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

        String inicioSelectionTurno = """

                Por favor, seleccione el tipo de turno:\s
                1. Item    2. Ataque    3. Pasar""".indent(1);

        mainTextArea.appendText(personajePrincipal);
        mainTextArea.appendText(inicioSelectionTurno);

    }


    public static void updatePhaseButton(PhaseType phaseType){
        phaseLabel.setText("Fase actual: " + phaseType.toString());
    }

}
