pipeline {
  agent any
  environment {
	DOCKERHUB_CREDENTIALS=credentials('docker_hub_login')
	tag = "${env.BUILD_NUMBER}"
    reponame = "kennedy02/bringbank"
	  
}
  stages {
    stage('Build') {
      steps{
        echo 'Running build automation tool'
        sh 'mvn clean install --settings configuration/settings.xml'
        archiveArtifacts artifacts: '**/target/*.jar'
      }
    }
    stage('Build image') {
      when {
        branch 'master'
      }
      steps {
        echo 'Building image'
        withKubeConfig([credentialsId: 'kubeconfigs', serverUrl: 'https://192.168.56.120:6443']){
          sh "mvn --settings configuration/settings.xml k8s:build -Pkubernetes -DskipTests -Djkube.generator.name='${env.reponame}:${env.tag}'"
        }
      }
    }
    stage('Push image to dockerhub'){
      when {
        branch 'master'
      }
      steps{
        sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
   	sh "mvn --settings configuration/settings.xml k8s:push -Djkube.generator.name='kennedy02/bringbank'"
      }
    }
    stage('Generate the Kubernetes resource manifests'){
      when {
        branch 'master'
      }
      steps{
   	sh "mvn --settings configuration/settings.xml k8s:resource -Djkube.enricher.jkube-service.type='NodePort' -Djkube.generator.name='${env.reponame}:${env.tag}'"
      }
    }
    stage('Deploy t0 Kubernetes cluster'){
      when {
        branch 'master'
      }
      steps{
   	withKubeConfig([credentialsId: 'kubeconfigs', serverUrl: 'https://192.168.56.120:6443']){
	  sh 'kubectl config set-context --current --namespace=bringbank'
	  sh "mvn --settings configuration/settings.xml k8s:deploy -Pkubernetes -DskipTests -Djkube.generator.name='${env.reponame}:${env.tag}'"
	}
      }
    }  
  }
}
