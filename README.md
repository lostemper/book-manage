# Test in local
### 1.  perform SQL Scripts
perform init mysql SQL Scripts which path is  /yourpath/backend/src/resources/db.sql 
in  your local mysql database
### 2.  start up the backend springboot   
run class App main method to start the backend service
### 3.  use `npm start` to run the frontend app
```
cd /path/frontend
npm start
```
Runs the app in the development mode.
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.
The page will reload when you make changes.
### 4. unit test
if you want to do the unit test,you need modify this class
/src/main/java/com/richard/entity/Book
from 
```
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
```
to 
```
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
```
because when using  h2  as test db,set 'strategy=GenerationType.AUTO' can generate auto increatment
id

