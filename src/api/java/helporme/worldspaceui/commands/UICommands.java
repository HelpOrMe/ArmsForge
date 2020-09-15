package helporme.worldspaceui.commands;

import cpw.mods.fml.common.event.FMLServerStartingEvent;
import helporme.worldspaceui.commands.actions.CloseUIAction;
import helporme.worldspaceui.commands.actions.OpenUIAction;
import helporme.worldspaceui.commands.actions.UIListAction;
import helporme.worldspaceui.commands.actions.UIMapAction;

import java.util.HashMap;
import java.util.Map;

public final class UICommands
{
    protected final UICommandAction[] defaultActions = new UICommandAction[] {
            new UIListAction(), new OpenUIAction(), new CloseUIAction(), new UIMapAction() };

    protected final Map<String, UICommandAction> actions = new HashMap<>();

    public void register(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new UIMainCommand());

        for (UICommandAction action : defaultActions)
        {
            addAction(action);
        }
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
}
