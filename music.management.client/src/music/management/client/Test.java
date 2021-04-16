package music.management.client;

import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.apache.cxf.jaxrs.client.*;
import music.management.web.data.*;
/**
*@author DJ
*Test de notre projet Music
*/
public class Test {

  private static String webServiceUrl = "http://localhost:8080/music.management.web/api/musics";

  public static void main(String[] args) {
	delete(1);
	 ArrayList<String> OboySong=new ArrayList<String>();
	 OboySong.add("Fané");
	 OboySong.add("Cabeza");
	 ArrayList<String>BadBunny=new ArrayList<String>();
	 BadBunny.add("El Mundo Es Mio");
	 BadBunny.add("Te Mudaste");
	 BadBunny.add("Hoy Cobré");
	 ArrayList<String>Layloww=new ArrayList<String>();
	 Layloww.add("Vent de l'est");
	 Layloww.add("Megatron");
	 Layloww.add("Brr");
	 
  Music Oboy = new Music("Oboy",OboySong,"Rapper",add("Oboy",OboySong,"Rappeur"));
  Music Bad_Bunny = new Music("Bad Bunny",BadBunny,"Spanish Singer",add("Bad Bunny",BadBunny,"Spanish Singer"));
  Music Laylow = new Music("Laylow",Layloww, "Original rapper style",add("Laylow",Layloww, "Original rappeur style"));
    
   get(Oboy.getId());
    get(Bad_Bunny.getId());
    get(Laylow.getId());
    
   
    getAll();
    AddSongForArtist(2,"Madre Mia");
    getSongByArtist(2);
   /* delete(4);
    delete(5);
    delete(6);
    delete(7);
    delete(8);
    delete(9);
    delete(10);
    delete(11);
    delete(12);
    delete(13);
    delete(14);
    delete(15);
    delete(16);
    */
    getAll();
    
    Music Oboy2 = new Music("Oboy",OboySong,"Rapper with many feat", Oboy.getId());    
    change(Oboy.getId(), Oboy2);
    
    getAll();    
  }

  

@SuppressWarnings("unused")
private static Integer add(String artist_name, ArrayList<String> song, String information) {
    System.out.print("Adding " + artist_name + "... ");
    WebClient c = WebClient.create(webServiceUrl);
    Music s = new Music(artist_name,song,information);
    Response r = c.post(s);
    if(r.getStatus() == 400) {
      System.out.println("Oops!");
      return null;
    }
    String uri = r.getHeaderString("Content-Location");
    System.out.println("OK.");
    return Integer.parseInt(uri.substring(uri.lastIndexOf('/') + 1));
  }

  private static Boolean change(Integer id, Music s) {
    System.out.print("Changing " + id + " with " + s.toString() + "... ");
    WebClient c = WebClient.create(webServiceUrl).path(id);
    Response r = c.put(s);
    if(r.getStatus() == 200) {
      System.out.println("OK.");
      return true;
    }
    System.out.println("Oops!");
    return false;
  }

  
  private static Boolean delete(Integer id) {
    System.out.print("Deleting " + id + "... ");
    WebClient c = WebClient.create(webServiceUrl).path(id);
    int status = c.delete().getStatus();
    if(status == 200) {
      System.out.println("OK.");
      return true;
    }
    System.out.println("Oops!");
    return false;
  }

  private static Music get(Integer id) {
    System.out.print("Getting Music for " + id + "... ");
    WebClient c = WebClient.create(webServiceUrl).path(id);
    Music s = null;
    try {
    	s = c.get(Music.class);
    	System.out.println(s.toString());
    } catch(NotFoundException e) {
    	System.out.println("Oops!");
    }
    return s;
  }
  
  private static Music getSongByArtist(Integer id) {
	  System.out.print("Song for " + id + "... ");
	    WebClient c = WebClient.create(webServiceUrl).path(id);
	    Music s = null;
	    try {
	    	s = c.get(Music.class);
	    	System.out.println(s.getSong().toString());
	    } catch(NotFoundException e) {
	    	System.out.println("Oops!");
	    }
	    return s;
	  }
  @SuppressWarnings("unchecked")
private static Music[] getAll() {
    System.out.println("Getting all...");
    WebClient c = WebClient.create(webServiceUrl);
    ArrayList<Music> l = (ArrayList<Music>) c.getCollection(Music.class);
    for(Music s : l) {
      System.out.println(s.toString());
    }
    System.out.println("OK.");
    return l.toArray(new Music[l.size()]);
  }
  
  private static String AddSongForArtist(int id,String music) {
	  System.out.print("Adding song for" + id + "... ");
	    WebClient c = WebClient.create(webServiceUrl);
	   Music m= get(id);
	   m.getSong().add(music);
	    Response r = c.post(m);
	    if(r.getStatus() == 400) {
	      System.out.println("Oops!");
	      return null;
	    }
	    @SuppressWarnings("unused")
		String uri = r.getHeaderString("Content-Location");
	    System.out.println("OK.");
	    return music;
	  }

}
