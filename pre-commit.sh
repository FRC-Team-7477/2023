#!/bin/sh

printf "***** Running pre-commit hook *****\n"
git stash -q --keep-index
./gradlew test
status=$?
git stash pop -q
printf "***** Pre-commit hook complete *****\n"
exit $status