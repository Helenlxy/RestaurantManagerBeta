package dataModel;

import java.util.ArrayList;

/**
 * A ColdDrink, one kind of dishes serving in this restaurant.
 *
 * <p>size The string represents the size of this cold drink, default is Size-M, medium and could be
 * Size-L, large.
 */
public class ColdDrink extends Dish {

  private String size = "Size-M"; // M or L

  /**
   * Creates a ColdDrink object based on given Recipe.
   *
   * @param r a Recipe object represents the specified recipe.
   */
  public ColdDrink(Recipe r) {
    super(r);
  }

  /**
   * Creates a ColdDrink object based on given Recipe and toppings.
   *
   * @param r a Recipe object represents the specified recipe.
   * @param toppings ArrayList of topping Recipes for this ColdDrink.
   */
  public ColdDrink(Recipe r, ArrayList<Recipe> toppings) {
    super(r);
    for (Recipe toppingRecipe : toppings) {
      if (toppingRecipe.getRefID() >= 900) {
        addIngredients(toppingRecipe.getIngredients());
        setName(getName() + " with " + toppingRecipe.getName());
        setPrice(getPrice() + toppingRecipe.getPrice());
      }
    }
  }

  /**
   * Creates a ColdDrink object based on given Recipe, toppings and the size.
   *
   * @param r a Recipe object represents the specified recipe.
   * @param toppings ArrayList of topping Recipes for this ColdDrink.
   * @param size string represents the size of this cold drink.
   */
  public ColdDrink(Recipe r, ArrayList<Recipe> toppings, String size) {
    super(r);
    setSize(size);
    for (Recipe toppingRecipe : toppings) {
      if (toppingRecipe.getRefID() >= 900) {
        addIngredients(toppingRecipe.getIngredients());
        setName(getName() + System.lineSeparator() + " with " + toppingRecipe.getName());
        setPrice(getPrice() + toppingRecipe.getPrice());
      }
    }
  }

  /**
   * Gets the size of this ColdDrink.
   *
   * @return a string represents the size.
   */
  public String getSize() {
    return size;
  }

  /**
   * Sets the size of this ColdDrink and change the property based on it.
   *
   * @param size a string represents the size.
   */
  public void setSize(String size) {
    this.size = size;
    changeProperty(size);
  }

  /**
   * Helper method for resetting the price and ingredients.
   *
   * @param size a string represents the size.
   */
  private void changeProperty(String size) {
    switch (size) {
      case "Size-M":
        setPrice(getPrice());
        getIngredients().put(IngredientsManager.getIngredient("cup (M)"), 1.0);
        break;
      case "Size-L":
        setPrice(Math.round(getPrice() * 1.4 * 10.0) / 10.0);
        for (Ingredient i : getIngredients().keySet()) {
          getIngredients().replace(i, getIngredients().get(i) * 1.3);
        }
        getIngredients().put(IngredientsManager.getIngredient("cup (L)"), 1.0);
        break;
    }
  }

  @Override
  public String toString() {
    return getName() + " | " + size + "  |  Status: " + State.stateMap.get(getStatus());
  }
}
