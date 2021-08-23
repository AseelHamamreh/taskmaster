# taskmaster

This android App built with java by Android Studio, it is a Tasks Application that Organizes your tasks.

## Android Fundamentals

The ```home page``` allows you to go to new pages:
1. Add Task: you can Add the task title and the task description.
2. All Task: no functionality here yet.

#### Home Page:
![home](https://github.com/AseelHamamreh/taskmaster/blob/main/screetshots/home.jpg)

#### AddTask Page:
![addTask](https://github.com/AseelHamamreh/taskmaster/blob/main/screetshots/addTask.jpg)

#### AllTasks Page:
![allTasks](https://github.com/AseelHamamreh/taskmaster/blob/main/screetshots/AllTasks.jpg)

***

## Data in TaskMaster

the home page has new 3 buttons, when the user click any of them it will show the Task Details page.
the User can add his UserName from the settings button and it will be saved by SharedPreferences.

#### Home Page:
![home](https://github.com/AseelHamamreh/taskmaster/blob/main/screetshots/home2.jpg)

#### TaskDetails:
![task details](https://github.com/AseelHamamreh/taskmaster/blob/main/screetshots/taskDetails.jpg)

***

## RecyclerView
the home page now has a recycled view and the user can view many tasks.
if the user click at any task he see the task details.

### Home page:
![home](https://github.com/AseelHamamreh/taskmaster/blob/main/screetshots/home3.jpg)

#### TaskDetails:
![task details](https://github.com/AseelHamamreh/taskmaster/blob/main/screetshots/taskDetails2.jpg)


***

## Room
the user now can add a task and its discription and these data will be saved in the data base and rendered by the adapter as recycled view.

#### AddTask Page:
![addTask](https://github.com/AseelHamamreh/taskmaster/blob/main/screetshots/addTask2.jpg)

### Home page:
![home](https://github.com/AseelHamamreh/taskmaster/blob/main/screetshots/home4.jpg)

***

##  Amplify and DynamoDB
No new features, but now the data will be stored in DynamoDB insted of rooms.

***

## Related Data
now the user can specify his tasks in a group while adding them.

![addTask](https://github.com/AseelHamamreh/taskmaster/blob/main/screetshots/addTask5.jpg)

***

## To run this app:
1. clone the repo: ```https://github.com/AseelHamamreh/taskmaster.git```
2. open the repo by Android Studio.
3. Connect your Android phone or use an emulater to run the app.
