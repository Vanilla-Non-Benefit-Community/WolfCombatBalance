package org.minecraft.server.wolf.wolfcombatbalance;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.minecraft.server.wolf.wolfcombatbalance.playerdata.PlayerData;

import java.util.*;

public class ConfigReader {
    public static double mobHealthChangePercent;
    public static double mobDamageChangePercent;
    public static double mobSpeedChangePercent;

    public static boolean othersBedExplosion;
    public static double othersBedExplosionDamage;
    public static double othersLavaDamage;
    public static double othersFallDamage;

    public static double effectTimes;
    public static double effectPercent;
    public static double maxEffectTimes;
    public static long coolDownTime;
    public static int maxThreadCount;
    public static boolean showMessage;
    public static boolean showBar;
    public static String title;
    public static String color;
    public static int style;

    public static double ironGolemHealthMultiple;
    public static double ironGolemDamageMultiple;
    public static double ironGolemSpeedMultiple;
    public static double snowGolemHealthMultiple;
    public static double snowGolemSpeedMultiple;
    public static double snowballDamage;

    public static String prefix;
    public static String attackMessage;
    public static String regenMessage;
    public static Map<Player, PlayerData> playerData = new HashMap<>();

    public static List<PotionEffect> mobPotionList = new ArrayList<>();
    public static void configLoad()
    {
        //重载
        Bukkit.getLogger().info("[ WolfCombatBalance ] 正在重载...");
        Main.instance.reloadConfig();//先重载
        mobPotionList.clear();
        FileConfiguration config = Main.instance.getConfig();//再读取
        //CombatBalance读取
        effectTimes = config.getDouble("CombatBalance.EffectTimes",0.3);
        effectPercent = config.getDouble("CombatBalance.EffectPercent",-2.0);
        maxEffectTimes = config.getDouble("CombatBalance.MaxEffectTimes",30.0);
        coolDownTime = config.getLong("CombatBalance.CoolDownTime",60);

        maxThreadCount = config.getInt("CombatBalance.ThreadLimit",1);

        prefix = config.getString("Message.Prefix");
        attackMessage = config.getString("Message.AttackMessage");
        regenMessage = config.getString("Message.RegenMessage");
        showMessage = config.getBoolean("CombatBalance.ShowMessage",false);
        showBar = config.getBoolean("CombatBalance.Bossbar.ShowBar",true);
        title = config.getString("CombatBalance.Bossbar.Title","疲劳值");
        color = config.getString("CombatBalance.Bossbar.Color","BLUE");
        style = config.getInt("CombatBalance.Bossbar.Style",6);
        //傀儡有关数据
        ironGolemHealthMultiple = config.getDouble("CustomMob.GolemAttributeMultiple.IronGolem.Health",1.0);
        ironGolemDamageMultiple = config.getDouble("CustomMob.GolemAttributeMultiple.IronGolem.Damage",1.0);
        ironGolemSpeedMultiple = config.getDouble("CustomMob.GolemAttributeMultiple.IronGolem.Speed",1.0);
        snowGolemHealthMultiple = config.getDouble("CustomMob.GolemAttributeMultiple.SnowGolem.Health",2.0);
        snowGolemSpeedMultiple = config.getDouble("CustomMob.GolemAttributeMultiple.SnowGolem.Speed",1.0);
        snowballDamage = config.getDouble("Others.SnowballDamage",2.0);
        //Others读取
        mobHealthChangePercent = config.getDouble("CustomMob.HealthChangePercent",0.0);
        mobDamageChangePercent = config.getDouble("CustomMob.DamageChangePercent",0.0);
        mobSpeedChangePercent = config.getDouble("CustomMob.SpeedChangePercent",0.0);
        othersBedExplosion = config.getBoolean("Others.Bed.Explosion",true);
        othersBedExplosionDamage = config.getDouble("Others.Bed.ExplosionDamage",8.0);
        othersLavaDamage = config.getDouble("Others.Lava.Damage",2.0);
        othersFallDamage = config.getDouble("Other.Fall.DamagePercent",1.0);
        //药水效果读取
        for(String s : Main.instance.getConfig().getStringList("CustomMob.Potions"))
        {
            if(!s.contains(","))
            {
                Bukkit.getLogger().info("\n[ WolfCombatBalance ] 配置文件格式错误！\n");
                continue;
            }
            String[] splitStr = s.split(",");
            PotionEffectType tempEffectType = PotionEffectType.getByName(splitStr[0]);
            if(tempEffectType == null)
            {
                Bukkit.getLogger().info("\n[ WolfCombatBalance ] 效果名 "+splitStr[0]+" 不存在！\n");
                continue;
            }
            int level;
            try
            {
                level = Integer.parseInt(splitStr[1]);
                if(level<0 || level>255)
                {
                    Bukkit.getLogger().info("\n[ WolfCombatBalance ] 效果等级 "+splitStr[1]+" 数值异常！\n");
                    continue;
                }
                mobPotionList.add(new PotionEffect(Objects.requireNonNull(PotionEffectType.getByName(splitStr[0])),20 * 60 * 30,Integer.parseInt(splitStr[1])));
                Bukkit.getLogger().info("[ WolfCombatBalance ] 重载成功！");
            }
            catch (NumberFormatException ignore)
            {
                Bukkit.getLogger().info("\n[ WolfCombatBalance ] 效果等级 "+splitStr[1]+" 格式不合法！\n");
            }
        }
        //刷新所有Bossbar
        if(!playerData.values().isEmpty())
        {
            for(PlayerData pd : playerData.values())
            {
                pd.refreshBar();
            }
        }
    }
}
