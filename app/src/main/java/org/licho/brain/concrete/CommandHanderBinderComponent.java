package org.licho.brain.concrete;

import org.licho.brain.enu.SimioCommand;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class CommandHanderBinderComponent {
    private CommandHanderBinderComponent.CommandHanderBinderComponentWrapper commandHanderBinderComponentWrapper;

    private Map<Class<?>, AbsCommandHandberBinderOperator> targetCommandHandberBinderOperatorMap;

    public CommandHanderBinderComponent() {
        this.commandHanderBinderComponentWrapper =
                new CommandHanderBinderComponent.CommandHanderBinderComponentWrapper(this);
        this.targetCommandHandberBinderOperatorMap = new HashMap<>();
    }

    public class CommandHanderBinderComponentWrapper {
        private Map<SimioCommand, CommandHandlerBinder> commandHandlerBinders;
        private CommandHanderBinderComponent commandHanderBinderComponent;

        CommandHanderBinderComponentWrapper(CommandHanderBinderComponent commandHanderBinderComponent) {
            this.commandHanderBinderComponent = commandHanderBinderComponent;
            this.commandHandlerBinders = new HashMap<>();
        }
    }
}
