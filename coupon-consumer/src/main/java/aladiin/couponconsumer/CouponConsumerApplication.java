package aladiin.couponconsumer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication(scanBasePackages = {"aladiin.couponconsumer", "aladiin.core"})
public class CouponConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponConsumerApplication.class, args);
	}

}
