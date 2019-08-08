package com.rbbn.cpaas.mobile.demo_java;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.rbbn.cpaas.mobile.demo_java.ui.main.EnterenceActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static java.lang.Thread.sleep;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class CPaaSCallManagerTest {

    @Rule
    public ActivityTestRule<EnterenceActivity> loginFragmentActivityTestRule = new ActivityTestRule<>(EnterenceActivity.class);

    @Test
    public void testLoginCPaaSMobileSDK() throws InterruptedException {
        sleep(2000);
        // Click on the Spinner
        onView(withId(R.id.platformList_LoginActivity_Spinner)).perform(click());
        // Click on the first item from the list, which is a marker string: "Select your country"
        onData(allOf(is(instanceOf(String.class)))).atPosition(0).perform(click());
        onView(withId(R.id.get_new_token_button)).perform(click());
        sleep(5000);
        onView(withId(R.id.login_form)).perform(swipeUp());
        onView(withId(R.id.login_button)).perform(click());
        sleep(2000);
        onView(withId(R.id.radio_video)).check(matches(isDisplayed()));
    }

    @Test
    public void testLoginWrongUsername() throws InterruptedException {
        sleep(2000);
        onView(withId(R.id.platformList_LoginActivity_Spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(0).perform(click());
        onView(withId(R.id.email)).perform(clearText());
        onView(withId(R.id.email)).perform(typeText("kubra"));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(clearText());
        onView(withId(R.id.password)).perform(typeText("1673258"));
        closeSoftKeyboard();
        onView(withId(R.id.get_new_token_button)).perform(longClick());
        sleep(5000);
        onView(withId(R.id.login_form)).perform(swipeUp());
        onView(withId(R.id.login_button)).perform(click());
        sleep(2000);
        onView(withId(R.id.radio_video)).check(matches(isDisplayed()));
    }

    public void LogOut() throws InterruptedException {
        sleep(2000);
        onView(withId(R.id.platformList_LoginActivity_Spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(0).perform(click());
        onView(withId(R.id.get_new_token_button)).perform(click());
        sleep(5000);
        onView(withId(R.id.login_form)).perform(swipeUp());
        onView(withId(R.id.login_button)).perform(click());
        sleep(2000);
        onView(withId(R.id.radio_video)).check(matches(isDisplayed()));
        //onView(withId(R.id.button)).perform(click());

    }



}