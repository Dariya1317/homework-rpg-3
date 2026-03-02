package com.narxoz.rpg.enemy;

import java.util.Random;

public class Joker extends BasicEnemy{
    private Random random = new Random();
    private boolean laughing = false;
    
    public Joker() {
        super("Joker", 15, 90);
    }
    
    @Override
    public int getDamage() {
        int baseDamage = super.getDamage();
        int chaosDamage = random.nextInt(25) + 5;
        
        if (random.nextInt(100) < 20) {
            laughing = true;
            return 0;
        }
        
        laughing = false;
        return chaosDamage;
    }
    
    @Override
    public void applyDamage(int amount) {
        super.applyDamage(amount);
        
        if (random.nextInt(100) < 10) {
            System.out.println("Joker laughs like crazy");
        }
    }
    
    public boolean isLaughing() {
        return laughing;
    }
    
}
