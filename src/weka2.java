import java.util.ArrayList;

import weka.core.*;
import weka.core.neighboursearch.LinearNNSearch;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Evaluation;

public class weka2{
	public static void main(String[] args) throws Exception{
		int numSamples = 1000;
		
		Attribute one = new Attribute("one");
		Attribute two = new Attribute("two");
		Attribute thr = new Attribute("thr");
		Attribute[] list = {one, two, thr};
		
		FastVector classVals = new FastVector(11);
		ArrayList<String> temp = new ArrayList<String>();
		String[] tempArr = {"blues","classical","country","disco","hiphop","jazz","metal","pop","reggae","rock"};
		//change tempArr to the genre array in CreateFeatureDB
		for (String x: tempArr){
			classVals.addElement(x);
		}
		classVals.addElement("none");
		Attribute classA = new Attribute("Class", classVals);
		
		FastVector features = new FastVector(list.length+1);
		for (Attribute a : list){
			features.addElement(a);
		}
		features.addElement(classA);
		
		Instances trainingSet = new Instances("train", features, numSamples);
		trainingSet.setClassIndex(list.length);
		
		//change to iterate through all the sample db songs
		for (int r = 0; r < numSamples; r++){
			Instance inst = new DenseInstance(4);
			inst.setValue((Attribute)features.elementAt(0), 1.0 /*value for one*/);
			inst.setValue((Attribute)features.elementAt(1), 1.0 /*value for two*/);
			inst.setValue((Attribute)features.elementAt(2), 1.0 /*value for thr*/);
			inst.setValue((Attribute)features.elementAt(3), "" /*genre string*/);
			trainingSet.add(inst);
		}
		//here's where we start iterating through database or wherever we get static genre data
		//create Instances with list.length+1 size and eventually add to training set
		
		//similar thing done for testing set, but with input data and none as genre
		//can change 1 to however many number of songs you want to take in as input
		Instances testingSet = new Instances("test", features, 1);
		testingSet.setClassIndex(list.length);
		Instance test = new DenseInstance(4);
		test.setValue((Attribute)features.elementAt(0), 1.0 /*value for one*/);
		test.setValue((Attribute)features.elementAt(1), 1.0 /*value for two*/);
		test.setValue((Attribute)features.elementAt(2), 1.0 /*value for thr*/);
		test.setValue((Attribute)features.elementAt(3), "none");
		testingSet.add(test);
		
		LinearNNSearch lin = new LinearNNSearch();
		lin.setDistanceFunction(new EuclideanDistance(testingSet));
		lin.setInstances(trainingSet);
		Instances w = lin.kNearestNeighbours(test, 5);
		System.out.println(w.toArray()[0]);
		
	}
	
	public static void compareValues(double[] avgVals, double[] gAvgVals, int kNum) throws Exception{
		//change to return instances?
		//with G at the end: the test genre values
		//else: the individual song
		//get coefficient, showing how close mfccD and bSum are to mfccDG bSumG
		
		//insert manipulation of data, should just be averaging maybe?
		
		
	}
}
