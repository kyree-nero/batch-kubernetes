package sample;


import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest//(webEnvironment=SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles({"test"})
//@TestPropertySource("/application-test.yml")
public abstract class AbstractBaseIT {

	
	
	
	
}
