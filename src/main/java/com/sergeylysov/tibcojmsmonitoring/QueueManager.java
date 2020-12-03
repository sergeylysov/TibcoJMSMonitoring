/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sergeylysov.tibcojmsmonitoring;

import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.sergeylysov.tibcojmsmonitoring.configuration.AllQueues;
import com.tibco.tibjms.admin.QueueInfo;
import com.tibco.tibjms.admin.TibjmsAdmin;
import com.tibco.tibjms.admin.TibjmsAdminException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sergey
 */
public class QueueManager {

    private final static org.slf4j.Logger log = LoggerFactory.getLogger(QueueManager.class);
    private TibjmsAdmin connection;
    private List<String> queues;

    public QueueManager(AllQueues allQueues) {
    queues = new ArrayList<>();
        try {
            connection = new TibjmsAdmin(allQueues.getUrl(), allQueues.getLogin(), allQueues.getPassword());
            for (QueueInfo queue : connection.getQueues()) {
                if (!queue.getName().startsWith("$")) {
                    queues.add(queue.getName());
                }
                log.debug("queue = " + queue.getName());
            }
      
        } catch (TibjmsAdminException ex) {
            log.error("JMS Exception");
        }

    }

    public List<Point> getPoints() {
        List<Point> points = new ArrayList<>();
        for (String queue : queues) {
            try {
                Point point = Point.measurement("TibcoMonitor")
                        .time(Instant.now().toEpochMilli(), WritePrecision.MS)
                        .addField("PendingMessageCount", connection.getQueue(queue).getPendingMessageCount())
                        .addField("ConsumerCount", connection.getQueue(queue).getConsumerCount())
                        .addField("InboundMessageRate", connection.getQueue(queue).getInboundStatistics().getMessageRate())
                        .addField("OutboundMessageRate", connection.getQueue(queue).getOutboundStatistics().getMessageRate())
                        .addTag("queueName", queue);
               
                 points.add(point);
                 
            } catch (TibjmsAdminException ex) {
                log.error("JMS Exception ");
                ex.printStackTrace();
            }
           
        }
        return points;
    }

}
