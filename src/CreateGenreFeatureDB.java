/*
 * echonest api key: WL2G6JXCX8WDOPZLN 
 * java api client: https://github.com/echonest/jEN
 * api tutorials: http://developer.echonest.com/tutorial-overview.html
 * api documentation: http://developer.echonest.com/docs/v4
 */

import java.io.*;

import sun.org.mozilla.javascript.internal.ast.CatchClause;

import com.echonest.api.v4.*;

public class CreateGenreFeatureDB {
	public static final int numGenres = 10, samplesPerGenre = 100;
	public static final String[] genres = {"blues","classical","country","disco","hiphop","jazz","metal","pop","reggae","rock"};
	public static final String echoNestApiKey = "WL2G6JXCX8WDOPZLN";

	public static void main(String[] args) throws IOException{
		EchoNestAPI echonestAPI = new EchoNestAPI(echoNestApiKey);		
		File curSample;
		PrintWriter db = null;
		db = new PrintWriter(new BufferedWriter(new FileWriter(new File("genres.db"),true)));
		db.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		for(int genreIndex = 0; genreIndex < numGenres; genreIndex++) {
			for(int sampleIndex = 0; sampleIndex < samplesPerGenre; sampleIndex++) {
				try {
					curSample = new File("genresamples/" + 												//samples folder
							genres[genreIndex] + "/" + 													//genre folder
							genres[genreIndex] + "." + String.format("%05d", sampleIndex) + ".au");		//audio file
					if (!java.nio.file.Files.exists(curSample.toPath()))
						throw new FileNotFoundException();

					Track curSampleData = null;

					while(true) {
						System.out.println("trying");
						try {
							curSampleData = echonestAPI.uploadTrack(curSample);	//upload
							curSampleData.waitForAnalysis(30000);				//overkill
							if(curSampleData != null) break;
						} catch (EchoNestException e) {
							db.close();
							Thread.sleep(30000);
							db = new PrintWriter(new BufferedWriter(new FileWriter(new File("genres.db"),true)));
						}
					}
					
					if(curSampleData.getStatus() == Track.AnalysisStatus.COMPLETE) {
						System.out.println("successfully got data for " + genres[genreIndex] + sampleIndex);
						String dataToWrite = "";
						dataToWrite += "\n<sampledata>" + 
								"\n\t<name>"+genres[genreIndex]+sampleIndex+"</name>" +
								"\n\t<tempo>" + curSampleData.getTempo() + "</tempo>" + 
								"\n\t<danceability>" + curSampleData.getDanceability() + "</danceability>" + 
								"\n\t<speechiness>" + curSampleData.getSpeechiness() + "</speechiness>" + 
								"\n\t<liveness>" + curSampleData.getLiveness() + "</liveness>" +
								"\n\t<energy>" + curSampleData.getEnergy() + "</energy>" +
								"\n\t<loudness>" + curSampleData.getLoudness() + "</loudness>" +
								"\n</sampledata>";
						//					System.out.println("<sampledata>");
						//					System.out.println("\t<name>"+genres[genreIndex]+sampleIndex+"</name>");
						//					System.out.println("\t<tempo>" + curSampleData.getTempo() + "</tempo>");
						//                    System.out.println("\t<danceability>" + curSampleData.getDanceability() + "</danceability>");
						//                    System.out.println("\t<speechiness>" + curSampleData.getSpeechiness() + "</speechiness>");
						//                    System.out.println("\t<liveness>" + curSampleData.getLiveness() + "</liveness>");
						//                    System.out.println("\t<energy>" + curSampleData.getEnergy() + "</energy>");
						//                    System.out.println("\t<loudness>" + curSampleData.getLoudness() + "</loudness>");
						//                    System.out.println("</sampledata>");
						db.write(dataToWrite);
					} else {
						System.err.println("error with track analysis");
					}
				} catch (IOException | EchoNestException | NullPointerException | InterruptedException e) {e.printStackTrace();}
			}
		}
		db.close();
	}
}