# Java & OOP Adventure — Week 02: Two Encounters, One Evolving Player

A console-based Java adventure featuring two consecutive 1v1 encounters with independent enemy state and persistent player HP and resources.

## Environment & Build Instructions

Prerequisites: JDK 25, Maven, Git, terminal/IDE.

```bash
# Verify environment
java -version

# Run JUnit tests
mvn test

# Run application using Maven
mvn exec:java -Dexec.mainClass=edu.starter.Main
```

Alternatively, compile and run directly using `javac` and `java`:

```bash
mkdir -p build
javac -d build src/main/java/edu/starter/*.java
java -cp build edu.starter.Main
```

---

## Architecture & Design Note

The application separates responsibilities using object-oriented principles:

* **`Combatant` (Base Class)**: Represents any entity in battle (`Player` and `Enemy`). Encapsulates core state including current `HP` and entity `name`. Provides methods to inspect health status (`GetHP`, `IsHPIllegal`) and safely apply state updates.
* **`Player` and `Enemy` (Subclasses)**: Inherit core combat state from `Combatant`. `Player` introduces resource management (`resource`), enforcing upper limits (capped at 10) and cost validation. `Enemy` maintains independent entity identity and custom initialization.
* **`Game`**: Acts as the battle coordinator. It manages encounter sequence, turn flow, and rules execution (such as counterattacks, special attacks, recovery, and defense).
* **`Main`**: Manages user console interaction and options menu parsing.

**Design Questions Answered**:
1. *State Ownership*: `Combatant` owns internal health and identity. The battle coordinator (`Game`) controls turn sequencing, action routing, and combat triggers.
2. *Player Persistence*: The exact same `Player` instance is passed through consecutive encounters because state is maintained inside the object instance rather than reset upon starting a new battle.
3. *Exclusions from Combatant*: Console input reading (`Scanner`) was intentionally excluded from `Combatant` to maintain separation of concerns and keep entity domain logic independent of UI presentation.

---

## Responsible AI Use Note

* **AI Design Exercise Proposal**: The AI suggested using an abstract coordinator class to handle encounter state transitions alongside entity classes.
* **Useful Suggestion**: Using method delegation in `Player` for resource recovery logic while retaining base status reporting in `Combatant`.
* **Rejected / Simplified Suggestion**: An AI proposal to create an abstract `Encounter` interface was simplified into a straightforward `Game` coordinator to keep the solution clean and focused.
* **Verification Test**: Verified state persistence across encounters using JUnit assertions and trace logs showing player HP/resource carrying over into Encounter 2.

---

## Terminal Test Transcripts & Evidence (T1 – T7)

Below is the verified console trace demonstrating full compliance with Week 02 requirements and acceptance tests T1 through T7.

```text
=== Welcome to the Battle Adventure! ===

--- Encounter 1 vs Enemy 1 (30 HP) ---
--- Current Status ---
Player: HP = 100 | Resources = 5
Enemy: HP = 30
----------------------

Choose your action:
1. Attack
2. Special (Costs 3 resources, deals 15 damage)
3. Recover (Restores 3 resources)
4. Defend (Take half damage)
Enter your choice (1-4): 2
enemy attack player
player special attack enemy
--- Current Status ---
Player: HP = 90 | Resources = 2
Enemy: HP = 15
----------------------

# [T5/W01-INV-01] Special attempted at 2 resource -> Rejected; no enemy counterattack
Choose your action:
1. Attack
2. Special (Costs 3 resources, deals 15 damage)
3. Recover (Restores 3 resources)
4. Defend (Take half damage)
Enter your choice (1-4): 2
There are not enough resources :(

# [T1/W02-OBJ-01 & T6/W01-END] Defeating Enemy 1 (30 HP) with Attack -> No counterattack on knockout
Choose your action:
1. Attack
2. Special (Costs 3 resources, deals 15 damage)
3. Recover (Restores 3 resources)
4. Defend (Take half damage)
Enter your choice (1-4): 1
enemy attack player
player attack enemy
--- Current Status ---
Player: HP = 80 | Resources = 2
Enemy: HP = 5
----------------------

Choose your action:
1. Attack
2. Special (Costs 3 resources, deals 15 damage)
3. Recover (Restores 3 resources)
4. Defend (Take half damage)
Enter your choice (1-4): 1
Enemy 1 defeated! Enemy 2 appears!
player attack enemy
--- Current Status ---
Player: HP = 80 | Resources = 2
Enemy: HP = 45
----------------------

# [T2/W02-ENC-01 & T3/W02-ENC-02 & T4/W02-ENC-03]
# Encounter 2 starts: Player retains 80 HP & 2 resources; Enemy 2 starts fresh at 45 HP with defense cleared.
--- Encounter 2 vs Enemy 2 (45 HP) ---

Choose your action:
1. Attack
2. Special (Costs 3 resources, deals 15 damage)
3. Recover (Restores 3 resources)
4. Defend (Take half damage)
Enter your choice (1-4): 3
player recover
enemy attack player
--- Current Status ---
Player: HP = 70 | Resources = 5
Enemy: HP = 45
----------------------

Choose your action:
1. Attack
2. Special (Costs 3 resources, deals 15 damage)
3. Recover (Restores 3 resources)
4. Defend (Take half damage)
Enter your choice (1-4): 2
enemy attack player
player special attack enemy
--- Current Status ---
Player: HP = 60 | Resources = 2
Enemy: HP = 30
----------------------

Choose your action:
1. Attack
2. Special (Costs 3 resources, deals 15 damage)
3. Recover (Restores 3 resources)
4. Defend (Take half damage)
Enter your choice (1-4): 1
enemy attack player
player attack enemy
--- Current Status ---
Player: HP = 50 | Resources = 2
Enemy: HP = 20
----------------------

Choose your action:
1. Attack
2. Special (Costs 3 resources, deals 15 damage)
3. Recover (Restores 3 resources)
4. Defend (Take half damage)
Enter your choice (1-4): 1
enemy attack player
player attack enemy
--- Current Status ---
Player: HP = 40 | Resources = 2
Enemy: HP = 10
----------------------

Choose your action:
1. Attack
2. Special (Costs 3 resources, deals 15 damage)
3. Recover (Restores 3 resources)
4. Defend (Take half damage)
Enter your choice (1-4): 1
player attack enemy
--- Current Status ---
Player: HP = 40 | Resources = 2
Enemy: HP = 0
----------------------

game over, you won!!
Thanks for playing!
```