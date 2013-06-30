import java.io.CharConversionException;
import java.io.File;
import java.util.*;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

import weka.core.*;
import weka.core.neighboursearch.LinearNNSearch;

import com.echonest.api.v4.*;
import com.echonest.api.v4.Track.AnalysisStatus;

public class GenreRecognizer {
//	static HashMap<String, double[]> genreMap = new HashMap<String, double[]>();
	public static final String echoNestApiKey = "WL2G6JXCX8WDOPZLN";
	public static void main(String[] args) throws Exception {
		/*
		 * i:		   1	 2		  3 			4			5		 6		  7
		 * something name, tempo, danceability, speechiness, liveness, energy, loudness
		 * sampledata.item(i).getTextContent() should give you the value
		 */

		//load genres.db into java's xml parser
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("genres.db"));
		doc.getDocumentElement().normalize(); //clean up the xml
		NodeList sampleData = doc.getFirstChild().getChildNodes();

		final ArrayList<Attribute> musicalFeatures = new ArrayList<Attribute>() {{
			add(new Attribute("Genres", Arrays.asList(CreateGenreFeatureDB.genres)));
			add(new Attribute("Tempo"));
			add(new Attribute("Danceability"));
			add(new Attribute("Speechiness"));
			add(new Attribute("Liveness"));
			add(new Attribute("Energy"));
			add(new Attribute("Loudness"));
		}};
		Instances trainingSet = new Instances("train", musicalFeatures, (CreateGenreFeatureDB.numGenres*CreateGenreFeatureDB.samplesPerGenre));
		trainingSet.setClassIndex(0);
		
		//each sample is an instance
		
		if(sampleData == null) {System.out.println(""); System.exit(1);}
//		double[] avgs = new double[6]; //tempo, danceability, speechiness, liveness, energy, loudness
		
		NodeList sampleDataList = doc.getElementsByTagName("sampledata");
		for(int genre = 0; genre < CreateGenreFeatureDB.numGenres; genre++) {
			System.out.println("genre: " +CreateGenreFeatureDB.genres[genre]);
			for(int sampleIndex = 0; sampleIndex < CreateGenreFeatureDB.samplesPerGenre; sampleIndex++) {
				DenseInstance sample = new DenseInstance(7);
				int sampleDataIndex = genre*100+sampleIndex;
				
				String name = sampleDataList.item(sampleDataIndex).getChildNodes().item(1).getTextContent();
				int i;
				for(i=0;i<name.length();i++) if(Character.isDigit(name.charAt(i))) break;
				sample.setValue(musicalFeatures.get(0), sampleDataList.item(sampleDataIndex).getChildNodes().item(1).getTextContent().substring(0, i)); //genre
				sample.setValue(musicalFeatures.get(1), Double.parseDouble(sampleDataList.item(sampleDataIndex).getChildNodes().item(3).getTextContent())); //tempo
				sample.setValue(musicalFeatures.get(2), Double.parseDouble(sampleDataList.item(sampleDataIndex).getChildNodes().item(5).getTextContent())); //danceability
				sample.setValue(musicalFeatures.get(3), Double.parseDouble(sampleDataList.item(sampleDataIndex).getChildNodes().item(7).getTextContent())); //speechiness
				sample.setValue(musicalFeatures.get(4), Double.parseDouble(sampleDataList.item(sampleDataIndex).getChildNodes().item(9).getTextContent())); //liveness
				sample.setValue(musicalFeatures.get(5), Double.parseDouble(sampleDataList.item(sampleDataIndex).getChildNodes().item(11).getTextContent())); //energy
				sample.setValue(musicalFeatures.get(6), Double.parseDouble(sampleDataList.item(sampleDataIndex).getChildNodes().item(13).getTextContent())); //loudness
				
				trainingSet.add(sample);
			}
		}	

		//prompt user
		EchoNestAPI echonestAPI = new EchoNestAPI(echoNestApiKey);	
		File file = null;
		while(file == null || !java.nio.file.Files.exists(file.toPath())) {
			System.out.print("\nplease enter a file path to recognize genre: ");
			file = new File(new Scanner(System.in).next());
		}
		Track testTrack = echonestAPI.uploadTrack(file);
		while(testTrack.getStatus() != AnalysisStatus.COMPLETE);
		final double[] testTrackData = {
				testTrack.getTempo(),
				testTrack.getDanceability(),
				testTrack.getSpeechiness(),
				testTrack.getLiveness(),
				testTrack.getEnergy(),
				testTrack.getLoudness()
		};
		
		Instances testingSet = new Instances("test",musicalFeatures,1);
		testingSet.add(new DenseInstance(7) {{
			setValue(musicalFeatures.get(0), "genre");
			setValue(musicalFeatures.get(1), testTrackData[0]); //tempo
			setValue(musicalFeatures.get(2), testTrackData[1]); //danceability
			setValue(musicalFeatures.get(3), testTrackData[2]); //speechiness
			setValue(musicalFeatures.get(4), testTrackData[3]); //liveness
			setValue(musicalFeatures.get(5), testTrackData[4]); //energy
			setValue(musicalFeatures.get(6), testTrackData[5]); //loudness
		}});
		
		LinearNNSearch lin = new LinearNNSearch();
		lin.setDistanceFunction(new EuclideanDistance(testingSet));
		lin.setInstances(trainingSet);
		Instances result = lin.kNearestNeighbours(testingSet.firstInstance(), 3);
		for(Object obj:result.toArray()) System.out.println(obj);
		
//		if(sampleData == null) {System.out.println(""); System.exit(1);}
//		double[] avgs = new double[6]; //tempo, danceability, speechiness, liveness, energy, loudness
//		NodeList sampleDataList = doc.getElementsByTagName("sampledata");
//		for(int genre = 0; genre < CreateGenreFeatureDB.numGenres; genre++) {
//			System.out.println("genre: " +CreateGenreFeatureDB.genres[genre]);
//			for(int sample = 0; sample < CreateGenreFeatureDB.samplesPerGenre; sample++) {
//				int sampleDataIndex = genre*100+sample;
//				for(int item=0;item<6;item++) {
//					avgs[item] += Double.parseDouble(sampleDataList.item(sampleDataIndex).getChildNodes().item(item*2+3).getTextContent());
//				}
//			}
//			for(int i=0;i<avgs.length;i++) avgs[i] /= CreateGenreFeatureDB.samplesPerGenre;
//			genreMap.put(CreateGenreFeatureDB.genres[genre], avgs.clone());
//			Arrays.fill(avgs, 0);
//		}
//		System.out.println("genre\t\t\ttempo\tdanceability speechiness liveness energy\tloudness");
//		for(String s:CreateGenreFeatureDB.genres) {
//			System.out.print("\n" + s+": \t\t");
//			if(s.equals("metal") || s.equals("jazz") || s.equals("disco") || s.equals("rock") || s.equals("pop") || s.equals("blues")) System.out.print("\t");
//			for(double d:genreMap.get(s)) System.out.print(String.format("%5f", d) + ", "/*d +", "*/);
//		}

		
//		String bestMatch = "";		
//		double matchdist = Double.MAX_VALUE;
//		for(int genre=0;genre<CreateGenreFeatureDB.genres.length;genre++) {
//			String g = CreateGenreFeatureDB.genres[genre];
//			System.out.println("comparing with genre: " + g);
//			double dist = compare(genreMap.get(g),testTrackData);
//			if(dist == -1) continue;
//			if (dist < matchdist) {
//				matchdist = dist;
//				bestMatch = CreateGenreFeatureDB.genres[genre];
//			}
//		}
//		System.out.println("\ngenre: " + bestMatch + "\tdistance: " + matchdist);
	}


	public static double compare(double[] x, double[] y){
		double val = 0;
		if(x.length == y.length) {
			for (int w = 0; w < x.length; w++) val += Math.pow((x[w] - y[w]), 2);
			System.out.println("distance: " + Math.sqrt(val));
			return Math.sqrt(val);
		} else return -1.0;
	}
}
