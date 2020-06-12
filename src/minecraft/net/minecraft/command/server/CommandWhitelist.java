package net.minecraft.command.server;

import com.mojang.authlib.GameProfile;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;

public class CommandWhitelist extends CommandBase
{
    /**
     * Gets the name of the command
     */
    public String getCommandName()
    {
        return "whitelist";
    }

    /**
     * Return the required permission level for this command.
     */
    public int getRequiredPermissionLevel()
    {
        return 3;
    }

    /**
     * Gets the usage string for the command.
     */
    public String getCommandUsage()
    {
        return "commands.whitelist.usage";
    }

    /**
     * Callback when the command is invoked
     */
    public void processCommand(ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length < 1)
        {
            throw new WrongUsageException("commands.whitelist.usage");
        }
        else
        {
            MinecraftServer minecraftserver = MinecraftServer.getServer();

            switch (args[0]) {
                case "on":
                    minecraftserver.getConfigurationManager().setWhiteListEnabled(true);
                    notifyOperators(sender, this, "commands.whitelist.enabled");
                    break;
                case "off":
                    minecraftserver.getConfigurationManager().setWhiteListEnabled(false);
                    notifyOperators(sender, this, "commands.whitelist.disabled");
                    break;
                case "list":
                    sender.addChatMessage(new ChatComponentTranslation("commands.whitelist.list", Integer.valueOf(minecraftserver.getConfigurationManager().getWhitelistedPlayerNames().length), Integer.valueOf(minecraftserver.getConfigurationManager().getAvailablePlayerDat().length)));
                    String[] astring = minecraftserver.getConfigurationManager().getWhitelistedPlayerNames();
                    sender.addChatMessage(new ChatComponentText(joinNiceString(astring)));
                    break;
                case "add":
                    if (args.length < 2) {
                        throw new WrongUsageException("commands.whitelist.add.usage");
                    }

                    GameProfile gameprofile = minecraftserver.getPlayerProfileCache().getGameProfileForUsername(args[1]);

                    if (gameprofile == null) {
                        throw new CommandException("commands.whitelist.add.failed", args[1]);
                    }

                    minecraftserver.getConfigurationManager().addWhitelistedPlayer(gameprofile);
                    notifyOperators(sender, this, "commands.whitelist.add.success", args[1]);
                    break;
                case "remove":
                    if (args.length < 2) {
                        throw new WrongUsageException("commands.whitelist.remove.usage");
                    }

                    GameProfile gameprofile1 = minecraftserver.getConfigurationManager().getWhitelistedPlayers().func_152706_a(args[1]);

                    if (gameprofile1 == null) {
                        throw new CommandException("commands.whitelist.remove.failed", args[1]);
                    }

                    minecraftserver.getConfigurationManager().removePlayerFromWhitelist(gameprofile1);
                    notifyOperators(sender, this, "commands.whitelist.remove.success", args[1]);
                    break;
                case "reload":
                    minecraftserver.getConfigurationManager().loadWhiteList();
                    notifyOperators(sender, this, "commands.whitelist.reloaded");
                    break;
            }
        }
    }

    public List<String> addTabCompletionOptions(String[] args, BlockPos pos)
    {
        if (args.length == 1)
        {
            return getListOfStringsMatchingLastWord(args, "on", "off", "list", "add", "remove", "reload");
        }
        else
        {
            if (args.length == 2)
            {
                if (args[0].equals("remove"))
                {
                    return getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getConfigurationManager().getWhitelistedPlayerNames());
                }

                if (args[0].equals("add"))
                {
                    return getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getPlayerProfileCache().getUsernames());
                }
            }

            return null;
        }
    }
}
