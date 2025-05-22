#!/bin/bash

# Read packets, removs spaces and tabs:
packets=$(cat | tr -s ' ')


# Initialize variable for storing good packets:
successful=""


# Read each line from the input file:
while read line; do
# Remove spaces and comments from the line:

line=$(echo $line | tr -d ' ' | sed -e 's/#.*//')


# Skip empty lines:
if [ ! -z "$line" ]; then
        # put packets to packets variable:
        remained=$packets

        # Split the array into four and send individually:
        for index in {1..4}; do
            rule=$(echo $line | cut -d "," -f$index)
            remained=$(echo "$remained" | ./firewall.exe "$rule")
        done

        # continue:
        successful+=$(echo "$remained")
        successful+="\n"
fi


done < "$1" 


# Print packets in sorted order:
echo -e "$successful" | grep -vE ^$ | tr -d ' ' | sort | uniq