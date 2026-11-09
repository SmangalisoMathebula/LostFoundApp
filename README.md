 Lost and Found Management System

A web app for keeping track of lost and found items. Users can register, log in, report items, search for them, update them, mark them as claimed and delete them. For found items, the system works out a claim deadline of 14 days after the date reported.

 Built With
- Java (JSP and Servlets)
- MySQL
- Apache Tomcat 9
- NetBeans

 Features
- Register and log in, with input checks and a unique email for each user
- Dashboard with the total, unclaimed and claimed item counts
- Report, view, search (by name or location), edit, claim and delete items
- Claim deadline for found items (date reported + 14 days)
- Profile page to change your name, email, contact number and password
- Passwords stored as SHA-256 hashes
- Every add, update, claim and delete written to a log file

 Setup
1. Install JDK, NetBeans, Apache Tomcat 9 and MySQL.
2. Run `lostfound_db.sql` in MySQL Workbench to create the database and tables.
3. Open the project in NetBeans and add the MySQL Connector/J jar to Libraries.
4. In `DBConnection.java`, enter your MySQL username and password.
5. Right-click the project, choose **Clean and Build**, then **Run**.
6. Open `http://localhost:3128/LostFoundApp/` (change the port to match your Tomcat).

 Database
- **Users:** `user_id`, `full_name`, `email` (unique), `password`, `contact_number`
- **LostFoundItems:** `item_id`, `user_id` (linked to Users), `item_name`, `item_type`, `description`, `location`, `date_reported`, `report_type` (Lost or Found), `status` (Unclaimed or Claimed)

 Project Structure
```
src/java/com/lostfound/
    model/     User, Item
    dao/       UserDAO, ItemDAO
    servlet/   Register, Login, Dashboard, Item, Profile, Logout
    util/      DBConnection, PasswordHelper, LogHelper
web/
    WEB-INF/views/   JSP pages
    css/             style.css
```

## Log File
Actions are saved to `lostfound_log.txt` in your user folder, with the date and time, user email, action and item name.

## Planned
- SOAP web service (JAX-WS) with `viewAllItems()` and `viewUnclaimedItems()`
