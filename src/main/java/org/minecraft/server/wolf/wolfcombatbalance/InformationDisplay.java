package org.minecraft.server.wolf.wolfcombatbalance;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class InformationDisplay {
    public static void showAttackMessage(Player p, String nowEffectTimes)
    {
        p.sendMessage(ConfigReader.prefix + ConfigReader.attackMessage + nowEffectTimes);
    }
    public static void showRegenMessage(Player p,String nowEffectTimes)
    {
        p.sendMessage(ConfigReader.prefix + ConfigReader.regenMessage + nowEffectTimes);
    }
//    public static void initBossbar()
//    {
//        nowEffectTimesBar = Bukkit.createBossBar("疲劳值", BarColor.BLUE, BarStyle.SEGMENTED_6);
//    }
}
