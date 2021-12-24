package org.minecraft.server.wolf.wolfcombatbalance;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandHandler implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if(args.length == 0)
        {
            sender.sendMessage(ConfigReader.prefix + "\u00a7f缺少必要参数！");
        }
        else if(args[0].equalsIgnoreCase("reload"))//有权限才能执行
        {
            if(sender.hasPermission("wolfcombatbalance.reload"))
            {
                ConfigReader.configLoad();
                sender.sendMessage(ConfigReader.prefix + "\u00a7f重载成功");
            }
            else
            {
                sender.sendMessage(ConfigReader.prefix + "\u00a7f你没有权限这么做！");
            }
        }
        else if(args[0].equalsIgnoreCase("help"))
        {
            sender.sendMessage("");
            sender.sendMessage(ConfigReader.prefix + "\u00a7f指令列表");
            sender.sendMessage("");
            sender.sendMessage("\u00a77/wolfcombatbalance help \u00a78- \u00a77查看命令帮助");
            sender.sendMessage("\u00a77/wolfcombatbalance reload \u00a78- \u00a77重载插件[需要OP权限]");
            sender.sendMessage("");
        }
        else
        {
            sender.sendMessage(ConfigReader.prefix + "\u00a7f指令不对哦？输入 /wcb help 查看帮助");
        }
        return true;
    }
}
