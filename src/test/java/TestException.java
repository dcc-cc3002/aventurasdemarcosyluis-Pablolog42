import aventurasdemarcoyluis.backend.controller.GameController;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.backend.controller.phases.FinishBattlePhase;
import aventurasdemarcoyluis.backend.controller.phases.FinishGamePhase;
import aventurasdemarcoyluis.backend.controller.phases.FinishTurnPhase;
import aventurasdemarcoyluis.backend.controller.phases.PhaseType;
import aventurasdemarcoyluis.backend.controller.phases.characterPhases.AttackPhase;
import aventurasdemarcoyluis.backend.controller.phases.characterPhases.StartPassingTurnPhase;
import aventurasdemarcoyluis.backend.controller.phases.characterPhases.WaitSelectTurnTypePhase;
import aventurasdemarcoyluis.backend.controller.phases.enemyPhases.EnemyAttackPhase;
import aventurasdemarcoyluis.backend.controller.phases.enemyPhases.EnemyAttackSetupPhase;
import aventurasdemarcoyluis.backend.controller.turns.TurnType;
import aventurasdemarcoyluis.backend.model.AttackType;
import aventurasdemarcoyluis.backend.model.EntityType;
import aventurasdemarcoyluis.backend.model.enemies.InterEnemy;
import aventurasdemarcoyluis.backend.model.items.ItemType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestException {

    public GameController controller;

    @BeforeEach
    public void setUp(){
        controller = new GameController();
        controller.setPlayer("Pablo");
    }

    @Test
    public void invalidTransitionTest() throws InvalidSelectionException {

        // We shouldn't be able to transition from the WAITSELECTTURNTYPE phase to EnemyAttackSetupPhase, as that transition
        // is invalid, according to the valid transitions diagram.
        // Thus, an InvalidTransitionException should be thrown.
        assertThrows(InvalidTransitionException.class, () -> {
            controller.tryToChangePhase(new EnemyAttackSetupPhase(controller));
        });

        controller.getCurrentPhase().toNextPhase(new WaitSelectTurnTypePhase(controller));
        controller.getCurrentPhase().selectTurnKind(TurnType.PASSING);


        // Next, let's try to make a bunch of invalid transitions, and see if it works:
        assertThrows(InvalidTransitionException.class, () -> {
            controller.tryToChangePhase(new FinishTurnPhase(controller));
        });
        assertThrows(InvalidTransitionException.class, () -> {
            controller.tryToChangePhase(new FinishGamePhase(controller));
        });
        assertThrows(InvalidTransitionException.class, () -> {
            controller.tryToChangePhase(new EnemyAttackPhase(controller));
        });
        assertThrows(InvalidTransitionException.class, () -> {
            controller.tryToChangePhase(new FinishBattlePhase(controller));
        });

        // We can actually only transition to the selected phase (passing).
        controller.getCurrentPhase().toNextPhase(new StartPassingTurnPhase(controller));

        // The passing turn is finished
        controller.getCurrentPhase().toNextPhase(new FinishTurnPhase(controller));


        assertEquals(PhaseType.FINISHTURNPHASE,controller.getCurrentPhase().getType());

    }

    @Test
    public void invalidSelectionTest(){
        controller.getCurrentPhase().toNextPhase(new WaitSelectTurnTypePhase(controller));

        // Lets empty the player's vault and try to select an Item turn.

        controller.getPlayer().addAnItem(ItemType.REDMUSHROOM,-3);
        controller.getPlayer().addAnItem(ItemType.HONEYSYRUP,-3);

        assertTrue(controller.getPlayer().getPlayerVault().isEmpty());

        // This should fail, as there are no items left to be picked.
        assertThrows(InvalidSelectionException.class, () -> {
            controller.tryToSelectNewTurnKind(TurnType.ITEM);
        });
    }

    @Test
    public void invalidAttackTest(){
        // Let's try to make an invalid attack

        InterEnemy testBoo = controller.createEnemy(EntityType.BOO,1,1,1,1,1);

        // As luis can't attack boo (and thus, there is no method that allows this)
        // The controller throws an invalid attack exception.
        Exception e = assertThrows(InvalidAttackException.class, () -> {
            controller.tryToMakeCharacterAttack(AttackType.HAMMER,controller.getPlayerMainCharacter(EntityType.LUIS), testBoo);
        });
        assertEquals("Luis can't attack boo", e.getMessage());



        // the same will happen if we try to attack with a KO character.
        controller.getPlayerMainCharacter(EntityType.MARCO).setKO(true);

        Exception e2 = assertThrows(InvalidAttackException.class, () -> {
            controller.tryToMakeCharacterAttack(AttackType.JUMP,controller.getPlayerMainCharacter(EntityType.MARCO), testBoo);
        });
        // check the exception message.
        assertEquals("Marco" + " is K.O. and can't attack",e2.getMessage());
    }

    @Test
    public void invalidTurnTest(){

    }


}
