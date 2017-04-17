package com.google.volley_reglogin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.registerIdlingResources;
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
@LargeTest
public class MainActivityEspressoTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Activity mCurrentActivity;

    public MainActivityEspressoTest() {
        super(MainActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mCurrentActivity = getActivity();
    }

    @Test
    public void existingUserRegister() throws Exception {

        String name = "dlee";
        String password = "1234";
        String email = "dlee@gmail.com";

        onView(withId(R.id.mainTextUsername)).perform(typeText(name), closeSoftKeyboard());
        onView(withId(R.id.mainTextPassword)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.mainTextEmail)).perform(typeText(email), closeSoftKeyboard());

        onView(withId(R.id.mainButtonRegister)).perform(click());

        VolleyIdlingResource volleyResources;
        try {
            volleyResources = new VolleyIdlingResource("VolleyCalls");
            registerIdlingResources(volleyResources);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.RegMessage)).check(matches(withText("Registration failed!")));
    }

    @Test
    public void newUserRegister() throws Exception {

        String name = "sam6";
        String password = "1234";
        String email = "sam6@gmail.com";

        onView(withId(R.id.mainTextUsername)).perform(typeText(name), closeSoftKeyboard());
        onView(withId(R.id.mainTextPassword)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.mainTextEmail)).perform(typeText(email), closeSoftKeyboard());

        onView(withId(R.id.mainButtonRegister)).perform(click());

        VolleyIdlingResource volleyResources;
        try {
            volleyResources = new VolleyIdlingResource("VolleyCalls");
            registerIdlingResources(volleyResources);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.RegMessage)).check(matches(withText("Registration succeed. Please login!")));
    }
}
