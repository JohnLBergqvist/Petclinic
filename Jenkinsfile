pipeline {
    agent { label 'linux' }
    environment {
        DCOVER_URL = credentials('DCOVER_URL')
        DCOVER_LICENSE_KEY = credentials('DCOVER_LICENSE_KEY')
        DCOVER = 'dcover/dcover'
    }
    stages {
        stage('Use dcover create in Jenkins') {
            steps {
                sh label: 'Get and unzip dcover jars into directory dcover, store dcover script location for later use',
                script: '''
                    mkdir -p dcover
                    wget --quiet "$DCOVER_URL" --output-document "$DCOVER.zip"
                    unzip -qo "$DCOVER.zip" -d dcover
                '''
                sh label: 'Activate dcover',
                script: '"$DCOVER" activate "$DCOVER_LICENSE_KEY"'
                sh label: 'Build project',
                script: 'mvn --batch-mode --no-transfer-progress compile'
                sh label: 'Run dcover create',
                script: '"$DCOVER" create --batch --maven org.springframework.samples.petclinic.system.CrashController'
                sh label: 'Run tests - incorporating any tests from dcover',
                script: 'mvn --batch-mode --no-transfer-progress prepare-package'
            }
        }
    }
}
