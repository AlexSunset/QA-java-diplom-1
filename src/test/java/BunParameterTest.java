import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

@RunWith(Parameterized.class)
public class BunParameterTest {

    private final float price;

    public BunParameterTest(float price) {
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {-1},
                {0},
                {10},
                {100},
        };
    }

    @Test
    public void getPriceReturnThisPrice() {
        Bun bun = new Bun("TestBun", price);
        Assert.assertEquals(bun.price, bun.getPrice(), 0);
    }
}
