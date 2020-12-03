/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sergeylysov.tibcojmsmonitoring.configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sergey Lysov
 * 
 */

/*
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<Configuration>
    <allQueues>
        <login>sdf</login>
        <password>pass</password>
        <url>url</url>
    </allQueues>
    <influxDbName>insd</influxDbName>
    <influxLogin>login</influxLogin>
    <influxUrl>url</influxUrl>
    <timeInterval>10</timeInterval>
</Configuration>
*/
@XmlRootElement(name = "Configuration")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Configuration implements Serializable{
   
    private int timeInterval;
    private List<AllQueues> allQueues = new ArrayList<>();
    private String influxUrl;
    private String influxDbName;
    private String influxPassword;
    private String influxLogin;

    public int getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;
    }

    public List<AllQueues> getAllQueues() {
        return allQueues;
    }

    public void setAllQueues(List<AllQueues> allQueues) {
        this.allQueues = allQueues;
    }

    public String getInfluxUrl() {
        return influxUrl;
    }

    public void setInfluxUrl(String influxUrl) {
        this.influxUrl = influxUrl;
    }

    public String getInfluxDbName() {
        return influxDbName;
    }

    public void setInfluxDbName(String influxDbName) {
        this.influxDbName = influxDbName;
    }

    public String getInfluxPassword() {
        return influxPassword;
    }

    public void setInfluxPassword(String influxPassword) {
        this.influxPassword = influxPassword;
    }

    public String getInfluxLogin() {
        return influxLogin;
    }

    public void setInfluxLogin(String influxLogin) {
        this.influxLogin = influxLogin;
    }

    @Override
    public String toString() {
        return "Configuration{" + "timeInterval=" + timeInterval + ", allQueues=" + allQueues + ", influxUrl=" + influxUrl + ", influxDbName=" + influxDbName + ", influxPassword=" + influxPassword + ", influxLogin=" + influxLogin + '}';
    }
    
}
