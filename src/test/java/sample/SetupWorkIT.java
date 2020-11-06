package sample;

import java.util.ArrayList;

import javax.jms.Destination;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import sample.common.SimpleRemotePartition;
import sample.setupwork.SetupWorkService;

public class SetupWorkIT extends AbstractBaseIT{
	@Autowired SetupWorkService service; 
	@Autowired JmsTemplate jmsTemplate;
	@Autowired Destination destination;
	
	
	@Test public void test() {
		service.execute();
		
		ArrayList<SimpleRemotePartition> receivedMessages = new ArrayList<SimpleRemotePartition>();
		Object o = null;//;
		jmsTemplate.setReceiveTimeout(3);
		while((o = jmsTemplate.receiveAndConvert(destination)) != null) {
			SimpleRemotePartition srp = (SimpleRemotePartition)o;
		   System.out.println("removed " + srp);	
		   receivedMessages.add(srp);
		}
		
		Assert.assertEquals(4, receivedMessages.size());
		
		System.out.println("done with test");
	}
}
