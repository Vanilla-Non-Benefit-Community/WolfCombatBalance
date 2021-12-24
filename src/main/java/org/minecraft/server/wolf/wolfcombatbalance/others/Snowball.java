package org.minecraft.server.wolf.wolfcombatbalance.others;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.minecraft.server.wolf.wolfcombatbalance.ConfigReader;

public class Snowball implements Listener {
    @EventHandler
    public void damagedBySnowball(EntityDamageByEntityEvent e)
    {
        if(e.getDamager().getType().equals(EntityType.SNOWBALL))
        {
            e.setDamage(ConfigReader.snowballDamage);
        }
    }
}
