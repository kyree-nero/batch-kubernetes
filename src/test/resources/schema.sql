
CREATE TABLE BATCH_SAMPLE (
	 id bigint auto_increment not null  primary key , 
	 content VARCHAR(100) not null,
	 version INT not null,
	-- queued_id VARCHAR(100) not null,
	--  queued_ts TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP
	 
); 



COMMIT;