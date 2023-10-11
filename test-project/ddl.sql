-- Create database to be used for project
CREATE DATABASE riggedDB;

-- Move into riggedDB Database
USE riggedDB;

-- Create the User table
CREATE TABLE User (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(255) NOT NULL
);

-- Create the Movie table
CREATE TABLE Movie (
    MovieID INT AUTO_INCREMENT PRIMARY KEY,
    Poster VARCHAR(255),
    Title VARCHAR(255) NOT NULL,
    ReleaseYear INT,
    MovieLength INT
);

-- Create the Review table
CREATE TABLE Review (
    ReviewID INT AUTO_INCREMENT PRIMARY KEY,
    StarCount INT,
    MovieID INT,
    UserID INT,
    FOREIGN KEY (MovieID) REFERENCES Movie(MovieID),
    FOREIGN KEY (UserID) REFERENCES User(UserID)
);

-- Create the Cast table
CREATE TABLE Cast (
    CastID INT AUTO_INCREMENT PRIMARY KEY,
    ActorName VARCHAR(255),
    MovieID INT,
    FOREIGN KEY (MovieID) REFERENCES Movie(MovieID)
);