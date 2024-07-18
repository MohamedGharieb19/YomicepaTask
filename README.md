# Yomicepa Mobile Coding Challenge

# Overview
This is an Android application for a pharmacy platform where users can create and manage return requests for outdated items. The app allows users to log in, create return requests, add items to requests, and view or update existing requests.

you can find the link to download the latest APK:

[Download APK](app/release/app-release.apk)

# Features:
- Login Screen: Users can log in with their credentials.
- Return Requests Screen: Displays all return requests with relevant details and allows creation of new requests.
- Create Return Request Screen: Users can create new return requests by selecting service type and wholesaler.
- Add Item Screen: Users can add items to a return request with details like NDC, description, manufacturer, etc.
- Items Screen: Lists all items in a return request and allows updating or deleting items.

# Technology Stack

- Kotlin: Used for Android development
- Hilt: For dependency injection.
- Navigation Component To handle navigation between screens
- Kotlin Cououtines For managing background threads and simplifying asynchronous programming.
- kotlin Flows For handling streams of data asynchronously.
- Retrofit: For network operations.
- Clean architecture To promote separation of concerns and make the codebase more modular and testable.
- ShareViewModel: To share data between fragments.

# Implementation Decisions and Rationales
- Architecture: Clean architecture Chosen for separation of concerns and to make the code more testable and maintainable.
- Dependency Injection: Hilt: Simplifies dependency injection, improves code readability, and reduces boilerplate code.
- Networking: Retrofit: Provides a type-safe HTTP client for making API calls and handles data parsing efficiently.
- Data Sharing: ShareViewModel Used to share data between fragments, ensuring data consistency and simplifying communication.
- Navigation: Navigation Component Manages app navigation and back stack, making it easier to handle fragment transactions.
- Error Handling: Custom Error Handling Functions: Centralized error handling to manage and log errors consistently across the app.

# Screenshots: 
![Screenshot 1](screenshots/loginScreen.png)
![Screenshot 2](screenshots/pharmaciesScreen.png)
![Screenshot 3](screenshots/returnRequestsScreen.png)
![Screenshot 4](screenshots/createRequestScreen.png)
![Screenshot 5](screenshots/addItemToRequest.png)
![Screenshot 6](screenshots/followAddItemScreen.png)
![Screenshot 7](screenshots/confirmationDialogOfAddItem.png)
![Screenshot 8](screenshots/itemsInReturnRequest.png)
![Screenshot 9](screenshots/editDescirptiontem.png)
![Screenshot 10](screenshots/validationOnEditDescription.png)
![Screenshot 11](screenshots/confirmationMessageBeforeDeleteItem.png)




