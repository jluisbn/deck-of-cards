# deck-of-cards

To run this locally:

1.- Install docker.

2.- After successful installation:

Run the following commands:

docker build -t deck .

docker run -p 8080:8080 deck


You are good to go!

To test locally via Postman, 
you can use the postman collection in: src/main/resources/DeckOfCards.postman_collection.json and the environment: src/main/resources/local.postman_environment.json

Please import both files to postman.

Or you can use a browser as well.

There are 4 endpoints:

POST: /decks => This one creates a new deck

GET: /decks?deckId=1 => This one gets a card from an existing deck (deckId is mandatory, optional you can pass an index)

PUT: /decks?deckId=1 => This one skips a card from an existing deck (deckId is mandatory, optional you can pass an index)

GET: /decks/{deckId} => This one returns the current deck so you can see which cards are still left on the deck

IMPORTANT: This project does not work with a database, so everytime you want to test it locally you might need to create a new deck. The deck will hold the status as 
long as the container is not stopped.
