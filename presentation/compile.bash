#!/bin/bash

echo "----------START-----------"
latexmk -pdf main.tex
rm *.aux
rm *.*_*
rm *.log
rm *.fls
rm *.nav
rm *.out
rm *.snm
rm *.toc
echo "----------END-------------"
