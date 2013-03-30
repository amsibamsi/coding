#!/bin/sh

set -e

VERSION="2.8.5"
NAME="lwjgl-${VERSION}"
ZIP="${NAME}.zip"
URL="http://switch.dl.sourceforge.net/project/java-game-lib/Official%20Releases/LWJGL%20${VERSION}"
TMP="../target/lwjgl"

cd `dirname $0`
mkdir -p $TMP
cd $TMP
if [ ! -e $ZIP ]; then
  wget -O ${ZIP}.tmp $URL/$ZIP
  mv ${ZIP}.tmp ${ZIP}
fi
[ -d $NAME ] && rm -rf $NAME
[ -d new ] && rm -rf new
unzip $ZIP
mkdir -p new
cd new
cp -vr ../${NAME}/native .
cd native/linux
mkdir x86
mv *.so x86
cp -vr x86 x86_64
cd ../macosx
mkdir x86
mv *.dylib *.jnilib x86
cp -vr x86 x86_64
cd ../windows
mkdir x86
mv *.dll x86
cp -vr x86 x86_64
cd ../solaris
mkdir x86
mv *.so x86
cp -vr x86 x86_64
cd ../..
unzip -n ../${NAME}/jar/lwjgl.jar
unzip -n ../${NAME}/jar/jinput.jar
rm -rf META-INF
cd ..
jar cvf new.jar -C new/ .
mvn install:install-file -Dfile=new.jar -DgroupId=org.lwjgl -DartifactId=lwjgl -Dversion=$VERSION -Dpackaging=jar -DcreateChecksum=true
