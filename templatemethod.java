import java.util.Scanner;

abstract class BreakfastRecipe {
    public final void makeRecipe() {
        gatherIngredients();
        prepareFood();
        serveFood();
        enjoyMeal();
    }

    protected abstract void gatherIngredients();
    protected abstract void prepareFood();

    protected void serveFood() {
        System.out.println("Serve on plate with any condiments you want.");
    }

    protected void enjoyMeal() {
        System.out.println("Enjoy!");
    }
}

class EggsRecipe extends BreakfastRecipe {
    protected void gatherIngredients() {
        System.out.println("Get 2 eggs.");
        System.out.println("Get 1 tablespoon of butter.");
        System.out.println("Get salt.");
        System.out.println("Get pepper.");
    }

    protected void prepareFood() {
        System.out.println("Crack eggs into a bowl.");
        System.out.println("Whisk eggs.");
        System.out.println("Melt butter in pan on medium heat.");
        System.out.println("Pour in eggs and fold as they cook.");
        System.out.println("Turn off stove when done and season with salt and pepper.");
    }
}

class PancakesRecipe extends BreakfastRecipe {
    protected void gatherIngredients() {
        System.out.println("Get 1 and 1/2 cups flour.");
        System.out.println("Get 3 and 1/2 teaspoons baking powder.");
        System.out.println("Get 2 tablespoons sugar.");
        System.out.println("Get 1 1/4 cups milk.");
        System.out.println("Get 3 tablespoons melted butter.");
        System.out.println("Get 1 egg.");
    }

    protected void prepareFood() {
        System.out.println("Mix dry ingredients in a bowl.");
        System.out.println("Add milk, butter, and egg to make a smooth batter.");
        System.out.println("Heat and oil griddle on medium-high.");
        System.out.println("Pour 1/4 cup batter per pancake.");
        System.out.println("Flip when bubbles form and cook until golden.");
    }
}

abstract class BeverageRecipe {
    public final void makeBeverage() {
        boilWater();
        brew();
        pourIntoCup();
        addCondiments();
    }

    protected abstract void brew();
    protected void boilWater() { System.out.println("Boil water."); }
    protected void pourIntoCup() { System.out.println("Pour into cup."); }
    protected void addCondiments() {}
}

class CoffeeRecipe extends BeverageRecipe {
    private boolean withMilkAndSugar;
    public CoffeeRecipe(boolean withMilkAndSugar) { this.withMilkAndSugar = withMilkAndSugar; }

    protected void brew() { System.out.println("Drip hot water through ground coffee."); }

    protected void addCondiments() {
        if (withMilkAndSugar) System.out.println("Add milk and sugar.");
    }
}

class TeaRecipe extends BeverageRecipe {
    protected void brew() { System.out.println("Steep the tea."); }
}

public class TemplateMethodRecipe {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        System.out.println("Hello! We are going to learn how to make breakfast!");
        System.out
