package edu.starter;

public class Game{
    private Player player;
    private Enemy enemy;
    private Enemy enemy2;
    private Enemy currEnemy;

    public Game(){
        this.player = new Player();
        this.enemy = new Enemy(30);
        this.enemy2 = new Enemy(30);
        this.currEnemy = enemy;
    }

    public Game(int HP, int resource, int HP2, int resource2, int HP3, int resource3){
        this.player = new Player(HP, resource);
        this.enemy = new Player(HP2, resource2);
        this.enemy2 = new Enemy(HP3, resource3);
        this.currEnemy = enemy;
    }

    public void BasicAttack(int damage){
        enemy.SetHP(currEnemy.GetHP() - damage);
        this.BackAttack(10);
        if (currEnemy.IsHPIllegal()){
            currEnemy = enemy2;
        }
        this.IsGamwOver();
        System.out.println("player attack enemy");
        this.GameStatus();
    }

    public void BackAttack(int damage){
        player.SetHP(player.GetHP() - damage);
        System.out.println("enemy attack player");
        this.GameStatus();
    }

    public void Attack(int damage){
        BasicAttack(10);
        this.IsGamwOver();
    }

    public void Special(){
        if (player.GetResource() < 3){
            System.out.println("There are not enough resources :(");
            return;
        }
        player.SetResource(player.GetResource() - 3);
        BasicAttack(15);
    }

    public void Recover(){
        player.Recover();
        this.BackAttack(10);
    }

    public void Defend(){
        player.SetHP(player.GetHP() - 5);
        this.IsGamwOver();
        this.GameStatus();
    }

    public void GameStatus(){
        player.PlayerStatus();
        enemy.PlayerStatus();
    }

    public boolean IsGamwOver(){
        if (player.IsHPIllegal()){
            return true;
        }
        if (enemy.IsHPIllegal()){
            return true;
        }
        return false;
    }

    public boolean IsCurrGameOver(){
        if (player.IsHPIllegal()){
            return true;
        }
        if (enemy.IsHPIllegal()){
            return true;
        }
        return false;
    }

    public boolean ShowGameOver(){
        if (player.IsHPIllegal()){
            System.out.println("game over, you lose :(");
        }
        if (enemy.IsHPIllegal()){
            System.out.println("game over, you won!!");
        }
        return false;
    }
}