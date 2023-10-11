-- Insert values into user (all of the project members)
INSERT INTO User (UserID, Username) VALUES
(1, 'DevenAllen'),
(2, 'PreetamJain');
(3, 'SanjanaSatish'),
(4, 'ArjunGupta');


-- Insert values into Movie (This will be the preliminary values for the application)
INSERT INTO Movie (MovieID, Poster, Title, ReleaseYear, MovieLength) VALUES
(100, 'https://m.media-amazon.com/images/I/71qW2aTK7mL._AC_UF894,1000_QL80_.jpg', 'Captain America: The First Avenger', 2011, 124),
(101, 'https://m.media-amazon.com/images/I/51AMyXw-IHL._AC_UF894,1000_QL80_.jpg', 'Grown Ups', 2010, 102),
(102, 'https://i.ebayimg.com/images/g/1EcAAOSwa-9klig5/s-l1200.jpg', 'Barbie', 2023, 114);


-- Insert data into the Cast table (This is based on the movies from above)
INSERT INTO Cast (ActorName, MovieID) VALUES
('Chris Evans', 100),
('Hugo Weaving', 100),
('Samuel L. Jackson', 100),
('Adam Sandler', 101),
('Salma Hayak', 101);
('Kevin James', 101);
('Margot Robbie', 102),
('Ryan Gosling', 102),
('Issa Rae', 102);


-- Insert sample reviews into the Review table
INSERT INTO Review (StarCount, MovieID, UserID) VALUES
(5, 1, 1), -- A review with 5 stars for Movie 1 by User 1
(4, 2, 1), -- A review with 4 stars for Movie 2 by User 2
(3, 3, 1); -- A review with 3 stars for Movie 3 by User 3