package top.qiaokeke.wechatbackend;

import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import top.qiaokeke.wechatbackend.dataaccess.entity.Customer;
import top.qiaokeke.wechatbackend.dataaccess.entity.SysUser;
import top.qiaokeke.wechatbackend.dataaccess.repository.CustomerRepository;
import top.qiaokeke.wechatbackend.dataaccess.repository.SysUserRepository;

@SpringBootApplication
public class WechatBackendApplication {

    private static final Logger log = LoggerFactory.getLogger(Application.class);



    public static void main(String[] args) {
        SpringApplication.run(WechatBackendApplication.class, args);
    }

    public CommandLineRunner demo(CustomerRepository repository, SysUserRepository sysUserRepository) {
        return (args) -> {
            sysUserRepository.save(new SysUser("001"));
            sysUserRepository.save(new SysUser("002"));
            sysUserRepository.save(new SysUser("003"));

            // save a couple of customers
            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "O'Brian"));
            repository.save(new Customer("Kim", "Bauer"));
            repository.save(new Customer("David", "Palmer"));
            repository.save(new Customer("Michelle", "Dessler"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            repository.findById(1L)
                    .ifPresent(customer -> {
                        log.info("Customer found with findById(1L):");
                        log.info("--------------------------------");
                        log.info(customer.toString());
                        log.info("");
                    });

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            repository.findByLastName("Bauer").forEach(bauer -> {
                log.info(bauer.toString());
            });
            // for (Customer bauer : repository.findByLastName("Bauer")) {
            // 	log.info(bauer.toString());
            // }
            log.info("");
        };
    }

}
