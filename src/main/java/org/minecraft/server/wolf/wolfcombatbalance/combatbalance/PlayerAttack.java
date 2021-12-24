package org.minecraft.server.wolf.wolfcombatbalance.combatbalance;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.minecraft.server.wolf.wolfcombatbalance.ConfigReader;

public class PlayerAttack implements Listener
{
    @EventHandler
    public void playerAttackEvent(EntityDamageByEntityEvent e)
    {
        if(e.getDamager() instanceof Player)
        {
            Player player = (Player) e.getDamager();
            if(ConfigReader.playerData.get(player).threadCount+1 <= ConfigReader.maxThreadCount)
            {
                ConfigReader.playerData.get(player).threadCount+=1;
                ConfigReader.playerData.get(player).timeTask();
            }
            ConfigReader.playerData.get(player).playerAddTimes();
            double realDamagePercent = 1 + ConfigReader.playerData.get(player).nowEffectPercent;
            e.setDamage(e.getDamage() * realDamagePercent);
        }
    }
}
