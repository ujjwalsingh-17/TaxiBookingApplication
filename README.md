# TaxiBookingApplication
This is a taxi booking application that lets you book taxis easily and securely.

## Tech Stack Used
- Java
- Spring Boot
- Spring MVC
- Spring Security
- Spring Validations
- Thymeleaf
- JPA
- MySQL
- HTML

## Folder Structure
- `/src/main/java` - Contains Java source files.
- `/src/main/resources` - Configuration files and static resources (HTML, CSS, etc.).
- `/src/test` - Test files.

## Features
- Implemented different logins for normal users and admin users using Spring Security. All admin routes are protected with a username and password for the admin.
- Used Spring Validation for backend validations on each form field.
- Users can book a taxi, which will be displayed to the admin on the admin side.
- Admins can add services that will be visible to users.

## Usage

1. **Accessing the Application**
   - Once the application is running, open your web browser and navigate to `http://localhost:8080`.

2. **Home Page**
   - On the home page, you can navigate to different routes:
     - `/services` route to view the services uploaded by the admin.
     - `/contact` route to fill out the contact form, which will be visible to the admin.

3. **Booking a Taxi**
   - On the home page, navigate to the **Book Taxi** section.
   - Fill out the booking form with your desired details (pickup location, drop-off location, date, and time).
   - Submit the form to finalize your booking.

4. **Admin Dashboard**
   - Admin users can access the **Admin Dashboard** from the URL `http://localhost:8080/admin/dashboard`.
   - Enter the admin username and password. By default, the username is 'admin' and the password is 'admin123', which can be changed by the admin.
   - In the dashboard, you can:
     - View all bookings made by users.
       - Access this feature at `/admin/readAllBookings`, where you can also delete bookings as needed.
     - View all contact form details filled out by users.
       - Access this feature at `/admin/readAllContacts`, which includes options to delete contacts if necessary.
     - Add new services for users, including the ability to upload an image for each service.
       - Access this feature at `/admin/addService`.
     - Change admin login credentials.
       - Access this feature at `/admin/changeCredentials`.


5. **Logging Out**
   - Click on the **Logout** button in the navigation menu to securely exit your account.

## Screenshots
![HomePage](https://imgur.com/a/HiBnvxC)
