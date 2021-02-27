package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.JokeDTO;
import entities.JokeEntity;
import facades.JokeFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;


@Path("jokes")
public class JokeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    private static final JokeFacade FACADE = JokeFacade.getJokeFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getJokeCount() {
        long count = FACADE.getJokeCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":" + count + "}";  //Done manually so no need for a DTO
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllJokes() {
        List<JokeEntity> jokes = FACADE.getAllJokes();
        List<JokeDTO> jokeDTOs = new ArrayList();
        for (JokeEntity joke : jokes) {
            JokeDTO jokeDTO = new JokeDTO(joke);
            jokeDTOs.add(jokeDTO);
        }
        return GSON.toJson(jokeDTOs);
    }

 

    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getJokeById(@PathParam("id") int id) {
        try {
            JokeEntity joke = FACADE.getJokeById(id);
            return GSON.toJson(joke);
        } catch (NoResultException | NullPointerException e) {
            return GSON.toJson(null);
        }
    }


//    @Path("random")
//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    public String getRandomJoke() {
//       try {
//        JokeEntity joke = FACADE.getRandomJoke();
//         return GSON.toJson(joke);
//      } catch (NoResultException | NullPointerException e) {
//          return GSON.toJson(null);
//      }
//     }
    }