import java.util.HashMap;
import jAudioFeatureExtractor.jAudioTools.AudioSamples;
import jAudioFeatureExtractor.AudioFeatures.*;

public class FeatureExtraction {	
	BeatHistogram beatHistogram;
	HashMap<String, Double> curSampleData = new HashMap<String, Double>();
	public FeatureExtraction(AudioSamples curSample) throws Exception {
		beatHistogram = new BeatHistogram();
		beatHistogram.extractFeature(curSample.getSamplesMixedDown(), curSample.getSamplingRateAsDouble(), null);
	}
	public HashMap<String, Double> getFeatureData(){
		return null;
	}
	
}
