package Section13.Burger;

public class Store {
    public static void main(String[] args) {
        Meal regularMeal = new Meal();
        System.out.println(regularMeal);
        Meal regularMealUS = new Meal(0.68);
        System.out.println(regularMealUS);
    }
}
