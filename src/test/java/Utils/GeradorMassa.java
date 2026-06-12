package Utils;

import Model.Produtos;
import com.github.javafaker.Faker;

public class GeradorMassa {
    Faker faker = new Faker();

    public Produtos geradorProduto() {
        Produtos pr = new Produtos();
        pr.setTitle(faker.beer().name());
        pr.setDescription(faker.beer().style());
        pr.setPrice(faker.number().numberBetween(1, 50));
        pr.setDiscountPercentage(faker.number().randomNumber());
        pr.setRating(faker.number().randomDigitNotZero());
        pr.setStock(faker.random().nextInt(1, 50));
        pr.setBrand(faker.beer().malt());
        pr.setCategory(faker.beer().style());
        pr.setThumbnail(faker.internet().domainName().toString());
        return pr;
    }

    public int geradorId() {
        int id=faker.random().nextInt(1,50);
        return id;
     }

}
