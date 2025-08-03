# CP3406 Assessment 2 - Bookshelf App
This is a light reading tracker app written in Kotlin for Android. It features searching with the Google Books API and saves books to a local database for persistent data.

## Screens
### Home
On the home screen, you are prompted to search for new books, and are shown every book you haven't finished.
![Screenshot preview of the home screen. It shows a prompt to search for more books, and two books the user hasn't finished yet.](/screenshots/home_screen.png)

### Search
You can search for new books here. Upon entering a search, the Google Books API is queried and the results are displayed. You can press the add button on any result to go to the next screen.
![Screenshot preview of the search screen. The search box is blank.](/screenshots/search_screen_1.png)
![Screenshot preview of the search result screen. The user has searched "The Hobbit" and the API has returned 20 books related to it.](/screenshots/search_screen_2.png)

### Add new book
After pressing the add button on the previous screen, you can type in your currently read pages, rating and review for the book. You can then press the "add book" button at the bottom to add the book to your shelf.
![Screenshot preview of the add book screen. The user is entering their number of read pages, giving a rating, and typing up a review.](/screenshots/search_screen_3.png)

### Shelf
You can see every book you have read here. Every book card has an edit button on the right, which will take you to the next screen.
![Screenshot preview of the shelf screen. It shows six books that the user has entered through searching. There is an edit icon next to each one that links to the next screen.](/screenshots/shelf_screen_1.png)

### Edit book
Pressing the edit button on a book will let you edit your read pages, rating or review. You can press "save changes" to save the book, or "remove book from shelf" to delete it permanently.
![Screenshot preview of the edit book screen. The user is editing "The Fellowship of the Ring" to update their progress. They have read 40 more pages and have a new review.](/screenshots/shelf_screen_2.png)

## Features
### Google Books API
This app searches your query using this API. It decodes each result from the JSON returned.
![Screenshot of Android Studio's Network Inspector. It shows the API query from the search result screenshot.](/screenshots/network_inspector.png)
![Screenshot of Android Studio's Database Inspector. It shows the JSON returned from the search result.](/screenshots/network_response.png)

### Room database
Persistent data storage is achieved using a Room database. The screenshot below shows each book from the shelf screenshot in the database.
![Screenshot of Android Studio's Database Inspector. It shows each book from the shelf screen has been entered into a database.](/screenshots/database_inspector.png)
