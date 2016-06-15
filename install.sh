lein localrepo install worldwind-2.0.0/worldwind.jar gov.nasa/worldwind 2.0.0
lein localrepo install worldwind-2.0.0/worldwindx.jar gov.nasa/worldwindx 2.0.0
lein localrepo install worldwind-2.0.0/jogl-all.jar gov.nasa/jogl 2.0.0
lein localrepo install worldwind-2.0.0/gluegen-rt.jar gov.nasa/gluegen-rt 2.0.0
lein localrepo install worldwind-2.0.0/gdal.jar gov.nasa/gdal 2.0.0

cp worldwind-2.0.0/gluegen-rt-natives-macosx-universal.jar \
~/.m2/repository/gov/nasa/gluegen-rt/2.0.0/gluegen-rt-2.0.0-natives-macosx-universal.jar

cp worldwind-2.0.0/jogl-all-natives-macosx-universal.jar \
~/.m2/repository/gov/nasa/jogl/2.0.0/jogl-2.0.0-natives-macosx-universal.jar
