(ns steamweb.core
  "High level Steam Web API for Clojure."
  (:require [steamweb.client :as client]
            [steamweb.util :as util]
            [clojure.string :as string]))

(def base-url "http://api.steampowered.com")

(defn- steam
  [interface method version args]
  (client/slurp-json (string/join "/" [base-url
                                       interface
                                       method
                                       (format "v%04d" version)
                                       (str "?" (util/serialise-args args))])))


;; SteamNews methods.

(def SteamNews "ISteamNews")

(defn news-app
  "Returns the latest news for the specified app id."
  ([appid] (news-app appid 3 300))
  ([appid count] (news-app appid count 300))
  ([appid count maxlength] (steam SteamNews
                                  "GetNewsForApp"
                                  2
                                  {"appid" appid,
                                   "count" count,
                                   "maxlength" maxlength,
                                   "format" "json"})))

;; SteamUser methods.

(def SteamUser "ISteamUser")

(defn player-summaries
  "Returns basic profile information for a list of 64-bit Steam IDs."
  [key steamids] (steam SteamUser
                        "GetPlayerSummaries"
                        2
                        {"key" key,
                         "steamids" (string/join "," steamids)
                         "format" "json"}))

(defn friend-list
  "Returns the friend list of the specified Steam user, provided his
Steam Community profile is set to Public.

  Correct values for relationship are: \"all\" and \"friend\"."
  ([key steamid] (friend-list key steamid "all"))
  ([key steamid relationship] (steam SteamUser
                                     "GetFriendList"
                                     1
                                     {"key" key
                                      "steamid" steamid
                                      "relationship" relationship
                                      "format" "json"})))

;; SteamUserStats methods.

(def SteamUserStats "ISteamUserStats")

(defn global-achievement-percentages-for-app
  "Returns global achievements overview for the specified game in percentages."
  [gameid] (steam SteamUserStats
                  "GetGlobalAchievementPercentagesForApp"
                  2
                  {"gameid" gameid
                   "format" "json"}))

(defn player-achievements
  "Returns a list of achievements for the specified user and app id."
  ([key steamid appid] (player-achievements key steamid appid "en"))
  ([key steamid appid l] (steam SteamUserStats
                                "GetPlayerAchievements"
                                1
                                {"key" key
                                 "steamid" steamid
                                 "appid" appid
                                 "l" l
                                 "format" "json"})))

(defn user-stats-for-game
  "Returns a list of stats for the specified user and app id."
  ([key steamid appid] (user-stats-for-game key steamid appid "en"))
  ([key steamid appid l] (steam SteamUserStats
                                "GetUserStatsForGame"
                                2
                                {"key" key
                                 "steamid" steamid
                                 "appid" appid
                                 "l" l
                                 "format" "json"})))

(defn schema-for-game
  "Returns game information for the specified app id."
  ([key appid] (schema-for-game key appid "en"))
  ([key appid l] (steam SteamUserStats
                        "GetSchemaForGame"
                        2
                        {"key" key
                         "appid" appid
                         "l" l
                         "format" "json"})))

;; PlayerService methods

(def PlayerService "IPlayerService")

(defn owned-games
  "Returns a list of games a player owns along with some playtime information,
if the profile is publicly visible. Private, friends-only, and other privacy
settings are not supported unless you are asking for your own personal details
(ie the WebAPI key you are using is linked to the steamid you are requesting)."
  [key steamid] (steam PlayerService
                       "GetOwnedGames"
                       1
                       {"key" key
                        "steamid" steamid
                        "format" "json"}))

(defn recently-played-games
  "Returns a list of games a player has played in the last two weeks, if the
profile is publicly visible. Private, friends-only, and other privacy settings
are not supported unless you are asking for your own personal details (ie the WebAPI
key you are using is linked to the steamid you are requesting)."
  [key steamid] (steam PlayerService
                       "GetRecentlyPlayedGames"
                       1
                       {"key" key
                        "steamid" steamid
                        "format" "json"}))
