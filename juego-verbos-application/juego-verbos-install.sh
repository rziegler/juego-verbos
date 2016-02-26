#!/bin/bash
#
# juego-verbos-install	This script installs the juego verbos application.
# 						It 
#
#

APPLICATION_NAME="Juego Verbos Application"
APPLICATION_PREFIX="juegeo-verbos"
APPLICATION_USER="ruth"
APPLICATION_HOME="/appdata/juego-verbos"
APPLICATION_JAR="juego-verbos-application.jar"
APPLICATION_CONFIG="juego-verbos-prd.yml"
APPLICATION_CMD="java -jar ${APPLICATION_HOME}/${APPLICATION_JAR} server ${APPLICATION_HOME}/${APPLICATION_CONFIG}"
APPLICATION_SHUTDOWN_WAIT=120
APPLICATION_SRC_BACKEND="/home/ruth/juego-verbos"
APPLICATION_SRC_UI="/home/ruth/juego-verbos/ui"



ui() {
	cd ${APPLICATION_SRC_UI}
	git pull
	echo "Copying juego-verbos UI from '${APPLICATIN_SRC_UI}' to '${APPLICATION_HOME}'"
	cp ${APPLICATIN_SRC_UI}/app ${APPLICATION_HOME}/ui
    return 0
}

backend() {
    cd ${APPLICATION_SRC_BACKEND}
	git pull
	cd ${APPLICATION_SRC_BACKEND}/juego-verbos-application
	mvn clean package
	echo "Adjusting chmod for scripts"
	chmod 755 ${APPLICATIN_SRC_BACKEND}/juego-verbos-application/${APPLICATION_PREFIX}*.sh
	
	echo "Copying juego-verbos backend from '${APPLICATIN_SRC_BACKEND}' to '${APPLICATION_HOME}'"
	cp ${APPLICATIN_SRC_BACKEND}/juego-verbos-application/target/${APPLICATION_PREFIX}*.jar ${APPLICATION_HOME}/${APPLICATION_JAR}
	cp ${APPLICATIN_SRC_BACKEND}/juego-verbos-application/${APPLICATION_PREFIX}-prd.yml ${APPLICATION_HOME}
	cp ${APPLICATIN_SRC_BACKEND}/juego-verbos-application/${APPLICATION_PREFIX}*.sh ${APPLICATION_HOME}
    return 0
}

case $1 in
    ui)
        ui
        ;;
    backend)
        backend
        ;;
    all)
        ui
        backend
        ;;
    *)
    echo "Usage: $0 {all|ui|backend}"
    exit 1
    ;;

esac

exit 0