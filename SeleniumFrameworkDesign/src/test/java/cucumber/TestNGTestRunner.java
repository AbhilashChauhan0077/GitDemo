package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber", glue="rahulshettyacademy.stepDefinition",monochrome=true,tags="@ErrorValidation",plugin= {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}

// JUnit do not require AbstractTestNGCucumberTests class to be extended it is for testng only.

// here we required this class to run cucumber .feature file
// annotation @CucumberOptions(features="path where .featurefile is present, glue="path where .stepdefinition file is present",
// monochrome=true-->> output would be in readable format, plugin={"html type of report: where to generate .html report"})
// tags="@Regression" means out of all the feature file in that location it will find Regression tags feature file and it will 
// triger that.

//AbstractTestNGCucumberTests is a base class provided by the cucumber-testng library in Java that acts as a bridge to run Cucumber


// we need just one more wrapper to change our testng framework to cucumber 

// to run cucumber test we need to depend on runner like testng or junit
// in this project we are using testng just to run the cucumber feature files
// if we are using testng assertion and library in framework then we will use testng to run the cucumber feature file.
// if we are using junit assertion and library in framework then we will use junit to run the cucumber feature file.

//if you want to run your test from maven--- mvn test -PCucumberTests
// <id>CucumberTests</id>
//<configuration>
//<includes><include> **/TestNGTestRunner.java</include></includes>--- it means in framework where ever this class is present
// it will run that class
//</configuration>

// if you want to run this test from jenkins then you can add one more profile CucumberTest in your configuration

// Intellij is a tool in eclipse which will do all push and pull operation in github automatically.
// tortoisegit is also a tool for the same operation but i think this is not for eclipse
// for git commands write basic git commands in google and find atlassian git command
// first we need to tell github name and email
// all the commands will start from git
// git config --global user.name "Abhilash Chauhan" -->in this we need to use config to tell username and email
//and global we are using so that we can access from anywhere from my system.
// git config --global user.email "abhilashchauhan58@gmail.com"

// you have a code
// initilize it as Git repository -> git init-- with this it create hidden git file in your folder to make it git compatible
// staging   and commit. then GITHUB
// move the code from local repository to staging-> git add <file name> or * "*" will move all the files
// git status -> from this command we can check which file are in staging and going to commit in next step
//git commit -m "first commit"-> from this we committed the code and now it is ready to push on git hub
//git remote add origin origin name from github->from this we are setting remote connection of git and github and 
// git push origin main-> here we are pushing the code to github and main or master branch by default while creating the repository

// Example like 2 guys want to push and pull the code on daily basis so there will be 2 projects suppose their name x and y
// Terminal cd x -> then write pull command from x -- git clone url of origin -> we can use clone first time only after that we just want updated code not full code so use pull
//command ---git pull origin main-- it will  pull the code 

// Branching in github--- master branch and develop branch (subbranch)-- if u want to modify the components of framework not testcases then architect will create develop branch
// git checkout -b develop  -> it means creating develop branch  and switching to develop branch as well.
// after this check status -> git branch -- it means checking how many branch we have and which is the active branch
// git add * then git commit -m "architect asian" then git push origin develop-- here we need to write develop instead of main or master if we want to push to develop branch
// american guy will pull the code -- git pull origin develop
// after pulling the code we need to switch our master of other person to develop branch first -- cd y: git checkout develop -- switched to develop branch of y
// click on eclipse where x mastername was written it will switch to x develop here we did not use -b while checkout because we have already created new branch develop


// how to merge and move from develop branch to master or main branch
// first of all we need to switch from develop branch to master branch   git checkout main
// then we ll check master branch should have updated code already   git pull origin main
// then we will merge the different branch to the active branch by simply writing     git merge develop

// Merge conflict scenerio: master has written numbers 1 to 7 --- then we created develop 2 branch and switched to that branch --  git checkout -b develop2
// inside develop 2 branch i started to modify the code but in between master has removed 6 and 7 numbers from master branch 
// then after finishing i started merge but before merge we need to perform these opearations  -   git add * -> git commit -m "my work completed" ->git merge master
// here we are merging master to develop2 --- so github we be confused and throw error--- because github and 2 things in develop2 6 and 7 is there but in master not there
// so in this case developer will decide by talking to each other what we should do

// from eclipse also we can do switch between branches right click on project>teams>switch and merge also

// if there is an issue in terminal then we can reset by using command  to reset and take the latest code -- git fetch origin and then write git reset --hard origin/master


/* Git credential manager is a plugin to help while pushing the code in github we do not need to put username and password it will approve the request.
 * pwd it shows the exact path where we are on terminal
 * mkdir cicd - it will create directory on that path
 * open . it will open explorer where cicd is 
 * then copy the framework and paste it in cicd directory
 * then reach to that framework inside cicd
 * then put git init command 
 * git checkout -b main  -- create branch and switch
 * then git add . or * -- it will do staging work
 * git commit -m "message"
 * git remote add origin path of origin from github
 * git push origin main 
 * ehco $JAVA_HOME -- to check the version of java like jdk-17
 * inside jenkins go to manage jenkins>plugins>availbale plugins and search maven integration and install that plugin
 * then stop the server from terminal ctrl +C
 * then restart the jenkins
 * then create new item
 * source code management from where u are getting the code select git and then pass instead of username and password
 * secret text it can be created from github right symbol then setting>developers setting>personal access tokens(classics)>generate
 * a new token select all the check boxes then click on generate a token give note as well it will create secret text copy that
 * otherwise it will lost
 * put the branch name in jenkins like main or master in github
 * select the checkbox of github hook trigger
 * we need to go to the tools for maven so that we can set path of maven and version of maven to use jenkins
 * type mvn -version
 * take full path of maven from that and put in path
 * and from path 3.0.6 this typer of version will be there in path take that and put in name
 * in jenkins go to the goals and put the command test -PRegression to run the goal click on build now
 * 
 * CI/CD: developer pushes the code>github>github webhook triggers jenkins> jenkins run the testcases if it passes> then 
 * jenkins deploy into the production
 * 
 * webhook: goto the repository like we created Automation cicd from this repository level go to settings>webhooks>add webhook
 * >payload url- it is the url where jenkins is listening"go to your jenkins manage jenkins>systems>github>advanced>select hook 
 * url checkbox then link will appear for payload url">just the push event select this.
 * from this whenever there will be push in github it tells payload url then payload url send it to jenkins where
 * jenkins is listening in this way we are connecting jinkins and github with the help of webhook
 * inside jenkins go to build triggers> and select github hook trigger for gitscm polling
 * in real time webhook will not work in localhost in companies we have a unique ip address where it will work
 * payload url cannot be private address it should be public or it should be hosted on cloud, azure etc
 * ngrock is a software which will create public payload url after downloading this it will also give u terminal command
 * use that then use sencond cmd from ngrock it will convert local host to public ip address
 * then inside payload url remove till 9090 and replace this then click on add webhook in github it will show tick*/


/*Selenium grid:
 * it is a smart proxy server which run tests in parallel on different machines.
 * 
 * Architecture of selenium grid
 * client ->sends request to "Router" -> for the first time router does not have any information over which node that request should be send.
 * So it sends request to "Distributor" for the first time. Distributor creates a new session id and distribute the code to the appropriate machine or node
 * -> Distributor sends the session id and node ipaddress to "session map" as well->next time client send the subsequent request to router now router will check in session map
 * whether information is present of the node in the session map or not if yes then what is the ip address 
 * router send the request to node or machine directly otherwise it sends request to distributor again >
 * 3 grid roles we have ->1. standalone role 2. distributed role 3. Hub and node- mostly we work on hub and node it is used in various companies for small and middle sized grids.
 * in this we start the selenium hub together. Hub contains:router,distributor,session map, new session queue and event bus.
 * new session queue: if multiple tests run then at a time distributor works on one test till then other tests stored in new session queue.
 * event bus: means the flow is working on event bus
 * grid inftracture setup: download selenium server jar files,chrome drivers and gecko drivers-- put them in same path must for each and every machine
 * first we need to start the selenium hub-- for this first reach to the same folder on terminal where above downloaded files are present
 * then write java -jar <selenium server name> hub ---> from this selenium hub has been started read the logs and check the local host there u can see it
 * now if u want to run the test on the same machine then we need to write on terminal
 * java -jar <selenium server name> node --detect-drivers true --> it will register the node on the same machine and detect dirvers also
 * now if you want to run tests on different machines first download selenium server and driver on same path in that machine then in terminal reach to the folder
 * then write java -jar <selenium server name> node --detect-drivers true --publish-events tcp://192.168.1.139:4442 --subscribe-events tcp://192.168.1.139:4443
 * these subscribe and publish information you can check on hub command XPUB:4442 AND XSUB:4443  and ip address at the bottom
 * 
 * eclipse on one machine > hub is on other machine > and node is on different machines*/

