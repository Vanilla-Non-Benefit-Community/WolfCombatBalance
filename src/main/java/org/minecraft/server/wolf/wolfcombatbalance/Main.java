package org.minecraft.server.wolf.wolfcombatbalance;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.minecraft.server.wolf.wolfcombatbalance.combatbalance.PlayerAttack;
import org.minecraft.server.wolf.wolfcombatbalance.custommob.GolemAttribute;
import org.minecraft.server.wolf.wolfcombatbalance.custommob.MobAttribute;
import org.minecraft.server.wolf.wolfcombatbalance.playerdata.LoginQuitListener;
import org.minecraft.server.wolf.wolfcombatbalance.others.*;

public final class Main extends JavaPlugin {
    public static JavaPlugin instance;
    @Override
    public void onEnable() {
        instance = this;//暴露插件实例

        saveDefaultConfig();//创建默认的配置文件
        ConfigReader.configLoad();//读取数据

        Bukkit.getPluginManager().registerEvents(new Bed(),this);
        Bukkit.getPluginManager().registerEvents(new Fall(),this);
        Bukkit.getPluginManager().registerEvents(new Lava(),this);
        Bukkit.getPluginManager().registerEvents(new MobAttribute(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerAttack(),this);
        Bukkit.getPluginManager().registerEvents(new LoginQuitListener(),this);
        Bukkit.getPluginManager().registerEvents(new GolemAttribute(),this);
        Bukkit.getPluginManager().registerEvents(new Snowball(),this);

        PluginCommand mainCommand = Bukkit.getPluginCommand("wolfcombatbalance");

        if(mainCommand != null)
        {
            mainCommand.setExecutor(new CommandHandler());
        }
    }

    @Override
    public void onDisable() {
    }
}
