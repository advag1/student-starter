package edu.starter;

import java.util.Scanner;

public class Game {
    private Player player;
    private Enemy enemy;
    private Enemy enemy2;
    private Enemy currEnemy;
    private boolean firstEncounterWon = false;

    /**
     * Standard game: player 100 HP / 5 resource, enemy1 30 HP, enemy2 45 HP.
     * @post getPlayer() != null && !isFirstEncounterWon() && enemies are named enemy1, enemy2
     */
    public Game() {
        Enemy.resetCount();
        this.player = new Player();
        this.enemy = new Enemy(30);
        this.enemy2 = new Enemy(45);
        this.currEnemy = this.enemy;
    }

    /**
     * Custom game (used for boundary setups).
     * Note: resource2 is unused; the signature is kept for compatibility.
     *
     * @pre 0 < HP && HP <= Combatant.MAX_HP
     *      && 0 <= resource && resource <= Player.MAX_RESOURCE
     *      && 0 < HP2 && HP2 <= Combatant.MAX_HP
     *      && 0 < HP3 && HP3 <= Combatant.MAX_HP
     */
    public Game(int HP, int resource, int HP2, int resource2, int HP3) {
        Enemy.resetCount();
        this.player = new Player(HP, resource);
        this.enemy = new Enemy(HP2);
        this.enemy2 = new Enemy(HP3);
        this.currEnemy = this.enemy;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isFirstEncounterWon() {
        return firstEncounterWon;
    }

    /**
     * Player hits the current enemy; a surviving enemy counterattacks for 10.
     *
     * @pre !isGameOver() && 0 <= damage
     * @post current enemy HP == max(0, $old HP - damage)
     *       && if the enemy survived: player HP == max(0, $old HP - 10)
     *       && if enemy1 died: isFirstEncounterWon()
     */
    public void basicAttack(int damage) {
        System.out.println("player attack enemy");
        currEnemy.setHP(Checks.within(currEnemy.getHP() - damage, 0, Combatant.MAX_HP));
        if (!currEnemy.isHpIllegal()) {
            backAttack(10);
        } else if (currEnemy == enemy) {
            firstEncounterWon = true;
            System.out.println("Enemy 1 defeated!");
        }
        gameStatus();
    }

    /**
     * Enemy hits the player.
     *
     * @pre 0 <= damage
     * @post player HP == max(0, $old HP - damage)
     */
    public void backAttack(int damage) {
        player.setHP(Checks.within(player.getHP() - damage, 0, Combatant.MAX_HP));
        System.out.println("enemy attack player");
    }

    /**
     * @pre !isGameOver()
     * @post same as basicAttack(player.getAttackDamage())
     */
    public void attack() {
        basicAttack(player.getAttackDamage());
    }

    /** @return true if the player has enough resource for Special (cost 3) */
    public boolean canSpecial() {
        return player.getResource() >= 3;
    }

    /**
     * Special attack: costs 3 resource, deals a fixed 15 damage
     * (independent of the player's basic attack).
     *
     * @pre canSpecial() && !isGameOver()
     * @post player resource == $old resource - 3, then basicAttack(15) postcondition holds
     */
    public void special() {
        player.setResource(player.getResource() - 3);
        basicAttack(15);
    }

    /**
     * @pre !isGameOver()
     * @post player resource == min(MAX_RESOURCE, $old resource + 3)
     *       && player HP == max(0, $old HP - 10)
     */
    public void recover() {
        player.recover();
        backAttack(10);
    }

    /**
     * @pre !isGameOver()
     * @post player HP == max(0, $old HP - 5); no enemy counterattack
     */
    public void defend() {
        player.setHP(Checks.within(player.getHP() - 5, 0, Combatant.MAX_HP));
        gameStatus();
    }

    /**
     * @pre isFirstEncounterWon()
     * @post the current enemy is the fresh, untouched enemy2
     */
    public void startSecondEncounter() {
        this.currEnemy = this.enemy2;
        System.out.println("Enemy 2 appears with " + enemy2.getHP() + " HP!");
    }

    /**
     * The reward gate. The client must check this before calling reward(...).
     *
     * @return true iff Enemy 1 was defeated, the player is alive,
     *         and no reward has been claimed yet
     */
    public boolean canClaimReward() {
        return firstEncounterWon && !player.isHpIllegal() && !player.isRewardClaimed();
    }

    /**
     * Prompts for a reward between encounters, applies exactly one,
     * and starts the second encounter. Invalid input re-prompts without changing state.
     *
     * @pre canClaimReward() && scanner != null
     * @post returns true: exactly one reward was applied to the same Player object
     *       and the second encounter has started.
     *       returns false (input ended): no state changed.
     */
    public boolean reward(Scanner scanner) {
        System.out.println("\n=== VICTORY IN ENCOUNTER 1! CHOOSE YOUR REWARD ===");
        Reward chosen = null;

        while (chosen == null) {
            System.out.println("1. " + Reward.RECOVERY + " (Restore 25 HP, capped at 100)");
            System.out.println("2. " + Reward.POWER + " (Permanently increase basic Attack by 2)");
            System.out.print("Select reward (1 or 2): ");

            int choice = ConsoleChoice.read(scanner, 1, 2);
            if (choice == -1 && !scanner.hasNextLine()) {
                return false;   // EOF: do not start the second encounter
            }
            if (choice == 1) {
                chosen = Reward.RECOVERY;
            } else if (choice == 2) {
                chosen = Reward.POWER;
            } else {
                System.out.println("Invalid reward choice! Please try again without changing state.");
            }
        }

        player.applyReward(chosen);
        System.out.println(chosen + " applied!");
        startSecondEncounter();
        return true;
    }

    public void gameStatus() {
        System.out.println("--- Current Status ---");
        player.playerStatus();
        if (currEnemy != null) {
            currEnemy.combatantStatus();
        }
        System.out.println("----------------------");
    }

    /** @return true iff the player is dead, or both enemies are dead */
    public boolean isGameOver() {
        if (player.isHpIllegal()) {
            return true;
        }
        return enemy.isHpIllegal() && enemy2.isHpIllegal();
    }

    public boolean showGameOver() {
        if (player.isHpIllegal()) {
            System.out.println("game over, you lose :(");
            return true;
        }
        if (enemy.isHpIllegal() && enemy2.isHpIllegal()) {
            System.out.println("game over, you won!!");
            return true;
        }
        return false;
    }
}