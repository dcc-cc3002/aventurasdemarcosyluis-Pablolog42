package aventurasdemarcoyluis.controller;

import aventurasdemarcoyluis.controller.turns.*;
import aventurasdemarcoyluis.model.EntityType;
import aventurasdemarcoyluis.model.enemies.*;
import aventurasdemarcoyluis.model.items.ItemType;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;
import aventurasdemarcoyluis.model.maincharacters.Luis;
import aventurasdemarcoyluis.model.maincharacters.Marco;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;


public class GameController {

    private String currentBattleWinner;
    private Battle currentBattle;
    private Player mainPlayer;

    private InterTurn currentTurn;
    private TurnOwner currentTurnOwner;

    private TurnOwner nextTurnOwner = null;



    private boolean winner;
    private boolean gameFinished;

    public GameController(){
        this.currentBattleWinner = null;
        this.currentBattle = null;

        // The player always plays first
        this.currentTurnOwner = TurnOwner.PLAYER;
        this.currentTurn = null;

        this.mainPlayer = new Player("J1");

        this.winner = false;
        this.gameFinished = false;
       }




    public InterMainCharacter createMainCharacter(@NotNull EntityType type, double atk, double def, double hp, double maxHP, int fp, int maxFP, int lvl){
        switch (type){
            case MARCO -> { return new Marco(atk,def,fp,maxFP,hp,maxHP,lvl); }
            case LUIS -> { return new Luis(atk,def,fp,maxFP,hp,maxHP,lvl);}
        }
        return null;
    }

    public AbstractEnemy createEnemy(@NotNull EntityType type, double atk, double def, double hp, double maxHP, int lvl){
        switch (type){
            case GOOMBA -> { return new Goomba(atk,def,hp,maxHP,lvl); }
            case BOO -> { return new Boo(atk,def,hp,maxHP,lvl); }
            case SPINY -> { return new Spiny(atk,def,hp,maxHP,lvl); }
        }
        return null;
    }


    public void setPlayer(Player player) {
        this.mainPlayer = player;
    }

    public void setPlayer(String playerName) {
        this.mainPlayer = new Player(playerName);
    }

    public void addItemToPlayer(ItemType item){
        this.mainPlayer.getPlayerVault().modifyItemAmount(1,item);
    }

    // Getters and setters

    public boolean isGameFinished(){return gameFinished;}

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }


    public void createAndSetNewBattle() {
        this.currentBattle = new Battle(this.mainPlayer);

        this.mainPlayer.increaseBattleNumber();
        // Restore the player's character's fp and hp to the max
        this.mainPlayer.getMarco().restoreHP(mainPlayer.getMarco().getMaxHP());
        this.mainPlayer.getLuis().restoreHP(mainPlayer.getLuis().getMaxHP());
        this.mainPlayer.getMarco().restoreFP(mainPlayer.getMarco().getMaxFP());
        this.mainPlayer.getLuis().restoreFP(mainPlayer.getLuis().getMaxFP());

        this.currentBattle.setRandomEnemyList();
        this.currentBattle.addInitialItems();

        if (this.mainPlayer.getBattleNumber()>1) {
            this.getPlayer().setPlayerLvl(getPlayer().getPlayerLvl() +1 );
            this.playerLvlUp();
        }

    }

    public InterTurn getCurrentTurn() {
        return currentTurn;
    }

    public TurnOwner getNextTurnOwner(){
        switch (this.getCurrentTurn().getType()){
            case ITEM,ATTACK,PASSING -> {return TurnOwner.ENEMY;}
            case ENEMY -> {return TurnOwner.PLAYER;}
        }
        return null;
    }

    public void setCurrentTurn(InterTurn currentTurn) {
        this.currentTurn = currentTurn;
    }

    public boolean getWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }





    public void playerLvlUp(){
        if(this.getPlayer().getPlayerLvl() == 1){
            //do nothing
            return;
        }
        this.mainPlayer.lvlUp();
    }


    public void selectTurnKind(@NotNull String selection) {
        // This switch statement sets the current turn
        switch (selection) {
                case "attack" -> {
                    InterTurn attackTurn = new AttackTurn(this);
                    this.setCurrentTurn(new AttackTurn(this));
                }
                case "item" -> {
                    // In case the player wants to use an item, but has none left.
                    if (this.mainPlayer.getPlayerVault().isEmpty()) {
                        System.out.println("You can't use an item, as you have none left!");
                        // Note: in the "flow" version (tarea 3), change this break for a "continue"
                        break;
                    }

                    ItemTurn itemTurn = new ItemTurn(this);
                    this.setCurrentTurn(itemTurn);

                }
                case "passing" -> {
                    InterTurn passingTurn = new PassingTurn(this);
                    this.setCurrentTurn(passingTurn);
                }
                default -> System.out.println("Please, select a valid option");
        }
    }

    public TurnOwner getCurrentTurnOwner() {
        return currentTurnOwner;
    }

    public void startCurrentTurn() throws IOException {
        this.currentTurn.main();
    }

    public InterMainCharacter getPlayerMainCharacter(EntityType type){
        return type == EntityType.MARCO? this.mainPlayer.getMarco():this.mainPlayer.getLuis();
    }

    // finishes the player turn
    public void finishTurn(){

        // This handles the finish game logic.
        // check if the player is KO after the turn.
        if(this.getPlayer().isPlayerKO()){
            this.setGameFinished(true);
            this.playerLosingSequence();
            return;
        }
        if (this.getPlayer().getBattleNumber() >= 5){
            this.setGameFinished(true);
            this.playerWinningSequence();
            return;
        }

        if(this.getCurrentTurnOwner() == TurnOwner.PLAYER){
            this.setCurrentTurn(new EnemyTurn(this));
            this.setNextTurnOwner(TurnOwner.ENEMY);
        }
        if(this.getCurrentTurnOwner() == TurnOwner.ENEMY){
            this.setNextTurnOwner(TurnOwner.PLAYER);
        }

    }

    private void playerWinningSequence() {
        this.setWinner(true);
        System.out.println("Congrats, " + this.getPlayer().getPlayerName() + ". You have won the game!");
    }

    public void playerLosingSequence() {
        this.setWinner(false);
        System.out.println("Sorry, " + this.getPlayer().getPlayerName() + ". You have lost the game :(");

    }

    public void setNextTurnOwner(TurnOwner nextTurnOwner) {
        this.nextTurnOwner = nextTurnOwner;
    }

    public Player getPlayer() {
        return mainPlayer;
    }
}
