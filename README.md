# Watchly App - Your favorite watch list
Watchly display the list of movies from iTunes Search Api, It allow you to browse and see the details of each item, This project repo showcase the used of MVVM architecture and some of jetpack libraries 

# Architecture
I used MVVM or Model-View-ViewModel architecture which is the Google recommendation for android developers, In Android, MVC refers to the default pattern where an Activity acts as a controller and XML files are views. MVVM treats both Activity classes and XML files as views, and ViewModel classes are where you write your business logic. It completely separates an app's UI from its logic, I also used Repository Pattern for data processing and to handle api request. I used most of the **jetpack library** like LifeCycleViewModel, Room, DataStore, DataBinding, Hilt, Navigation Components to make the app run smooth and more organize

![MVVM](https://github.com/johnpaulcas/codein-android/blob/main/images/mvvm.png)

```bash
├── watchlyApp
│   ├── api
│   │   ├── response
│   ├── base
│   ├── di
│   ├── persistence
│   │   ├── database
│   │   ├── datastore
│   ├── repositores
│   │   ├── home
│   ├── ui
│   │   ├── components
│   │   ├── fragments
│   ├── utils
│   └── viewmodels
└── .gitignore
```

# API
The _API_ folder contains ApiHelper class interface and ApiHelperImpl which contains the api implementation, I used _retrofit_ to make things simpler and make the code more cleaner

# Base
The _Base_ folder contains **BaseActivity** that implement AppCompatActivity, **BaseFragment** implements Fragment and **BaseEpoxyHolder** which is used to easily bind the data from Epoxy components, this make things more organize when developing on a large project that need a lot of function that can be easily share in different components

# DI
The _DI_ folder is one of the most important part, I used Android Hilt which makes easier to inject dependency on viewmodel and other methods , it provides all the application dependency from Repository, Api to ViewModel

# Persistence
The _Persistence_ folder contains the database model and dao interface and implementation, I also used DataStore which is one of jetpack library in replace for SharePreferences

# Repositories
Repository Pattern is one of the most popular patterns to create an enterprise level application. It restricts us to work directly with the data in the application and creates new layers for database operations and business logic 

# UI
The _UI_ folder contains fragments and components folder, In component folder it handle the Epoxy view model that is really handy and I can say that I enjoy using this than the traditional RecyclerView, it allow you to reduce boilerplate code and it makes the views more organize, the plus plus for me using Epoxy is that you don't need to worry for view changes that if you used recyclerView you need DiffUtil to do that, You don't also need to worry about the duplication of data cause Epoxy library handle that for you, it easier also to inject either list or horizonal list view

# ViewModels
ViewModel is part of the Lifecycle library which was designed to help solve common Android Lifecycle challenges and to make apps more maintainable and testable. A ViewModel holds app's UI data in a lifecycle conscious way that survives configuration changes

![Watchly](https://github.com/johnpaulcas/codein-android/blob/main/images/app.png)

