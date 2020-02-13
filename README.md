# AIOPA
What is AIOPA ?
============== 
(Full: All in one Personal App)

An all in one personal android application used to calculate your BMI and save your expenses. Implemented on a basic level as a project for the second year of college. It is targeted to students to help them save their daily expenses and help them record their BMIs.

Contains: Fitness tracker, expense tracker.
<!--  https://guides.github.com/features/mastering-markdown/ -->
<!-- - [x] HEY
- [ ] HEYY

ACRA is used in 1.87% ([See AppBrain/stats](http://www.appbrain.com/stats/libraries/details/acra/acra)) of all apps on Google Play as of Feb 2019. That's over 48K **apps** using ACRA.

See [BasicSetup](http://github.com/ACRA/acra/wiki/BasicSetup) for a step-by-step installation and usage guide.

A crash reporting feature for android apps is native since Android 2.2 (FroYo) but only available through the official Android Market (and with limited data). ACRA is a great help for Android developers :

  * [developer configurable user interaction](http://github.com/ACRA/acra/wiki/AdvancedUsage#wiki-User_Interaction): silent reports, Toast notification, status bar notification or dialog
  * usable with ALL versions of Android supported by the official support libraries.
  * more [detailed crash reports](http://github.com/ACRA/acra/wiki/ReportContent) about the device running the app than what is displayed in the Android Market developer console error reports
  * you can [add your own variables content or debug traces](http://github.com/ACRA/acra/wiki/AdvancedUsage#wiki-Adding_your_own_variables_content_or_traces_in_crash_reports) to the reports
  * you can send [error reports even if the application doesn't crash](https://github.com/ACRA/acra/wiki/AdvancedUsage#sending-reports-for-caught-exceptions-or-for-unexpected-application-state-without-any-exception)
  * works for any application even if not delivered through Google PLay => great for devices/regions where the Google Play is not available, beta releases or for enterprise private apps
  * if there is no network coverage, reports are kept and sent on a later application restart
  * can be used with [your own self-hosted report receiver script](https://github.com/ACRA/acra/wiki/Report-Destinations)

ACRA's notification systems are clean. If a crash occurs, your application does not add user notifications over existing system's crash notifications or reporting features. By default, the "force close" dialog is not displayed anymore, to enable it set `alsoReportToAndroidFramework` to `true`.

The user is notified of an error only once, and you might enhance the perceived quality of your application by defining your own texts in the notifications/dialogs.

Please do not hesitate to open defects/enhancements requests in [the issue tracker](http://github.com/ACRA/acra/issues).

Latest version
===========================================

For the latest version and a complete changelog, please see the [Release page](https://github.com/ACRA/acra/releases).

For migrating from 4.x, please see our [Migration guide](http://github.com/ACRA/acra/wiki/Migrating) in the Wiki.

Backends
========
[Acralyzer](https://github.com/ACRA/acralyzer) is the official backend for report storage and analysis. It runs on CouchDB, for which free hosting solutions exist. It is feature complete, but currently unmaintained. Anybody picking this project up is very welcome.

[Acrarium](https://github.com/F43nd1r/Acrarium) is the recommended alternative, if you do not want to rely on an unmaintained project. Acrarium is in active development and has not reached stable phase yet.

[A lot of other solutions](https://github.com/ACRA/acra/wiki/Backends) have been provided by the community, just check which one you like most.

-->
