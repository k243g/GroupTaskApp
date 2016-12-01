package com.example.grouptaskapp;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddGroupIncompleteTest {

    @Rule
    public ActivityTestRule<AddGroupActivity> mActivityTestRule = new ActivityTestRule<>(AddGroupActivity.class);

    @Test
    public void addGroupIncompleteTest() {


        ViewInteraction appCompatEditText3 = onView(
                withId(R.id.input_groupName));
        appCompatEditText3.perform(scrollTo(), replaceText("Test Group Incomplete"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btn_addGroup), withText("Create Group")));
        appCompatButton2.perform(scrollTo(), click());

    }

}
