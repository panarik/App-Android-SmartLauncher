package com.github.panarik.smartlauncher;

import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.schibsted.spain.barista.interaction.BaristaListInteractions;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.schibsted.spain.barista.assertion.BaristaListAssertions.assertDisplayedAtPosition;
import static com.schibsted.spain.barista.interaction.BaristaListInteractions.clickListItem;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.EasyMock2Matchers.equalTo;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestMain {
    @Rule
    public ActivityScenarioRule<MainActivity> rule = new ActivityScenarioRule<>(
            MainActivity.class);


    //отображение списка приложений
    @Test
    public void test_appList_IsDisplayed() {
        onView(withId(R.id.main_drawerGridView)).check(matches(isDisplayed()));
    }

    //клик на первом приложении
    @Test
    public void test_firstView_click() {
        onData(anything()).inAdapterView(withId(R.id.main_drawerGridView)).atPosition(0).perform(click());
    }

    @Test
    public void test_ViewOnAppList_onDisplayed(){
        assertDisplayedAtPosition(R.id.main_drawerGridView, 0, "Contact");
    }



    /*
    //тест с использованием кастомного матчера
    @Test
    public void test_ViewOnAppList_onDisplayed(){
        onData(allOf(instanceOf(AppObject.class), checkAppName(equalTo("Contacts")))).check(matches(isDisplayed()));
    }
     */

    //кастомный матчер. Ищет поля с наименованием App в AppObject
    /*
    private static Matcher<Object> checkAppName(final String expected) {
        return new BoundedMatcher<Object, AppObject>(AppObject.class) {
            @Override
            public boolean matchesSafely(final AppObject actualObject) {
                return expected.matches(actualObject.getName());
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("row with string " + expected.toString() + " was not found!");
            }
        };
    }

     */

}


