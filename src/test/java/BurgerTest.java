import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient;

    @Mock
    Ingredient ingredient2;

    Burger burger = new Burger();


    @Test
    public void addIngredientPutElementInIngredientList() {
        assertEquals(0, burger.ingredients.size());
        burger.addIngredient(ingredient);
        Assert.assertTrue(burger.ingredients.contains(ingredient));
    }

    @Test
    public void setBunSetActualBun() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void removeIngredientRemoveItemFromIngredientList() {
        assertEquals(0, burger.ingredients.size());
        burger.ingredients.add(ingredient);
        Assert.assertTrue(burger.ingredients.contains(ingredient));
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void moveIngredientRemoveCurrentIndexItemAndAddNewIndexItem() {
        for (int i = 0; i < 2; i++) {
            burger.ingredients.add(ingredient);
        }
        burger.addIngredient(ingredient2);
        Mockito.when(ingredient2.getPrice()).thenReturn(3F);
        assertEquals(3, burger.ingredients.size());
        assertEquals(0, burger.ingredients.get(0).price, 0);
        burger.moveIngredient(2, 0);
        assertEquals(3, burger.ingredients.get(0).getPrice(), 0);
    }

    @Test
    public void getReceiptReturnCorrectReceipt() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);
        Mockito.when(bun.getName()).thenReturn("TestBun");
        Mockito.when(ingredient.getName()).thenReturn("TestSauce");
        Mockito.when(ingredient.getType()).thenReturn(SAUCE);
        Mockito.when(ingredient2.getName()).thenReturn("TestFilling");
        Mockito.when(ingredient2.getType()).thenReturn(FILLING);
        Mockito.when(bun.getPrice()).thenReturn(40F);
        Mockito.when(ingredient.getPrice()).thenReturn(50F);
        Mockito.when(ingredient2.getPrice()).thenReturn(30F);
        MatcherAssert.assertThat(burger.getReceipt(), startsWith("(==== TestBun ====)"));
        MatcherAssert.assertThat(burger.getReceipt(), containsString("= sauce TestSauce ="));
        MatcherAssert.assertThat(burger.getReceipt(), containsString("= filling TestFilling ="));
        MatcherAssert.assertThat(burger.getReceipt(), containsString("Price: 160,000000"));
    }

    @Test
    public void getPriceCalculateCorrectPrice() {
        burger.setBuns(bun);
        burger.ingredients.add(ingredient);
        burger.ingredients.add(ingredient2);
        Mockito.when(bun.getPrice()).thenReturn(10F);
        Mockito.when(ingredient.getPrice()).thenReturn(20F);
        Mockito.when(ingredient2.getPrice()).thenReturn(30F);
        Assert.assertEquals(70, burger.getPrice(), 0);
    }
}
