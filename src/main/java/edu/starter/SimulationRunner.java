package edu.starter;

import java.util.Scanner;

public final class SimulationRunner {
    
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("  AUTOMATED SIMULATION: SCENARIO 1 (POWER)");
        System.out.println("=========================================");
        runAutomatedGame("2\n1\n1\n2\n");

        System.out.println("\n=========================================");
        System.out.println("  AUTOMATED SIMULATION: SCENARIO 2 (RECOVERY)");
        System.out.println("=========================================");
        runAutomatedGame("2\n1\n1\n1\n");

        System.out.println("\n=========================================");
        System.out.println("  AUTOMATED SIMULATION: SCENARIO 3 (LOSS T7)");
        System.out.println("=========================================");
        runAutomatedGame("4\n4\n4\n4\n4\n4\n4\n4\n4\n4\n");
    }

    private static void runAutomatedGame(String simulatedInputs) {
        Scanner scanner = new Scanner(simulatedInputs);
        Game game = new Game();

        System.out.println("=== Welcome to the Battle Adventure! ===");
        System.out.println("\n--- Encounter 1 vs Enemy 1 (30 HP) ---");
        game.gameStatus();

        while (!game.isGameOver() && !game.isFirstEncounterWon()) {
            if (!scanner.hasNextLine()) break;
            int choice = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.println("\nChoose your action:");
            System.out.println("Enter your choice (1-4): " + choice);
            
            executeAction(game, choice);
        }

        if (game.isGameOver()) {
            game.showGameOver();
            System.out.println("Thanks for playing!");
            return;
        }

        if (game.isFirstEncounterWon()) {
            game.reward(scanner);
        }

        if (!game.isGameOver()) {
            System.out.println("\n--- Encounter 2 vs Enemy 2 (45 HP) ---");
            game.gameStatus();

            while (!game.isGameOver()) {
                if (!scanner.hasNextLine()) break;
                int choice = Integer.parseInt(scanner.nextLine().trim());
                
                System.out.println("\nChoose your action:");
                System.out.println("Enter your choice (1-4): " + choice);
                
                executeAction(game, choice);
            }
            game.showGameOver();
        }
        System.out.println("Thanks for playing!");
    }

    private static void executeAction(Game game, int choice) {
        switch (choice) {
            case 1:
                game.attack();
                break;
            case 2:
                game.special();
                break;
            case 3:
                game.recover();
                break;
            case 4:
                game.defend();
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }
}