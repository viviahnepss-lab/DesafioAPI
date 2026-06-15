package Step;

import Model.Produtos;
import Service.APIMetodos;
import Utils.GeradorMassa;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProdutoStep {


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
        produto = api.extraiDadosBody(dadosVal,200);
        Assert.assertEquals("ok", produto.get(0));
    }

    @Test
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
        produto=(api.extraiDadosBody(dadosVal, 201));
        System.out.print(dadosVal.get(0));
        Assert.assertFalse(produto.isEmpty());
    }

}
