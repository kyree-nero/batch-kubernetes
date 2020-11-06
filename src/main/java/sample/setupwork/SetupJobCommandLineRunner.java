package sample.setupwork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.AllNestedConditions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJndi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ConfigurationCondition.ConfigurationPhase;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"setup"})
public class SetupJobCommandLineRunner implements CommandLineRunner{
	

	@Autowired SetupWorkService service;

	
	public void run(String... args) throws Exception {
		System.out.println("setup work");
		service.execute();
//		JobParameters jobParameters = new JobParametersBuilder().addLong("unq", System.nanoTime()).toJobParameters();
//		System.out.println(jobParameters);
//		JobExecution jobExecution = jobLauncher.run(job, jobParameters);
		System.out.println("setup work done");
	}
	
//	@Autowired @Qualifier("setupWorkerJob") Job job;
//	@Autowired JobLauncher jobLauncher;
//	@Autowired JobExplorer jobExplorer;
	
	

}
