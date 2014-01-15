# steamweb

A clojure wrapper for Steam Web API.

[![Build Status](https://travis-ci.org/nabrozidhs/steamweb.clj.png?branch=master)](https://travis-ci.org/nabrozidhs/steamweb.clj)

## Installation

`steamweb` is available as a Maven artifact from [Clojars](http://clojars.org/clj-http):

```clojure
[steamweb "0.1.0"]
```

## Usage

The main funcionality is provided by the `steamweb.core` namespace.

``` clojure
;; include steamweb
(require '[steamweb.core :as steam])

;; Load latest news for Dota 2
(steam/news-app 570)

;; For most Steam methods you'll need a Steam Web API key,
;; you can obtain one from http://steamcommunity.com/dev/apikey
(def key "YOUR_API_KEY")

;; retrieve basic steam profile information.
(steam/player-summaries key "76561198002786626")

;; retrieve a list of games a player owns.
(steam/owned-games key "76561198002786626")
```

## Acknowledgement

I used Sun Ning's [reddit.clj](https://github.com/sunng87/reddit.clj) as a base for this project.

## License

Copyright (C) 2014 Alex Nabrozidis

Distributed under the Eclipse Public License, the same as Clojure.
