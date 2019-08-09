package com.rbbn.cpaas.mobile.demo_java;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.rbbn.cpaas.mobile.demo_java.ui.main.EnterenceActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.swipeRight;
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

    private Boolean isLoginSucceed = false;

    @Rule
    public ActivityTestRule<EnterenceActivity> mActivity = new ActivityTestRule<>(EnterenceActivity.class);

    @Before
    public void setup(){

    }

    @Test
    public void testIsLoginSucceeded() throws InterruptedException {
        loginMethod();
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

    @After
    public void tearDown() {
        if (isLoginSucceed){
            logoutMethod();
            isLoginSucceed = false;
        }
    }

    public void loginMethod() throws InterruptedException {
        sleep(1000);
        onView(withId(R.id.platformList_LoginActivity_Spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(0).perform(click());
        onView(withId(R.id.get_new_token_button)).perform(click());
        sleep(3000);
        onView(withId(R.id.login_form)).perform(swipeUp());
        onView(withId(R.id.login_button)).perform(click());
        sleep(2000);
        isLoginSucceed = true;
    }

    public void logoutMethod() {
        onView(withId(R.id.radio_singlemline)).perform(swipeRight());
    }
}