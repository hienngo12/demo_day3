package example;

import config.SpringConfig;
import entity.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.CustomerRepository;

import java.time.LocalDate;
import java.util.List;

class MainCutomer {
    static ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    static CustomerRepository customerRepository = context.getBean(CustomerRepository.class);

    public static void main(String[] args) {
        addCustomer("nguyen van a", LocalDate.of(2000, 6, 15), "Male", "abc@gmail.com", 1234567890, "DN");
        listAllCustomers();
        findCustomerById(1);
        findCustomersByName("nguyen van a");
        findCustomersByPhoneOrEmail(1234567890, "abc@gmail.com");
        listMenAgedBetween20And30();
    }

    private static void addCustomer(String name, LocalDate birthdate, String sex, String email, int phone, String address) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setBirdate(birthdate);
        customer.setSex(sex);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(address);
        customerRepository.save(customer);
        System.out.println("Added customer: " + customer);
    }

    private static void listAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    private static void findCustomerById(int id) {
        List<Customer> customers = customerRepository.findByIdAsList(id);
        if (!customers.isEmpty()) {
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        } else {
            System.out.println("Customer not found with ID: " + id);
        }
    }

    private static void findCustomersByName(String name) {
        List<Customer> customers = customerRepository.findByName(name);
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    private static void findCustomersByPhoneOrEmail(int phone, String email) {
        List<Customer> customers = customerRepository.findByPhoneOrEmail(phone, email);
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    private static void listMenAgedBetween20And30() {
        List<Customer> customers = customerRepository.findMenAgedBetween20And30();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
}