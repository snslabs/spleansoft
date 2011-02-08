#/bin/sh
if [ -f setEnv-my.sh ]
then
    ./setEnv-my.sh
else
    ./setEnv.sh
fi

./apache-ant-1.6.3/bin/ant $*
