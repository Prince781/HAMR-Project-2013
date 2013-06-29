import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class CreateGenreFeatureDB {
	public static final int sampleAmount = 100;
		//the default sample amount within audio file
	public static final String[] genres = {"blues","classical","country","disco","hiphop","jazz","metal","pop","reggae","rock"};
	public static void main(String[] args) throws Exception {
		AudioInputStream audioStrm;
		int totalFramesRead = 0, bytesPerFrame, numBytes;
		
		for(int genreIndex=0;genreIndex<genres.length;genreIndex++) for(int sampleIndex=0;sampleIndex<100;sampleIndex++) {
			String filepath = "genresamples/" + genres[genreIndex] + "/" + genres[genreIndex] + "." + String.format("%05d", sampleIndex) + ".au";
			audioStrm = AudioSystem.getAudioInputStream(new File(filepath));
			bytesPerFrame = audioStrm.getFormat().getFrameSize();
			//if the audio format has an unspecified frame size, read any amount of bytes
			if(bytesPerFrame == AudioSystem.NOT_SPECIFIED) bytesPerFrame = 1;
			byte[] audioBytes = new byte[1024 * bytesPerFrame];
		}
	}
}