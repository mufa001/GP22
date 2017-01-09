#!/bin/sh

ls -1 | grep '[A-Z]' | tr '[A-Z]' '[a-z]' |xargs ls -1 2> /dev/null
