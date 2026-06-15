package Step;

import Model.Users;
import Service.APIMetodos;
import Utils.GeradorMassa;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

import java.util.ArrayList;
import java.util.List;


public class UsuarioStep {
     Users usuario = new Users();
     GeradorMassa gm = new GeradorMassa();
     APIMetodos api = new APIMetodos();
     List<String> dadosVal = new ArrayList();
     List<String> user = new ArrayList<>();
     int id =0;


    // Verifica se o sistema está funcionando

    @BeforeEach
     public void ValidaHealth() throws Throwable {
         api.getRequest("base", "health");
         dadosVal.add("status");
         user = api.extraiDadosBody(dadosVal,200);
         Assert.assertEquals("ok", user.get(0));
     }


    @Test
    @Tag("Usuario")
    @DisplayName("CTU001- Validar busca de usuário por ID com sucesso -")
    public void CTU001_Busca_UsuarioLista_Valido() throws Throwable {

        api.getRequest("base", "users");
        user = api.extraiDadosBody(dadosVal, 200);
        System.out.print("Buscado: "+user.get(3));
        Assert.assertNotNull(user);

            }


     @Test
     @Tag("Usuario")
     @DisplayName("CTU002- Validar busca de usuário por ID com sucesso -")
     public void CTU002_Busca_UsuarioID_Valido() throws Throwable {

         api.getRequestParametro("base", "users",0 ,false);
         dadosVal.add("firstName");
         dadosVal.add("password");
         user = api.extraiDadosBody(dadosVal, 200);
         Assert.assertNotNull(user);
         Assert.assertEquals(2, user.size());
     }

    @Test
    @Tag("Usuario")
    @DisplayName("CTU003- Validar busca de usuário inexistente")
    public void CTU003_Busca_Usuario_inexistente() throws Throwable {

        api.getRequestParametro("base", "users", 1000, false);
        dadosVal.add("message");
        user = api.extraiDadosBody(dadosVal, 404);
        Assert.assertTrue(user.get(0).contains("not found"));
        Assert.assertTrue(user.get(0).contains("1000"));
    }

    @Test
    @Tag("Usuario")
    @DisplayName("CTU004- Validar busca de usuário inválido")
    public void CTU004_Busca_Usuario_Invalido() throws Throwable {
        api.getRequestParametro("base", "users", 0, true);
        dadosVal.add("message");
        user = api.extraiDadosBody(dadosVal, 400);
        Assert.assertTrue(user.get(0).contains("Invalid user id"));
     }

    }
