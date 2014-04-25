# clj2fa

Example [HMAC](http://en.wikipedia.org/wiki/HMAC-based_One-time_Password_Algorithm)/[Time](http://en.wikipedia.org/wiki/Time-based_One-time_Password_Algorithm) -based [One Time Password](http://en.wikipedia.org/wiki/One-time_password) project using [clj-otp](https://github.com/ttasterisco/clj-otp).

You'll need `clj-otp` in your `~/.m2` folder since it's not published to clojars.
Just clone the repo, `lein jar`-it, `mkdir ~/.m2/repository/clj-otp/clj-otp/0.1.2` and put the jar there.

Then install [Google Authenticator](https://code.google.com/p/google-authenticator/) on your phone, `lein run` here and follow the steps.
