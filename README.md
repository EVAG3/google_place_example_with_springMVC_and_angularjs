# Google Place example with 

## [Important] Give an valid Google Place API Key before you run the application

## 1 General introduction
This application demonstrate how to use Google Place with Spring MVC and AngularJS. The Spring MVC part is generally referenced from:
http://www.java2blog.com/2016/03/angularjs-restful-web-service-example.html
This application modify the example and add the Google Place functions. 

## 2 Runtime environment
1) With Eclipse
You should use "import ..." and "import existing Maven projects".

Usually you should only need to include the Maven dependency:
Right click on the project and select "Properties".
Then in "Deployment Assembly" under "Resource", click "add ...". Then add "Java Build Path Entry...". Then choose the "Maven dependency".

If it doesn't work, you need to check all the steps:
Right click on the project and select "Properties".
Then click on the "Project Facets". Apply facets form and click on the "Dynamic Web Module".
Then for runtime environment, click on your tomcat server. 
Then in "Deployment Assembly" under "Resource", click "add ...". Then add "Java Build Path Entry...". Then choose the "Maven dependency".
Still in "Resource" under "Deployment Assembly", click "add..." and then "Folder". Add "src/main/webapp"
Then "run as ..." and "Maven build", type "clean install".
Finally "run as..." and "run on server" with tomcat8.

2) With Maven
You could run this web application with maven.
type the following command.
mvn compile
mvn org.apache.tomcat.maven:tomcat7-maven-plugin:2.2:run
* currently this setting and maven plugin works for me. Change to other tomcat plugin if it doesn't work.

## 3 How to use the web application
First properly deploy this web application
Then open the internet browser like Google Chrome/Firefox, and go to the address:
http://localhost:8080/google_place_example_with_springMVC_and_angularjs/
type the target locations and the type of the nearest location you want. Make sure the type is supported by the Google Place API, like hospital, real_estate_agency, etc. You could search the supported type here:
https://developers.google.com/places/supported_types
Then click confirm. Because we will provide the URL link to the place. So it may takes up to 10 seconds (other than 3 seconds for normal Google Place API call). 
