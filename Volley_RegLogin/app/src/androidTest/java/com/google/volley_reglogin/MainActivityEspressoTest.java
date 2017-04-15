package com.google.volley_reglogin;

import android.content.Context;
import android.support.test.rule.ActivityTestRule;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.is;

/**
 * Created by rayleigh on 4/14/17.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void existingUserRegister() {
        String name = "dlee";
        String password = "1234";
        String email = "dlee@gmail.com";

        onView(withId(R.id.mainTextUsername)).perform(typeText(name), closeSoftKeyboard());
        onView(withId(R.id.mainTextPassword)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.mainTextEmail)).perform(typeText(email), closeSoftKeyboard());

        onView(withId(R.id.mainButtonRegister)).perform(click());

        onView(withText(R.string.registerfail_toast)).inRoot(new ToastMatcher()).check(matches(withText("Registration failed!")));
    }

    @Test
    public void newUserRegister() {
        String name = "sam1";
        String password = "1234";
        String email = "sam1@gmail.com";

        onView(withId(R.id.mainTextUsername)).perform(typeText(name), closeSoftKeyboard());
        onView(withId(R.id.mainTextPassword)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.mainTextEmail)).perform(typeText(email), closeSoftKeyboard());

        onView(withId(R.id.mainButtonRegister)).perform(click());

        onView(withText(R.string.Welcome)).inRoot(new ToastMatcher()).check(matches(withText("Welcome!")));
    }
}
