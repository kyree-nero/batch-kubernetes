package sample.dowork;

import java.util.List;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import sample.common.SimpleRemotePartition;

@Service
public class WorkerService {
	@Autowired JmsTemplate jmsTemplate;
	@Autowired Destination destination;
	@Autowired NamedParameterJdbcOperations namedParameterJdbcOperations;
	
	
	
	public void execute() {
		SimpleRemotePartition workChunk = null;
		while ((workChunk = (SimpleRemotePartition)jmsTemplate.receiveAndConvert(destination)) != null) {
			MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
			sqlParameterSource.addValue("start", workChunk.getFromId());
			sqlParameterSource.addValue("end", workChunk.getToId());
			System.out.println("working on " + workChunk);
			List<Integer> ids = 
				namedParameterJdbcOperations.queryForList(
					"SELECT id FROM BATCH_SAMPLE WHERE id >= :start AND id <= :end", 
					sqlParameterSource, 
					Integer.class);
			
			for(int i : ids) {
				System.out.println("working on id -- " + i);
				sqlParameterSource = new MapSqlParameterSource();
				sqlParameterSource.addValue("id", i);
				//namedParameterJdbcOperations.update("DELETE FROM BATCH_SAMPLE WHERE id = :id", sqlParameterSource);
				
			}
			
		}
		
		
	}
}
