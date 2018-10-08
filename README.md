# Java-Call-Graph
1. A program that takes a package as an input and returns a call graph and class hierarchy graph.
1. This is a maven based java project. Download the file and import all the dependencies through maven.

For parsing java files the spoon framework was used, which can be downloaded from it's [github repository.](https://github.com/INRIA/spoon)

##### Quick information about call graphs:
[![Call Graphs: SEPL Goethe University Frankfurt](http://img.youtube.com/vi/YOUTUBE_VIDEO_ID_HERE/0.jpg)](http://www.youtube.com/watch?v=giGqdwuZBKQ)
##### Quick description of Class Hierarchy Graph:
* The Class Hierarchy is a directed acyclic graph reflecting the inheritance relations(s) between classes and interfaces.
* Edge types may be distinguished depending on the programming language (eg. in Java: *extends* and *implements* relations).

`Source: Class Lectures, Applied Program Analysis, Linnaeus University, Sweden`


#### Class Hierarchy: (ClassHierarchy.java)
##### Input
* The root package has to be given as an input argument.
* Example in the edit configurations of any IDE, in the **Program Arguments** field, enter the full path of the package folder of the java program to be tested. The path should be in double-quotes and two backslashes (//) should be used. eg. `"D:\\your-path\\Java Projects\\Target-Project\\src"`

##### Output
* The resultant class hierarchy graph is returned in the form of GML file. The file is saved in the root folder of the project. A popular tool to visualize these graph is [yED Graph Editor](https://www.yworks.com/products/yed). Just open the GML file through yED and click on __one-click layout__.
* THe generated GML file is created by a very simple graph package which is included in the project.
* In the output graph, all possible classes and interfaces will have a node and there will be an edge between inherited and extended classes to it's parent. 
 
#### Call Graph: (CallGraph.java)
##### Input
* The root package has to be given as an input argument.
* Note that in this particular implementation, constructor calls have not been considered as a method.
* If the root package has more than one main method, the first main method will be used as default.
* For using any other main method as the root, a second integer-argument can be passed.
* Example in the edit configurations of any IDE, in the **Program Arguments** field, enter the full path of the package folder of the java program to be tested. The path should be in double-quotes and two backslashes (//) should be used. If the program has more than one main methods, a second argument may be added mentioning which method to use. eg. `"D:\\your-path\\Java Projects\\Target-Project\\src" 3`

##### Output:
* The resultant call graph is returned in the form of GML file in the root directory.
* The graph will start with the main method as it's root, following which it will have it's children with all the other method it calls. This goes on until all the methods within the package are covered. Note that if any method calls a method outside the root package (eg. a library), those will not have a node in the graph.
