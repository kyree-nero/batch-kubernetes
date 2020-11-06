package sample.dowork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"worker"})
public class WorkerCommandLineRunner implements CommandLineRunner{
	@Autowired WorkerService service;

	
	public void run(String... args) throws Exception {
		System.out.println("do work");
		service.execute();
//		JobParameters jobParameters = new JobParametersBuilder().addLong("unq", System.nanoTime()).toJobParameters();
//		System.out.println(jobParameters);
//		JobExecution jobExecution = jobLauncher.run(job, jobParameters);
		System.out.println("do done");
	}
	
//	@Autowired @Qualifier("setupWorkerJob") Job job;
//	@Autowired JobLauncher jobLauncher;
//	@Autowired JobExplorer jobExplorer;
	
	

}
