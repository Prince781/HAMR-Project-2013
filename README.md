#Hacking Audio and Musical Research 2013
#####Columbia University
- Princeton Ferro
- Ben Iofel
- David Maginley

###Discerning Musical Preferences Based on User-Defined Input

####Aim
We aim to explore the possibilities of discerning musical preferences based on a user-defined set of musical pieces, namely by analyzing their characteristics—such as tonality, pitch, modality, and other musical characteristics that can be used to identify general taste based on an analysis of the input of the program. Our exploration will seek to find an ideal suggestion for the user based on qualitative analysis.

####Differentiation
While our concept, in its most general sense, is far from anything particularly novel and while there do exist music suggestion applications currently (such as Pandora and Shazam), these programs tend to suggest songs to a user based on non-musical qualitative data—for example, aggregating similar artists and genre associations, as well as collaborators, may be one method by which these applications suggest new music pieces to the user. Our research aims to cover the most important consideration that seems absent here, which are the actual musical components that present an aestheticism to the listener. Using this information, we hypothesize, has the potential for far more accurate suggestions if implemented properly.

####Methodology
We seek to accomplish this by a general procedure, optimistically using our resources and capabilities to the best of our current understanding. These resources include:
* A method of acquiring real-time audio input (a microphone should do)
* A cross-platform programming language (ideally Java) that can accomplish operating system agnosticism
* Any additional APIs that simplify the audio processing hurdle of our program

####Constraints
One of the difficulties with real-time audio input is finding a proper programming language and libraries to accomplish this in a functional way, while minding performance. In terms of performance, C becomes a viable language, although we intend to make this entirely cross-platform. As a result, in terms of cross-platform compatibility, it appears that Java is the ideal language of choice. However, this poses a few additional problems that wouldn’t be present in C:
* Performance - the JVM is severely limited to native C in terms of speed, for low-level operations such as "listening" through a microphone and getting raw audio data
* Libraries - Java might have some built-in libraries for this task, but C’s built-in libraries may be more optimal for our tasks, in terms of functionality and low-level optimization

While it’s likely far from decision, Ruby, C#, and Python are potential cross-platform alternatives.

####Conclusion
Through the analysis of qualitative musical characteristics, an optimal suggestion method is to be determined. It is suspected that this approach will allow for song recommendations that are more accurate than those determined by genre or artist. This is going to be accomplished with the use of audio input, a cross-platform programming language, as well as audio processing APIs. By our pursuit in this goal, we hope to gain knowledge through the necessary research relating to machine learning and programmatic music analysis.

Original abstract [here](https://docs.google.com/document/d/1QoTRFzYsAdKLGDw8dEG460pqgEuyAR_iNE0yy5v7zOg/edit?usp=sharing).
