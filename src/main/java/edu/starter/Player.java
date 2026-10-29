package edu.starter;

public class Player{
    private int HP;
    private  int resource;

    public Player(){
        this.HP = 100;
        this.resource = 5;
    }

    public Player(int HP, int resource){
        this.HP = HP;
        this.resource = resource;
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