(ns user
  (:require [clojure.tools.namespace.repl :as tnr]
            [clojure.repl :refer :all]
            [proto]))

(defn start
  [])

(defn reset []
  (tnr/refresh :after 'user/start))

(println "worldwind_demo dev/user.clj loaded.")
