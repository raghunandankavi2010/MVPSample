package me.raghu.mvpassignment;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;

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
        onView(withId(R.id.errorText))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.progressBar))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

        onView(withId(R.id.recyclerView)).check(new RecyclerViewItemCountAssertion(13));

        IdlingRegistry.getInstance().unregister(componentIdlingResource);

    }

    /**
     *  Disable Wifi/mobile data and test if error text is visible
     */
    @Test
    public void testIfErrorTextISVisible() {

        CountingIdlingResource componentIdlingResource =  mActivityRule.getActivity().getIdlingResourceInTest();
        IdlingRegistry.getInstance().register(componentIdlingResource);

        onView(withId(R.id.progressBar))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.recyclerView))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.errorText))
                .check(matches(isDisplayed()));
        onView(withId(R.id.errorText))
               .check(matches(withText(containsString(mActivityRule.getActivity().getResources().getString(R.string.error)))));

        IdlingRegistry.getInstance().unregister(componentIdlingResource);

    }
}
