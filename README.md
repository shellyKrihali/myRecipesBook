# Recipe App

## our final project android app is a recipe app that has the following features:

## 1.Upload Image recipe from gallery and save them to firebase storage 
each user that uses the app can upload a recipe imgae from their gallery to the app and it will be saved in an Image slider

![image](https://user-images.githubusercontent.com/46194686/126328764-b916f374-bc1b-4a0b-9c05-7897146775ec.png) 
 
## 2.Create own recipe by adding the following details:
name, ingredients, instructios, description and category.
each of these recipes are saved in a real-time database firebase and they will be displayed by a recyclerview.
acitivties to make to ech recipe: remove and edit.

### *Edit recipe
![image](https://user-images.githubusercontent.com/46194686/126328898-7325abe8-e85a-4051-97b7-35fc0b1a66d9.png)
### *Display all created recipes
![image](https://user-images.githubusercontent.com/46194686/126328919-1f1a8322-ff51-4b3e-aa05-84d8f29bd62d.png)
### *Create new recipe
![image](https://user-images.githubusercontent.com/46194686/126328937-7092229b-ce21-4dfc-b203-62a4ba5c4536.png)


## 3.fetch recipes from spooncular food API (with our own API KEY) and display them in recyclerview. 
data that is fetched from API will be saved in a local database (Room), so next time wont be the need to call an API to fetch data.

activies: 
search recipes menu by meal type: main course, dessert , drink...
serach menu by ingredient key word: chips, tomato, bannana, sugar...

whenever click on a recipe a  more details activity will show which will show a pager adapter fragment of Ingredients, overview and web page of more details of the recipe
each recipe can be added/ removed to/ from favorites fragment.
## *Favorites Fragment
![image](https://user-images.githubusercontent.com/46194686/126329258-ef406ed7-c8f5-473f-9501-a713d9ea3fbc.png)

## *Details activity: have 3 fragments: OVERVIEW, INGREDIENTS AND WEB PAGE
![image](https://user-images.githubusercontent.com/46194686/126329359-836b01f7-654d-446c-b507-b9af8c172113.png)
![image](https://user-images.githubusercontent.com/46194686/126329394-96550a19-7a04-45fe-8e1b-b4cf5ce626be.png)
![image](https://user-images.githubusercontent.com/46194686/126329405-9f37f0a6-3a7b-4219-ab1e-441a152cd6ca.png)
## *All Recipes 
![image](https://user-images.githubusercontent.com/46194686/126329428-ab619989-8607-41e3-a5be-d4f60f7e1b8b.png)
## *Search recipe by meal type
![image](https://user-images.githubusercontent.com/46194686/126329504-5da66c7b-cd21-4043-a850-035600a93552.png)









