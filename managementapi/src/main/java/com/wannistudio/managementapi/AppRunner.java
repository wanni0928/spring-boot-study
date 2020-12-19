package com.wannistudio.managementapi;

import com.wannistudio.managementapi.domain.*;
import com.wannistudio.managementapi.service.CategoryService;
import com.wannistudio.managementapi.service.CustomerService;
import com.wannistudio.managementapi.service.EmployeeService;
import com.wannistudio.managementapi.service.ShipperService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class AppRunner implements ApplicationRunner {
    private final CustomerService customerService;
    private final CategoryService categoryService;
    private final EmployeeService employeeService;
    private final ShipperService shipperService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Customer customer1 = new Customer();
        customer1.setCustomerName("wanni");
        customer1.setPlace(new Place("개포동", "서울1", "1234", "한국"));
        customerService.join(customer1);

        Customer customer2 = new Customer();
        customer2.setCustomerName("curry");
        customer2.setPlace(new Place("개포동", "서울2", "1234", "한국"));
        customerService.join(customer2);

        Customer customer3 = new Customer();
        customer3.setCustomerName("형중");
        customer3.setPlace(new Place("개포동", "서울3", "1234", "한국"));
        customerService.join(customer3);

        Shipper shipper1 = new Shipper();
        shipper1.setShipperName("Speedy Express1");
        shipper1.setPhone("(503) 555-9831");

        Shipper shipper2 = new Shipper();
        shipper2.setShipperName("United Package");
        shipper2.setPhone("(503) 555-3199");

        Shipper shipper3 = new Shipper();
        shipper3.setShipperName("Federal Shipping");
        shipper3.setPhone("(503) 555-9931");

        shipperService.saveShipper(shipper1);
        shipperService.saveShipper(shipper2);
        shipperService.saveShipper(shipper3);

        Category category1 = new Category("Beverages", "Soft drinks, coffees, teas, beers, and ales");
        Category category2 = new Category("Condiments", "Sweet and savory sauces, relishes, spreads, and seasonings");
        Category category3 = new Category("Confections", "Desserts, candies, and sweet breads");
        Category category4 = new Category("Dairy Products", "Cheeses");
        Category category5 = new Category("Grains/Cereals", "Breads, crackers, pasta, and cereal");
        Category category6 = new Category("Meat/Poultry", "Prepared meats");
        Category category7 = new Category("Produce", "Dried fruit and bean curd");
        Category category8 = new Category("Seafood", "Seaweed and fish");

        categoryService.enroll(category1);
        categoryService.enroll(category2);
        categoryService.enroll(category3);
        categoryService.enroll(category4);
        categoryService.enroll(category5);
        categoryService.enroll(category6);
        categoryService.enroll(category7);
        categoryService.enroll(category8);

        Employee employee1 = Employee.addEmployee("Davolio",
                LocalDateTime.of(LocalDate.of(1968, 12, 10), LocalTime.now()),
                "EmpID1.pic",
                "Education includes a BA in psychology from Colorado State University. She also completed (The Art of the Cold Call). Nancy is a member of 'Toastmasters International'."
        );

        Employee employee2 = Employee.addEmployee("Peacock",
                LocalDateTime.of(LocalDate.of(1968, 12, 10), LocalTime.now()),
                "EmpID1.pic",
                "Education includes a BA in psychology from Colorado State University. She also completed (The Art of the Cold Call). Nancy is a member of 'Toastmasters International'."
        );

        Employee employee3 = Employee.addEmployee("Buchanan",
                LocalDateTime.of(LocalDate.of(1968, 12, 10), LocalTime.now()),
                "EmpID1.pic",
                "Education includes a BA in psychology from Colorado State University. She also completed (The Art of the Cold Call). Nancy is a member of 'Toastmasters International'."
        );

        Employee employee4 = Employee.addEmployee("Suyama",
                LocalDateTime.of(LocalDate.of(1968, 12, 10), LocalTime.now()),
                "EmpID1.pic",
                "Education includes a BA in psychology from Colorado State University. She also completed (The Art of the Cold Call). Nancy is a member of 'Toastmasters International'."
        );

        Employee employee5 = Employee.addEmployee("King",
                LocalDateTime.of(LocalDate.of(1968, 12, 10), LocalTime.now()),
                "EmpID1.pic",
                "Education includes a BA in psychology from Colorado State University. She also completed (The Art of the Cold Call). Nancy is a member of 'Toastmasters International'."
        );

        Employee employee6 = Employee.addEmployee("Callahan",
                LocalDateTime.of(LocalDate.of(1968, 12, 10), LocalTime.now()),
                "EmpID1.pic",
                "Education includes a BA in psychology from Colorado State University. She also completed (The Art of the Cold Call). Nancy is a member of 'Toastmasters International'."
        );

        Employee employee7 = Employee.addEmployee("Dodsworth",
                LocalDateTime.of(LocalDate.of(1968, 12, 10), LocalTime.now()),
                "EmpID1.pic",
                "Education includes a BA in psychology from Colorado State University. She also completed (The Art of the Cold Call). Nancy is a member of 'Toastmasters International'."
        );

        employeeService.join(employee1);
        employeeService.join(employee2);
        employeeService.join(employee3);
        employeeService.join(employee4);
        employeeService.join(employee5);
        employeeService.join(employee6);
        employeeService.join(employee7);
    }
}
