package edu.starter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

public class StarterTest {

    @Test void t1_recoveryFrom90() {
        Player p = new Player(90, 5);
        p.receiveRecovery();
        assertEquals(100, p.getHP());
        assertEquals(5, p.getResource());
        assertEquals(10, p.getAttackDamage());
    }

    @Test void t2_recoveryAt100() {
        Player p = new Player();
        p.receiveRecovery();
        assertEquals(100, p.getHP());
        assertTrue(p.isRewardClaimed());
    }

    @Test void t3_powerGives12() {
        Player p = new Player(80, 2);
        p.receivePower();
        assertEquals(12, p.getAttackDamage());
        assertEquals(80, p.getHP());
        assertEquals(2, p.getResource());
    }

    @Test void hpAndResourceStayInBounds() {
        Player p = new Player(5, 5);
        p.setHP(-10);       assertEquals(0, p.getHP());
        p.setHP(500);       assertEquals(100, p.getHP());
        p.setResource(99);  assertEquals(10, p.getResource());
        p.setResource(-3);  assertEquals(0, p.getResource());
    }

    @Test void t5_noRewardBeforeVictory() {
        Game g = new Game();
        assertFalse(g.canClaimReward());
    }

    @Test void t6_noSecondReward() {
        Game g = new Game(100, 5, 10, 5, 45);
        g.attack();                                   // הורג את אויב 1
        assertTrue(g.canClaimReward());
        assertTrue(g.reward(new Scanner("9\n1\n")));  // קלט לא תקין ואז Recovery
        assertFalse(g.canClaimReward());
    }

    @Test void t7_lossNoReward() {
        Game g = new Game(10, 5, 30, 5, 45);
        g.defend(); g.defend();
        assertTrue(g.isGameOver());
        assertFalse(g.canClaimReward());
    }

    @Test void t9_finishingAttackNoCounterattack() {
        Game g = new Game(100, 5, 10, 5, 10);
        g.attack();
        g.reward(new Scanner("1\n"));
        g.attack();                                   // הורג את אויב 2
        assertTrue(g.isGameOver());
        assertEquals(100, g.getPlayer().getHP());
    }
}