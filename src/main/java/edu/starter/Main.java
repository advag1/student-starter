package edu.starter;

import java.util.Scanner;

/** Student-owned game entry point; replace this message with your own implementation. */
public final class Main {
    public static void main(String[] args){
        System.out.println("Java project ready. Build your Week 1 adventure here.");
        
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();

        System.out.println("=== Welcome to the Battle Game! ===");
        game.GameStatus();

        while (!game.IsGamwOver()) {
            System.out.println("\nChoose your action:");
            System.out.println("1. Attack");
            System.out.println("2. Special (Costs 3 resources, deals 15 damage)");
            System.out.println("3. Recover (Restores 3 resources)");
            System.out.println("4. Defend (Take half damage)");
            
            System.out.print("Enter your choice (1-4): ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number between 1 and 4.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    game.Attack(10);
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
                    System.out.println("Invalid choice! Choose between 1 and 4.");
                    break;
            }
        }
        
        game.ShowGameOver();
        System.out.println("Thanks for playing!");
        scanner.close();
    }
}