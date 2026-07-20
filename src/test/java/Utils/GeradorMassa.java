package Utils;

import Model.Produtos;
import Model.Usuario;
import com.github.javafaker.Faker;

public class GeradorMassa {
    Faker faker = new Faker();


    public int geradorId() {
        int id=faker.random().nextInt(1,50);
        return id;
     }

    public String geradorLetra() {
        String id=faker.letterify("?");
        return id;
    }
    public Usuario geraUsuario() {

        Usuario usuario = new Usuario ();
        usuario.setToken(faker.name().firstName());
        usuario.setPassword(faker.name().firstName()+geradorId());

      return usuario;
    }
    public Produtos geraProduto() {

        Produtos produto = new Produtos ();
        produto.setTitle(faker.commerce().productName());
        produto.setDescription((faker.commerce().material()));
        produto.setPrice(Double.valueOf(faker.commerce().price(1.00,10000.00)));
        produto.setDiscountPercentage(Double.valueOf((faker.number().digit())));
        produto.setStock(Integer.valueOf(faker.number().digit()));
        produto.setBrand(faker.artist().name());
        produto.setCategory(faker.commerce().department());
        produto.setThumbnail("https://"+faker.internet().url());
        return produto;
    }

}
