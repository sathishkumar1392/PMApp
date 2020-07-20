Created by SATHISH KUMAR R on 20-07-2020

Project Management App
A sample application consist of 5 screens:

 * Screen 1 : Show the list of project in PMApp.
 * Screen 2 : Show the list of Task  in PMApp.
 * Screen 3 : User Able to add more project to list.
 * Screen 4 : User Able to more subTask to list.
 * Screen 5 : Clicking on Task its show the  Title, Description, Creation date, and a list of comments. and User able to update and add more comments.

Introduction
The application achieved solid principle based on MVVM Architecture and Repository patterns. Implemented Architecture principles follow Google recommended Guide to app architecture.

The application is written entirely in Kotlin.

Android Jetpack is used as an Architecture glue including but not limited to ViewModel, LiveData, Navigation, LifeCycles, RoomDatBase and Data Binding. See a complete list in "Libraries used" section.


Kotlin Coroutines manage background threads with simplified code and reducing needs for callbacks. Combination of Coroutines and Kotlin build in functions (transformation, collections) are preferred

Koin is used for dependency injection.


Libraries Used
The application goal is to show case current Android Architecture state using out of box Android tools made by Google (Android Jetpack) and 3rd party community driven libraries.

Android Jetpack is a set of components, tools and guidance to make great Android apps. They bring together the existing Support Library and Architecture Components and arranges them into four categories:

[Android KTX] - Write more concise, idiomatic Kotlin code.

[Data Binding] - Declaratively bind observable data to UI elements.

[Navigation] - Handle everything needed for in-app navigation.

[Lifecycles] - Create a UI that automatically responds to lifecycle events.

[LiveData] - Build data objects that notify views when the underlying database changes.

[ViewModel] - Store UI-related data that isn't destroyed on app rotations. Easily schedule asynchronous tasks for optimal execution.

[UI] - Details on why and how to use UI Components in your apps - together or separate.

[Room DataBase]  - Store all data locally used room Database

Third party

[Kotlin Coroutines][91] for managing background threads with simplified code and reducing needs for callbacks.
[Koin] A fast dependency injector.


Android Studio IDE setup
The Android Studio I used is "4.0.1" Stable version