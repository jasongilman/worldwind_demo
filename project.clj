(defproject worldwind_demo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [gov.nasa/worldwind "2.0.0"]
                 [gov.nasa/worldwindx "2.0.0"]
                 [gov.nasa/jogl "2.0.0"]
                 [gov.nasa/gluegen-rt "2.0.0"]
                 [gov.nasa/gdal "2.0.0"]]

  :plugins [[lein-localrepo "0.5.3"]]

  :jvm-opts ["-Xmx1024M" "-Djava.library.path=worldwind-2.0.0"]

  :profiles
  {:dev {:source-paths ["dev" "src" "test"]
         :dependencies [[org.clojure/tools.namespace "0.2.11"]
                        [pjstadig/humane-test-output "0.7.1"]
                        [proto-repl "0.2.0"]]
         :injections [(require 'pjstadig.humane-test-output)
                      (pjstadig.humane-test-output/activate!)]}})
