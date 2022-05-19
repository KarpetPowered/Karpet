#!/usr/bin/env bash

function printHelp(){
  echo "dev commands:"
  echo "  build      - Build fatjar"
  echo "  devserver  - Run & build the dev server"
}


if [[ "$1" == "" ]]; then
  printHelp
fi

if [[ "$1" == "build" ]]; then
  gradle shadowJar --console=
fi

if [[ "$1" == "devserver" ]]; then
  gradle shadowJar
  java -jar build/libs/karpet-1.0-SNAPSHOT-all.jar
fi