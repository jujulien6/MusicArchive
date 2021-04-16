package music.management.web.data;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
/**
*@author DJ
*Class who contains most of element Music
*/
@XmlRootElement
public class Music {
	private Integer id;
	private String artist_name;
	private ArrayList<String> song = new ArrayList<String>();
	private String information;
	
	public Music() {}
	
	public Music(String artist_name, ArrayList<String> song, String information,int id) {
		this.artist_name = artist_name;
		this.song = song;
		this.information = information;
		this.id = id;
	}
	
	public Music( String artist_name, ArrayList<String> song, String information) {
		this.artist_name = artist_name;
		this.song = song;
		this.information = information;
		this.id=null;
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ArrayList<String> getSong() {
		return song;
	}

	public void setSong(ArrayList<String> song) {
		this.song = song;
	}

	public String getArtist_name() {
		return artist_name;
	}

	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}
	
	/**
	 * @return information of music class
	 */
	@Override
	public String toString() {
		return "Music [id=" + id + ", artist_name=" + artist_name + ", song=" + song + ", information=" + information
				+ "]";
	}

	

}
