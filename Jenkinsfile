pipeline 
{
    agent any

    tools 
    {
        maven "Maven3"
        jdk "JDK11"
    }
    
    parameters
    {
        booleanParam(name: 'CLEAN_WORKSPACE', defaultValue: true, description: 'To delete build folder')
    }
    
    environment
    {
        GOOGLE_API_KEY = "AIzaSyA-DCgVotefXQ9QVG3MvMsnJvKBzNkHi50"
        ON_FAILURE_SEND_EMAIL = true
        ON_SUCCESS_SEND_EMAIL = true
        TESTING_FRONTEND = false
    }
    
    stages 
    {
        stage('Build') 
        {
            steps 
            {
                // Get some code from a GitHub repository
                git poll: false, url: 'https://github.com/alexandr1na/springboot-cloud-storage.git'

                bat "./mvnw package"
            
            }

            post 
            {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success
                {
                    echo "Updates installed successful."
                    //junit '**/target/surefire-reports/TEST-*.xml'
                    junit skipPublishingChecks: true, testResults: '**/target/surefire-reports/TEST-*.xml'
                    // archiveArtifacts 'target/*.jar'
                    // cleanWs()
                }
                failure
                {
                    echo "Critical failure!"
                }
            }
        }
        
        stage('Backend Test')
        {
            steps
            {
                echo "Backend test stage: execution."
            }
            post
            {
                success
                {
                    script
                    {
                        echo "Backend test stage: successful."
                        TESTING_FRONTEND = true
                        ON_FAILURE_SEND_EMAIL = false
                    }
                }
                failure
                {
                    script
                    {
                        echo "Backend test stage: failed."
                        TESTING_FRONTEND = false
                        ON_SUCCESS_SEND_EMAIL = false
                    }
                }
            }
        }
        
        stage('Frontend Test')
        {
            steps
            {
                echo "Frontend test stage: ${TESTING_FRONTEND}"
            }
        }
    }
    
    post
    {
        always
        {
            echo "Pipeline execution has ended."
        }
            
        success
        {
            script
            {
                echo "Success !!!"
                if (env.ON_SUCCESS_SEND_EMAIL)
                {
                     emailext(body: "Job name: ${JOB_NAME}, build number: ${BUILD_NUMBER} - Successful! Build URL: ${BUILD_URL}", subject: 'Build success', to: 'alexandrina.cosciuc@gmail.com')
                }
                if (params.CLEAN_WORKSPACE == true)
                {
                    bat "if exist ${BUILD_TAG} rmdir ${BUILD_TAG} /q /s"
                    echo "Folder /builds/${BUILD_TAG} has been deleted."
                }
                else
                {
                    echo "Folder /builds/${BUILD_TAG} doesn't exist."
                }
                cleanWs()
                echo "Workspace has been cleaned."
            }
        }
            
        failure
        {
            script
            {
                echo "Your code sucks !!!"
                if (env.ON_FAILURE_SEND_EMAIL)
                {
                     emailext(body: "Job name: ${JOB_NAME}, build number: ${BUILD_NUMBER} - Failure! Build URL: ${BUILD_URL}", subject: 'Build fail', to: 'alexandrina.cosciuc@gmail.com')
                }
                cleanWs()
                echo "Workspace has been cleaned."
            }
        }
        
        unstable
        {
            echo "Idk man, there's problems."
        }
    }
}
