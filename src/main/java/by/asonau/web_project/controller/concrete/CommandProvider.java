package by.asonau.web_project.controller.concrete;

import java.util.HashMap;
import java.util.Map;

import by.asonau.web_project.controller.concrete.impl.*;

public final class CommandProvider {

    private final Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.DO_AUTH, new DoAuth());
        commands.put(CommandName.DO_REGISTRATION, new DoRegistration());
        commands.put(CommandName.DO_ADD_NEWS, new DoAddNews());
        commands.put(CommandName.DO_DELETE_NEWS, new DoDeleteNews());
        commands.put(CommandName.DO_EDIT_NEWS, new DoEditNews());
        commands.put(CommandName.DO_EDIT_ACCOUNT_PAGE, new DoEditAccountPage());

        commands.put(CommandName.GO_TO_INDEX_PAGE, new GoToIndexPage());
        commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
        commands.put(CommandName.GO_TO_AUTHENTICATION_PAGE, new GoToAuthenticationPage());
        commands.put(CommandName.GO_TO_NEWS_PAGE, new GoToNewsPage());
        commands.put(CommandName.GO_TO_NEWS_BY_CATEGORY_PAGE, new GoToNewsByCategoryPage());
        commands.put(CommandName.GO_TO_ACCOUNT_PAGE, new GoToAccountPage());
        commands.put(CommandName.GO_TO_ADD_NEWS_PAGE, new GoToAddNewsPage());
        commands.put(CommandName.GO_TO_MY_NEWS_PAGE, new GoToMyNewsPage());
        commands.put(CommandName.GO_TO_EDIT_NEWS_PAGE, new GoToEditNewsPage());
        commands.put(CommandName.GO_TO_EDIT_ACCOUNT_PAGE, new GoToEditAccountPage());

        commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
        commands.put(CommandName.LOGOUT, new Logout());
        commands.put(CommandName.GENERATE_ERROR, new GenerateError());
    }

    public Command takeCommand(String userCommand) {
        CommandName commandName;
        Command command;

        System.out.println("Attempting to take command: " + userCommand);

        try {
            commandName = CommandName.valueOf(userCommand.toUpperCase());
            command = commands.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            command = commands.get(CommandName.GENERATE_ERROR);
        }
        return command;
    }
}
