package edu.starter;

public class Player extends Combatant {
    protected int resource;

    public Player() {
        super("Player");
        this.resource = 5;
    }

    public Player(int HP, int resource) {
        super(HP, "Player");
        this.resource = resource;
    }

    public Player(int HP, int resource, String name) {
        super(HP, name);
        this.resource = resource;
    }

    public int GetResource() {
        return this.resource;
    }

    public void SetResource(int newResource) {
        this.resource = newResource;
    }

    public void Recover() {
        this.resource += 3;
        if (this.resource > 10) {
            this.resource = 10;
        }
    }

    public void PlayerStatus() {
        super.CombatantStatus();
        System.out.println("Player resource left: " + this.resource);
    }
}