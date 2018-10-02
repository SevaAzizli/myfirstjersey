package myfirstjersey;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path( "aliens" )
public class AlienResource {

    AlienRepository repository = new AlienRepository();
    private Alien a1;
    private int id;


    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Alien> getAliens() {
        System.out.println( "get called..." );

        return repository.getAliens();

    }

    @GET
    @Path("alien/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Alien getAlien(@PathParam("id") int id) {
        return repository.getAlien( id );
    }

    @POST
    @Path("createAlien")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Alien createAlien(Alien a1) {
        System.out.println( "is created " + a1 );
        repository.create( a1 );
        return a1;
    }

    @PUT
    @Path("updateAlien")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Alien updateAlien(Alien a1) {

        if (repository.getAlien( a1.getId() ).getId() == 0) {
            repository.create( a1 );
        } else {
            System.out.println( "is updated " + a1 );
            repository.update( a1 );

        }
        return a1;
    }

    @DELETE
    @Path("deleteAlien/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Alien deleteAlien(@PathParam( "id" )int id) {

        Alien a = repository.getAlien( id );
            if(a.getId()!=0)
                repository.delete(id);

            return a;
    }
}











