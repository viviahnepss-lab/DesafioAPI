package Test;

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

public class LoginTest {

    GeradorMassa gm = new GeradorMassa();
    APIMetodos api = new APIMetodos();
    List<String> dadosVal = new ArrayList();
    List<String> user = new ArrayList<>();
    Map<String,String> dadosReq = new HashMap<>() ;



    @BeforeEach
    public void ValidaHealth() throws Throwable {
        api.getRequest("base", "health");
        dadosVal.add("status");
        user = api.extraiDadosBody(dadosVal,"200");
        Assert.assertEquals("ok", user.get(0));
    }

    @Test
    @Category(TestePositivos.class)
    @DisplayName("CTL005- Validar login com sucesso")
    public void CTL004_Efetua_Login_Usuario() throws Throwable {

        api.getRequestParametro("base", "users",0 ,false);
        dadosVal.add("username");
        dadosVal.add("password");
        user = api.extraiDadosBody(dadosVal, "200");

        dadosReq.put(dadosVal.get(0), user.get(0).toString());
        dadosReq.put(dadosVal.get(1), user.get(1).toString());
        api.request(dadosReq);
        api.postRequest("base","login");
        dadosVal.add("accessToken");
        String token = api.extraiDadosBody(dadosVal, "201").get(2).toString();
        Assert.assertNotNull(token);
    }

    @Test
    @Category(TesteNegativos.class)
    @DisplayName("CTL005- Validar login com usuario inexistente ")
    public void CTL005_Efetua_Login_Usuario_Inexistente() throws Throwable {
        dadosReq.put("username", gm.geraUsuario().getToken());
        dadosReq.put("password",gm.geraUsuario().getPassword());
        api.request(dadosReq);
        api.postRequest("base","login");

        dadosVal.add("message");
        user=(api.extraiDadosBody(dadosVal, "400"));
        Assert.assertEquals("Invalid credentials",user.get(0));

    }
    @Test
    @Category(TesteNegativos.class)
    @DisplayName("CTL006- Validar login com usuario invalida ")
    public void CTL006_Efetua_Login_Usuario_Invalido() throws Throwable {
        api.getRequestParametro("base", "users",0 ,false);
        dadosVal.add("username");
        dadosVal.add("password");
        user = api.extraiDadosBody(dadosVal, "200");

        dadosReq.put(dadosVal.get(0),gm.geraUsuario().getToken());
        dadosReq.put(dadosVal.get(1), user.get(1).toString());
        api.request(dadosReq);
        api.postRequest("base","login");
        dadosVal.clear();
        dadosVal.add("message");
        user=(api.extraiDadosBody(dadosVal, "400"));
        Assert.assertEquals("Invalid credentials",user.get(0));

    }

    @Test
    @Category(TesteNegativos.class)
    @DisplayName("CTL007- Validar login com senha invalida ")
    public void CTL007_Efetua_Login_Usuario_senha_Invalida() throws Throwable {
        api.getRequestParametro("base", "users",0 ,false);
        dadosVal.add("username");
        dadosVal.add("password");
        user = api.extraiDadosBody(dadosVal, "200");

        dadosReq.put(dadosVal.get(0),user.get(0).toString() );
        dadosReq.put(dadosVal.get(1), gm.geraUsuario().getPassword());
        api.request(dadosReq);
        api.postRequest("base","login");
        dadosVal.clear();
        dadosVal.add("message");
        user=(api.extraiDadosBody(dadosVal, "400"));
        Assert.assertEquals("Invalid credentials",user.get(0));

    }
}
