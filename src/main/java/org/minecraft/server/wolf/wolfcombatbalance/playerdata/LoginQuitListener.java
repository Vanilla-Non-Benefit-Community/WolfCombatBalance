package org.minecraft.server.wolf.wolfcombatbalance.playerdata;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.minecraft.server.wolf.wolfcombatbalance.ConfigReader;
import org.minecraft.server.wolf.wolfcombatbalance.Main;

public class LoginQuitListener implements Listener {
    @EventHandler
    public void playerLogin(PlayerLoginEvent e)
    {
        if(!ConfigReader.playerData.containsKey(e.getPlayer()))
        {
            new PlayerData(e.getPlayer());//避免覆写
        }
    }
    @EventHandler
    public void playerQuit(PlayerQuitEvent e)
    {
        //如果玩家离线，则调用dataDelete方法
        dataDelete(e.getPlayer());
    }
    private void dataDelete(Player p)
    //延迟60秒，删除玩家缓存数据。
    {
        new BukkitRunnable() {
            @Override
            public void run() {
                if(!ConfigReader.playerData.get(p).player.isOnline())ConfigReader.playerData.remove(p);//如果玩家不在线,则删除其数据。
            }
        }.runTaskLaterAsynchronously(Main.instance,20 * 60);
    }
}
