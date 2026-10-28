package edu.starter;

public class Game{
    private Player player;
    private Player enemy;

    public Game(){
        this.player = new Player();
        this.enemy = new Player();
    }

    public Game(int HP, int resource, int HP2, int resource2){
        this.player = new Player(HP, resource);
        this.enemy = new Player(HP2, resource2);
    }

    public void BasicAttack(int damage){
        player.attack(enemy, damage);
        enemy.attack(player, damage); //I'm not sure but I think you meant that after attack always have a back regular attack 
        if (player.IsHPIllegal()){
            this.ShowGameOver(player);
        }
        if (enemy.IsHPIllegal()){
            this.ShowGameOver(enemy);
        }
        this.GameStatus();
    }

    public void Attack(int damage){
        BasicAttack(10);
    }

    public void Special(){
        if (player.GetResource() < 3){
            System.out.println("There are not enough resources:(");
            return;
        }
        player.SetResource(3);
        BasicAttack(15);
    }

    public void Recover(){
        player.Recover();
        enemy.attack(player, 10);
        if (player.IsHPIllegal()){
            this.ShowGameOver(player);
        }
        this.GameStatus();
    }

    public void Defend(){
        enemy.attack(player, 5);
        if (player.IsHPIllegal()){
            this.ShowGameOver(player);
        }
        this.GameStatus();
    }

    public void GameStatus(){
        player.PlayerStatus();
        enemy.PlayerStatus();
    }

    public void ShowGameOver(Player losingPlayer){
        if (losingPlayer == player){
            System.out.println("game over, you lose:(");
        }
        else{
            System.out.println("game over, you won!!");
        }
    }

}