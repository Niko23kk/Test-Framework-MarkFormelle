package test;

import static org.assertj.core.api.Assertions.*;
import model.Product;
import org.testng.annotations.Test;
import page.OrderPage;
import page.ProductPage;
import service.ProductCreator;

public class AOrderTest extends CommonConditions {
    @Test
    public void checkCorrectChangeCountProduct(){
        Product product=ProductCreator.withAllProperty("first");
        OrderPage orderPage=new ProductPage(driver)
                .openPage(product.getProductUrl())
                .addProductToOrder()
                .goToOrderPage();

        orderPage.enlargeCountProduct(1);

        assertThat(orderPage.getTotalProductPrice(1)).isEqualTo(orderPage.getProductPrice(1)*
                orderPage.getCountProduct(1));
    }

    @Test
    public void checkCorrectDeleteProduct(){
        Product product=ProductCreator.withAllProperty("first");
        OrderPage orderPage=new ProductPage(driver)
                .openPage(product.getProductUrl())
                .addProductToOrder()
                .goToOrderPage();

        orderPage.deleteProduct(1);

        assertThat(orderPage.basketIsEmpty()).isTrue();
    }

    @Test
    public void checkCorrectAddProductToBasket() {
        Product productFirst = ProductCreator.withAllProperty("first");

        OrderPage orderPage = new ProductPage(driver)
                .openPage(productFirst.getProductUrl())
                .addProductToOrder()
                .goToOrderPage();

        assertThat(orderPage.getCountBasketProducts()).isEqualTo(1);
        assertThat(orderPage.getTotalSumAllProductPrice()).isEqualTo(orderPage.getTotalPriceBasket());
    }

    @Test
    public void checkCorrectSumPriceProduct() {
        Product productFirst = ProductCreator.withAllProperty("first");
        Product productSecond = ProductCreator.withAllProperty("second");

        OrderPage orderPage = new ProductPage(driver)
                .openPage(productFirst.getProductUrl())
                .addProductToOrder()
                .openPage(productSecond.getProductUrl())
                .addProductToOrder()
                .goToOrderPage();

        assertThat(orderPage.getCountBasketProducts()).isEqualTo(2);
        assertThat(orderPage.getTotalSumAllProductPrice()).isEqualTo(orderPage.getTotalPriceBasket());
    }
}
