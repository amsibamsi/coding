#!/bin/sh

set -e

VERSION="0.9.6"
ZIP="libgdx-${VERSION}.zip"
URL="http://libgdx.googlecode.com/files"
LIBS='gdx gdx-backend-lwjgl gdx-backend-lwjgl-natives gdx-natives gdx-openal gdx-setup-ui'
TMP='../target/libgdx'

cd `dirname $0`
mkdir -p $TMP
cd $TMP
if [ ! -e $ZIP ]; then
  wget -O $ZIP.part $URL/$ZIP
  mv $ZIP.part $ZIP
fi
mkdir -p extracted
cd extracted
for lib in $LIBS; do
  if [ ! -e ${lib}.jar ]; then
    unzip ../$ZIP ${lib}.jar
  fi
  mvn install:install-file -Dfile=${lib}.jar -DgroupId=com.badlogic -DartifactId=$lib -Dversion=$VERSION -Dpackaging=jar -DcreateChecksum=true
done
