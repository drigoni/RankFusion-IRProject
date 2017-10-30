#!/bin/bash

echo "----------START-----------"
latexmk -pdf essay.tex
rm *.aux
rm *.*_*
rm *.log
rm *.fls
rm *.nav
rm *.out
rm *.snm
rm *.toc
rm *.bbl
rm *.blg
echo "----------END-------------"
