package seedu.kitchenhelper;

import seedu.kitchenhelper.command.Command;
import seedu.kitchenhelper.command.CommandResult;
import seedu.kitchenhelper.command.ExitCommand;
import seedu.kitchenhelper.storage.Storage;
import seedu.kitchenhelper.exception.KitchenHelperException;
import seedu.kitchenhelper.object.Chore;
import seedu.kitchenhelper.object.Recipe;
import seedu.kitchenhelper.object.ingredient.Ingredient;
import seedu.kitchenhelper.parser.Parser;
import seedu.kitchenhelper.ui.Ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import seedu.kitchenhelper.storage.Storage;

public class KitchenHelper {
    
    public ArrayList<Ingredient> ingredientList = new ArrayList<>();
    public ArrayList<Recipe> recipeList = new ArrayList<>();
    public ArrayList<Chore> choreList = new ArrayList<>();
    /* Hi pls, look at this main program.
     * https://github.com/nus-cs2113-AY1920S2/personbook/blob/master/src/main/java/seedu/personbook/Main.java */
    
    private Ui ui;
    private Storage storage;
  
    private void start() {
        ui = new Ui();
        ui.showWelcomeMessage();
        storage = new Storage("outputIngredient.txt", "outputRecipe.txt",
                "outputChore.txt");
        try {
            ingredientList = new ArrayList<>(storage.getIngredientData());
            //recipeList = new ArrayList<Recipe>(storage.getRecipeData());
            choreList = new ArrayList<Chore>(storage.getChoreData());
        } catch (FileNotFoundException err) {
            //ui.errorMessage(err.toString());
            //ingredientList = new
        }
    }
    
    private void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }
    
    private void exit() {
        System.exit(0);
    }
    
    private void runCommandLoopUntilExitCommand() {
        Command command;
        String userCommandInput = "";
        
        do {
            try {
                // takes in the user's input
                userCommandInput = ui.getUserCommand();
                // parse input to return obj of the corresponding
                // type of command (i.e add/ delete/ list/ help / exit)
                command = new Parser().parseUserCommand(userCommandInput);
                CommandResult result = executeCommand(command);

                command.executeIngredientStorage(ingredientList, storage);
                command.executeChoreStorage(choreList, storage);

                ui.showResultToUser(result);
                ui.printDivider();
            } catch (KitchenHelperException e) {
                ui.printInvalidCmd();
                ui.print(e.getMessage());
                ui.printDivider();
            }
        } while (!userCommandInput.equalsIgnoreCase(ExitCommand.COMMAND_WORD));
        
    }
    
    public static void main(String[] args) throws KitchenHelperException {
        new KitchenHelper().run();
    }
    
    /**
     * Executes the command and return result.
     *
     * @param command the command being executed.
     * @return the return message of the command.
     */
    public CommandResult executeCommand(Command command) {
        try {
            // to check if you get the right object
            // System.out.println(command.getClass().getName());
            CommandResult result = command.execute(ingredientList, recipeList, choreList);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
