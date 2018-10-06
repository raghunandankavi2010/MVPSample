# MVPMobileAssignment
Mobile Assignment to showcase MVP pattern.

Requirements

1. UI rendering of images and text should looks good. //done
2. Negative and error handling scenarios to be handled. //done. 
3. Swipe to refresh to be handled.// there is no new api call happenning. so no need for pull to refresh. if you refresh the data should refresh ( not in our case)
4. Use 3rd party libraries like Retrofit, Butter knife or Dagger etc. Kotlin codes adds advantage.// used retrofit2. need not use other libs mentioned
5. Separation of concern to be met. Network calls and logic to be moved out activity views. Do some MVP patterns. // mvp used
6. More Unit test cases to be covered. // only couple of espresso tests - accroding to additional guidelines

As far as unit testing is concerned i will have more unit tests provided i have time.

If url is null we can have different view types. One for text with image and the other for text only. Otherwise add a palceholder if url is null.

The other problem is DNS and 404 not found for image urls. We need a valid url. Invalid urls needs to be fixed in the json response itself. Read https://github.com/bumptech/glide/issues/1235#issuecomment-292778822

# ScreenShot

![Sample Screenshot](https://github.com/raghunandankavi2010/MVPMobileAssignment/blob/master/device-2018-10-06-134901.png)

