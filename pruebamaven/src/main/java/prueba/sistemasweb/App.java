package prueba.sistemasweb;

import static spark.Spark.*;
import com.google.gson.*;
import java.util.UUID;

/**
 * Hello world!
 *
 */
public class App 
{
    public static Gson gson = new Gson();
    public static void main( String[] args )
    {
        //aquí va el cors??
        

        get("/gatitos", (req, resp) -> gson.toJson(DAO.dameGatos())); 

        
    }
}
