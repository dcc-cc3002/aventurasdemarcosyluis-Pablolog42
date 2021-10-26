package aventurasdemarcoyluis.controller;

import aventurasdemarcoyluis.entities.EntityType;
import aventurasdemarcoyluis.entities.enemies.*;
import aventurasdemarcoyluis.entities.maincharacters.AbstractMainCharacter;
import aventurasdemarcoyluis.entities.maincharacters.Luis;
import aventurasdemarcoyluis.entities.maincharacters.Marco;
import org.jetbrains.annotations.NotNull;


public class GameController {

    public GameController(){

    }




    public AbstractMainCharacter createMainCharacter(@NotNull EntityType type, double atk, double def, double hp, double maxHP, int fp, int maxFP, int lvl){
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










}
