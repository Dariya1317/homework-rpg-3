package com.narxoz.rpg;

import com.narxoz.rpg.adapter.EnemyCombatantAdapter;
import com.narxoz.rpg.adapter.HeroCombatantAdapter;
import com.narxoz.rpg.battle.BattleEngine;
import com.narxoz.rpg.battle.Combatant;
import com.narxoz.rpg.battle.EncounterResult;
import com.narxoz.rpg.enemy.Goblin;
import com.narxoz.rpg.enemy.Joker;
import com.narxoz.rpg.enemy.Thanos;
import com.narxoz.rpg.hero.Mage;
import com.narxoz.rpg.hero.Warrior;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== RPG Battle Engine Demo ===\n");

        System.out.println("--- Creating heroes ---");
        Warrior warrior = new Warrior("Arthas");
        Mage mage = new Mage("Jaina");
        
        System.out.println("Heroes:");
        System.out.println("  " + warrior.getName() + " (Power: " + warrior.getPower() + 
                          ", Health: " + warrior.getHealth() + ")");
        System.out.println("  " + mage.getName() + " (Power: " + mage.getPower() + 
                          ", Health: " + mage.getHealth() + ")");
        System.out.println();

        System.out.println("--- Creating enemies ---");
        Goblin goblin = new Goblin();
        Joker joker = new Joker();
        Thanos thanos = new Thanos();
        
        System.out.println("Enemies:");
        System.out.println("  " + goblin.getTitle() + " (Damage: " + goblin.getDamage() + 
                          ", Health: " + goblin.getHealth() + ")");
        System.out.println("  " + joker.getTitle() + " (Damage: variable, " + 
                          "Health: " + joker.getHealth() + ")");
        System.out.println("  " + thanos.getTitle() + " (Damage: " + thanos.getDamage() + 
                          ", Health: " + thanos.getHealth() + ")");
        System.out.println();

        System.out.println("--- Adapters ---");
        List<Combatant> heroes = new ArrayList<>();
        heroes.add(new HeroCombatantAdapter(warrior));
        heroes.add(new HeroCombatantAdapter(mage));
        System.out.println("Heroes wrapped with HeroCombatantAdapter");

        List<Combatant> enemies = new ArrayList<>();
        enemies.add(new EnemyCombatantAdapter(goblin));
        enemies.add(new EnemyCombatantAdapter(joker));
        enemies.add(new EnemyCombatantAdapter(thanos));
        System.out.println("Enemies wrapped with EnemyCombatantAdapter");
        System.out.println();

        System.out.println("--- Singleton check ---");
        BattleEngine engineA = BattleEngine.getInstance();
        BattleEngine engineB = BattleEngine.getInstance();
        System.out.println("engineA == engineB? " + (engineA == engineB));
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

        System.out.println("\n=== Demo Complete ===");
    }
}
