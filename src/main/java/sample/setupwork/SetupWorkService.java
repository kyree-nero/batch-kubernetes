package sample.setupwork;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Service;

import sample.common.SimpleRemotePartition;

@Service
public class SetupWorkService {
	@Autowired JmsOperations jmsOperations;
	@Autowired Destination batchQueueDestination;
	@Autowired NamedParameterJdbcOperations namedParameterJdbcOperations;
	@Autowired Environment environment;
	
	
	public void execute() {
		
		MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
		int count = namedParameterJdbcOperations.queryForObject("SELECT count(*) FROM BATCH_SAMPLE", sqlParameterSource, Integer.class);
		List<SimpleRemotePartition> partitions = splitWork(count);
		queueWork(partitions);
	}
	
	private List<SimpleRemotePartition> splitWork(Integer count) {
		String gridSize = environment.getProperty("app.gridSize");
		
		Integer itemsPerGrid = count/new Integer(gridSize);
		
		ArrayList<SimpleRemotePartition> partitions = new ArrayList<SimpleRemotePartition> ();
		for(int i = 0; i < count; i+=itemsPerGrid) {
			SimpleRemotePartition simpleRemotePartition = new SimpleRemotePartition();
			simpleRemotePartition.setFromId(i);
			simpleRemotePartition.setToId(i+itemsPerGrid - 1);
			simpleRemotePartition.setGroupName("groupName is " + i);
			partitions.add(simpleRemotePartition);
		}
		
		return partitions;
	}
	
	
	private void queueWork(List<SimpleRemotePartition> partitions) {
			
			List<SqlParameterSource> sqlParameterSources = new ArrayList<SqlParameterSource>();
			for(SimpleRemotePartition simpleRemotePartition: partitions) {
				System.out.println ("adding " + simpleRemotePartition);
				MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
				jmsOperations.convertAndSend(batchQueueDestination, simpleRemotePartition);
				
//				sqlParameterSource.addValue("start", simpleRemotePartition.getFromId());
//				sqlParameterSource.addValue("end", simpleRemotePartition.getToId());
//				
//				sqlParameterSource.addValue("queued", UUID.randomUUID().toString());
//				sqlParameterSource.addValue("queuedTs",  new Date());
//				sqlParameterSources.add(sqlParameterSource);
			}
//			namedParameterJdbcOperations.batchUpdate(
//					"UPDATE queued=::queued, queued_ts = :queuedTs FROM BATCH_SAMPLE WHERE id >= :start AND id <= :end"
//					, sqlParameterSources.toArray(new SqlParameterSource[sqlParameterSources.size()]));
			
			
			
			
			
	}

}
