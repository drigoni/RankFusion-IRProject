#!/bin/bash 

############################
#Author: Davide Rigoni 
#Github: drigoni
###########################


echo "-------Start---------"
#make directory and extract file.zip
fName=$(echo $1 | awk 'BEGIN{FS="."}{ print $1}');
mkdir "$fName";
unzip $1 -d "./$fName/";
rm -R "$fName/__MACOSX";

#extract all files file .Z
for f in $(find $fName -name '*.Z')
do
	echo "uncompress $f";
	uncompress $f;
done


#rename and extract all file *.?Z
for f in $(find $fName -name '*.?Z')
do
	name1=$(echo "$f" | awk 'BEGIN{FS="."}{ print $1}')
	extension=$(echo "$f" | awk 'BEGIN{FS="."}{ print $2 }');
	firstC=$(echo $extension | awk '{print substr($0,0,1)}');
	
	name=$name1$firstC".Z";
	echo "cp $f $name";
	cp $f $name;

	echo "uncompress $name";
	uncompress $name;

	echo "rm $f";
	rm $f;
done



echo "-------End---------"

