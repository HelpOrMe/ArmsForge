package helporme.worldspaceui.commands.helper;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class ChatHelper
{
    public static void printSep(ICommandSender sender)
    {
        printSep(sender, '=', 30);
    }

    public static void printSep(ICommandSender sender, char chr, int len)
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++)
        {
            stringBuilder.append(chr);
        }
        print(sender, stringBuilder.toString());
    }

    public static void printError(ICommandSender sender, String text)
    {
        String message = "§c" + String.join("§c", text.split("(?<=\\G.{30})"));
        print(sender, message);
    }

    public static void print(ICommandSender sender, Object... objects)
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object object : objects)
        {
            stringBuilder.append(object.toString());
        }
        print(sender, stringBuilder.toString());
    }

    public static void print(ICommandSender sender, String text)
    {
        for (String snippet : text.split("\n"))
        {
            sender.addChatMessage(new ChatComponentText(snippet));
        }
    }
}
