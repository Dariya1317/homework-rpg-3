package com.narxoz.rpg.enemy;

public class Thanos extends BasicEnemy {
    private int infinityStones = 0;
    private boolean snapped = false;
    
    public Thanos() {
        super("Thanos", 30, 200);
    }
    
    @Override
    public int getDamage() {
        int baseDamage = super.getDamage();
        
        int stoneBonus = infinityStones * 5;
        int totalDamage = baseDamage + stoneBonus;
        
        if (infinityStones >= 6 && !snapped) {
            snapped = true;
            return 999;
        }
        
        return totalDamage;
    }
    
    @Override
    public void applyDamage(int amount) {
        super.applyDamage(amount);
        
        if (!snapped && Math.random() < 0.2) {
            infinityStones++;
            System.out.println("Thanos found an infinity stone (Total: " + infinityStones + ")");
        }
    }
    
    public int getInfinityStones() {
        return infinityStones;
    }
    
    public boolean hasSnapped() {
        return snapped;
    }
    
}
