#!/usr/bin/env sh
if [ $# -ne 2 ]
then
  echo "Error: Usage $0 <input> <password>"
  exit 1
fi

input=$1
password=$2
~/bin/jasypt/bin/encrypt.sh input="$input" password="$password" # algorithm="PBEWithMD5AndDES" keyObtentionIterations="1000" providerName="SunJCE" saltGeneratorClassName="org.jasypt.salt.RandomSaltGenerator" ivGeneratorClassName="org.jasypt.iv.NoIvGenerator" stringOutputType="base64"