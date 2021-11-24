# escape=`

FROM openjdk:8-jdk-alpine

LABEL maintainer="hieulv0712@gmail.com"

# Mount /tmp as a volumn
#VOLUME /mnt/fileshare

# Change working directory to another
#WORKDIR /etc/opt

#### START OF TIMEZONE RELATED COMMANDS ####
RUN apk update
RUN apk add tzdata
ENV TZ="Asia/HoChiMinh"
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
RUN "date"
#### END OF TIMEZONE RELATED COMMANDS ####

# Define jar file name
ENV JAR_FILE_NAME=fsoft-igos-edxops.jar
ENV JAR_FILE_PATH=target/${JAR_FILE_NAME}

# Copy jar file to image
COPY ${JAR_FILE_PATH} ${JAR_FILE_NAME}

# Set java commands as entry point to run images
ENTRYPOINT ["java", "-Xms256M", "-Xmx512M", "-jar", "fsoft-igos-edxops.jar"]
