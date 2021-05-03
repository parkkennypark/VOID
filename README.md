# VOID
void is a forum-like social media platform that gives people the space to post and comment about anything on their minds.

---
# Installation
1. Download and install JDK
2. Download or install IntelliJ, or a similar IDE 
3. Download the project code
4. Run one instance of Server.java
5. Run Application.java 

---
# Project Submission
Danilo Dragovic: submitted presentation

Hao Zhou:

Kenny Park: submitted Vocareum code

Moosh Khan: submitted written report

---
# Descriptions of Each Class

## AppLandingFrame:
A GUI class that allows the user to sign up or log in to the app. 
It extends JFrame and is called from the Application class.

## Application:
The main class to be run by the client. Handles the flow of the program - 
it connects the client, shows the app's landing frame, and then launches the main app GUI.
It also stores data that is unique to the local client, namely the currently logged in Profile.

## Client:
A class that connects to the server. 
Handles client-side networking by establishing a connection and then awaiting object data from the server. 
It is run on its own thread so that objects can be read and written from the server separate from the rest of the program. 
It implements Runnable.

## Comment:
A class that defines a comment. 
It has several constructors to handle different ways to create a comment, as well as 
variables to define and identify the comment such as commentID, profileID, and the comment text. 
It implements Serializable so that it can be serialized and sent over the network.

## CommentCreationDialog:
A GUI class that allows for the creation or editing of a comment. 
It extends JDialog.

## CommentGUI:
A GUI class that shows an individual comment, to be instanced in the FeedPanel. 
Displays edit and delete buttons if the comment is owned by the local user.
It extends JPanel.

## CommentNotFoundException:
An exception that is thrown when a comment cannot be found in the database.
It extends Exception.

## Database:
A class that contains all post and profile data. 
The server has its own instance, and each client has its own local instance.
The server instance is saved to a file by the Server class - local instances are not saved.
It implements Serializable so that it can be written to a file.

## FeedPanel:
A GUI class that that displays all the posts and comments in the database.
It is instanced in the MainAppFrame.
It extends JPanel.

## MainAppFrame:
The main GUI for the app.
It buttons for making posts and editing or deleting your profile.
A FeedPanel is also instanced in the main center area of the interface.
It extends JFrame.

## Muffin:
An enumerator containing the possible muffin choices.
'Favorite Muffin' is one of the attributes users choose when creating a profile, and the muffins are defined here.
Note: a quiche is not a muffin.

## Packet:
A class that is used to send labeled data between the server and clients.
It defines a packet type as well as a generic object which is the data to be sent.
It implements Serializable so that it can be sent over the network.

## Post:
A class that defines a post.
It has several constructors to handle different ways to create a post, as well as
variables to define and identify the post such as postID, profileID, subject, and body.
It implements Serializable so that it can be serialized and sent over the network.

## PostCreationDialog:
A GUI class that allows for the creation or editing of a post.
It extends JDialog.

## PostGUI:
A GUI class that shows an individual post, to be instanced in the FeedPanel.
Displays edit and delete buttons if the post is owned by the local user.
It extends JPanel.

## PostNotFoundException:
An exception that is thrown when a post cannot be found in the database.
It extends Exception.

## Profile:
A class that defines a profile.
It has several constructors to handle different ways to create a profile, as well as
variables to define and identify the profile such as a profileID, password, and muffinIndex.
It implements Serializable so that it can be serialized and sent over the network.

## ProfileEditDialog:
A GUI class that allows for the user to edit their profile.
It extends JDialog.

## ProfileNotFoundException:
An exception that is thrown when a profile cannot be found in the database.
It extends Exception.

## Server:
A class that accepts multiple clients simultaneously. 
When a new client is connected, a new thread is made that handles object receiving and sending.
Packets are sent/received using object output/input streams.
It implements Runnable.

## ServerGUI:
The GUI for the server.
Very minimalistic - has just a quit button.
It extends JFrame.

## Style:
A helper class that stores style variables for use across all GUI elements.
This was made to keep consistency across the program and allow for easy UI changes.

## UserPostsDialog:
A GUI class that displays all posts and comments from a user.
It searches the database for every post and comment made by the profile with the given ID.
It extends JDialog.

---
# Test Guidance
Testing netcode automatically proved to be challenging, as there are many classes at play that need to be initialized - 
the Server and Client need to be connected, accounts set up, and local and server databases initialized. 
Because of this, we decided it made little sense to test these items individually since they belonged to a greater 
whole and instead manually tested the network elements together. 
Many print statements were put in place to describe exactly what objects were being sent and received by the server 
and clients, as well as what was being done with them so that we could monitor the program's state and communication
across the network.

GUI was tested manually by stress-testing the various interfaces. 
Because we separated our GUI code into discrete parts for different sections of the interface, 
it was easy to run individual components and test to see if anything was wrong. 
For example, we tested TextFields by inputting long Strings to ensure
that text wrap worked, and the scroll panes engaged when applicable. 

We omitted tests for methods such as getters and setters as they are frankly too simple to fail and would be an
inefficient use of our time as creating the tests would take more time than making the methods.