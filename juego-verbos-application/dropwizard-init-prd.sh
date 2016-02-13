#!/bin/bash
#
# dropwizard     This shell script takes care of starting and stopping Dropwizard applications
# inspired by https://gitlab.tux-inside.com/jboelen/dropwizard-tools/blob/master/dropwizard-init-script
#
# chkconfig: - 80 20
#
### BEGIN INIT INFO
# Provides: dropwizard
# Required-Start: $network $syslog
# Required-Stop: $network $syslog
# Default-Start:
# Default-Stop:
# Short-Description: start and stop dropwizard
### END INIT INFO

APPLICATION_NAME="Juego Verbos Application"
APPLICATION_USER="ruth"
APPLICATION_HOME="/appdata/juego-verbos"
APPLICATION_JAR="juego-verbos-application.jar"
APPLICATION_CONFIG="juego-verbos-prd.yml"
APPLICATION_CMD="java -jar ${APPLICATION_HOME}/${APPLICATION_JAR} server ${APPLICATION_HOME}/${APPLICATION_CONFIG}"
APPLICATION_SHUTDOWN_WAIT=120

dropwizard_pid() {
    echo `ps aux | grep "${APPLICATION_CMD}" | grep -v grep | awk '{ print $2 }'`
}

start() {
    pid=$(dropwizard_pid)
    if [ -n "$pid" ]
    then
        echo "${APPLICATION_NAME} is already running (pid: $pid)"
    else
        # Start dropwizard
        currentUser=$(whoami)
        echo "Starting ${APPLICATION_NAME} for user '${currentUser}'"
        if [ $currentUser  == ${APPLICATION_USER} ]
        then
                cd ${APPLICATION_HOME}; ${APPLICATION_CMD} > /dev/null &
        else
                runuser ${APPLICATION_USER} -c "cd ${APPLICATION_HOME}; ${APPLICATION_CMD} > /dev/null &"
        fi
    fi
    return 0
}

stop() {
    pid=$(dropwizard_pid)
    if [ -n "$pid" ]
    then
        echo "Stopping ${APPLICATION_NAME}"
        kill $pid

    let kwait=$APPLICATION_SHUTDOWN_WAIT
    count=0
    count_by=5
    until [ `ps -p $pid | grep -c $pid` = '0' ] || [ $count -gt $kwait ]
    do
        echo "Waiting for processes to exit. Timeout before we kill the pid: ${count}/${kwait}"
        sleep $count_by
        let count=$count+$count_by;
    done

    if [ $count -gt $kwait ]; then
        echo "Killing processes which didn't stop after ${APPLICATION_SHUTDOWN_WAIT} seconds"
        kill -9 $pid
    fi
    else
        echo "${APPLICATION_NAME} is not running"
    fi

    return 0
}

status(){
    pid=$(dropwizard_pid)
    if [ -n "$pid" ]; then
        echo "${APPLICATION_NAME} is running with pid: $pid"
    else
        echo "${APPLICATION_NAME} is not running"
	exit 1
    fi
}

case $1 in
    start)
        start
        ;;
    stop)
        stop
        ;;
    restart)
        stop
        start
        ;;
    status)
        status
        ;;
    *)
    echo "Usage: $0 {start|stop|restart|status}"
    exit 1
    ;;

esac

exit 0