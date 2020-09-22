# Customised Story
In this project users can create a customised story based off their responses to a series of supplied prompts. The user is offered two themes for their story, a superhero story or an adventure story. The user input is manipulated where necessary and fed into a generic template according to their selection. The completed story is then made available to the user as a PDF. 

![Animation of user input](https://media.giphy.com/media/fwW34xo84PhmjT7Wo5/giphy.gif)
## Setup
- Download or clone the project
- Configure a path for the following external Java libraries according to your IDE
-- `commons-logging.jar`
-- `fontbox.jar`
-- `pdfbox.jar`
-- `pdfbox-tools.jar`
--`preflight.jar`
-- `xmpbox.jar`

	Click here to download the most recent version of [PDFBox](https://pdfbox.apache.org/download.cgi#20x ) and [Apache Commons Logging](
 https://commons.apache.org/proper/commons-logging/download_logging.cgi)    
   
	**Note:** These `jar`files are included in the repository and are up to date as of release of the project
- Run `MyStory.java`and follow in application instructions

## About
This project was undertaken with the aim of better understanding  I/O and to implement  basic OOP concepts.

The project is reliant on the built-in Java I/O package, as well as the Apache PDFBox library. Since Java has no built- in support for PDFs is was necessary to use an external library. The Apache PDFBox library created the PDF itself, while the built-in Java I/O package was required to read in the story template.  `java.io.InputStreamReader` in combination with `java.io.BufferedReader`was used to accept user input. In certain instances the Scanner class, part of the `java.util`package, was preferable for accepting user input. 

The project applies basic OOP concepts, namely the creation of objects and use of inheritance. Both stories are created in same manner and rely on the same methods. Therefore, it was logical to have a super class for these common methods to avoid code duplication.





