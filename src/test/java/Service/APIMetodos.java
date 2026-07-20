package Service;

import Utils.GeradorMassa;
import Utils.Propriedades;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class APIMetodos {
   // private static final Logger logger = (Logger) LoggerFactory.getLogger(APIMetodos.class);
    public static Response response;
    public Properties prop = new Propriedades().getProperties();
    public Random randon = new Random();
    public static RequestSpecification request = RestAssured.with();
    int i;


    GeradorMassa gm = new GeradorMassa();
   // private Logger log = new Logger();


    public void request(Map<String,String> json) throws IOException {
      // logger.info("Preparando request. \n");
        request = request.given().contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .body(json)
                .body(request)
                .log().all();


        Files.writeString(
                Path.of("target/evidencias/request.json"),
                (CharSequence) request
        );


    }


    public void getRequest(String env, String endpoint) throws Throwable{
       // logger.info("Enviando request tipo GET simples");

        String responseBody = response.getBody().asPrettyString();

      response = request.when()
              .given()
              .log().all()
              .get(prop.getProperty(env)+ prop.getProperty(endpoint));



    }

    public void getRequestParametro(String env, String endpoint, boolean flag) throws Throwable{
      //  logger.info("Preparando request GET com parametro");
        if (flag== true) {
            response = request.when()
                    .log().all()
                    .get(prop.getProperty(env)+ prop.getProperty(endpoint)+"/"+gm.geradorLetra());

           }
        else{
            response = request.when()
                    .log().all()
                    .get(prop.getProperty(env) + prop.getProperty(endpoint) + "/" + randon.nextInt(0,150));

        }

    }

    public void postRequest(String env, String endpoint) throws Throwable{
     //   logger.info("Preparando request POST");
        response = request.when()
                .log().all()
                .post(prop.getProperty(env)+ prop.getProperty(endpoint));
    }

    public ResponseBodyExtractionOptions extraiBody(String cod) throws Throwable {

        ResponseBodyExtractionOptions resp =  response
                .then()
                .statusCode(Integer.parseInt(cod.toString()))
                .extract().body()
                ;
        return  resp;
    }

    public void  validaStatusCode(int code) throws Throwable {
        ValidatableResponse codeVal = response
                        .then();
              codeVal.extract().path("statusCode");
              Assert.assertEquals(code, codeVal.extract().statusCode());
    }

    public List<String> extraiDadosBodyPath(List<String> path) throws Throwable {
        ResponseBodyExtractionOptions bodyres = response
                .then()
                .extract().body();

        List<String> dados = new ArrayList<>();
        for (i=0;i<path.size();i++){
          dados.add(bodyres.path(path.get(i).toString()));
        }


        String responseBody = response.getBody().asPrettyString();

        Files.write(
                Paths.get("target/evidencias/response.json"),
                responseBody.getBytes()
        );;

        return dados;
    }

    public List<Map<String, Object>> extraiListasResponse( String name) throws Throwable {

        List<Map<String, Object>> resultado =
                response.jsonPath().getList(name);

        return resultado ;
    }


    public List<String> extraListasProdutoID(List<String>pathDado) throws Throwable {
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









}
