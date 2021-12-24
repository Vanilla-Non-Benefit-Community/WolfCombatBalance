package org.minecraft.server.wolf.wolfcombatbalance.others;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.minecraft.server.wolf.wolfcombatbalance.ConfigReader;

public class Bed implements Listener {
    @EventHandler
    public void bedExplosionEvent(PlayerBedEnterEvent e)
    {
        if(!ConfigReader.othersBedExplosion)//设置了不允许爆炸
        {
            if(!(e.getPlayer().getWorld().getEnvironment().equals(World.Environment.NORMAL)))//非NORMAL环境，阻止床炸
            {
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void bedExplosionDamageChange(EntityDamageEvent m)
    {
        if(ConfigReader.othersBedExplosion && m.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION))//允许爆炸，且爆炸源为方块
        {
            m.setDamage(ConfigReader.othersBedExplosionDamage);//修改伤害
        }
    }
}
