package edu.starter;

public class Player extends Combatant {
    protected int resource;
    protected int attackDamage = 10;
    protected boolean rewardClaimed = false;

    public Player() {
        super("player");
        this.resource = 5;
    }

    public Player(int HP, int resource) {
        super(HP, "player");
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

    public int GetAttackDamage() {
        return this.attackDamage;
    }

    public boolean IsRewardClaimed() {
        return this.rewardClaimed;
    }

    public void ReceiveRecovery() {
        if (this.rewardClaimed) {
            return;
        }
        this.HP = Math.min(100, this.HP + 25);
        this.rewardClaimed = true;
    }

    public void ReceivePower() {
        if (this.rewardClaimed) {
            return;
        }
        this.attackDamage += 2;
        this.rewardClaimed = true;
    }

    public void Recover() {
        this.resource += 3;
        if (this.resource > 10) {
            this.resource = 10;
        }
    }

    public void PlayerStatus() {
        super.CombatantStatus();
        System.out.println("Player resource left: " + this.resource + " | Attack: " + this.attackDamage);
    }
}