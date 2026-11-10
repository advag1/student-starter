package edu.starter;

public class Enemy extends Combatant {
    protected int resource;
    private static int count = 0;

    /** @post the next created Enemy will be named "enemy1" */
    public static void resetCount() {
        count = 0;
    }

    public Enemy() {
        super("enemy" + (++count));
        this.resource = 5;
    }

    public Enemy(int HP) {
        super("enemy" + (++count), HP);
        this.resource = 5;
    }
}