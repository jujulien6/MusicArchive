package music.management.web.resource;

import java.net.URI;
import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import music.management.web.service.*;
import music.management.web.data.*;
@Path("/musics")
/**
 * 
 * @author DJ
 *
 */
public class MusicResource {
	
	MusicService service=new MusicService();
	
	 @Context
	 UriInfo uriInfo;
	 
	 /**
	  * 
	  * @param m
	  * @return add an artist
	  */
	 @POST
	  @Consumes(MediaType.APPLICATION_XML)
	  @Produces(MediaType.APPLICATION_XML)
	  public Response addArtist(Music m) {
	    Music musique = service.addArtist(m);
	    if(musique == null) {
	      return Response.status(Response.Status.BAD_REQUEST).build();
	    }
	    URI uri = uriInfo.getRequestUri();
	    String newUri = uri.getPath() + "/" + musique.getId();
	    return Response.status(Response.Status.CREATED)
	                   .contentLocation(uri.resolve(newUri))
	                   .build();
	  }
	 
	 /**
	  * 
	  * @param id
	  * @param musiques
	  * @return a new song for an artist
	  */
	 @POST
	  @Consumes(MediaType.APPLICATION_XML)
	  @Produces(MediaType.APPLICATION_XML)
	  public Response AddSongForArtist(@PathParam("id") int id,String musiques) {
	    String musique = service.AddSongForArtist(id,musiques);
	   
	    if(musiques == null) {
	      return Response.status(Response.Status.BAD_REQUEST).build();
	    }
	    URI uri = uriInfo.getRequestUri();
	    String newUri = uri.getPath() + "/" + musique;
	    return Response.status(Response.Status.CREATED)
	                   .contentLocation(uri.resolve(newUri))
	                   .build();
	  }
	 
	 /**
	  * 
	  * @param id
	  * @param s
	  * @return a music with new information
	  */
	 @PUT
	  @Path("/{id}")
	  @Consumes(MediaType.APPLICATION_XML)
	  @Produces(MediaType.APPLICATION_XML)
	  public Response changeArtist(@PathParam("id") int id, Music s) {
	    Music artist = service.changeArtist(id, s);
	    if(artist == null) {
	      return Response.status(Response.Status.BAD_REQUEST).build();
	    }
	    Link link = Link.fromUri(uriInfo.getRequestUri())
	                    .rel("self")
	                    .type("application/xml")
	                    .build();
	    return Response.status(Response.Status.OK)
	                   .links(link)
	                   .build();
	  }
	 
	 /**
	  * 
	  * @param id
	  * @return true if an artist was delete, false if not
	  */
	 @DELETE
	  @Path("/{id}")
	  @Produces(MediaType.APPLICATION_XML)
	  public Response deleteArtist(@PathParam("id") int id) { 
	    if(service.deleteArtist(id) == false) {
	      return Response.status(Response.Status.NOT_FOUND).build();
	    }
	    return Response.status(Response.Status.OK).build();
	  }
	 
	 /**
	  * 
	  * @param id
	  * @return a music
	  */
	  @GET
	  @Path("/{id}")
	  @Produces(MediaType.APPLICATION_XML)
	  public Response getArtist(@PathParam("id") int id) {
	    Music artist = service.getArtist(id);
	    if(artist == null) {
	      return Response.status(Response.Status.NOT_FOUND).build();
	    }
	    Link link = Link.fromUri(uriInfo.getRequestUri())
	                    .rel("self")
	                    .type("application/xml")
	                    .build();
	    return Response.status(Response.Status.OK)
	                   .entity(artist)
	                   .links(link)
	                   .build();
	  }
	  
	  /**
	   * 
	   * @return HashMap of all artist
	   */
	  @GET
	  @Produces(MediaType.APPLICATION_XML)
	  public Response getAllArtist() {
	    return Response.status(Response.Status.OK)
	                   .entity(service.getAllArtist())
	                   .build();
	  }
	  
	  /**
	   * 
	   * @param id
	   * @return song for an artist
	   */
	 @GET
	 @Path("/{id}")
	  @Produces(MediaType.APPLICATION_XML)
	  public Response getSongByArtist(@PathParam("id") int id) {
		  ArrayList<String> artist = service.getSongByArtist(id);
		    if(artist == null) {
		      return Response.status(Response.Status.NOT_FOUND).build();
		    }
		    Link link = Link.fromUri(uriInfo.getRequestUri())
		                    .rel("self")
		                    .type("application/xml")
		                    .build();
		    return Response.status(Response.Status.OK)
		                   .entity(artist)
		                   .links(link)
		                   .build();
	  }
	  
	  
}
