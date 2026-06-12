package Step;

import Model.Users;
import Service.APIMetodos;
import Utils.GeradorMassa;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class LoginStep {
     Users usuario = new Users();
     GeradorMassa gm = new GeradorMassa();
     APIMetodos api = new APIMetodos();

     // Verifica se o sistema está funcionando
     @Test
     @DisplayName("Valida disponibilidade do sistema")
     public void ValidaHealth() throws Throwable {
      api.getRequest("base", "health");
      Assert.assertEquals("ok", api.extraiResposta("status"));
     }

     @Test
     @DisplayName("Valida busca de usuario valido")
     public void BuscaUsuario() throws Throwable {
         int id= gm.geradorId();
         api.getRequestParametro("base", "users", id);
         usuario.setFirstName(api.extraiResposta("firstName"));
        usuario.setFirstName(api.extraiResposta("password"));
       System.out.print("Usuario buscado:"+"\n"+usuario.getFirstName()+"\n"+usuario.getPassword());
     }


     }
