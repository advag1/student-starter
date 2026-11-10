package edu.starter;

public class Player extends Combatant {
    public static final int MAX_RESOURCE = 10;
    protected int resource;
    protected int attackDamage = 10;
    protected boolean rewardClaimed = false;

    public Player() {
        super("player");
        this.resource = 5;
    }

    /**
     * @pre 0 <= HP && HP <= MAX_HP && 0 <= resource && resource <= MAX_RESOURCE
     */
    public Player(int HP, int resource) {
        super(HP, "player");
        this.resource = resource;
    }

    /**
     * @pre 0 <= HP && HP <= MAX_HP && 0 <= resource && resource <= MAX_RESOURCE
     */
    public Player(int HP, int resource, String name) {
        super(HP, name);
        this.resource = resource;
    }

    public int getResource() {
        return this.resource;
    }

    /**
     * @pre 0 <= newResource && newResource <= MAX_RESOURCE
     * @post getResource() == newResource
     */
    public void setResource(int newResource) {
        this.resource = newResource;
    }

    public int getAttackDamage() {
        return this.attackDamage;
    }

    public boolean isRewardClaimed() {
        return this.rewardClaimed;
    }

    /**
     * Restores 25 HP, capped at MAX_HP, without changing resource or attack.
     *
     * @pre !isRewardClaimed()
     * @post getHP() == Math.min(MAX_HP, $old.getHP() + 25)
     *       && getResource() == $old.getResource()
     *       && getAttackDamage() == $old.getAttackDamage()
     *       && isRewardClaimed()
     */
    public void receiveRecovery() {
        this.HP = Math.min(MAX_HP, this.HP + 25);
        this.rewardClaimed = true;
    }

    /**
     * Permanently increases basic attack damage by 2 (Special is unaffected).
     *
     * @pre !isRewardClaimed()
     * @post getAttackDamage() == $old.getAttackDamage() + 2
     *       && getHP() == $old.getHP()
     *       && getResource() == $old.getResource()
     *       && isRewardClaimed()
     */
    public void receivePower() {
        this.attackDamage += 2;
        this.rewardClaimed = true;
    }

    /**
     * Applies the chosen reward.
     *
     * @pre reward != null && !isRewardClaimed()
     * @post the postcondition of receiveRecovery() or receivePower() holds,
     *       according to the chosen reward
     */
    public void applyReward(Reward reward) {
        switch (reward) {
            case RECOVERY -> receiveRecovery();
            case POWER -> receivePower();
        }
    }

    /**
     * Restores 3 resource, capped at MAX_RESOURCE.
     *
     * @post getResource() == Math.min(MAX_RESOURCE, $old.getResource() + 3)
     */
    public void recover() {
        this.resource = Math.min(MAX_RESOURCE, this.resource + 3);
    }

    public void playerStatus() {
        super.combatantStatus();
        System.out.println("Player resource left: " + this.resource + " | Attack: " + this.attackDamage);
    }
}