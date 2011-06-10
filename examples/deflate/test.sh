#!/bin/sh

if [ -z "$1" -o -z "$2" ]; then
  echo "Usage: $0 <deflate_executable> <inflate_executable>" >&2
  exit 1
else
  cd `dirname $1`
  DEFLATE="`pwd`/`basename $1`"
  INFLATE="`pwd`/`basename $2`"
fi

TMP=`mktemp -d /tmp/test.sh.XXXX`
cd $TMP

dd if=/dev/urandom of=random bs=1m count=10
cat random | $DEFLATE > def
cat def | $INFLATE > inf
diff random inf

cd ..
rm -rf $TMP
