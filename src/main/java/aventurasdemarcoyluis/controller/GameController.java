package aventurasdemarcoyluis.controller;

import aventurasdemarcoyluis.controller.turns.*;
import aventurasdemarcoyluis.model.EntityType;
import aventurasdemarcoyluis.model.enemies.*;
import aventurasdemarcoyluis.model.items.InterItem;
import aventurasdemarcoyluis.model.items.ItemType;
import aventurasdemarcoyluis.model.maincharacters.AbstractMainCharacter;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;
import aventurasdemarcoyluis.model.maincharacters.Luis;
import aventurasdemarcoyluis.model.maincharacters.Marco;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class GameController {

    private String currentBattleWinner;
    private Battle currentBattle;
    private Player mainPlayer;

    private InterTurn currentTurn;
    private TurnOwner currentTurnOwner;

    private boolean winner;
    private boolean gameFinished;

    public GameController(){
        this.currentBattleWinner = null;
        this.currentBattle = null;

        // The player always plays first
        this.currentTurnOwner = TurnOwner.PLAYER;
        this.currentTurn =null;

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

    // To create an enemy with random stats.
    // The range limits for the stats are defined as follows:

    public InterEnemy createRandomStatsEnemy(@NotNull EnemyType type){

        // Generates an array with 4 random stat values
        int[] stats = new int[4];
        for (int i = 0; i <= 3; i++) { stats[i] = ThreadLocalRandom.current().nextInt(1, 15 + 1); }

        switch (type){
            // Note: MAXHP has to be grater than hp, and hp has to be grater than 1
            case GOOMBA -> { return new Goomba(stats[0],stats[1],stats[2]+1,stats[2]+2,stats[3]); }
            case BOO -> { return new Boo(stats[0],stats[1],stats[2]+1,stats[2]+2,stats[3]); }
            case SPINY -> { return new Spiny(stats[0],stats[1],stats[2]+1,stats[2]+2,stats[3]); }
        }
        return null;
    }


    public void runBattle(){
        if (this.isGameFinished()) return;
        currentBattle = new Battle(this.mainPlayer);
        currentBattle.main();
        mainPlayer.increaseBattleNumber();
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

    public String getCurrentBattleWinner() {
        return currentBattleWinner;
    }

    public Battle getCurrentBattle() {
        return currentBattle;
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

    public void useItemFromVault(@NotNull InterItem item, InterMainCharacter character){
        this.mainPlayer.useItem(item.getType(), character);
    }

    // Note: can return null if player doesn't have he requested item
    public InterItem getItemFromVault(ItemType itemType){
        return this.mainPlayer.getPlayerVault().retrieveItem(itemType);
    }

    public ArrayList<InterMainCharacter> getTurnMainCharacters(){
        return this.getCurrentTurn().getCurrentTurnMainCharaters();
    }

    public boolean getWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public void playerLvlUp(){
        this.mainPlayer.lvlUp();

    }


    public void selectTurnKind(@NotNull String selection) {
        // This switch statement sets the current turn
        switch (selection) {
                case "attack" -> {
                    AttackTurn attackTurn = new AttackTurn(this.mainPlayer);
                    this.setCurrentTurn(attackTurn);
                }
                case "item" -> {
                    // In case the player wants to use an item, but has none left.
                    if (this.mainPlayer.getPlayerVault().isEmpty()) {
                        System.out.println("You can't use an item, as you have none left!");
                        // Note: in the "flow" version (tarea 3), change this break for a "continue"
                        break;
                    }

                    ItemTurn itemTurn = new ItemTurn(this.mainPlayer);
                    this.setCurrentTurn(itemTurn);

                }
                case "passing" -> {
                    PassingTurn passingTurn = new PassingTurn(this.mainPlayer);
                    this.setCurrentTurn(passingTurn);
                }
                default -> {
                    System.out.println("Please, select a valid option");
                }
        }
    }

    public TurnOwner getCurrentTurnOwner() {
        return currentTurnOwner;
    }

    public void startCurrentTurn(){
        this.currentTurn.main();
    }

    public InterMainCharacter getPlayerMainCharacter(EntityType type){
        return type == EntityType.MARCO? this.mainPlayer.getMarco():this.mainPlayer.getLuis();
    }
}
