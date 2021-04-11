package test;

import static org.assertj.core.api.Assertions.*;

import model.Product;
import org.testng.annotations.Test;
import page.FavoritePage;
import page.ProductPage;
import service.ProductCreator;

public class ProductTest extends CommonConditions{
    @Test
    public void checkCorrectAddToFavorite() {
        Product product= ProductCreator.withAllProperty("first");
        int expectedCount=1;
        ProductPage productPage = new ProductPage(driver)
                .openPage(product.getProductUrl())
                .addToFavorite();

        assertThat(productPage.isFavoriteProduct()).isTrue();

        FavoritePage favoritePage = productPage
                .goToFavoritePage();

        assertThat(favoritePage.getNumberOfFavoriteProduct()).isEqualTo(expectedCount);
        assertThat(favoritePage.getSizeFavoriteProductPanel()).isEqualTo(expectedCount);
    }

    @Test
    public void checkChangeColorProduct(){
        Product product=ProductCreator.withAllProperty("third");

        ProductPage productPage=new ProductPage(driver)
                .openPage(product.getProductUrl())
                .chooseProductColor("синий");

        assertThat(productPage.getColorProduct()).contains("синий");
    }

    @Test
    public void checkCorrectCertificatePrice(){
        Product product=ProductCreator.withAllProperty("fourth");

        ProductPage productPage=new ProductPage(driver)
                .openPage(product.getProductUrl())
                .chooseCertificatePrice(100);

        assertThat(productPage.getCertificatePrice()).isEqualTo(100);
        assertThat(productPage.getProductPrice()).isEqualTo(100);
    }
}
