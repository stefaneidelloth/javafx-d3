# javafx-d3 #

Provides a Java API for using the JavaScript library d3.js with JavaFx Applications.

Many thanks to the authors of the projects **gwt-d3** [[1]](https://github.com/gwtd3/gwt-d3 "gwt-d3")  and **d3.js** [[2]](http://d3js.org "d3.js") !!! 

Most of the source code of javafx-d3 originates from gwt-d3. If you use the original
gwt-d3 project your result will be a web application that is written in JavaScript. 

If you use javafx-d3, your aim is to end up with Java code that can be applied 
for JavaFx desktop applications.

## Current state

* The code from gwt-d3 has been transformed in large parts and compile errors have been resolved.
* The code regions that have not yet been translated throw "not yet implemented" exceptions.
* There might also be some bugs left...

=> Please feel free to help fixing the remaining issues.  

## How it works: ##

Javafx-d3 uses the JavaFx WebView [[3]](https://docs.oracle.com/javafx/2/webview/jfxpub-webview.htm "JavaFx WebView") to display an html page and to execute JavaScript.
The JavaScript library d3.js is injected into the WebView. Using 
so called JSObjects [[4]](http://docstore.mik.ua/orelly/web/jscript/ch19_06.html "Using JavaScript from Java") we are able to communicate between Java and JavaScript. 
This way, you can use the full power of d3.js (at least the part that is already wrapped here) 
for Java desktop applications to produce neat visualizations [[5]](https://github.com/mbostock/d3/wiki/Gallery "d3.js gallery"). 

## How to get started: ##

* Get the source code 
* You can use the main folder as an Eclipse workspace including two projects.
* Import the two Eclipse Maven projects **javafx-d3** and **javafx-d3-demo**
* Run the demo com.github.javafxd3.demo.client.JavaFxD3SingleDemo 
* If you do not get javafx-d3 up and running please create an issue ticket. 

## License ##

This project is licensed under BSD:
* javafx-d3: https://github.com/stefaneidelloth/javafx-d3/blob/master/LICENSE

Javafx-d3 is based on third party code which is licensed under BSD as well:
* d3.js: https://github.com/mbostock/d3/blob/master/LICENSE
* gwt-d3: https://github.com/gwtd3/gwt-d3/blob/master/LICENSE

----  
 
[1]: gwt-d3: https://github.com/gwtd3/gwt-d3<br>
[2]: d3.js: http://d3js.org<br>
[3]: JavaFx WebView: https://docs.oracle.com/javafx/2/webview/jfxpub-webview.htm<br>
[4]: Using JavaScript from Java: http://docstore.mik.ua/orelly/web/jscript/ch19_06.html<br>
[5]: d3.js gallery: https://github.com/mbostock/d3/wiki/Gallery<br>

