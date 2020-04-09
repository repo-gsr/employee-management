# Employee-management 

### This is Sample Application.

#### Technology Stack

* Spring boot.
* Mongodb.
* Jdk 1.8
* Maven.
* Code Coverage Using Jacoco Plugin.
* Docker.
* Docker-Compose.
* Junit.
* Dozer Mapping.
* Jenkinsfile for pipeline.
* Jwt Token Based Security.
* swagger

Keep on Adding new tools to it...........


#### Spring boot

Spring boot application. it provides lotoff features like autoConfiguration facilty to all configuratons like jdbc and security.

#### Mongodb

MongoDb as backend Database for persisting the application data.

#### Maven

Application Build tool to build the application.

#### Code Coverage Using Jacoco Plugin.

Jacoco plugin used for generating codecoverge report of application.

#### Docker & Docker-compose 

Docker is used for creating application docker images to easly deploy it environment and maintain the application scalability.

Docker-compose used for to up the all the container at a time.

#### Junit

Individual Testcase to test the functionality.

#### Dozer Mapping

This is used for bean to bean mapping in side th application.

#### Jenkinsfile 

Jenkinsfile have stages, each stage have one task like Checkout Application from git and build that application and Junit Testing and CodeCoverage and also create the docker images and push those to private docker repository

and push hole application to sonarQube to static code analysis.

#### Jwt Token Based Security

Provid the tocken based security to all rest apis.

#### swagger

API documention.

Steps to try My sample application

* git clone 

* change mongodb creadentials accounrding to you local mongoserver.

* ```mvn clean install -Ddockerfile.skip=true -Dreversion=${ReleaseVersion} -Dverbose=true```

* After building application start the application.
  
  ```mvn spring-boot:run```
  
* access http://localhost:8081

Dockerfile add in the application.

Mainly am using this Dockerfile to build the image of Application by using dockerfile-maven plugin.this plugin when you do docker build it will automatically create the Docker image ans tag that image. it mean it is ready to push you private docker repository.

Following are Some Maven Docker Commands.

mvn dockerfile:build -Dreversion=2.0

-Dreversion is not mandetory this related My sample application.

After dockerfile:build it will create dockerImage with tag(to your private dokcer repository)


mvn dockerfile:push 

The above command will push the create image into private Docker Repository.

For pushing Docker image to your private Dcoker repository you need to add credentails in  maven setting.xml at { User/.m2/setting.xml}

Sample Setting.xml file.

```
<?xml version="1.0"?>

<settings xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://maven.apache.org/SETTINGS/1.0.0">
    <servers>
          <server>
          <id>docker.io</id>
          <username>xxxxxxx</username>
          <password>xxxxxxx</password>
          </server>
    </servers>
</settings>
```

And also add following tag in pom.xml file dockerfile plugin.

```
<useMavenSettingsForAuth>true</useMavenSettingsForAuth>

```

Added Sonar Analysis Stage in jenkins file

And also added CodeCovergae Report Plugin and that will show into Sonar DashBoard.

Run the Junit Test Coverage

mvn test -Dreversion=2.0 -Dverbose=true

mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=a7d10b41f3ddfb5b9fe32c3d69f9476ee82ae6b6 -Dreversion=2.0


While writing Junit Test Case Don't need write Test case for priviate methods.

Those are automatically call while testing normal public methods.
 
 Docker commands
 
 To remove all running containers
 
docker container rm -fv `docker ps -qa`

 To Remove all images
docker rmi `docker images -qa`






mongodb docker conatiner

{
  "requestdata": { 
    "firstName": "venkata",
    "lastName": "Subbareddy",
    "gmail": "subbareddy@gmail.com",
    "gender": "Male",
    "dateOfJoin": "2019-05-27",
    "dateOfBirth": "1991-05-02",
    "phoneNumber": "8446320613",
    "address": [
      {
        "street": "kalamandhir road",
        "zipCode": "5600037",
        "city": "kerala",
        "country": "india",
        "addType": null
      },
      {
        "street": "KPHB",
        "zipCode": "5160037",
        "city": "Hyderabad",
        "country": "india",
        "addType": null
      }
    ],
    "department": {
      "name": "Accounts",
      "description": "account department"
    }
  }
}


