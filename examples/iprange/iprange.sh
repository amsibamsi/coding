#!/usr/bin/env bash

failed=0

ip2dec() {
  local ip=$1
  IFS=. read -r t1 t2 t3 t4 <<< "$ip"
  echo "$((t1 * 2 ** 24 + t2 * 2 ** 16 + t3 * 2 ** 8 + t4))"
}

dec2ip() {
  local ip_dec=$1
  local t1 t2 t3 t4
  t1=$(($ip_dec           / 2 ** 24))
  t2=$(($ip_dec % 2 ** 24 / 2 ** 16))
  t3=$(($ip_dec % 2 ** 16 / 2 **  8))
  t4=$(($ip_dec % 2 **  8          ))
  echo "$t1.$t2.$t3.$t4"
}

first_ip_dec() {
  local ip_dec=$1 mask=$2 skip=$3
  local mask_dec=$(((2 ** mask - 1) << (32 - mask)))
  echo $(((ip_dec & mask_dec) + skip))
}

first_ip() {
  local ip=$1 mask=$2 skip=$3
  echo $(dec2ip $(first_ip_dec $(ip2dec $ip) $mask $skip))
}

last_ip_dec() {
  local ip_dec=$1 mask=$2 skip=$3
  local mask_dec=$((2 ** (32 - mask) - 1))
  echo $(((ip_dec | mask_dec) - skip))
}

last_ip() {
  local ip=$1 mask=$2 skip=$3
  echo $(dec2ip $(last_ip_dec $(ip2dec $ip) $mask $skip))
}

assert() {
  local name=$1 is=$2 should=$3
  if [ "$is" = "$should" ]; then
    echo "+ $name is $is"
  else
    echo "! $name should be $should, but is $is" >&2
    failed=1
  fi
}

IP=(0.0.0.0 0.0.0.1 0.0.1.0 0.1.0.0 1.0.0.0 1.1.1.1)
IP_DEC=(0 1 256 65536 16777216 16843009)
for ((i=0; i<${#IP[*]}; i++)); do
  assert 'decimal ip' `ip2dec ${IP[i]}` ${IP_DEC[i]}
  assert 'ip' `dec2ip ${IP_DEC[i]}` ${IP[i]}
done

NET=(192.168.1.0 10.0.0.0 10.1.2.3 1.2.3.20)
MASK=(24 8 16 28)
SKIP_START=(0 0 0 3)
SKIP_END=(0 0 0 5)
FIRST=(192.168.1.0 10.0.0.0 10.1.0.0 1.2.3.19)
LAST=(192.168.1.255 10.255.255.255 10.1.255.255 1.2.3.26)
for ((i=0; i<${#NET[*]}; i++)); do
  assert 'first ip' `first_ip ${NET[i]} ${MASK[i]} ${SKIP_START[i]}` ${FIRST[i]}
  assert 'last ip' `last_ip ${NET[i]} ${MASK[i]} ${SKIP_END[i]}` ${LAST[i]}
done

exit $failed
