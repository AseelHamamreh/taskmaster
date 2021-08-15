package com.example.taskmaster;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import androidx.test.espresso.contrib.RecyclerViewActions;

@RunWith(AndroidJUnit4.class)
public class EspressoTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void addNewTaskTest(){
        onView(withId(R.id.AddNewTask)).perform(click());

        onView(withId(R.id.myTitle)).perform(typeText("new task"),closeSoftKeyboard());
        onView(withId(R.id.myDescription)).perform(typeText("do something"),closeSoftKeyboard());
        onView(withId(R.id.myStatus)).perform(typeText("in progress"),closeSoftKeyboard());

        onView(withId(R.id.addTaskButton)).perform(click());
        pressBack();

        onView(ViewMatchers.withId(R.id.songList)).check(matches(isDisplayed()));
    }

    @Test
    public void detailsPageTest(){
        onView(ViewMatchers.withId(R.id.songList)).check(matches(isDisplayed()));

        onView(withId(R.id.songList))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

        onView(withId(R.id.showTitle)).check(matches(withText("new task")));
        onView(withId(R.id.showDescription)).check(matches(withText("do something")));
        onView(withId(R.id.showStatus)).check(matches(withText("in progress")));
    }

    @Test
    public void changingUserNameTest(){
        onView(withId(R.id.settingsIcon)).perform(click());

        onView(withId(R.id.userName)).perform(typeText("aseel"),closeSoftKeyboard());
        onView(withId(R.id.addingUserName)).perform(click());
    }

    @Test
    public void displayUserNameTest(){
        onView(withId(R.id.textView11)).check(matches(withText("aseel's tasks")));
    }



}
