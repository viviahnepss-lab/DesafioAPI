package Test;

import Model.Produtos;
import Runners.TesteNegativos;
import Runners.TestePositivos;
import Service.APIMetodos;
import Utils.GeradorMassa;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.greaterThan;

public class ProdutoTest {


    GeradorMassa gm = new GeradorMassa();
    APIMetodos api = new APIMetodos();
    List<String> dadosVal = new ArrayList();
    List<String> produto = new ArrayList<>();
    Map<String,String> dadosReq = new HashMap<>() ;
    int i;

    // Verifica se o sistema está funcionando

    @BeforeEach
    public void ValidaHealth() throws Throwable {
        api.getRequest("base", "health");
        dadosVal.add("status");
        produto = api.extraiDadosBody(dadosVal,"200");
        Assert.assertEquals("ok", produto.get(0));
    }

    @Test
    @Category(TestePositivos.class)
    @DisplayName("CTP008 -Cadastrar produto com sucesso")
    public void CTP008_Efetua_Cadastro_Produto_Sucesso() throws Throwable {
        Produtos prd = new Produtos();
        prd=gm.geraProduto();
        dadosReq.put("title",  prd.getTitle());
        dadosReq.put("description", prd.getDescription());
        dadosReq.put("price",prd.getPrice());
        dadosReq.put("discountPercentage",prd.getDiscountPercentage());
        dadosReq.put("rating",prd.getRating());
        dadosReq.put("stock", prd.getStock());
        dadosReq.put("brand",prd.getBrand());
        dadosReq.put("category",prd.getCategory());
        dadosReq.put("thumbnail",  prd.getThumbnail());

        api.request(dadosReq);
        api.postRequest("base","produtoAdd");
        dadosVal.add("id");
        produto=(api.extraiDadosBody(dadosVal, "201"));
        System.out.print(dadosVal.get(0));
        Assert.assertFalse(produto.isEmpty());
    }

    @Test
    @Category(TestePositivos.class)
    @DisplayName("CTP009 -Validar busca de produto com sucesso")
    public void CTP009_Efetua_Busca_Produto_Sucesso() throws Throwable {
        List<Produtos> lprod = new ArrayList<>();
        api.getRequest("base", "produtos");
        dadosVal.add("id");
        dadosVal.add("title");
        dadosVal.add("description");
        dadosVal.add("category");
        dadosVal.add("price");
        dadosVal.add("discountPercentage");
        dadosVal.add("rating");
        dadosVal.add("stock");
       lprod.addAll(api.extraiListaProdutos(dadosVal));
        System.out.print("Total: "+lprod.size());
        //Assert.assertTrue(lprod.size() >0);

    }

    @Test
    @Category(TestePositivos.class)
    @DisplayName("CTP010 -Validar busca de produto por ID com sucesso")
    public void CTP010_Efetua_Busca_ProdutoID_Sucesso() throws Throwable{
        api.getRequestParametro("base", "produtos",0 ,false);
        dadosVal.add("id");
        dadosVal.add("title");
        dadosVal.add("description");
        dadosVal.add("category");
        dadosVal.add("price");
        dadosVal.add("discountPercentage");
        dadosVal.add("rating");
        dadosVal.add("stock");
        produto = api.extraiProdutoID(dadosVal);
        Assert.assertNotNull(produto.get(0), greaterThan(0));

        for(i=0;i< dadosVal.size();i++){
            System.out.print(dadosVal.get(i)+":"+produto.get(i)+"\n");

        }
    }
        @Test
        @Category(TesteNegativos.class)
        @DisplayName("CTP011 -Validar busca de produto por ID inexistente")
        public void CTP011_Efetua_Busca_ProdutoID_inexistente() throws Throwable{
            api.getRequestParametro("base", "produtos",1000 ,false);
            dadosVal.add("message");
            produto = api.extraiProdutoID(dadosVal);
            api.extraiDadosBody(dadosVal, "404");
            Assert.assertTrue(produto.get(0).contains("not found"));
            Assert.assertTrue(produto.get(0).contains("1000"));
    }

    @Test
    @Category(TesteNegativos.class)
    @DisplayName("CTP012 -Validar busca de produto por inválido")
    public void CTP012_Efetua_Busca_ProdutoID_inválido() throws Throwable{

        api.getRequestParametro("base", "produtos", 0, true);
        dadosVal.add("message");
        produto = api.extraiDadosBody(dadosVal, "400");
        Assert.assertTrue(produto.get(0).toString().contains("not found"));

    }


}
