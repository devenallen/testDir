-- This sql query is going to select all of the rows within the Movie table. This is going to add the query results to an arraylist. 
-- From here, it is going to display all of the resulting rows on the Homepage: localhost:8080/
String sql = "SELECT * FROM Movie";

-- This sql query is going to select all of the rows within the Review table. This is going to add the query results to an arraylist. 
-- This is going to help later on with the MyReviews page (sorting through the reviews made by the specific user): localhost:8080/MyReviews
sql = "SELECT * FROM Review";

-- This sql query is going to select all of the rows within the Cast table. This is going to add the query results to an arraylist. 
-- This is going to help later on with the PerMovie page (sorting through the cast of each movie): localhost:8080/PerMovie
sql = "SELECT * FROM Cast";

-- This sql query is going to insert values into the Movie table. This is not directly used on a page, but in essence, it will take
-- a Movie object and add it to the sql Movie table. ***This was going to be used, but we never ended up using it.
statement.executeUpdate("INSERT INTO Movie VALUES(" + movie.id + ", "  + movie.url + ", "+ movie.name + ", " + movie.year + ", " + movie.length);

-- This sql query is going to delete a specifically clicked review from the review table. This will be executed and shown on the 
-- MyReviews Page: localhost:8080/MyReviews
statement.executeUpdate("DELETE from Review WHERE ReviewID=" +id);            

-- This sql query is going to insert a review into the review table. You can see an update of this entry on the MyReviews page: localhost:8080/MyReviews
-- You will see the insert form on the PerMovie Page: localhost:8080/PerMovie
statement.executeUpdate("INSERT INTO Review (StarCount, MovieID, UserID) VALUES (" + stars + ", " +MovieID +", "+UserID+")");     

-- This sql query is going to join the Movie table and Cast table together to show the Movie information along with the cast that play in said movie based on MovieID.
-- This can be seen on the PerMovie Page: localhost:8080/PerMovie
String sql = "select M.Title, M.Poster, M.ReleaseYear, M.MovieLength, C.ActorName from Movie M join Cast C on M.MovieID = C.MovieID WHERE M.MovieID="+MovieID;

-- This sql query is going to do two things: it will do a table join of Review table, User table, and Movie table to show the movies that the user has left a review on.
-- It will use that table join on the MyReview Page: localhost:8080/MyReviews
-- This query will also (at the end of the query) use the aggregate function of ORDER BY to order the results based on the star count from highest to lowest. This will
-- show the stars in descending order on the MyReview Page: localhost:8080/MyReviews
String sql = "select R.ReviewID, R.StarCount, M.Title from Review R join User U on R.UserID = U.UserID join Movie M on R.MovieID = M.MovieID WHERE U.UserID =" + UserID + " ORDER BY R.StarCount desc";

-- This sql query will select any entries in the User table where the Username is the same as the undername entered from User input on the Login page.
-- localhost:8080/login
String sql = "SELECT * FROM User WHERE Username='" + username +"'"; 

-- This sql query will insert the username into the User table if they username is not already there before. This will be done on the Login Page:
-- localhost:8080/login
statement.executeUpdate("INSERT INTO User (Username) VALUES ( '" + username + "' )");


