#!/usr/bin/env zsh
path+=/home/lucas/.jdks/openjdk-15.0.2/bin
FILE2="/home/lucas/Documentos/GitProjects/Machine/execute/instructionstxt/instructions90.txt"
for FILE in /home/lucas/Documentos/GitProjects/Machine/execute/instructionstxt/*.txt; do
java -jar /home/lucas/Documentos/GitProjects/Machine/out/artifacts/Machine_jar/Machine.jar <<EOF
$FILE
$FILE2
8
16
32
EOF
echo;

java -jar /home/lucas/Documentos/GitProjects/Machine/out/artifacts/Machine_jar/Machine.jar <<EOF
$FILE
$FILE2
32
64
128
EOF
echo;

java -jar /home/lucas/Documentos/GitProjects/Machine/out/artifacts/Machine_jar/Machine.jar <<EOF
$FILE
$FILE2
16
64
256
EOF
echo;

java -jar /home/lucas/Documentos/GitProjects/Machine/out/artifacts/Machine_jar/Machine.jar <<EOF
$FILE
$FILE2
8
32
128
EOF
echo;

java -jar /home/lucas/Documentos/GitProjects/Machine/out/artifacts/Machine_jar/Machine.jar <<EOF
$FILE
$FILE2
16
32
64
echo;
EOF
echo;
echo;
done
