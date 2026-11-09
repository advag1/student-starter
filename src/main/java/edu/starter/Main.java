package edu.starter;

import java.util.Scanner;

public final class Main {
    public static void main(String[] args) {
        System.out.println("=== Welcome to the Battle Adventure! ===");
        
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();

        // --- Encounter 1 ---
        System.out.println("\n--- Encounter 1 vs Enemy 1 (30 HP) ---");
        game.GameStatus();

        while (!game.IsGameOver() && !game.isFirstEncounterWon()) {
            printMenu();
            
            int choice = ConsoleChoice.read(scanner, 1, 4);
            if (choice == -1) {
                System.out.println("Invalid input! Please enter a number between 1 and 4.");
                continue;
            }

            executeAction(game, choice);
        }

        if (game.IsGameOver()) {
            game.ShowGameOver();
            System.out.println("Thanks for playing!");
            return;
        }

        if (game.isFirstEncounterWon()) {
            game.Reward(scanner);
        }

        // --- Encounter 2 ---
        System.out.println("\n--- Encounter 2 vs Enemy 2 (45 HP) ---");
        game.GameStatus();

        while (!game.IsGameOver()) {
            printMenu();
            
            int choice = ConsoleChoice.read(scanner, 1, 4);
            if (choice == -1) {
                System.out.println("Invalid input! Please enter a number between 1 and 4.");
                continue;
            }

            executeAction(game, choice);
        }

        game.ShowGameOver();
        System.out.println("Thanks for playing!");
    }

    private static void printMenu() {
        System.out.println("\nChoose your action:");
        System.out.println("1. Attack");
        System.out.println("2. Special (Costs 3 resources, deals 15 damage)");
        System.out.println("3. Recover (Restores resources)");
        System.out.println("4. Defend (Take damage)");
        System.out.print("Enter your choice (1-4): ");
    }

    private static void executeAction(Game game, int choice) {
        switch (choice) {
            case 1:
                game.Attack();
                break;
            case 2:
                game.Special();
                break;
            case 3:
                game.Recover();
                break;
            case 4:
                game.Defend();
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }
}