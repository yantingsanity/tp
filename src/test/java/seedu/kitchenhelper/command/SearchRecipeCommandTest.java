package seedu.kitchenhelper.command;

import org.junit.jupiter.api.Test;
import seedu.kitchenhelper.exception.KitchenHelperException;
import seedu.kitchenhelper.object.Chore;
import seedu.kitchenhelper.object.Recipe;
import seedu.kitchenhelper.object.ingredient.Ingredient;
import seedu.kitchenhelper.parser.Parser;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchRecipeCommandTest {
    
    @Test
    void execute_notEmpty() throws KitchenHelperException {
        ArrayList<Ingredient> ingredientArrayList = new ArrayList<>();
        ArrayList<Recipe> recipeArrayList = new ArrayList<>();
        ArrayList<Chore> choreArrayList = new ArrayList<>();
        addFirstRecipe(recipeArrayList);
        addSecondRecipe(recipeArrayList);
        String expectedOutput =
                "Here are your matching recipes in your list\r\n" + "1.Chicken Salad located at listrecipe 1\r\n"
                + "2.Chicken Stew located at listrecipe 2";
        String output = new Parser().parseUserCommand("searchrecipe chicken")
                .execute(ingredientArrayList, recipeArrayList, choreArrayList).feedbackToUser;
        assertEquals(expectedOutput, output);
    }
    
    @Test
    void execute_empty() throws KitchenHelperException {
        ArrayList<Ingredient> ingredientArrayList = new ArrayList<>();
        ArrayList<Recipe> recipeArrayList = new ArrayList<>();
        ArrayList<Chore> choreArrayList = new ArrayList<>();
        String expectedOutput = "There are no matching recipes in your list.";
        String output = new Parser().parseUserCommand("searchrecipe chicken")
                .execute(ingredientArrayList, recipeArrayList, choreArrayList).feedbackToUser;
        assertEquals(expectedOutput, output);
    }
    
    @Test
    void execute_notFound() throws KitchenHelperException {
        ArrayList<Ingredient> ingredientArrayList = new ArrayList<>();
        ArrayList<Recipe> recipeArrayList = new ArrayList<>();
        ArrayList<Chore> choreArrayList = new ArrayList<>();
        addFirstRecipe(recipeArrayList);
        String expectedOutput = "There are no matching recipes in your list.";
        String output = new Parser().parseUserCommand("searchrecipe pork")
                .execute(ingredientArrayList, recipeArrayList, choreArrayList).feedbackToUser;
        assertEquals(expectedOutput, output);
    }
    
    private void addSecondRecipe(ArrayList<Recipe> recipeArrayList) {
        Recipe newRecipe = new Recipe();
        newRecipe.recipeName = "Chicken Stew";
        ArrayList<Ingredient> recipeItems2 = newRecipe.getRecipeItem();
        recipeItems2.add(newRecipe.createIngr("Chicken Breast", "meat", 2));
        recipeItems2.add(newRecipe.createIngr("Lettuce", "vegetable", 4));
        recipeArrayList.add(newRecipe);
    }
    
    private void addFirstRecipe(ArrayList<Recipe> recipeArrayList) {
        Recipe newRecipe = new Recipe();
        newRecipe.recipeName = "Chicken Salad";
        ArrayList<Ingredient> recipeItems = newRecipe.getRecipeItem();
        recipeItems.add(newRecipe.createIngr("Chicken Breast", "meat", 2));
        recipeItems.add(newRecipe.createIngr("Lettuce", "vegetable", 4));
        recipeArrayList.add(newRecipe);
    }
}
