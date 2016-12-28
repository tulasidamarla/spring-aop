

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springsample.config.AppConfig;
import com.springsample.service.CustomerService;


public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx= new AnnotationConfigApplicationContext(AppConfig.class);
		CustomerService service = ctx.getBean("customerService", CustomerService.class);
		System.out.println(service.getCustomers().get(0).getFirstName());
		System.out.println(service.getCustomers().get(0).getLastName());
	}
	
}


