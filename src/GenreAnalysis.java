import jAudioFeatureExtractor.jAudioTools.AudioSamples;
import jAudioFeatureExtractor.AudioFeatures.*;

import java.io.*;
import java.util.HashMap;
import java.util.Random;

public class GenreAnalysis {
	private static final boolean optimize = false;
		//whether or not to optimize the process
	private static final double clipLength = 3.0;
		/* the maximum clip length of the taken sample from
		 * our example audio source
		 */
	public static double[] mfccData(double[] sample_data, double sample_rate) throws Exception {
		MFCC mfcc = new MFCC();
		MagnitudeSpectrum ms = new MagnitudeSpectrum();
		double[] msData = ms.extractFeature(sample_data, sample_rate, null);
		return mfcc.extractFeature(sample_data, sample_rate, new double[][]{msData});
	}
	public static double[] beatSum(double[] sample_data, double sample_rate) throws Exception {
		BeatSum bSum = new BeatSum();
		BeatHistogram bH = new BeatHistogram();
		RMS rms = new RMS();
		double[] rmsData = rms.extractFeature(sample_data, sample_rate, null);
		double[][] rmsValues = new double[256][rmsData.length];
		for (int i=0; i<256; i++) rmsValues[i] = rmsData;
			//copy into 256 windows
		double[] bHData = bH.extractFeature(sample_data, sample_rate, rmsValues);
		return bSum.extractFeature(sample_data, sample_rate, new double[][]{bHData});
	}
	public static double[] spectralFlux(double[] sample_data, double sample_rate) throws Exception {
		SpectralFlux sf = new SpectralFlux();
		MagnitudeSpectrum ms = new MagnitudeSpectrum();
		double[] msData = ms.extractFeature(sample_data, sample_rate, null);
		double[][] msDataVals = new double[2][msData.length];
		for (int i=0; i<2; i++)
			msDataVals[i] = msData;
		return sf.extractFeature(sample_data, sample_rate, msDataVals);
	}
	public static double[] spectralCentroid(double[] sample_data, double sample_rate) throws Exception {
		SpectralCentroid sc = new SpectralCentroid();
		PowerSpectrum ps = new PowerSpectrum();
		double[] psData = ps.extractFeature(sample_data, sample_rate, null);
		return sc.extractFeature(sample_data, sample_rate, new double[][]{psData});
	}
	public static double mean(double[] data_set) {
		double sum = 0;
		for (int i=0; i<data_set.length; i++) sum += data_set[i];
		return sum/data_set.length;
	}
	public static void findClosest(HashMap<String, double[]> points, double[] val) {
		//using Euclidean Distance, find the closest value
		HashMap<String, Double> distances = new HashMap<String, Double>();
		for (String genre:points.keySet()) {
			double[] pnts = points.get(genre);
			distances.put(genre, 
					Math.sqrt(
							Math.pow(pnts[0]-val[0], 2)
						+	Math.pow(pnts[1]-val[1], 2)
						+	Math.pow(pnts[2]-val[2], 2)
					)
			); //distance formula
		}
	}
	public static void main(String[] args) throws Exception {
		/* get genre information about the sample, and then compare it to
		 * our database
		 */
		AudioSamples as = new AudioSamples(new File("sound/fireflies.wav"), "Demo Sound", false);
		double endClip = clipLength + (new Random()).nextDouble()*(as.getDuration()-clipLength);
			//speed tweak: only compare a few seconds of the song, at random
		as.normalize(); //normalize the audio: combine channels into one
		double[] samples = optimize ? as.getSamplesChannelSegregated(endClip-clipLength, endClip)[0] : as.getSamplesMixedDown();
			//here, we have a random sampled portion of music to work with
		double[] averages = {
			mean(mfccData(samples, as.getSamplingRateAsDouble())),
			mean(beatSum(samples, as.getSamplingRateAsDouble())),
			mean(spectralCentroid(samples, as.getSamplingRateAsDouble())) 
		};
		
		//debug
		System.out.println("Our song: ");
		for (double n:averages) System.out.print(n+", ");
		System.out.println();
		//TODO: System.out.println(<<<guessed genre>>>);
	}
}
