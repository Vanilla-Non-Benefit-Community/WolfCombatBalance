package org.minecraft.server.wolf.wolfcombatbalance.others;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.minecraft.server.wolf.wolfcombatbalance.ConfigReader;

public class Lava implements Listener {
    @EventHandler
    public void lavaDamage(EntityDamageEvent e)
    {
        if(e.getCause() == EntityDamageEvent.DamageCause.LAVA)//岩浆来源的伤害
        {
            e.setDamage(ConfigReader.othersLavaDamage);
        }
    }
}
