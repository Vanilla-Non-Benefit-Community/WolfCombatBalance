package org.minecraft.server.wolf.wolfcombatbalance.custommob;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.minecraft.server.wolf.wolfcombatbalance.ConfigReader;

import java.util.Objects;

public class MobAttribute implements Listener {
    @EventHandler
    public void mobSummonEvent(EntitySpawnEvent e)
    {
        Entity entity = e.getEntity();
        if(entity instanceof Monster)
        {
            Monster monster = (Monster) entity;
            //获取基础属性
            double health = Objects.requireNonNull(monster.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue();
            double damage = Objects.requireNonNull(monster.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).getBaseValue();
            double speed = Objects.requireNonNull(monster.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).getBaseValue();
            //按照Config里设置的倍率修改基础属性
            Objects.requireNonNull(monster.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(health + health * ConfigReader.mobHealthChangePercent / 100);
            monster.setHealth(health + health * ConfigReader.mobHealthChangePercent / 100);
            Objects.requireNonNull(monster.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(damage + damage * ConfigReader.mobDamageChangePercent / 100);
            Objects.requireNonNull(monster.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(speed + speed * ConfigReader.mobSpeedChangePercent / 100);
            //monster.addPotionEffect(new PotionEffect(PotionEffectType.getByName(""),10,15));
            //for(String s : Main.instance.getConfig().getStringList("CustomMobs.Potions"))
            for(int k = 0 ; k < ConfigReader.mobPotionList.size() ; k++)
            {
//                Bukkit.getLogger().info("i = "+i+" INFO = "+ConfigReader.mobPotionList.get(i).toString());
                monster.addPotionEffect(ConfigReader.mobPotionList.get(k));
            }
        }
    }
}
