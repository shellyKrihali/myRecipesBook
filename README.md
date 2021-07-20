Recipe App

our final project android app is a recipe app that has the following features:

1. Upload Image recipe from gallery and save them to firebase storage 
each user that uses the app can upload a recipe imgae from their gallery to the app and it will be saved in an Image slider


 ![Alt text](C:\Users\DELL\Downloads\WhatsApp Image 2021-07-20 at 15.50.08.png)
 
 
2. Create own recipe by adding the following details:
name, ingredients, instructios, description and category.
each of these recipes are saved in a real-time database firebase and they will be displayed by a recyclerview.
acitivties to make to ech recipe: remove and edit.

3. fetch recipes from spooncular food API (with our own API KEY) and display them in recyclerview. 
data that is fetched from API will be saved in a local database (Room), so next time wont be the need to call an API to fetch data.

activies: 
search recipes menu by meal type: main course, dessert , drink...
serach menu by ingredient key word: chips, tomato, bannana, sugar...

whenever click on a recipe a  more details activity will show which will show a pager adapter fragment of Ingredients, overview and web page of more details of the recipe

each recipe can be added/ removed to/ from favorites fragment.
