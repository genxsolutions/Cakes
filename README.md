# CakesApp

Welcome to CakesApp, presents list of cakes built with MVVM architecture and Jetpack Compose.

## Major Highlights

- **Jetpack Compose** for modern UI
- **MVVM architecture** for a clean and scalable codebase
- **Kotlin** and **Kotlin DSL**
- **Dagger Hilt** for efficient dependency injection.
- **Retrofit** for seamless networking
- **Coroutines** and **Flow** for asynchronous programming
- **StateFlow** for streamlined state management
- **Unit tests** and **UI tests** for robust code coverage
- **Navigation** for smooth transitions between screens
- **Coil** for efficient image loading
- **Pull to refresh** for refreshing the contents
- **multi module** just for concept of separating utilities into separate module (use android multimodule template for feature level separation)

<p align="center">
<img alt="screenshots"  src="https://github.com/genxsolutions/Cakes/blob/main/assets/Cakes_app_architecture.jpeg">
</p>

## Features Implemented

- Show list of cakes
- show popup to show descriptions of the cake
- pull to refresh
- no network and error screen with retry functionality
- accessibility support
- dark and light mode support
- config change handling
- central dependency management
- modularised concept

## Features planned as backlog
- **Pagination** to efficiently load and display cake list 
- **Offline caching** with a **single source of truth**
- **Room DB** for local storage of cake listings
- **work manager** for background caching
- **version catalogs** centralised dependency system 
- detailed testing and handling the low memory and restart scenarios 

## Dependency Use

- Jetpack Compose for UI: Modern UI toolkit for building native Android UIs
- Coil for Image Loading: Efficiently loads and caches images
- Retrofit for Networking: A type-safe HTTP client for smooth network requests
- Dagger Hilt for Dependency Injection: Simplifies dependency injection
- Paging Compose for Pagination: Simplifies the implementation of paginated lists
- Mockito, JUnit, Turbine for Testing: Ensures the reliability of the application

## How to Run the Project

- Clone the Repository:
```
git clone https://github.com/genxsolutions/Cakes.git
cd Cakes
```
- Build and run the Cakes App.


## The Complete Project Folder Structure

```
app:
|── CakesApplication.kt
├── common
│   ├── Const.kt
│   ├── NoInternetException.kt
│   ├── dispatcher
│   │   ├── DefaultDispatcherProvider.kt
│   │   └── DispatcherProvider.kt
│   ├── logger
│   │   ├── AppLogger.kt
│   │   └── Logger.kt
│   └── util
│       ├── Util.kt
├── data
│   ├── model
│   │   ├── Cakes.kt
│   │   ├── CakesItem.kt
│   ├── network
│   │   ├── ApiInterface.kt
│   └── repository
│       └── CakesRepository.kt
├── di
│   ├── module
│   │   └── ApplicationModule.kt
│   └── qualifiers.kt
├── ui
│   ├── CakesActivity.kt
│   ├── base
│   │   ├── CommonUI.kt
│   │   ├── CakesDestination.kt
│   │   ├── CakesNavigation.kt
│   │   └── UIState.kt
│   ├── components
│   │   ├── CakeItem.kt
│   │   └── CakesListLayout.kt
│   ├── paging
│   │   └── CakesPagingSource.kt
│   ├── screens
│   │   ├── CakesScreen.kt
│   │   ├── PopupScreen.kt
│   ├── theme
│   │   ├── Color.kt
│   │   ├── Theme.kt
│   │   └── Type.kt
│   └── viewmodels
│       ├── CakesViewModel.kt

utilities:
│   ├── networkhelper
│   │   ├── NetworkHelper.kt
│   │   └── NetworkHelperImpl.kt

buildSrc:
│   ├── Dependencies.kt
│   ├── Versions.kt 

```
<div style="display: flex; justify-content: space-between;">
    <img alt="cake list"  src="https://github.com/genxsolutions/Cakes/blob/main/assets/cakeList.png" width="400" height="600">
    <img alt="description" src="https://github.com/genxsolutions/Cakes/blob/main/assets/popup_description.png" width="400" height="600">
    <img alt="error" src="https://github.com/genxsolutions/Cakes/blob/main/assets/error.png" width="400" height="600">
    <img alt="animation" src="https://github.com/genxsolutions/Cakes/blob/main/assets/fadein_animation.png" width="400" height="600">
    <img alt="pull-to-refresh" src="https://github.com/genxsolutions/Cakes/blob/main/assets/pull_to_refresh.png" width="400" height="600">
</div>
