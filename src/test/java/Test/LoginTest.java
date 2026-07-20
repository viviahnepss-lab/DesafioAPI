package Test;

import Runners.TesteNegativos;
import Runners.TestePositivos;
import Service.APIMetodos;
import Utils.GeradorMassa;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.*;

public class LoginTest {

    GeradorMassa gm = new GeradorMassa();
    APIMetodos api = new APIMetodos();
    List<String> dadosVal = new ArrayList();
    List<String> user = new ArrayList<>();
    Map<String,String> dadosReq = new HashMap<>() ;
    Faker faker = new Faker();


    @BeforeEach
    public void ValidaHealth() throws Throwable {
        api.getRequest("base", "health");
        api.validaStatusCode(200);
        Assert.assertEquals("ok",api.extraiDadosBodyPath(Collections.singletonList("status")));

    }
/*
    @AfterAll
      public void LimpaDados() throws Throwable {
            dadosVal.clear();
        }*/


    @Test
    @Category(TestePositivos.class)
    @DisplayName("CTL005- Validar login com sucesso")
    public void CTL004_Efetua_Login_Usuario() throws Throwable {
        dadosVal.add("username");
        dadosVal.add("password");
        api.getRequestParametro("base", "users",false);
        api.validaStatusCode(200);
        user = api.extraiDadosBodyPath(dadosVal);
        dadosReq.put(dadosVal.get(0),user.get(0));
        dadosReq.put(dadosVal.get(1),user.get(1));

        api.request(dadosReq);
        api.postRequest("base","login");
        api.validaStatusCode(200);
        dadosVal.add("accessToken");
        dadosReq.put(dadosVal.get(2),api.extraiDadosBodyPath(dadosVal).get(2));


        api.request(dadosReq);
        api.postRequest("base","produtoAuth");
        api.validaStatusCode(200);


/*
        dadosVal.add("accessToken");
        dadosReq.put("token",api.extraiDadosBody(dadosVal).get(2));

        System.out.println(dadosReq.get("token"));
        //Assert.assertNotNull(token);*/
    }

    @Test
    @Category(TesteNegativos.class)
    @DisplayName("CTL005- Validar login com usuario inexistente ")
    public void CTL005_Efetua_Login_Usuario_Inexistente() throws Throwable {
        dadosReq.put("username", gm.geraUsuario().getToken());
        dadosReq.put("password",gm.geraUsuario().getPassword());
        api.request(dadosReq);
        api.postRequest("base","login");
        api.validaStatusCode(400);
        dadosVal.add("message");
        user=(api.extraiDadosBodyPath(dadosVal));
        Assert.assertEquals("Invalid credentials",user.get(0));

    }
    @Test
    @Category(TesteNegativos.class)
    @DisplayName("CTL006- Validar login com usuario invalida ")
    public void CTL006_Efetua_Login_Usuario_Invalido() throws Throwable {
        api.getRequestParametro("base", "users",false);
        dadosVal.add("username");
        dadosVal.add("password");
        user = api.extraiDadosBodyPath(dadosVal);

        dadosReq.put(dadosVal.get(0),gm.geraUsuario().getToken());
        dadosReq.put(dadosVal.get(1), user.get(1).toString());
        api.request(dadosReq);
        api.postRequest("base","login");
        dadosVal.clear();
        dadosVal.add("message");
        api.validaStatusCode(400);
        user=(api.extraiDadosBodyPath(dadosVal));
        Assert.assertEquals("Invalid credentials",user.get(0));

    }

    @Test
    @Category(TesteNegativos.class)
    @DisplayName("CTL007- Validar login com senha invalida ")
    public void CTL007_Efetua_Login_Usuario_senha_Invalida() throws Throwable {
        api.getRequestParametro("base", "users",false);
        dadosVal.add("username");
        dadosVal.add("password");
        api.validaStatusCode(200);
        user = api.extraiDadosBodyPath(dadosVal);

        dadosReq.put(dadosVal.get(0),user.get(0).toString() );
        dadosReq.put(dadosVal.get(1), gm.geraUsuario().getPassword());
        api.request(dadosReq);
        api.postRequest("base","login");

        dadosVal.add("message");
        api.validaStatusCode(200);
        user=(api.extraiDadosBodyPath(dadosVal));
        Assert.assertEquals("Invalid credentials",user.get(0));

    }


}

