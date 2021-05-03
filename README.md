# Void
void is an application that lets people post and comment on things freely

---
# Installation
1. download jdk and install it
2. have intellJ or other editor installed
3. download the codes
4. run the server first
5. run the application

---
# Contribution(who's submitting what)
Danilo Dragovic: presentation

Hao Zhou:

Kenny Park: vocareum code

Moosh Khan: report

---
# Description on Each Class

## AppLandingFrame:
GUI that allows the user to sign up or log in to the app.

## Application:
The main class to be run by the client. Handles the flow of the program.

## Client:
A class that connects to the server. Handles client-side networking.

## Comment:
This program enable the comment functionality for the application.

## CommentCreationDialog:
GUI that allows for the creation or editing of a comment.

## CommentGUI:
The GUI for each individual post in the post feed.

## CommentNotFoundException:
Thrown when a comment cannot be found in the database.

## Database:
Contains post and profile data. The server has a server instance, and a local instance exists per individual client.

## FeedPanel:
The panel that displays all posts and comments.

## MainAppFrame:
The main GUI for the app.

## Muffin:
An enum containing the possible muffin choices.

## Packet:
A serializable object that is used to send labeled data between the server and clients.

## Post:
Defines a post.

## PostCreationDialog:
A frame that takes input for a new post.

## PostGUI:
The GUI for each individual post in the post feed.

## PostNotFoundException:
Thrown when a post cannot be found in the database.

## Profile:
Defines a profile.

## ProfileEditDialog:
A frame that handles changes to the user's profile.

## ProfileNotFoundException:
Thrown if a profile cannot be found in the database.

## Server:
A class that accepts multiple clients simultaneously. Receives and sends information to and from clients.

## ServerGUI:
The GUI for the server.

## Style:
Stores style variables for use across all GUI elements.

## UserPostsDialog:
A frame that displays all posts and comments from a user.

---
# Test Guidance
