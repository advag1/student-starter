package edu.starter;

import java.util.Scanner;

public class Game {
    private Player player;
    private Enemy enemy;
    private Enemy enemy2;
    private Enemy currEnemy;
    private boolean firstEncounterWon = false;

    public Game() {
        this.player = new Player();
        this.enemy = new Enemy(30);
        this.enemy2 = new Enemy(45);
        this.currEnemy = this.enemy;
    }

    public Game(int HP, int resource, int HP2, int resource2, int HP3) {
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

    public void BasicAttack(int damage) {
        currEnemy.SetHP(Math.max(0, currEnemy.GetHP() - damage));
        if (!currEnemy.IsHPIllegal()) {
            this.BackAttack(10);
        } else {
            if (currEnemy == enemy) {
                firstEncounterWon = true;
                System.out.println("Enemy 1 defeated!");
            }
        }

        this.IsGameOver();
        System.out.println("player attack enemy");
        this.GameStatus();
    }

    public void BackAttack(int damage) {
        player.SetHP(player.GetHP() - damage);
        System.out.println("enemy attack player");
    }

    public void Attack() {
        BasicAttack(player.GetAttackDamage());
    }

    public void Special() {
        if (player.GetResource() < 3) {
            System.out.println("There are not enough resources :(");
            return;
        }
        player.SetResource(player.GetResource() - 3);
        BasicAttack(15);
    }

    public void Recover() {
        player.Recover();
        this.BackAttack(10);
    }

    public void Defend() {
        player.SetHP(player.GetHP() - 5);
        this.IsGameOver();
        this.GameStatus();
    }

    public void StartSecondEncounter() {
        this.currEnemy = this.enemy2;
        System.out.println("Enemy 2 appears with 45 HP!");
    }

    public void Reward(Scanner scanner) {
        System.out.println("\n=== VICTORY IN ENCOUNTER 1! CHOOSE YOUR REWARD ===");
        boolean rewardSelected = false;

        while (!rewardSelected) {
            System.out.println("1. RECOVERY (Restore 25 HP, capped at 100)");
            System.out.println("2. POWER (Permanently increase basic Attack by 2)");
            System.out.print("Select reward (1 or 2): ");

            int rewardChoice = ConsoleChoice.read(scanner, 1, 2);
            if (rewardChoice == 1) {
                player.ReceiveRecovery();
                System.out.println("Recovery applied!");
                rewardSelected = true;
            } else if (rewardChoice == 2) {
                player.ReceivePower();
                System.out.println("Power applied!");
                rewardSelected = true;
            } else {
                System.out.println("Invalid reward choice! Please try again without changing state.");
            }
        }
        StartSecondEncounter();
    }


    public void GameStatus() {
        System.out.println("--- Current Status ---");
        player.PlayerStatus();
        if (currEnemy != null) {
            currEnemy.CombatantStatus();
        }
        System.out.println("----------------------");
    }

    public boolean IsGameOver() {
        if (player.IsHPIllegal()) {
            return true;
        }
        if (enemy.IsHPIllegal() && enemy2.IsHPIllegal()) {
            return true;
        }
        return false;
    }

    public boolean ShowGameOver() {
        if (player.IsHPIllegal()) {
            System.out.println("game over, you lose :(");
            return true;
        }
        if (enemy.IsHPIllegal() && enemy2.IsHPIllegal()) {
            System.out.println("game over, you won!!");
            return true;
        }
        return false;
    }
}