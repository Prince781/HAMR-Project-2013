import java.util.HashMap;
import jAudioFeatureExtractor.jAudioTools.AudioSamples;
import jAudioFeatureExtractor.AudioFeatures.*;

public class FeatureExtraction {	
	Compactness com;
	HashMap<String, Double> curSampleData = new HashMap<String, Double>();
	public FeatureExtraction(AudioSamples curSample) throws Exception {
		com = new Compactness();
		double[] result = com.extractFeature(
				curSample.getSamplesMixedDown(), 
				curSample.getSamplingRateAsDouble(),
				getOtherFeatureValues(com.getDepenedencies(), curSample));
		for(double i:result) System.out.println(i);
	}
	public HashMap<String, Double> getFeatureData(){
		return null;
	}
	double[][] getOtherFeatureValues(String[] features, AudioSamples curSample) throws Exception{
		if(features == null) return null;
		System.out.println("getting dependencies: ");
		for(String s:features) System.out.println(s);
		
		double[][] result = new double[features.length][];
		for(int i = 0; i < features.length; i++) {
			if(features[i].equals("Area Method of Moments")) 							result[i] = new AreaMoments().extractFeature(curSample.getSamplesMixedDown(), curSample.getSamplingRateAsDouble(), getOtherFeatureValues(new AreaMoments().getDepenedencies(), curSample));
			else if(features[i].equals("Beat Histogram")) 								result[i] = new BeatHistogram().extractFeature(curSample.getSamplesMixedDown(), curSample.getSamplingRateAsDouble(), getOtherFeatureValues(new BeatHistogram().getDepenedencies(), curSample));
			else if(features[i].equals("Beat Histogram Bin Labels")) 					result[i] = new BeatHistogramLabels().extractFeature(curSample.getSamplesMixedDown(), curSample.getSamplingRateAsDouble(), getOtherFeatureValues(new BeatHistogramLabels().getDepenedencies(), curSample));
			else if(features[i].equals("Beat Sum")) 									result[i] = new BeatSum().extractFeature(curSample.getSamplesMixedDown(), curSample.getSamplingRateAsDouble(), getOtherFeatureValues(new BeatSum().getDepenedencies(), curSample));
			else if(features[i].equals("Compactness")) 									result[i] = new Compactness().extractFeature(curSample.getSamplesMixedDown(), curSample.getSamplingRateAsDouble(), getOtherFeatureValues(new Compactness().getDepenedencies(), curSample));
			else if(features[i].equals("FFT Bin Frequency Labels")) 					result[i] = new FFTBinFrequencies().extractFeature(curSample.getSamplesMixedDown(), curSample.getSamplingRateAsDouble(), getOtherFeatureValues(new FFTBinFrequencies().getDepenedencies(), curSample));
			else if(features[i].equals("Fraction Of Low Energy Windows")) 				result[i] = new FractionOfLowEnergyWindows().extractFeature(curSample.getSamplesMixedDown(), curSample.getSamplingRateAsDouble(), getOtherFeatureValues(new FractionOfLowEnergyWindows().getDepenedencies(), curSample));
			else if(features[i].equals("Partial Based Spectral Centroid")) 				result[i] = new HarmonicSpectralCentroid().extractFeature(curSample.getSamplesMixedDown(), curSample.getSamplingRateAsDouble(), getOtherFeatureValues(new HarmonicSpectralCentroid().getDepenedencies(), curSample));
			else if(features[i].equals("Partial Based Spectral Flux"))					result[i] = new HarmonicSpectralFlux().extractFeature(curSample.getSamplesMixedDown(), curSample.getSamplingRateAsDouble(), getOtherFeatureValues(new HarmonicSpectralFlux().getDepenedencies(), curSample));
			else if(features[i].equals("Peak Based Spectral Smoothness")) 				result[i] = new HarmonicSpectralSmoothness().extractFeature(curSample.getSamplesMixedDown(), curSample.getSamplingRateAsDouble(), getOtherFeatureValues(new HarmonicSpectralSmoothness().getDepenedencies(), curSample));
			else if(features[i].equals("LPC")) 											result[i] = new LPC().extractFeature(curSample.getSamplesMixedDown(), curSample.getSamplingRateAsDouble(), getOtherFeatureValues(new LPC().getDepenedencies(), curSample));
			else if(features[i].equals("Magnitude Spectrum")) 							result[i] = new MagnitudeSpectrum().extractFeature(curSample.getSamplesMixedDown(), curSample.getSamplingRateAsDouble(), getOtherFeatureValues(new MagnitudeSpectrum().getDepenedencies(), curSample));
			else if(features[i].equals("MFCC")) 										result[i] = new MFCC().extractFeature(curSample.getSamplesMixedDown(), curSample.getSamplingRateAsDouble(), getOtherFeatureValues(new MFCC().getDepenedencies(), curSample));
			else if(features[i].equals("Method of Moments")) 							result[i] = new Moments().extractFeature(curSample.getSamplesMixedDown(), curSample.getSamplingRateAsDouble(), getOtherFeatureValues(new Moments().getDepenedencies(), curSample));
			else if(features[i].equals("Peak Detection")) 								result[i] = new PeakFinder().extractFeature(curSample.getSamplesMixedDown(), curSample.getSamplingRateAsDouble(), getOtherFeatureValues(new PeakFinder().getDepenedencies(), curSample));
			else if(features[i].equals("Power Spectrum")) 								result[i] = new PowerSpectrum().extractFeature(curSample.getSamplesMixedDown(), curSample.getSamplingRateAsDouble(), getOtherFeatureValues(new PowerSpectrum().getDepenedencies(), curSample));
			else if(features[i].equals("Relative Difference Function")) 				result[i] = new RelativeDifferenceFunction().extractFeature(curSample.getSamplesMixedDown(), curSample.getSamplingRateAsDouble(), getOtherFeatureValues(new RelativeDifferenceFunction().getDepenedencies(), curSample));
			else if(features[i].equals("Root Mean Square")) 							result[i] = new RMS().extractFeature(curSample.getSamplesMixedDown(), curSample.getSamplingRateAsDouble(), getOtherFeatureValues(new RMS().getDepenedencies(), curSample));
			else if(features[i].equals("Spectral Centroid")) 							result[i] = new SpectralCentroid().extractFeature(curSample.getSamplesMixedDown(), curSample.getSamplingRateAsDouble(), getOtherFeatureValues(new SpectralCentroid().getDepenedencies(), curSample));
			else if(features[i].equals("Spectral Flux")) 								result[i] = new SpectralFlux().extractFeature(curSample.getSamplesMixedDown(), curSample.getSamplingRateAsDouble(), getOtherFeatureValues(new SpectralFlux().getDepenedencies(), curSample));
			else if(features[i].equals("Spectral Rolloff Point")) 						result[i] = new SpectralRolloffPoint().extractFeature(curSample.getSamplesMixedDown(), curSample.getSamplingRateAsDouble(), getOtherFeatureValues(new SpectralRolloffPoint().getDepenedencies(), curSample));
			else if(features[i].equals("Spectral Variability")) 						result[i] = new SpectralVariability().extractFeature(curSample.getSamplesMixedDown(), curSample.getSamplingRateAsDouble(), getOtherFeatureValues(new SpectralVariability().getDepenedencies(), curSample));
			else if(features[i].equals("Strength Of Strongest Beat")) 					result[i] = new StrengthOfStrongestBeat().extractFeature(curSample.getSamplesMixedDown(), curSample.getSamplingRateAsDouble(), getOtherFeatureValues(new StrengthOfStrongestBeat().getDepenedencies(), curSample));
			else if(features[i].equals("Strongest Beat")) 								result[i] = new StrongestBeat().extractFeature(curSample.getSamplesMixedDown(), curSample.getSamplingRateAsDouble(), getOtherFeatureValues(new StrongestBeat().getDepenedencies(), curSample));
			else if(features[i].equals("Strongest Frequency Variability"))				result[i] = new StrongestFrequencyVariability().extractFeature(curSample.getSamplesMixedDown(), curSample.getSamplingRateAsDouble(), getOtherFeatureValues(new StrongestFrequencyVariability().getDepenedencies(), curSample));
			else if(features[i].equals("Strongest Frequency Via FFT Maximum")) 			result[i] = new StrongestFrequencyViaFFTMax().extractFeature(curSample.getSamplesMixedDown(), curSample.getSamplingRateAsDouble(), getOtherFeatureValues(new StrongestFrequencyViaFFTMax().getDepenedencies(), curSample));
			else if(features[i].equals("Strongest Frequency Via Spectral Centroid")) 	result[i] = new StrongestFrequencyViaSpectralCentroid().extractFeature(curSample.getSamplesMixedDown(), curSample.getSamplingRateAsDouble(), getOtherFeatureValues(new StrongestFrequencyViaSpectralCentroid().getDepenedencies(), curSample));
			else if(features[i].equals("Strongest Frequency Via Zero Crossings")) 		result[i] = new StrongestFrequencyViaZeroCrossings().extractFeature(curSample.getSamplesMixedDown(), curSample.getSamplingRateAsDouble(), getOtherFeatureValues(new StrongestFrequencyViaSpectralCentroid().getDepenedencies(), curSample));
			else if(features[i].equals("Zero Crossings")) 								result[i] = new ZeroCrossings().extractFeature(curSample.getSamplesMixedDown(), curSample.getSamplingRateAsDouble(), getOtherFeatureValues(new ZeroCrossings().getDepenedencies(), curSample));
			else System.out.println("Unaccounted feature: " + features[i]);
		}
		return result;
	}
}
