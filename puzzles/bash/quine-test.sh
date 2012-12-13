#!/usr/bin/env sh

cd `dirname $0`

src=`cat quine.sh`
out=`./quine.sh`

if [ "$src" = "$out" ]; then
  echo 'is quine'
  exit 0
else
  echo 'is not quine'
  exit 1
fi
