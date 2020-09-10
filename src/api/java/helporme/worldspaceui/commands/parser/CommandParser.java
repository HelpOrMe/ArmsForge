package helporme.worldspaceui.commands.parser;

import helporme.worldspaceui.commands.ICommandSupportedObj;
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
import java.util.List;
import java.util.Random;


public class CommandParser
{
    public ICommandSender sender;
    public String[] args;
    public Class<?>[] targetTypes;

    private int argumentPointer = 0;

    public CommandParser(ICommandSender sender, String[] args, Class<?>[] targetTypes)
    {
        this.sender = sender;
        this.args = args;
        this.targetTypes = targetTypes;
    }

    public Object[] parse() throws ParseException
    {
        Object[] parsedArgs = new Object[args.length];
        for (int i = 0; i < targetTypes.length; i++)
        {
            try
            {
                parsedArgs[i] = parseNextArgument(targetTypes[i]);
                if (argumentPointer >= args.length)
                {
                    throw new ParseException("Unexpected argument \"" + args[args.length - 1] + "\"");
                }
            }
            catch (Exception e)
            {
                throw new ParseException(
                        "Unable to cast \"" + parsedArgs[argumentPointer] +
                        "\" to \"" + targetTypes[i].getSimpleName() + "\"");
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
        if (cls.isAssignableFrom(ICommandSupportedObj.class))
        {
            return parseNextSupportedObj(cls);
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

        double[] playerPos = new double[] {player.posX, player.posY, player.posZ};
        double[] parsedArgs = new double[3];
        for (int i = 0; i < 3; i++)
        {
            parsedArgs[i] = Double.parseDouble(args[argumentPointer + i]) + playerPos[i];
        }
        argumentPointer += 3;
        return new Vector3d(parsedArgs[0], parsedArgs[1], parsedArgs[2]);
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

    private ICommandSupportedObj parseNextSupportedObj(Class<?> cls) throws Exception
    {
        ICommandSupportedObj supportedObject = (ICommandSupportedObj)cls.newInstance();
        Class<?>[] requiredTypes = supportedObject.getRequiredArgumentTypes();
        CommandParser parser = new CommandParser(sender, popNextArgs(requiredTypes.length), requiredTypes);
        supportedObject.initFromCommand(sender, parser.parse());
        return supportedObject;
    }

    private String[] popNextArgs(int length)
    {
        String[] splitArgs = new String[length];
        if (length > 0) System.arraycopy(args, argumentPointer, splitArgs, argumentPointer + length, length);
        argumentPointer += length;
        return splitArgs;
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
