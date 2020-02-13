# employee-management

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

<?xml version="1.0"?>

-<settings xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://maven.apache.org/SETTINGS/1.0.0">
    -<servers>
          -<server>
          <id>docker.io</id>
          <username>xxxxxxx</username>
          <password>xxxxxxx</password>
          </server>
    </servers>
</settings>

And also add following tag in pom.xml file dockerfile plugin.

<useMavenSettingsForAuth>true</useMavenSettingsForAuth>
