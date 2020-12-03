/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sergeylysov.tibcojmsmonitoring;


import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import com.sergeylysov.tibcojmsmonitoring.configuration.AllQueues;
import com.sergeylysov.tibcojmsmonitoring.configuration.Configuration;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.LoggerFactory;

/**
 *
 * @author Sergey Lysov
 */
public class JmsMonitoring {

    private final static org.slf4j.Logger log = LoggerFactory.getLogger(JmsMonitoring.class);
    private static InfluxDBClient client;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        JAXBContext jaxbContext;
        try {

            File xmlFile = new File(args[0]);
            jaxbContext = JAXBContext.newInstance(Configuration.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Configuration config = (Configuration) jaxbUnmarshaller.unmarshal(xmlFile);

            client = InfluxDBClientFactory.createV1(config.getInfluxUrl(),
                    config.getInfluxLogin(),
                    config.getInfluxPassword().toCharArray(),
                    config.getInfluxDbName(),
                    "autogen");
            List<QueueManager> managers = new ArrayList<>();
            for (AllQueues allQueues : config.getAllQueues()) {
                managers.add(new QueueManager(allQueues));
            }

            while (true) {
                try ( WriteApi writeApi = client.getWriteApi()) {
                    for (QueueManager qManager : managers) {
                        writeApi.writePoints(qManager.getPoints());
                    }

                }
                Thread.sleep(config.getTimeInterval() * 1000);
            }

        } catch (JAXBException ex) {
            log.error("Jaxb Exception");
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            log.error("interrupted");
        }
    }

}
