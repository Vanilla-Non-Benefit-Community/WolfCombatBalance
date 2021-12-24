package org.minecraft.server.wolf.wolfcombatbalance.others;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.minecraft.server.wolf.wolfcombatbalance.ConfigReader;

public class Fall implements Listener {
    @EventHandler
    public void playerFallingEvent(EntityDamageEvent e)
    {
        if(e.getEntity() instanceof Player && e.getCause() == EntityDamageEvent.DamageCause.FALL)//是玩家 受到摔落伤害
        {
            double fallDamage = e.getDamage();
            e.setDamage(fallDamage * ConfigReader.othersFallDamage);
        }
    }
}
