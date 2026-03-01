package com.narxoz.rpg;

import com.narxoz.rpg.adapter.EnemyCombatantAdapter;
import com.narxoz.rpg.adapter.HeroCombatantAdapter;
import com.narxoz.rpg.battle.BattleEngine;
import com.narxoz.rpg.battle.Combatant;
import com.narxoz.rpg.battle.EncounterResult;
import com.narxoz.rpg.enemy.Goblin;
import com.narxoz.rpg.hero.Mage;
import com.narxoz.rpg.hero.Warrior;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== RPG Battle Engine Demo ===\n");

        System.out.println("--- Creating characters ---");
        Warrior warrior = new Warrior("Arthas");
        Mage mage = new Mage("Jaina");
        Goblin goblin1 = new Goblin();  
        Goblin goblin2 = new Goblin();  
        
        System.out.println("Heroes:");
        System.out.println("  " + warrior.getName() + " (Power: " + warrior.getPower() + 
                          ", Health: " + warrior.getHealth() + ")");
        System.out.println("  " + mage.getName() + " (Power: " + mage.getPower() + 
                          ", Health: " + mage.getHealth() + ")");
        
        System.out.println("\nEnemies:");
        System.out.println("  " + goblin1.getTitle() + " (Damage: " + goblin1.getDamage() + 
                          ", Health: " + goblin1.getHealth() + ")");
        System.out.println("  " + goblin2.getTitle() + " (Damage: " + goblin2.getDamage() + 
                          ", Health: " + goblin2.getHealth() + ")");
        System.out.println();

        System.out.println("--- Adapters ---");
        List<Combatant> heroes = new ArrayList<>();
        heroes.add(new HeroCombatantAdapter(warrior));
        heroes.add(new HeroCombatantAdapter(mage));
        System.out.println("Heroes wrapped with HeroCombatantAdapter");

        List<Combatant> enemies = new ArrayList<>();
        enemies.add(new EnemyCombatantAdapter(goblin1));
        enemies.add(new EnemyCombatantAdapter(goblin2));
        System.out.println("Enemies wrapped with EnemyCombatantAdapter");
        System.out.println();

        System.out.println("--- Singleton check ---");
        BattleEngine engineA = BattleEngine.getInstance();
        BattleEngine engineB = BattleEngine.getInstance();
        System.out.println("engineA == engineB? " + (engineA == engineB));
        System.out.println("engineA hash: " + engineA.hashCode());
        System.out.println("engineB hash: " + engineB.hashCode());
        System.out.println();

        System.out.println("=== BATTLE START ===");
        System.out.println("Heroes: " + heroes.size() + " | Enemies: " + enemies.size() + "\n");
        
        engineA.setRandomSeed(42L);
        EncounterResult result = engineA.runEncounter(heroes, enemies);

        System.out.println("\n=== BATTLE RESULT ===");
        System.out.println("Winner: " + result.getWinner());
        System.out.println("Total rounds: " + result.getRounds());
        System.out.println("\n--- Battle log ---");
        
        for (String line : result.getBattleLog()) {
            System.out.println(line);
        }

        System.out.println("\n=== Demo finished ===");
    }
}
