package helporme.worldspaceui.commands.parser;

import helporme.worldspaceui.types.Vector3d;
import helporme.worldspaceui.types.Vector3i;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class CommandParser
{
    public final ICommandSender sender;
    public final String[] args;
    public final Class<?>[] targetTypes;

    private int argumentPointer = 0;

    public CommandParser(ICommandSender sender, String[] args, Class<?>[] targetTypes)
    {
        this.sender = sender;
        this.args = args;
        this.targetTypes = targetTypes;
    }

    public Object[] parse() throws ParseException
    {
        Object[] parsedArgs = new Object[targetTypes.length];
        for (int i = 0; i < targetTypes.length; i++)
        {
            int lastPointer = argumentPointer;
            try
            {
                parsedArgs[i] = parseNextArgument(targetTypes[i]);
            }
            catch (Exception e)
            {
                throw new ParseException(
                        "Unable to cast \"" + args[lastPointer] +
                        "\" to \"" + targetTypes[i].getSimpleName() + "\"");
            }
            if (argumentPointer > args.length)
            {
                throw new ParseException("Unexpected argument \"" + args[args.length - 1] + "\"");
            }
        }

        return parsedArgs;
    }

    private Object parseNextArgument(Class<?> cls) throws Exception
    {
        if (cls == Vector3d.class)
        {
            return parseNextVector3dArgument();
        }
        if (cls == Vector3i.class)
        {
            return parseNextVector3iArgument();
        }
        if (cls == EntityPlayerMP.class)
        {
            return parseNextPlayerArgument();
        }
        if (cls.isAssignableFrom(Block.class))
        {
            argumentPointer++;
            return CommandBase.getBlockByText(sender, args[argumentPointer]);
        }
        if (cls.isAssignableFrom(Item.class))
        {
            argumentPointer++;
            return CommandBase.getItemByText(sender, args[argumentPointer]);
        }
        return parseNextPrimitive(cls);
    }

    private Vector3i parseNextVector3iArgument()
    {
        Vector3d vec = parseNextVector3dArgument();
        return new Vector3i((int)vec.x, (int)vec.y, (int)vec.z);
    }

    private Vector3d parseNextVector3dArgument()
    {
        EntityPlayerMP player = CommandBase.getCommandSenderAsPlayer(sender);

        double[] position = new double[] {player.posX, player.posY, player.posZ};
        for (int i = 0; i < 3; i++)
        {
            String clearNumber = args[argumentPointer + i].replaceAll(" ", "").replaceAll("~", "");
            if (!clearNumber.isEmpty())
            {
                position[i] += Double.parseDouble(clearNumber);
            }
        }
        argumentPointer += 3;
        return new Vector3d(position[0], position[1], position[2]);
    }

    private EntityPlayerMP[] parseNextPlayerArgument()
    {
        String arg = args[argumentPointer];
        argumentPointer++;

        if (arg.startsWith("@p"))
        {
            return new EntityPlayerMP[] { CommandBase.getCommandSenderAsPlayer(sender) };
        }
        if (arg.startsWith("@a"))
        {
            return getAllPlayers().toArray(new EntityPlayerMP[0]);
        }
        if (arg.startsWith("@r"))
        {
            List<EntityPlayerMP> allPlayers = getAllPlayers();
            int randomIndex = new Random().nextInt(allPlayers.size());
            return new EntityPlayerMP[] { allPlayers.get(randomIndex) };
        }

        return new EntityPlayerMP[] { CommandBase.getPlayer(sender, arg) };
    }

    private List<EntityPlayerMP> getAllPlayers()
    {
        List<EntityPlayerMP> allPlayers = new ArrayList<>();
        for (WorldServer world : DimensionManager.getWorlds())
        {
            for (Object playerObject : world.playerEntities)
            {
                allPlayers.add((EntityPlayerMP)playerObject);
            }
        }
        return allPlayers;
    }

    private String[] popNextArgs(int length)
    {
        return Arrays.copyOfRange(args, argumentPointer, length);
    }

    private Object parseNextPrimitive(Class<?> cls)
    {
        String arg = args[argumentPointer];
        argumentPointer++;

        if (Boolean.class == cls) return Boolean.parseBoolean(arg);
        if (Byte.class == cls) return Byte.parseByte(arg);
        if (Short.class == cls) return Short.parseShort(arg);
        if (Integer.class == cls) return Integer.parseInt(arg);
        if (Long.class == cls) return Long.parseLong(arg);
        if (Float.class == cls) return Float.parseFloat(arg);
        if (Double.class == cls) return Double.parseDouble(arg);

        return arg;
    }
}
