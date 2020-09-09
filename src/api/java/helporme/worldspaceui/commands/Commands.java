package helporme.worldspaceui.commands;

import cpw.mods.fml.common.event.FMLServerStartingEvent;
import helporme.worldspaceui.commands.actions.UIListAction;
import helporme.worldspaceui.ui.UITargetBlock;

import java.util.HashMap;
import java.util.Map;

public final class Commands
{
    protected final UICommandAction[] defaultActions = new UICommandAction[] { new UIListAction() };
    protected final Class<? extends ICommandSupported>[] defaultSupportClasses = new Class[] { UITargetBlock.class };

    public final Map<String, UICommandAction> actions = new HashMap<>();
    public final Map<String, String> supportedClasses = new HashMap<>();

    public void register(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new UICommand());

        for (UICommandAction action : defaultActions) addAction(action);
        for (Class<? extends ICommandSupported> cls : defaultSupportClasses) addSupportedClass(cls);
    }

    public void addAction(UICommandAction action)
    {
        for (String name : action.getNames())
        {
            actions.put(name, action);
        }
    }

    public void addSupportedClass(Class<? extends ICommandSupported> cls)
    {
        supportedClasses.put(cls.getTypeName(), cls.getSimpleName());
    }
}
