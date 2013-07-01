import java.io.File;
import java.util.HashMap;

import jAudioFeatureExtractor.AudioFeatures.*;
import jAudioFeatureExtractor.jAudioTools.*;

public class GenreDB {
	private static final int sampleCount = 3;
	private static String[] genres = {
		"blues", "classical", "country", "disco", "hiphop",
		"jazz", "metal", "pop", "reggae", "rock"
	};
	public static HashMap<String, double[]> genreVals  = new HashMap<String, double[]>();
		//a hashmap containing information regarding the average values for each
		//particular genre
	public static void printGenreValues() {
		for (String genre:genreVals.keySet()) {
			System.out.print(genre+": "); 
			for (double n:genreVals.get(genre))
				System.out.print(n+", ");
			System.out.println();
		}
	}
	public static void createDB() throws Exception {
		//analyze genre examples and generate a database of findings; compare later
		for (String genre:genres) {
			double[] averageVals = new double[3];
				/* [0] - contains average MFCC
				 * [1] - contains spectral flux
				 * [2] - contains spectral centroid
				 */
			double MFCC_s = 0; //mfcc sum
			//double sF_s = 0; //spectral flux sum
			double bS_s = 0; //beat sum sum
			double sC_s = 0; //spectral centroid sum
			for (int i=1; i<=sampleCount; i++) {
				//100 is the maximum number of samples per genre
				AudioSamples as = new AudioSamples(new File("sound/genresamples/"+genre+"/"+genre+"."+String.format("%05d", i)+".au"), 
						genre+" "+i, false);
				as.normalize();
				double[] samples = as.getSamplesMixedDown();
				MFCC_s += GenreAnalysis.mean(GenreAnalysis.mfccData(samples, as.getSamplingRateAsDouble()));
				bS_s += GenreAnalysis.mean(GenreAnalysis.beatSum(samples, as.getSamplingRateAsDouble()));
				sC_s += GenreAnalysis.mean(GenreAnalysis.spectralCentroid(samples, as.getSamplingRateAsDouble()));
				System.out.println("Working with "+genre+" "+i);
			}
			averageVals[0] = MFCC_s/sampleCount;
			averageVals[1] = bS_s/sampleCount;
			averageVals[2] = sC_s/sampleCount;
			genreVals.put(genre, averageVals); //put average values in for each genre
		}
		printGenreValues(); //debug
	}
}
