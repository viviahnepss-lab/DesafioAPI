package Service;

import Utils.Propriedades;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Properties;

public class APIMetodos {
    public static Response response;
    public Properties prop = new Propriedades().getProperties();
    public static RequestSpecification request = RestAssured.with();


    public void request(JsonObject json){
        request = request.given().contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .body(json)
                .log().all();
    }


    public void getRequest(String env, String endpoint) throws Throwable{
      response = request.when()
              .log().all()
              .get(prop.getProperty(env)+ prop.getProperty(endpoint));
    }

    public void getRequestParametro(String env, String endpoint , int id) throws Throwable{
        response = request.when()
                .log().all()
                .get(prop.getProperty(env)+ prop.getProperty(endpoint)+"/"+id);
    }
    public void postRequest(String env, String endpoint) throws Throwable{
        response = request.when().post(prop.getProperty(env)+ prop.getProperty(endpoint));
    }


    public String extraiResposta(String path) throws Throwable{
      String valor = response.then().extract().path(path).toString();
        return valor;
    }

}
