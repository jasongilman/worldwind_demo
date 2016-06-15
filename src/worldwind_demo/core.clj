(ns worldwind-demo.core
  (:import  (java.net URL)
            (java.io BufferedReader InputStreamReader)
            (java.awt Dimension)
            (gov.nasa.worldwind Configuration WorldWind)
            (gov.nasa.worldwind.avlist AVKey)
            (gov.nasa.worldwind.awt WorldWindowGLCanvas)
            (gov.nasa.worldwind.geom LatLon Position)
            (gov.nasa.worldwind.view.orbit BasicOrbitView)
            (gov.nasa.worldwind.layers IconLayer)
            (gov.nasa.worldwind.render UserFacingIcon)
            (gov.nasa.worldwind.event SelectEvent)))


(defn world []
  (Configuration/setValue AVKey/INITIAL_LATITUDE 39.3113)
  (Configuration/setValue AVKey/INITIAL_LONGITUDE 32.8038)
  (Configuration/setValue AVKey/INITIAL_ALTITUDE 1000000)
  (doto (WorldWindowGLCanvas.)
    (.setModel (WorldWind/createConfigurationComponent
                AVKey/MODEL_CLASS_NAME))))


(defn frame []
  (let [world (world)]
        ; layers (.getLayers (.getModel world))
        ; earth-quakes (map #(icon %) (eartquakes))
    ; (.add layers (icon-layer earth-quakes))
    ; (.addSelectListener world (select-listener))
    (doto (javax.swing.JFrame.)
          (.add world)
          (.setSize (Dimension. 400 400))
          (.setAlwaysOnTop true)
          (.setVisible true))))

(comment
 (frame))
