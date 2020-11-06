package sample;

import javax.jms.Destination;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import sample.common.SimpleRemotePartition;
import sample.dowork.WorkerService;

public class WorkerIT extends AbstractBaseIT{
	@Autowired WorkerService service; 
	@Autowired JmsTemplate jmsTemplate;
	@Autowired Destination destination;
	
	@BeforeEach public void before() {
		while ( jmsTemplate.receive(destination) != null) {
			System.out.println("removed queued item");
		}
		
		
		SimpleRemotePartition simpleRemotePartition = new SimpleRemotePartition();
		simpleRemotePartition.setFromId(4);
		simpleRemotePartition.setToId(9);
		simpleRemotePartition.setGroupName("xyz");
		jmsTemplate.convertAndSend(destination, simpleRemotePartition);
	}
	
	@Test public void test() throws Exception{
		
		service.execute();
		Thread.sleep(10);
		Assert.assertNull(jmsTemplate.receive(destination));
	}
	
}
