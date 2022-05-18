# Karpet
Karpet is a work in progress minestom server written for vanilla
![](./minecraft.png)

## Building
First install the following

   - [JDK 17](https://adoptium.net)
   - [Gradle](https://gradle.org) (not required, you can use gradlew)
   - [Git](https://git-scm.com)
    
Then clone this repo like below:
```shell
git clone https://github.com/Interfiber/Karpet.git
cd Karpet
```
Then building it with gradle
```shell
gradle shadowJar
# or use ./gradlew shadowJar on linux(or gradlew shadowJar on windows)
```
Then run it with java:
```shell
java -jar build/libs/*.jar
```
