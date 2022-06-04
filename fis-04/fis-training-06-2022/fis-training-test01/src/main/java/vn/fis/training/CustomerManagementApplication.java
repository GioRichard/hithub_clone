package vn.fis.training;


import vn.fis.training.domain.Customer;
import vn.fis.training.domain.CustomerStatus;
import vn.fis.training.store.InMemoryCustomerStore;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CustomerManagementApplication
{
    public static void main( String[] args )
    {
        LocalDate localDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDateTime localDateTime = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        CustomerStatus customerStatus ;

        InMemoryCustomerStore inMemoryCustomerStore = new InMemoryCustomerStore();


        Customer c1 = new Customer(UUID.randomUUID(),"ha",localDate,
                "0912345678", CustomerStatus.ACTIVE,localDateTime);
        Customer c2 = new Customer(UUID.randomUUID(),"phu",localDate,
                "098935729", CustomerStatus.ACTIVE,localDateTime);
        Customer c3 = new Customer(UUID.randomUUID(),"phong",localDate,
                "0863389874", CustomerStatus.ACTIVE,localDateTime);

        inMemoryCustomerStore.insertOrUpdate(c1);
        inMemoryCustomerStore.insertOrUpdate(c2);
        inMemoryCustomerStore.insertOrUpdate(c3);
        inMemoryCustomerStore.getMapCustomer().
        List<Customer> listCustomer = inMemoryCustomerStore.findAll();
        System.out.println(listCustomer);



    // TODO: Implement method to test all CustomerService


    }

}
