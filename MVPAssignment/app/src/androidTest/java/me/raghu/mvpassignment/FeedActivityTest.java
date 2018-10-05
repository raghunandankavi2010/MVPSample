package me.raghu.mvpassignment;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FeedActivityTest {


    @Rule
    public ActivityTestRule<FeedActivity> mActivityRule = new ActivityTestRule<>(
            FeedActivity.class);

    /**
     * enable Wifi/mobile data and test if reyclerview is displayed with correct count
     */
    @Test
    public void testIfRecyclerViewIsVisible() {

        CountingIdlingResource componentIdlingResource =  mActivityRule.getActivity().getIdlingResourceInTest();

        IdlingRegistry.getInstance().register(componentIdlingResource);


        onView(withId(R.id.recyclerView))
                .check(matches(isDisplayed()));

        onView(withId(R.id.recyclerView)).check(new RecyclerViewItemCountAssertion(14));

    }

    /**
     *  Disable Wifi/mobile data and test if error text is visible
     */
    @Test
    public void testIfErrorTextISVisible() {

        CountingIdlingResource componentIdlingResource =  mActivityRule.getActivity().getIdlingResourceInTest();

        IdlingRegistry.getInstance().register(componentIdlingResource);


        onView(withId(R.id.errorText))
                .check(matches(isDisplayed()));
        onView(withId(R.id.errorText))
               .check(matches(withText(containsString("Something Wrong!.No data to display"))));

    }
}
