package Service;

import Model.Produtos;
import Model.Users;
import Utils.GeradorMassa;
import Utils.Propriedades;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class APIMetodos {
    public static Response response;
    public Properties prop = new Propriedades().getProperties();
    public static RequestSpecification request = RestAssured.with();
    int i;

    GeradorMassa gm = new GeradorMassa();
   // private Logger log = new Logger();


    public void request(Map<String,String> json){
        request = request.given().contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .body(json)
                .log().body();
    }


    public void getRequest(String env, String endpoint) throws Throwable{
      response = request.when()
              .log().all()
              .get(prop.getProperty(env)+ prop.getProperty(endpoint));

    }

    public void getRequestParametro(String env, String endpoint,int id, boolean flag) throws Throwable{
        if (flag== true) {
            response = request.when()
                    .log().all()
                    .get(prop.getProperty(env)+ prop.getProperty(endpoint)+"/"+gm.geradorLetra());

           }
        if(id ==1000){
            response = request.when()
                    .log().all()
                    .get(prop.getProperty(env) + prop.getProperty(endpoint) + "/" + id);

        }
       else if(id == 0 && flag == false){
            int ran = gm.geradorId();
            response = request.when()
                    .log().all()
                    .get(prop.getProperty(env) + prop.getProperty(endpoint) + "/" + ran);
        }

    }

    public void postRequest(String env, String endpoint) throws Throwable{
        response = request.when()
                .log().all()
                .post(prop.getProperty(env)+ prop.getProperty(endpoint));
    }

    public ResponseBodyExtractionOptions extraiBody(String cod) throws Throwable {

        ResponseBodyExtractionOptions resp =  response
                .then()
                .extract().response()
                ;
        return  resp;
    }


    public List<String> extraiDadosBody (List<String> path, String code) throws Throwable {
        ResponseBodyExtractionOptions bodyresp =  extraiBody(code);
        List<String> dados = new ArrayList<>();
        int i=0;

        for (i=0;i<path.size();i++){
          dados.add(bodyresp.path(path.get(i).toString()));
        }

        return dados;
    }

    public List<String> extraiProdutoID(List<String>pathDado) throws Throwable {
        List<String> aux = new ArrayList<>();

        int id;
        JsonPath resp =  response
                .then()
                .extract().jsonPath();

        for(i=0; i<pathDado.size();i++){
            if(pathDado.get(i).toString().equals("id")){
                 id = resp.getInt((pathDado.get(0)));
                aux.add((String.valueOf(id)));
                 i++;
            }
            aux.add(resp.getString(pathDado.get(i)));
        }
        return aux ;
    }

    public List<Users> extraiListaUsuarios(List<String>pathDado) throws Throwable {

        List<Users> usersLista = response
                .jsonPath()
                .getList("users",Users.class);

        return usersLista ;
    }


    public List<Produtos> extraiListaProdutos(List<String>pathDado) throws Throwable {
        Response rps = response
                   .then()
                .extract().response();

                List<Produtos> produtosLista = rps
                .jsonPath()
                .getList("produtos",Produtos.class);
     System.out.print("ProdutoLista: "+produtosLista.size() );
        return produtosLista ;
    }


}
