package com.google.volley_reglogin;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by rayleigh on 4/14/17.
 */

@RunWith(AndroidJUnit4.class)
public class LoginActivityEspressoTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void existingUserLogin() {
        String email = "dlee@gmail.com";
        String password = "1234";

        onView(withId(R.id.loginTextPassword)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.loginTextEmail)).perform(typeText(email), closeSoftKeyboard());

        onView(withId(R.id.loginButtonLogin)).perform(click());

        onView(withText(R.string.Welcome)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void newUserLogin() {
        String email = "sam11@gmail.com";
        String password = "1234";

        onView(withId(R.id.loginTextPassword)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.loginTextEmail)).perform(typeText(email), closeSoftKeyboard());

        onView(withId(R.id.loginButtonLogin)).perform(click());

        onView(withText(R.string.loginfail_toast)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}
