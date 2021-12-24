package org.minecraft.server.wolf.wolfcombatbalance.playerdata;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.minecraft.server.wolf.wolfcombatbalance.ConfigReader;
import org.minecraft.server.wolf.wolfcombatbalance.InformationDisplay;
import org.minecraft.server.wolf.wolfcombatbalance.Main;

public final class PlayerData
{
    public double nowEffectTimes = 0.0;
    public double nowEffectPercent = 0.0;
    public int threadCount = 0;
    public Player player;
    public BossBar nowEffectTimesBar;
    public PlayerData(Player p)
    {
        //为每个玩家初始化一个疲劳值显示条
        if(ConfigReader.showBar)
        {
            refreshBar();
        }
        player = p;
        ConfigReader.playerData.put(p,this);
    }
    public void playerAddTimes()
    {
        nowEffectTimes += ConfigReader.effectTimes;
        if(nowEffectTimes > ConfigReader.maxEffectTimes)nowEffectTimes = ConfigReader.maxEffectTimes;//防止叠层越界
        nowEffectPercent = (nowEffectTimes * ConfigReader.effectPercent)/100;
        //攻击消息显示
        if(ConfigReader.showMessage) InformationDisplay.showAttackMessage(player,String.format("%.1f",nowEffectTimes));
        if(ConfigReader.showBar)showAttackBar();
    }
    public void playerCoolDownTimes(BukkitRunnable timer)
    {
        if(nowEffectTimes >= 1)
        {
            nowEffectTimes -= 1;
        }
        else//小于1了，可以归零了，清除计时器任务
        {
            nowEffectTimes = 0;
            threadCount = 0;
            timer.cancel();
        }
        nowEffectPercent = (ConfigReader.effectPercent * nowEffectTimes)/100;
        //恢复消息显示
        if(ConfigReader.showMessage)InformationDisplay.showRegenMessage(player,String.format("%.1f",nowEffectTimes));
        if(ConfigReader.showBar)showRegenBar();
    }
    public void timeTask()
    {
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                //Bukkit.getLogger().info("Thread:"+threadCount);
                playerCoolDownTimes(this);
            }
        }.runTaskTimerAsynchronously(Main.instance,ConfigReader.coolDownTime,ConfigReader.coolDownTime); //(Main.instance, ConfigReader.coolDownTime) ;
    }
    private void showAttackBar()
    {
            if(!nowEffectTimesBar.getPlayers().contains(player))nowEffectTimesBar.addPlayer(player);//如果疲劳值是0，触发显示事件，则为玩家添加Boss条
            nowEffectTimesBar.setProgress(nowEffectTimes / ConfigReader.maxEffectTimes);
            nowEffectTimesBar.setVisible(true);
    }
    private void showRegenBar()
    {
        if(nowEffectTimes == 0)
        {
            nowEffectTimesBar.setProgress(0);
            nowEffectTimesBar.removePlayer(player);
        }
        else
        {
            nowEffectTimesBar.setProgress(nowEffectTimes / ConfigReader.maxEffectTimes);
        }
    }
    public void refreshBar()
    {
        if(nowEffectTimesBar != null)nowEffectTimesBar.removeAll();
        String title = ConfigReader.title;
        BarColor barColor = BarColor.valueOf(ConfigReader.color);
        BarStyle barStyle = BarStyle.valueOf("SEGMENTED_"+ConfigReader.style);
        nowEffectTimesBar = Bukkit.createBossBar(title, barColor, barStyle);
        if(nowEffectTimes != 0)
        {
            nowEffectTimesBar.addPlayer(player);
            nowEffectTimesBar.setProgress(nowEffectTimes / ConfigReader.maxEffectTimes);
        }
    }
}