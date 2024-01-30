This is an API developed for the Ubots Technical Challenge.
The API is capable of posting, deleting, updating and searching for Movies, as well for Reviews.

URL: localhost:8080

**MOVIE CONTROLLER**

GET(/api/movie)
Selects all movies saved in the Database;

GET(/api/movie/{id})
Selects a movie found by the Id sent as the URL parameter.

GET(/api/movie/new)
Selects all movies that currently don`t have been reviewed.

GET(/api/movie/name)
Selects a movies based on the name sent on the requested body (Moviename JSON).

POST(/api/movie)
Posts a new movie sent on the requested body (MovieDTO JSON).

POST(/api/movie/{id})
Updates the movie found by the URL Id to match the requested body (MovieDTO JSON).

DELETE(/api/movie/{id})
Deletes the movie found through the URL Id.


**REVIEW CONTROLLER**

GET(/api/review/{id})
Selects a review found by the Id in the URL parameter.

GET(/api/review/name)
Selects all reviews made to the movie specified by the name sent on the requested body (Moviename JSON).

POST(/api/review)
Posts a new review to a movie sent on the requested body (ReviewDTO JSON).

POST(/api/review/{id})
Updates the review found by the URL Id to match the requested body (ReviewDTO JSON).

DELETE(/api/review/{id})
Deletes the review found by the URL Id.

**MOVIE DTO JSON**
{
  "name": " ",
  "description": " ",
  "minuteLength": 0
}

**REVIEW DTO JSON**
{ 
  "review": " ",
  "points": 0,
  "movieName": " ",
  "movieId": 0
}

**MOVIENAME JSON**
{ 
  "name": " "
}



