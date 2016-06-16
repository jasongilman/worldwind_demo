(ns worldwind-demo.core
  (require [clojure.string :as str])
  (import [java.net URL]
          [java.io BufferedReader InputStreamReader]
          [java.awt Dimension]
          [gov.nasa.worldwind Configuration WorldWind]
          [gov.nasa.worldwind.avlist AVKey]
          [gov.nasa.worldwind.awt WorldWindowGLCanvas]
          [gov.nasa.worldwind.geom LatLon Position]
          [gov.nasa.worldwind.view.orbit BasicOrbitView]
          [gov.nasa.worldwind.layers IconLayer]
          [gov.nasa.worldwind.render UserFacingIcon]
          [gov.nasa.worldwind.event SelectEvent]))

;; Based on http://nakkaya.com/2009/12/23/hello-world-wind/

(defrecord EarthQuake
  [date
   time
   latitude
   longitude
   depth
   md
   ml
   ms
   location])

(defn fetch-url
  [address]
  (let [url (URL. address)]
    (with-open [stream (.openStream url)]
               (let [buf (BufferedReader.
                          (InputStreamReader. stream "windows-1254"))]
                 (apply str (interleave (line-seq buf) (repeat \newline)))))))

(defn parse
  [rows]
  (map #(apply ->EarthQuake (str/split % #"\s+" 9))
       rows))

(defn earthquakes
  []
  (let [page (fetch-url "http://www.koeri.boun.edu.tr/scripts/lst9.asp")
        rows (->> (str/split-lines page)
                  (drop-while #(not (.contains % "<pre>")))
                  (drop 9)
                  reverse
                  (drop-while #(not (.contains % "</pre>")))
                  (drop 2)
                  reverse)]
    (parse rows)))


(comment
 (def quake2 (first (earthquakes))))

(defn goto-pos [world lat long elev]
  (let [position (Position. (LatLon/fromDegrees lat long) (* elev 10000))
        view (cast BasicOrbitView (.getView world))]
    (.goTo view position (* elev 10000))))

(defn icon [quake]
  (doto (UserFacingIcon.
         "icon.png" (Position.
                     (LatLon/fromDegrees (Double. (:latitude quake))
                                         (Double. (:longitude quake))) 0.0))

        (.setToolTipText (format "Time: %s\nMagnitude: %s\nDepth: %s"
                                 (:time quake) (:ml quake) (:depth quake)))))

(defn icon-layer [icons]
  (let [layer (IconLayer.)]
    (doseq [icon icons]
      (.addIcon layer icon)) layer))

(defn select-listener []
  (proxy
   [gov.nasa.worldwind.event.SelectListener] []
   (selected
    [e]
    (let [object (.getTopObject e)]
      (if (= (.getEventAction e) SelectEvent/LEFT_CLICK)
        (if (instance? UserFacingIcon object)
          (.setShowToolTip object (not (.isShowToolTip object)))))))))

(defn world
  []
  (Configuration/setValue AVKey/INITIAL_LATITUDE 39.3113)
  (Configuration/setValue AVKey/INITIAL_LONGITUDE 32.8038)
  (Configuration/setValue AVKey/INITIAL_ALTITUDE 1000000)
  (doto (WorldWindowGLCanvas.)
    (.setModel (WorldWind/createConfigurationComponent
                AVKey/MODEL_CLASS_NAME))))

(defn frame []
  (let [world (world)
        layers (.getLayers (.getModel world))
        earth-quakes (map #(icon %) (earthquakes))]
    (.add layers (icon-layer earth-quakes))
    (.addSelectListener world (select-listener))
    (doto (javax.swing.JFrame.)
          (.add world)
          (.setSize (Dimension. 400 400))
          (.setAlwaysOnTop true)
          (.setVisible true))))

(comment
 (frame))
