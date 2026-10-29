# Java & OOP Adventure — Student Starter

**Start with your own design.** The files here deliberately do not contain a game engine, combatant hierarchy, future rules, or an instructor solution.

Prerequisites: JDK 25, Maven, Git, terminal/IDE.

```
java -version
mvn test
mvn exec:java -Dexec.mainClass=edu.starter.Main

```

If the Maven exec plugin is unavailable, compile the starter directly:

```
mkdir -p build
javac -d build src/main/java/edu/starter/*.java
java -cp build edu.starter.Main

```

Choose one theme: Fantasy Dungeon, Space Skirmish, Robot Arena or Creature Tournament.
Read the workshop PDF for the current week and maintain one evolving Git repository all semester.

Utilities: `ConsoleChoice.read()` for safe numeric input, `Checks.that()` and `Checks.within()` for early tests. There are no hidden future requirements in this kit.

## Architecture & Design Note

The architecture separates responsibilities cleanly between input handling, game orchestration, and participant state:

* **`Player`**: Encapsulates participant state (`HP`, `resource`) and enforces invariants such as clamping HP bounds and validating legal resource limits.

* **`Game`**: Manages the battle flow, coordinates turns, executes specific actions (Attack, Special, Recover, Defend), and tracks victory/loss state.

* **`Main`**: Acts as the user interaction layer, parsing console input using `Scanner` and maintaining the primary game loop.

**Design Decision**: Separating state validation (`IsGamwOver`) from side effects (printing status) prevents recursive state corruption and avoids duplicate output during turn execution.

## Python vs. Java Comparison (R05)

1. **Typing**: Java enforces static typing at compile time (e.g., `int HP`), preventing type-mismatch errors before execution. Python uses dynamic typing, resolving variable types at runtime.

2. **Compilation & Execution**: Java code compiles into platform-independent Bytecode (`.class`) executed by the Java Virtual Machine (JVM). Python is an interpreted language executing source code directly via the Python interpreter.

3. **Entry Point**: Java requires a formal class-bound entry point (`public static void main(String[] args)`), whereas Python scripts execute sequentially from top to bottom.

## Responsible AI Use Note

* **Question Asked**: "How can I prevent duplicate game over messages during turn execution?"

* **Useful Suggestion**: Separating `IsGamwOver()` state validation from console printing (`System.out.println`) so the loop controls display logic.

* **Verified / Rejected Suggestion**:

  * *Verified*: Adding input validation (`scanner.hasNextInt()`) prior to consuming integers to avoid unhandled exceptions on invalid input (`W01-INV-01`).

  * *Rejected*: An AI suggestion to create an abstract `Unit` class hierarchy was rejected to keep the current Week 01 implementation minimal, explainable, and compliant with project constraints.

## Terminal Test Transcripts

### Test 1: Basic Attack Flow

```
=== Welcome to the Battle Game! ===
HP left: 100, resource left: 5
HP left: 100, resource left: 5

Choose your action:
1. Attack
2. Special (Costs 3 resources, deals 15 damage)
3. Recover (Restores 3 resources)
4. Defend (Take half damage)
Enter your choice (1-4): 1
enemy attack player
HP left: 90, resource left: 5
HP left: 90, resource left: 5
player attack enemy
HP left: 90, resource left: 5
HP left: 90, resource left: 5

```

### Test 2: Special Attack Execution & Resource Deduction

```
Choose your action:
1. Attack
2. Special (Costs 3 resources, deals 15 damage)
3. Recover (Restores 3 resources)
4. Defend (Take half damage)
Enter your choice (1-4): 2
enemy attack player
HP left: 80, resource left: 2
HP left: 85, resource left: 5
player attack enemy
HP left: 80, resource left: 2
HP left: 85, resource left: 5

```

### Test 3: Resource Recovery & Boundary Clamping

```
Choose your action:
1. Attack
2. Special (Costs 3 resources, deals 15 damage)
3. Recover (Restores 3 resources)
4. Defend (Take half damage)
Enter your choice (1-4): 3
enemy attack player
HP left: 70, resource left: 5
HP left: 85, resource left: 5

```

### Test 4: Defensive Stance (Half Damage)

```
Choose your action:
1. Attack
2. Special (Costs 3 resources, deals 15 damage)
3. Recover (Restores 3 resources)
4. Defend (Take half damage)
Enter your choice (1-4): 4
HP left: 65, resource left: 5
HP left: 85, resource left: 5

```

### Test 5: Invalid Input Handling & Game Over State

```
Choose your action:
1. Attack
2. Special (Costs 3 resources, deals 15 damage)
3. Recover (Restores 3 resources)
4. Defend (Take half damage)
Enter your choice (1-4): abc
Invalid input! Please enter a number between 1 and 4.

Enter your choice (1-4): 9
Invalid choice! Choose between 1 and 4.

...
game over, you won!!
Thanks for playing!

```