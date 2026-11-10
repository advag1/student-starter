package edu.starter;

public abstract class Combatant {
    protected int HP;
    protected String name;
    public static final int MAX_HP = 100;

    public Combatant() {
        this.HP = 100;
        this.name = "A";
    }

    public Combatant(String name) {
        this.HP = 100;
        this.name = name;
    }

    public Combatant(int HP) {
        this.HP = HP;
        this.name = "";
    }

    public Combatant(String name, int HP) {
        this.HP = HP;
        this.name = name;
    }

    public Combatant(int HP, String name) {
        this.HP = HP;
        this.name = name;
    }

    public int getHP() {
        return this.HP;
    }

    /**
     * @pre 0 <= newHP && newHP <= MAX_HP
     * @post getHP() == newHP
     */
    public void setHP(int newHP) {
        this.HP = newHP;
    }

    public boolean isHpIllegal() {
        return this.HP <= 0;
    }

    public void combatantStatus() {
        System.out.println("Name: " + this.name + " | HP left: " + this.HP);
    }
}