# SpringMVC + ReactJS + Hibernate + MySQL + Tomcat Server App

1. To install ReactApp you need:
    - open folder with react app in your terminal,
    - :~//ReactApp$ npm install
    - :~//ReactApp$ sudo npm install webpack -g

2. Create folder /src/main/webapp/properties . 
    Create file "database.properties" inside properties folder.
    
    Paste next code in database.properties :
    
    jdbc.driverClassName=com.mysql.jdbc.Driver
    jdbc.url=jdbc:mysql://localhost:3306/testmyapp
    jdbc.username=root
    jdbc.password=root 
    
3. Change jdbc.username , jdbc.password and jdbc.url as in your 
   databse. In my case I change just password on my 
   local MySQL database. 
   
4. Run react app with $ sudo npm start . 
   Build index_bundle.js with $ npm run build . 
   
5. Copy index_bundle.js from /ReactApp/dist folder to 
    /src/main/webapp/resources/js folder.
    
6. Create WAR with maven and deploy your app on the server. 
    Or run as Aplication Servers in Intellij IDEA.

