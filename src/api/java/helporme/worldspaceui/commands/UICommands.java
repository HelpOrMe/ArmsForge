package helporme.worldspaceui.commands;

import cpw.mods.fml.common.event.FMLServerStartingEvent;
import helporme.worldspaceui.commands.actions.CreateUIAction;
import helporme.worldspaceui.commands.actions.UIListAction;
import helporme.worldspaceui.commands.actions.UISupportedListAction;
import helporme.worldspaceui.ui.UITargetBlock;

import java.util.HashMap;
import java.util.Map;

public final class UICommands
{
    protected final UICommandAction[] defaultActions = new UICommandAction[] { new UIListAction(), new UISupportedListAction(), new CreateUIAction()};
    protected final Class<? extends ICommandSupportedObj>[] defaultSupportClasses = new Class[] { UITargetBlock.class };

    protected final Map<String, UICommandAction> actions = new HashMap<>();
    public final Map<String, String> supportedClasses = new HashMap<>();

    public void register(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new UIMainCommand());

        for (UICommandAction action : defaultActions) addAction(action);
        for (Class<? extends ICommandSupportedObj> cls : defaultSupportClasses) addSupportedClass(cls);
    }

    public void addAction(UICommandAction action)
    {
        for (String name : action.getNames())
        {
            actions.put(name, action);
        }
    }

    public boolean isActionExists(String actionName)
    {
        return actions.containsKey(actionName);
    }

    public void addSupportedClass(Class<? extends ICommandSupportedObj> cls)
    {
        supportedClasses.put(cls.getTypeName(), cls.getSimpleName());
    }
}
