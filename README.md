Coverage: 88%~
#To Do List

The to do list system is designed to allow a user to host their own to do list, which can then be used in order to keep track of what tasks they have to do.

## Getting Started

On startup the api will create and host an empty database storing tasks and steps.  After that the api can be accessed by using the webpage located inside the WebPage folder.

On the webpage there are two buttons, one for creating/updating tasks and the other for deleting.  To create a task press the button which says create.  This will bring up a modal where you can enter the name, description and add steps to your task.  In order for the task to be sucessfully added the Task does need to have a name, however a description and steps are both optional.

To add a step press the add step button, an input box will then appear under the steps heading.  Enter the name of the step in there, you can do this as many times as you need for that step. Press submit to add the task and any steps to the database.  Then press the X to exit the modal.  Once there the task will be considered selected, allowing you to edit this task, this can be seen by the "CREATE" button changing to say "EDIT" instead.  You can unselect it by clicking anywhere on the container for the task.  When you can see the CREATE button again, you can use it to add another task.

To edit a task select the task you wish to edit and click on the "EDIT" button.  From there you can change the name and description of the task or you can modify it's steps.  Steps can be changed by modifying the text in the text box for each task, pressing the add step button will add a new input box for a new step and deleting the contents of a text box will allow you to delete a step.  Press submit and the changes will update on the database.  Finally press the X to exit the modal and see the changes on the webpage

To delete a task you must select the task so you would be able to edit it.  Once you can see the "Edit" button you have selected the task.  Once a task is selected press the "DELETE" button and it will remove the task and all of the steps associated with it from the database.

### Prerequsites

You will require Mysql server 5.7 or greater, and Java 1.8 or greater in order to run the API.  For the WebPage you will require VScode to locally host the webserver on 5050 in order to run the selenium tests. 

### Installation

The program can be installed by downloading this git repository.  Place it in a folder and run the .war file from the command line to start the RestAPI.  Then either host the webpage, or open the html file (Index.html) in a browser.

## Running tests

Unit and Integration Tests can be run by opening a command line inside the ToDoListAPI folder and running the command `mvn test`  this will require that you have maven installed on your machine.  The User acceptance tests will require the use of Eclipse or another compatible ide as well as a method by which you can host the .  To run them:
First run the War file to open up the API.  
Then host the webpage on a localhost webserver with port 5500.  This can be done with liveserver from VScode.
Then the tests must be run as a Junit test in your IDE.

### Unit tests

Unit tests check that the service and controller classes of the application perform each of their part of their functions correctly. For the sake of code coverage, the entity classes and DTO classes are also unit tested however their tests do not produce an extent report.

### Integration tests

Integration tests check that the service and controller classes of the application perform their functions correctly including their connections to other classes.

### User-Access tests


###Extent reports
All of the above tests will produce a HTML extent report in the /Documentation/Reports folder, unless stated otherwise.

## Built with

* [Maven](https://maven.apache.org/) - Dependency Management
