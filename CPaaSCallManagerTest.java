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
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
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
        onView(withId(R.id.platformList_LoginActivity_Spinner)).perform(click());
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
        LogOut();
        onView(withId(R.id.login_form)).perform(swipeDown());
        onView(withId(R.id.platformList_LoginActivity_Spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(0).perform(click());
        onView(withId(R.id.email)).perform(clearText());
        onView(withId(R.id.email)).perform(typeText("bcdefg"));
        closeSoftKeyboard();
        onView(withId(R.id.login_form)).perform(swipeUp());
        onView(withId(R.id.login_button)).perform(click());
        sleep(2000);
        onView(withId(R.id.radio_video)).check(matches(isDisplayed()));
    }
    @Test
    public void testLogOutWrongPassword() throws InterruptedException {
        LogOut();
        onView(withId(R.id.login_form)).perform(swipeDown());
        onView(withId(R.id.platformList_LoginActivity_Spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(0).perform(click());
        onView(withId(R.id.password)).perform(clearText());
        onView(withId(R.id.password)).perform(typeText("123456789"));
        closeSoftKeyboard();
        onView(withId(R.id.login_form)).perform(swipeUp());
        onView(withId(R.id.login_button)).perform(click());
        sleep(2000);
        onView(withId(R.id.radio_video)).check(matches(isDisplayed()));
    }

    @Test
    public void sms() throws InterruptedException {
        LogIn();
        onView(withId(R.id.radio_singlemline)).perform(swipeRight());
        sleep(5000);
        onView(withText("nav_sms_item")).perform(click());
        sleep(3000);
        onView(withId(R.id.participant_text_view)).check(matches(isDisplayed()));
        onView()
        



    }



    public void LogOut() throws InterruptedException {
        onView(withId(R.id.radio_singlemline)).perform(swipeRight());
        sleep(5000);
        onView(withId(R.id.nav_header_loggedin_user_name)).check(matches(isDisplayed()));
        sleep(2000);
        onView(withText("Logout")).perform(click());
        sleep(3000);
        onView(withId(R.id.email)).check(matches(isDisplayed()));
    }

    public void LogIn() throws InterruptedException {
        onView(withId(R.id.platformList_LoginActivity_Spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(0).perform(click());
        onView(withId(R.id.get_new_token_button)).perform(click());
        sleep(5000);
        onView(withId(R.id.login_form)).perform(swipeUp());
        onView(withId(R.id.login_button)).perform(click());
        sleep(2000);

    }



}
