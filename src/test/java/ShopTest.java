import org.example.AlreadyExistsException;
import org.example.NotFoundException;
import org.example.Product;
import org.example.ShopRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopTest {
    ShopRepository repository = new ShopRepository();

    @Test
    public void shouldAddToArray() {
        repository.add(new Product(5, "картошка", 60));
        repository.add(new Product(6, "молоко", 70));

        Assertions.assertEquals(2, repository.findAll().length);

    }

    @Test
    public void remove() {
        repository.add(new Product(5, "картошка", 60));
        repository.add(new Product(6, "молоко", 70));
        repository.add(new Product(7, "редис", 100));

        repository.remove(6);

        Assertions.assertEquals(2, repository.findAll().length);

    }

    @Test
    public void findById() {
        Product searchItem = new Product(5, "картошка", 60);
        Product searchItem1 = new Product(6, "молоко", 70);
        Product searchItem2 = new Product(7, "редис", 100);
        repository.add(searchItem);
        repository.add(searchItem1);
        repository.add(searchItem2);

        Assertions.assertEquals(searchItem1, repository.findById(6));
    }
    @Test
    public void remoteById() {
        Product searchItem = new Product(5, "картошка", 60);
        Product searchItem1 = new Product(6, "молоко", 70);
        Product searchItem2 = new Product(7, "редис", 100);
        repository.add(searchItem);
        repository.add(searchItem1);
        repository.add(searchItem2);

        repository.removeById(7);

        Product[] expected = {searchItem,searchItem1};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repository.removeById(8);
        });
    }
    @Test
    public void shouldGetAndSetProduct() {
        Product product = new Product(1,"мороженое", 100);

        product.setPrice(200);
        product.setTitle("пирог");

        Assertions.assertEquals(1, product.getId());
        Assertions.assertEquals("пирог", product.getTitle());
        Assertions.assertEquals(200, product.getPrice());

    }
    @Test
    public void shouldAddProductById() {
        repository.add(new Product(11,"сок",105));
        repository.add(new Product(12,"лимон",90));
        repository.add(new Product(13,"печенье",150));

        Assertions.assertEquals(3,repository.findAll().length);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repository.addProductById(12);
        });
    }

}
