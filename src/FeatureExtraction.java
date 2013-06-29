import java.io.*;
import java.nio.file.*;
import jAudioFeatureExtractor.*;

public class FeatureExtraction {
	public FeatureExtraction(File file) throws Exception {
		if (!Files.exists(file.toPath()))
			throw new Exception();
		
	}
}
