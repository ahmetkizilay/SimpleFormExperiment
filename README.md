va/com/ahmetkizilay/test/simpleform/simpleformexperiment/fragments/FormShowFragment.java). Depending on the contents of the SharedPreferences, the Activity chooses between these two Fragments. Upon saving or clearing data, the Fragments are replaced by each other. (That simple)

###Tests
I created a MockContext that provides me with shadowed SharedPreferences. You can find these custom components underneath the [mock package](https://github.com/artsince/SimpleFormExperiment/tree/master/app/src/androidTest/java/com/ahmetkizilay/test/simpleform/simpleformexperiment/mock). The rests of the tests should be quite self-explanatory.

###Closing Notes
As you can see on Travis's online documentation, the support for Android development is still in [beta](http://docs.travis-ci.com/user/languages/android/). Sometimes the emulator just won't work and the build will fail. So, be patient and try again...

I hope you find this useful in some way.