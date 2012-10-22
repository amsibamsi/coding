#!/bin/sh

NIGHTLY='2012-10-21'
VERSION="3.0.0-$NIGHTLY"
ZIP="jME3_$NIGHTLY.zip"
URL="http://jmonkeyengine.com/nightly"
LIBS='jME3-core jME3-desktop jME3-lwjgl jME3-lwjgl-natives lwjgl jME3-testdata'
TMP='../target/jme'

cd `dirname $0`
mkdir -p $TMP
cd $TMP
if [ ! -e $ZIP ]; then
  wget -O $ZIP.part $URL/$ZIP || exit 1
  mv $ZIP.part $ZIP || exit 1
fi
mkdir -p $NIGHTLY
cd $NIGHTLY
if [ ! -e lib ]; then
  unzip ../$ZIP lib/* || exit 1
fi
for lib in $LIBS; do
  mvn install:install-file -Dfile=lib/$lib.jar -DgroupId=com.jme3 -DartifactId=$lib -Dversion=$VERSION -Dpackaging=jar -DcreateChecksum=true || exit 1
done
