# TibcoJMSMonitoring
This utility provide read metrics from Tibco ESB manager via tibjmsadmin and send to InfluxDb

Required InfluxDB 1.8 or later
### Metrics:
* PendingMessageCount

* ConsumerCount

* OutboundMessageRate

* InboundMessageRate

# Usage
start TibcoJMSMonitoring in CLI with param config-file
### Example:
    java -jar TibcoJMSMonitoring-1.0-jar-with-dependencies.jar config.xml

Config.xml example:  
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<Configuration>
    <allQueues>
        <login>admin</login>
        <password></password>
        <url>tcp://localhost:7222</url>
    </allQueues>
    <influxDbName>monitoring</influxDbName>
    <influxLogin>monitoring</influxLogin>
    <influxPassword>monitoring</influxPassword>
    <influxUrl>http://localhost:8086</influxUrl>
    <timeInterval>10</timeInterval>
</Configuration>

```
Block allQueues may be repeat several times.

last version
https://github.com/sergeylysov/TibcoJMSMonitoring/releases/download/1/TibcoJMSMonitoring-1.0-jar-with-dependencies.jar
