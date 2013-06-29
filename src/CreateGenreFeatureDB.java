import java.io.*;
import javax.sound.sampled.*;
import sun.audio.*;

public class CreateGenreFeatureDB {
	public static final int numGenres = 10, samplesPerGenre = 100;
	public static final String[] genres = {"blues","classical","country","disco","hiphop","jazz","metal","pop","reggae","rock"};

	public static void main(String[] args){		
		AudioStream audioStrm;
		String filepath;

		for(int genreIndex = 0; genreIndex < numGenres; genreIndex++) {
			for(int sampleIndex = 0; sampleIndex < samplesPerGenre; sampleIndex++) {
				filepath = "genresamples/" + 														//samples folder
						genres[genreIndex] + "/" + 													//genre folder
						genres[genreIndex] + "." + String.format("%05d", sampleIndex) + ".au";		//audio file
				try {
					audioStrm = new AudioStream(new FileInputStream(filepath));						//load .au file
				} catch (IOException e) {
					System.out.println("IO Error with " + filepath);
				}
			}
		}
	}
}