# COM1008 - Software Engineering

# Assignment 1 - Report

**Student Name: Molly Sheppard**

**Student ID: 6865590**

## 1.1 Proto Personas

### Persona 1

 **Shannon Borgle**

Shannon, age 62, is a receptionist at her local hospice in Liverpool, working 4.5hrs every weekday. She has little further education as she dropped out of school at 16 to try to pursue a hair and makeup business. She prefers gossiping with her colleagues than assisting the general public and is fairly unfamiliar with using technology. Her gossip often revolves around her love for the actor George Clooney, she uses FlickFinder to explore more of his movies to aid her obsession. 

### Persona 2

**James Sharpe**

James, age 28, is a instagram content-creator from Hackney, London. Growing up, both his parents worked full time jobs at The Royal London Hospital which left James often alone after school to watch movies. After studying "Film and Media Studies" at the University of Southampton, he moved back to Hackney to pursue a self-employed social media career sharing his love for film. He often uses FlickFinder to find information about the movies he plans to watch to be able to share reviews and recommendations with his followers. 

## 1.2 Scenario

**Helping Shannon watch all of DiCaprio's movies**

Shannon is a hospice receptionist in Liverpool. As she only works part time, she often enjoys her other interests in her free time, mainly involving her fascination with the actor George Clooney. She aims to watch all of his movies so that she has more to gossip with her friends whilst at work.

She wants to find a useful site that allows her to list all movies by a specified actor so that she can go through the list and watch all of Clooney's movies in her spare time. This should include some information about the movies and its ratings to help her choose what to watch next. As Shannon finds technology a little difficult to use, she wants a site that is easy to understand with simpler features and interfaces. Her family recommends her to use FlickFinder as it has both a user-friendly interface that is easy to navigate and a range of simple features for searching for movies. She uses the site's "search movies by star" feature, which is clearly outlined using a simplistic illustration to symbolise actors, to find a long list of movies starring George Clooney (id 123) that she can work through in her free time. This list includes basic information on the movie, such as the release date, so as to not overwhelm the user - which Shannon quite likes.

To further improve her experience with the site, she would prefer a way to track the movies that she has already watched as well as receive recommendations for other movies without having to search through the whole system again. This could be implemented by creating a log-in system which can store details such as "films watched" in order to recommended movies based on similar actors or genres and can be displayed on the homepage for easy access.

## 1.3 User Stories

### User Story 1

**Film Critic reviewing popular movies**

As a guest user of the FlickFinder system, I need to search for all movies on the IMDB database with a high rating to decide what to review. 

### User Story 2

**User wants to track movies**

As a logged-in user, I want to be able to create an account that can log movies that I have watched so that I can keep track of movies to watch.

### User Story 3

**Casting director finding actors**

As a guest user, I need to search for actors and actresses by their popularity rating in order to find promising stars for my new movie.

### User Story 4

**Film Blogger wants to only find the top movies of each genre**

As a guest user, I want to find the most highly rated movies of each specific genre to add to my new blog post.

### User Story 5

**Local Cinema wants to print out cast list**

As a guest user, I want to view a list of all the actors and actresses from a movie to create a custom brochure.

### User Story 6

**Fan account needs to compile a list of films for new fans**

As a guest user, I need to find a list of movies starring a specific actor so that I can share with new fans.

## 2 Critical Analysis and Reflection

### 2.1 Reflection

In this project, it has gone reasonably well for furthering my understanding of software development. I found the implementation went particularly well as I built upon labs from this module as well as my knowledge of java from previous modules. I now feel more confident in creating SQL statements in the DAO and corresponding test classes to make sure my program functions and receives information from the connected database correctly. I also better understand the purpose of the controllers to connect the routes to the DAO and SQL statements, as well as the suitable test classes for error handling and validation. The integration tests took longer to understand than I had expected as there were often various Hamcrest matchers that could be used which I had less knowledge on. Nevertheless, I feel that the coursework has certainly developed my understanding of using various software dependencies such as javalin, mockito and junit which I could use in my own project.
If I were to build upon the code in the future, I would like to implement more features such as a "genre" list or a log-in page to better understand creating a new route. I would also like further practice creating and implementing personas/scenarios/user stories to better understand how they would be used in industry.

### 2.2 Professional Aspects

Between 2015 to 2024, Internet users have increased by 86% (1), this ever-growing number is likely to further increase in the future which would mean that the FlickFinder site should have to have multiple  plans for maintenance and sustainability factors. Common software design patterns such as classes or objects in object-oriented code are used in the program to keep the code efficient and scalable (such as the limit function) for better maintenance over a long period and more data. Also, as AI grows in efficiency and use, it may be useful to implement AI features to keep up with competing film sites.
Keeping movie data up to date will require the use of data from the IMDB API which is stored using the AWS cloud (2), this service is one of the most eco-friendly cloud services that became 100% renewable in 2023 (3). Implementing new features such as a logging in to an account would also require the create a Client-Server architecture to keep account data, the sustainability effects increasing hardware would have would need to be accounted for. It must also be made sure that account details do not break any General Data Protection Regulation and used fairly, lawfully and transparently (4). 

## 3. References

(References from the implementation part can be found where used in the code.)

(1) - ITU. (2024). Number of internet users worldwide from 2005 to 2024 (in millions). Statista. Statista Inc.Available at: https://www.statista.com/statistics/273018/number-of-internet-users-worldwide/ (Accessed: 10/05/2025)

(2) - IMDB. (2025) Introducing the IMDB API. Available at: https://help.imdb.com/article/imdb/general-information/introducing-the-imdb-api/G49M5Y59L5N4WABM?ref_=helpart_nav_44# (Accessed: 10/05/2025)

(3) - Amazon. (2025) AWS Cloud Progress. Available at: https://sustainability.aboutamazon.com/products-services/aws-cloud (Accessed: 10/05/2025)

(4) - UK Government (2025) Data Protection. Available at: https://www.gov.uk/data-protection (Accessed: 10/05/2025)
