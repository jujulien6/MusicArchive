package music.management.web.service;

import java.util.*;

import music.management.web.data.Music;

/**
 * 
 * @author DJ
 *MusicService
 */
public class MusicService {
	
	private static Map<Integer, Music> MUSIQUE_DATA = new HashMap<Integer, Music>();
	
	/**
	 * 
	 * @return Id for Artist
	 */
	 private int getNewId() {
		    int newId = 0;
		    for (int id : MUSIQUE_DATA.keySet()) {
		      if (newId < id)
		        newId = id;
		    }
		    return ++newId;
		  }
	 
	 /**
	  * 
	  * @param s 
	  * @return music s
	  */
	 public Music addArtist(Music s) {
		    int id = getNewId();
		    if(MUSIQUE_DATA.get(s.getId()) != null) {
		      return null;
		    }
		    s.setId(id);
		    MUSIQUE_DATA.put(id, s);
		    return s;
		  }
	 
	 /**
	  * 
	  * @param id
	  * @param musique
	  * @return musique for an artist
	  */
	 public String AddSongForArtist(int id,String musique) {
		 MUSIQUE_DATA.get(id).getSong().add(musique);
		 return musique;
	 }
	 
	 /**
	  * 
	  * @param id
	  * @param s
	  * @return change of artist information
	  */
	 public Music changeArtist(int id, Music s) {
		    if(MUSIQUE_DATA.get(s.getId()) == null) {
		      return null;
		    }
		    MUSIQUE_DATA.put(id, s);
		    return s;
		  }
	 
	 /**
	  * 
	  * @param id
	  * @return true, if you can delete the artist, and false is the artist doesn't exist
	  */
	  public boolean deleteArtist(int id) {
		    if(MUSIQUE_DATA.get(id) == null) {
		      return false;
		    }
		    MUSIQUE_DATA.remove(id);
		    return true;
		  }
	  
	  /**
	   * 
	   * @param id
	   * @return an artist
	   */
	  public Music getArtist(int id) {
		    return MUSIQUE_DATA.get(id);
		  }
	  
	  /**
	   * 
	   * @param id
	   * @return arrayList song of the artist
	   */
	public ArrayList<String> getSongByArtist(int id) {
		  
		  return MUSIQUE_DATA.get(id).getSong();
			  
		  
		  }
		  
	/**
	 * 
	 * @return an HashMap of all the artist
	 */
	  	public Music[] getAllArtist() {
		    Set<Integer> ids = MUSIQUE_DATA.keySet();
		    Music[] s = new Music[ids.size()];
		    int i = 0;
		    for(Integer id : ids) {
		      s[i] = MUSIQUE_DATA.get(id);
		      i++;
		    }
		    return s;
		  }


}
