import aventurasdemarcoyluis.enemies.Boo;
import aventurasdemarcoyluis.enemies.Goomba;
import aventurasdemarcoyluis.players.Marco;

public class PlayExample {


    public static void main(String[] args) {

        Goomba testGoomba = new Goomba(1000, 10, 100, 1000, 100, 100, 1, "Gumball");
        Boo testBoo = new Boo(100, 10, 100, 1000, 1, 100, 1, "Spooky Scary Skeleton");
        Marco testMarco = new Marco(10000, 10, 10, 1000, 10, 10, 2, "Marco Polo");


        testMarco.jumpAttack(testBoo); // Let's Jump-attack Spooky Scary Skeleton to kill him
        testMarco.enemyAttacking(testBoo); // Boo tries to counter-attack, but they're dead :( .

        testGoomba.attackPlayer(testMarco); // Gumball wants revenge on marcos. So, he kills him.

        testMarco.restoreHP(3000); // Inyectamos epinefrina a Marco para que reviva. Tratamos de curar 3000 HP.
        testGoomba.playerJumpAttacking(testMarco); // After marco revives, he murders Gumball.

    }
}