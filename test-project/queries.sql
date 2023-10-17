-- This sql query is going to select all of the rows within the Movie table. This is going to add the query results to an arraylist. 
-- From here, it is going to display all of the resulting rows on the Homepage: INSERT HOMEPAGE URL HERE
String sql = "SELECT * FROM Movie";

-- This sql query is going to select all of the rows within the Review table. This is going to add the query results to an arraylist. 
-- From here, it is going to display all of the resulting rows on the My Reviews Page (based on which user is being used): INSERT MYREVIEWS URL HERE
sql = "SELECT * FROM Review";

-- This sql query is going to select all of the rows within the Cast table. This is going to add the query results to an arraylist. 
-- From here, it is going to display all of the resulting rows on the PerMovie page (based on which movie is clicked on): INSERT PERMOVIE URL HERE
sql = "SELECT * FROM Cast";

-- This sql query is going to insert values into the Movie table. This is not directly used on a page, but in essence, it will take
-- a Movie object and add it to the sql Movie table.
statement.executeUpdate("INSERT INTO Movie VALUES(" + movie.id + ", "  + movie.url + ", "+ movie.name + ", " + movie.year + ", " + movie.length);

-- This sql query is going to delete a specifically clicked review from the review table. This will be executed and shown on the 
-- MyReviews Page: ENTER MYREVIEWS PAGE URL
statement.executeUpdate("DELETE from Review WHERE ReviewID=" +id);            

-- This sql query is going to insert a review into the review table. You can see an update of this entry on the MyReviews page: ENTER MYREVIEWS URL
-- You will see the insert form on the Review Page: ENTER REVIEW PAGE
statement.executeUpdate("INSERT INTO Review (StarCount, MovieID, UserID) VALUES (" + stars + ", " +MovieID +", "+UserID+")");     

-- This sql query is going to join the Movie table and Cast table together to show the Movie information along with the cast that play in said movie based on MovieID.
-- This can be seen on the PerMovie Page: ENTER PERMOVIE URL
String sql = "select M.Title, M.Poster, M.ReleaseYear, M.MovieLength, C.ActorName from Movie M join Cast C on M.MovieID = C.MovieID WHERE M.MovieID="+MovieID;

-- This sql query is going to do two things: it will do a table join of Review table, User table, and Movie table to show the movies that the user has left a review on.
-- It will use that table join on the MyReview Page: ENTER MYREVIEW URL
-- This query will also (at the end of the query) use the aggregate function of ORDER BY to order the results based on the star count from highest to lowest. This will
-- show the stars in descending order on the MyReview Page: ENTER MYREVIEW URL
String sql = "select R.ReviewID, R.StarCount, M.Title from Review R join User U on R.UserID = U.UserID join Movie M on R.MovieID = M.MovieID WHERE U.UserID =" + UserID + " ORDER BY R.StarCount desc";

-- This sql query will select any entries in the User table where the Username is the same as the undername entered from User input on the Login page.
-- ENTER LOGIN PAGE URL
String sql = "SELECT * FROM User WHERE Username='" + username +"'"; 

-- This sql query will insert the username into the User table if they username is not already there before. This will be done on the Login Page:
-- ENTER LOGIN PAGE URL
statement.executeUpdate("INSERT INTO User (Username) VALUES ( '" + username + "' )");


