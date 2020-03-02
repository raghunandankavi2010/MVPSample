package me.raghu.mvpassignment;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.idling.CountingIdlingResource;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(AndroidJUnit4ClassRunner.class)
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

        CountingIdlingResource componentIdlingResource = mActivityRule.getActivity().getIdlingResourceInTest();
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
     * Disable Wifi/mobile data and test if error text is visible
     */
    @Test
    public void testIfErrorTextISVisible() {

        CountingIdlingResource componentIdlingResource = mActivityRule.getActivity().getIdlingResourceInTest();
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
