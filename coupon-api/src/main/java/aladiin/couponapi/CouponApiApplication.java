package aladiin.couponapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"aladiin.couponapi", "aladiin.core"})
public class CouponApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(CouponApiApplication.class, args);
	}

}
