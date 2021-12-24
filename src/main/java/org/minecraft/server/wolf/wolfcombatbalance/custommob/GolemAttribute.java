package org.minecraft.server.wolf.wolfcombatbalance.custommob;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Snowman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.minecraft.server.wolf.wolfcombatbalance.ConfigReader;

import java.util.Objects;

public class GolemAttribute implements Listener {
    @EventHandler
    public void golemSummonEvent(EntitySpawnEvent e)
    {
        if(e.getEntityType().equals(EntityType.IRON_GOLEM))
        {
            IronGolem ironGolem = (IronGolem)e.getEntity();

            double health = Objects.requireNonNull(ironGolem.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue();
            Objects.requireNonNull(ironGolem.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(health * ConfigReader.ironGolemHealthMultiple);
            ironGolem.setHealth(Objects.requireNonNull(ironGolem.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue());
            double damage = Objects.requireNonNull(ironGolem.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).getBaseValue();
            Objects.requireNonNull(ironGolem.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(damage * ConfigReader.ironGolemDamageMultiple);

            double speed = Objects.requireNonNull(ironGolem.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).getBaseValue();
            Objects.requireNonNull(ironGolem.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(speed * ConfigReader.ironGolemSpeedMultiple);
        }
        if(e.getEntityType().equals(EntityType.SNOWMAN))
        {
            Snowman snowman = (Snowman) e.getEntity();

            double health = Objects.requireNonNull(snowman.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue();
            Objects.requireNonNull(snowman.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(health * ConfigReader.snowGolemHealthMultiple);
            snowman.setHealth(Objects.requireNonNull(snowman.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue());
            double speed = Objects.requireNonNull(snowman.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).getBaseValue();
            Objects.requireNonNull(snowman.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(speed * ConfigReader.snowGolemSpeedMultiple);
        }
    }
}
