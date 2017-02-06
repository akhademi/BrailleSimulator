Team #: 1

Name: Zaeem Israr	Student #: 213566229
Name: Amir Khademi	Student #: 213639885
Name: Kevin Banh	Student #: 213565676


Contents of Zip File:

1) The following grade components:
	i) Requirements Document
		The requirements document contains a general description of the document, what the software does for the user/client, outlines basic customer requirements and describes the acceptance test cases.
		The document also includes a "Definitions" section that defines certain objects/elements of the simulator to improve overall readability of the document.

	ii) Testing Document:
		The testing document contains an introduction and "Definitions" section similar to the ones found in the requirements document. The document subsequently gives a description of different classes
		along with the description of the test cases used to test methods in those classes as well as how the test cases were derived. The document also describes why the test cases were sufficient for 
		testing purposes through the use of EclEmma.

	iii) Simulator API:
		The API has been provided in the form of a Javadoc. It provides all the necessary data structures, object classes, variables and methods required by the hardware device.
	
	iv) Implementation: 
		The implementation of the simulator API has been provided through a link to the GitHub repository. All classes are thoroughly commented and describe their functionality accurately. 




2) Access to the API with HTML:
	The API can be accessed using a web browser as well as eclipse's built in Javadoc viewer. 
	In order to access the API via a web browser, download the implementation using the link provided in this document, navigate to the "doc" folder inside BrailleSimulator and open the "index.html"
	file using any web browser.
	In order to access the API through eclipse, open the project using eclipse and simply double click on the "index.html" file inside the "doc" folder using the eclipse package explorer on the left.
	
	The API provides all the necessary data structures, object classes, variables and methods required by the hardware device.


3) GitHub Repository:
	The GitHub repository can be accessed using the following link: https://github.com/akhademi/BrailleSimulator



Note:

1: There is a rare bug where you create an instance of BrailleClient, the GUI shows up, but none of the cells/pins show up. In this event, simply rerun the code.

2: For testing purposes, some methods have been added to allow access by the testing code that will not be available in the final product. BrailleClient should also be a Singleton class.

3: When running the JUnit test, do not move the GUI window or open another window that will cover the GUI.

4: In the requirement document, the document mentions a feature where translating an invalid message will result in a message in the GUI. 
   This only works when the GUI is used to translate the message. If the translation is done by calling a method in the code, an InvalidInputException will be thrown and left to users to handle.



