import java.io.*;
import java.util.*;
import javax.sound.sampled.*;
import jAudioFeatureExtractor.jAudioTools.*;

public class CreateGenreFeatureDB {
	public static final int numGenres = 10, samplesPerGenre = 100;
	public static final String[] genres = {"blues","classical","country","disco","hiphop","jazz","metal","pop","reggae","rock"};

	public static void main(String[] args) throws Exception{
		File curSample;
		AudioInputStream audioStrm;
		AudioSamples sample;
		HashMap<String, Double> curSampleFeatures;
		List<Map<String, Double>> genreAvgFeatures = new ArrayList<Map<String, Double>>();
		
		for(int genreIndex = 0; genreIndex < numGenres; genreIndex++) {
			for(int sampleIndex = 0; sampleIndex < samplesPerGenre; sampleIndex++) {
				curSample = new File("genresamples/" + 												//samples folder
						genres[genreIndex] + "/" + 													//genre folder
						genres[genreIndex] + "." + String.format("%05d", sampleIndex) + ".au");		//audio file
				if (!java.nio.file.Files.exists(curSample.toPath())) throw new FileNotFoundException();	//make sure it exists
				
				audioStrm = AudioSystem.getAudioInputStream(curSample);								//load .au file
				sample = new AudioSamples(audioStrm, (genres[genreIndex]+sampleIndex), true);		//sample object for feature extraction
				curSampleFeatures = new FeatureExtraction(sample).getFeatureData();
			}
		}
		
		//average or range the feature data here
		
		ObjectOutputStream dbWriteStream = new ObjectOutputStream(new FileOutputStream(new File("genres.db")));
		dbWriteStream.writeObject(genreAvgFeatures);
		dbWriteStream.close();
	}
}