package Test;

import Model.Usuario;
import Service.APIMetodos;
import Utils.GeradorMassa;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class UsuarioTest {

     Usuario usuario = new Usuario();
     GeradorMassa gm = new GeradorMassa();
     APIMetodos api = new APIMetodos();
     List<String> dadosVal = new ArrayList();
     List<String> user = new ArrayList<>();
     List<Map<String, Object>> usuariosResp;
     Faker faker = new Faker();

     int id =0;


    // Verifica se o sistema está funcionando

    @BeforeEach
     public void ValidaHealth() throws Throwable {
         api.getRequest("base", "health");
         dadosVal.add("status");
         api.validaStatusCode(200);
         user = api.extraiDadosBodyPath(dadosVal);
         Assert.assertEquals("ok", user.get(0));
     }


    @Test
    @Tag("Usuario")
    @DisplayName("CTU001- Validar busca de usuário  com sucesso -")
    public void CTU001_Busca_Apenas_Um_Usuario_Valido() throws Throwable {

        api.getRequest("base", "users");
        api.validaStatusCode(200);
        user =api.extraiDadosBodyPath(dadosVal);
        System.out.print("Buscado: "+user.get(3));
        Assert.assertNotNull(user);

            }


     @Test
     @Tag("Usuario")
     @DisplayName("CTU002- Validar busca de usuário por ID com sucesso -")
     public void CTU002_Busca_UsuarioID_Valido() throws Throwable {
         api.getRequestParametro("base", "users",false);
         api.validaStatusCode(200);

         dadosVal.add("username");
         dadosVal.add("password");

         user = api.extraiDadosBodyPath(dadosVal);
         Assert.assertNotNull(user);

     }

    @Test
    @Tag("Usuario")
    @DisplayName("CTU003- Validar busca de usuário inexistente")
    public void CTU003_Busca_Usuario_inexistente() throws Throwable {

        api.getRequestParametro("base", "users", false);
        dadosVal.add("message");
        api.validaStatusCode(404);
        user = api.extraiDadosBodyPath(dadosVal);
        Assert.assertTrue(user.get(0).contains("not found"));
        Assert.assertTrue(user.get(0).contains("1000"));
    }

    @Test
    @Tag("Usuario")
    @DisplayName("CTU004- Validar busca de usuário inválido")
    public void CTU004_Busca_Usuario_Invalido() throws Throwable {
        api.getRequestParametro("base", "users", true);
        dadosVal.add("message");
        api.validaStatusCode(400);
        user = api.extraiDadosBodyPath(dadosVal);
        Assert.assertTrue(user.get(0).contains("Invalid user id"));
     }

    @Test
    @Tag("Usuario")
    @DisplayName("CTU005- Validar busca de lista de usuários com sucesso -")
    public void CTU005_Busca_Lista_Usuario_Valido() throws Throwable {
        api.getRequest("base", "users");
        api.validaStatusCode(200);
        usuariosResp = api.extraiListasResponse("users");
        Assert.assertNotNull(usuariosResp.size());


    }


    }
