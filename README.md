#Hacking Audio and Musical Research 2013
#####Columbia University
- Princeton Ferro
- Ben Iofel
- David Maginley

###Discerning Musical Genres Through the Comparison of Musical Features

####Aim
We aim to explore the possibilities of discerning musical genres, namely by analyzing their characteristics — such as tonality, pitch, modality, and other musical characteristics that can be used to identify genres. Our exploration will seek to identify a genre of a certain song (in MIDI format) by comparing its qualities to a database of previously supplied MIDI files of which the genre is known.

####Differentiation
While our concept, in its most general sense, is far from anything particularly novel and while there do exist other similar music applications currently (such as Pandora and Shazam), these programs suggest similar songs or identify songs. We intend to develop a method to recognize genres, which would use similar techniques but will be different in the output. Our research aims to cover the most important consideration, which are the actual musical components that present an aestheticism to the listener. Using this information, we hypothesize, has the potential great accuracy if implemented properly.

####Methodology
We seek to accomplish this by a general procedure, optimistically using our resources and capabilities to the best of our current understanding. These resources include:
* A method of acquiring real-time audio input (a microphone should do)
* A cross-platform programming language (ideally Java)
* Any additional APIs, built-in, or external libraries that will enable our program to read MIDI files and extract musical features from them

####Constraints
One of the difficulties with audio input is finding a proper programming language and libraries to accomplish this in a functional way, while minding performance. In terms of performance, C becomes a viable language, although we wish to make this cross-platform. As a result, in terms of cross-platform compatibility, it appears that Java is the ideal language of choice, for its "compile once, run anywhere" . However, this poses a few additional problems that wouldn’t be present in C, such as performance - the JVM is limited to native C in terms of speed, for some operations such as getting raw audio data. On the other hand, Java has a range of built in libraries, so we would be able to use its MIDI libraries with out any hassle.

Note: while they're unlikely to be used, other programming languages might have certain benefits in this field and allow for cross-platformness. However, Java is being used due to our proficiency with this language.

####Conclusion
Through the analysis of qualitative musical characteristics, an accurate guess for genre will be determined. It is suspected that this approach will allow for improvements in the quality of song recommendations. This is going to be accomplished with the use of MIDI audio input, a cross-platform programming language, as well as audio processing APIs. Through our pursuit in this goal, we hope to gain knowledge through the necessary research relating to machine learning and programmatic music analysis.

Original abstract [here](https://docs.google.com/document/d/1QoTRFzYsAdKLGDw8dEG460pqgEuyAR_iNE0yy5v7zOg/edit?usp=sharing).
