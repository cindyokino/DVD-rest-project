# DvdProject


GET Returns a dvd based on id\
`/dvdapi/dvd/{id}`

GET Returns all dvd's in table\
`/dvdapi/dvds` 

GET Gets all movies with the requested title\
`/dvdapi/dvds/title/{title}`

GET Gets all movies with the requested release year\
`/dvds/year/{releaseYear}`

GET Gets all movies with the requested director name\
`/dvds/director/{directorName}`

GET Gets All movies with the requested rating\
`/dvdapi/dvds/rating/{rating}`

POST Creates a new dvd in the database\
`/dvdapi/dvd`
```
Raw Data : {
    "id":1,
    "title": "Movie Name",
    "releaseYear": "1998",
    "director":" D Name",
    "rating":"E",
    "notes":"None"
}
```
PUT Updates a dvd in the table\
`/dvdapi/dvd/{id}`
```
Raw Data : {
    "id":1,
    "title": "Movie Name",
    "releaseYear": "1998",
    "director":" D Name",
    "rating":"E",
    "notes":"None"
}
``` 

DELETE  : Deletes DVD based on the id\
`/dvdapi/dvd/{id}`
