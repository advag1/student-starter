package edu.starter;

public abstract class Combatant {
    protected int HP;
    protected String name;

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

    public int GetHP() {
        return this.HP;
    }

    public void SetHP(int newHP) {
        this.HP = newHP;
    }

    public boolean IsHPIllegal() {
        return this.HP <= 0;
    }

    public void CombatantStatus() {
        System.out.println("Name: " + this.name + " | HP left: " + this.HP);
    }
}