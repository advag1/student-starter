package edu.starter;

public class Combatant{
    protected int HP;
    protected  int resource;
    protected String name;


    public Player(){
        this.HP = 100;
        this.resource = 5;
        this.name = "A";
    }

    public Player(String name){
        this.HP = HP;
        this.resource = 0;
        this.name = name;
    }

    public Player(int HP){
        this.HP = HP;
        this.resource = 0;
        this.name = "";
    }

    public Player(String name, int HP){
        this.HP = HP;
        this.resource = 0;
        this.name = name;
    }

    public Player(int HP, int resource, String name){
        this.HP = HP;
        this.resource = resource;
        this.name = name;
    }

    public int GetHP(){
        return this.HP;
    }

    public int GetResource(){
        return this.resource;
    }

    public void SetResource(int newResource){
        this.resource = newResource;
    }

    public void SetHP(int newHP){
        this.HP = newHP;
    }

    public boolean IsHPIllegal(){
        return this.HP <= 0;
    }
    //
    public void Recover(){
        this.resource += 3;
        if (this.resource > 10){
            this.resource = 10;
        }
    }
    //

    public void PlayerStatus(){
        System.out.println("HP left: " + this.HP + ", resource left: " + this.resource);
    }

}