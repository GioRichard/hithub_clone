package vn.fis.training.service;

import vn.fis.training.domain.Customer;
import vn.fis.training.exception.CustomerNotFoundException;
import vn.fis.training.exception.DuplicateCustomerException;
import vn.fis.training.exception.InvalidCustomerException;
import vn.fis.training.store.InMemoryCustomerStore;

import javax.swing.plaf.IconUIResource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class SimpleCustomerService implements CustomerService{

    private InMemoryCustomerStore customerStore;

    @Override
    public void setCustomerStore(InMemoryCustomerStore customerStore) {
        this.customerStore = customerStore;
    }

    @Override
    public Customer findById(String id) {
        return null;
    }

    @Override
    public Customer findById(UUID id) {
       if(isNullOrEmtry(String.valueOf(id))){
           throw new IllegalArgumentException("ko the tim kiem voiw dmtryy ID");
       }
       //c1
//        return customerStore.findAll().stream().filter(cus ->{
//            id.equals(cus.getId());
//        }).findFirst().orElseThrow(){
//            throw new CustomerNotFoundException(
//                    String.format("ko tim thay customer voi ID %s",id));
//
//        }
       //c2
       List<Customer> listCustomer = customerStore.findAll();
       for (int idx =0; idx<listCustomer.size(); idx++){
           if(listCustomer.get(idx).getId().equals(id)){
               return listCustomer.get(idx);
           }
       }

       throw new CustomerNotFoundException(
               String.format("ko tim thay customer voi ID %s",id));
    }
    private  boolean isNullOrEmtry(String id){
        if(id==null) return false;
        if(id.trim().length()==0) return false;
        return true;
    }

    @Override
    public Customer createCustomer(Customer customer) {
//        //TODO: Implement method tho dung dac ta cua CustomerService interface
//        Customer cus = customerStore.insertOrUpdate(customer);
//        return cus;

        validate(customer);
        checkDuplicate(customer);
        return  customerStore.insertOrUpdate(customer);
    }

    private  void validate(Customer customer){
        if(isNullOrEmtry(customer.getName())){
            throw new InvalidCustomerException(customer,"Customer mane is emtry");
        }
        if(isNullOrEmtry(customer.getMobile())){
            throw new InvalidCustomerException(customer,"Mobile is emtry");
        }
    }
    private void checkDuplicate(Customer customer) {
        if(customerStore.findAll().stream().filter(c -> {
            return c.getMobile().equals(customer.getMobile());
        }).findFirst().isPresent())
            throw  new DuplicateCustomerException(customer,String.format(
                    "Customer with phone number %s is duplicate",customer.getMobile()));
    }


    @Override
    public Customer updateCustomer(Customer customer) {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        Customer cus = customerStore.insertOrUpdate(customer);
//        return cus;

        validate(customer);
        findById(cus.getId());
        return customerStore.insertOrUpdate(customer);
    }

    @Override
    public void deleteCustomerById(String id) {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
//        customerStore.deleteById(UUID.fromString(id));

        if(isNullOrEmtry(id)){
            throw new IllegalArgumentException("can not delete customer with emtry id");

        }
        findById(id);
        customerStore.deleteById(UUID.fromString(id));

    }


    @Override
    public List<Customer> findAllOrderByNameAsc() {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        List<Customer> listCustomer = customerStore.findAll();
        listCustomer = listCustomer.stream().sorted(((o1, o2) -> {
            if(o1.getName().compareTo(o2.getName()) > 0)
                return 1;
            else if(o1.getName().compareTo(o2.getName()) < 0)
                return -1;
            return 0;
        })).collect(Collectors.toList());
        return listCustomer;


    }

    @Override
    public List<Customer> findAllOrderByNameLimit(int limit) {
        List<Customer> listCustomer = findAllOrderByNameAsc();
        listCustomer = listCustomer.stream().limit(limit).collect(Collectors.toList());
        return listCustomer;
    }

    @Override
    public List<Customer> findAllCustomerByNameLikeOrderByNameAsc(String custName, String limit) {
        List<Customer> listCustomer = customerStore.findAll();
        listCustomer = listCustomer.stream().filter(c -> c.getName().equals(custName)).collect(Collectors.toList());
        listCustomer = listCustomer.stream().sorted((o1,o2) -> {
            if(o1.getName().compareTo(o2.getName()) > 0)
                return 1;
            else if(o1.getName().compareTo(o2.getName()) < 0)
                return -1;
            return 0;
        }).limit(3).collect(Collectors.toList());
        return listCustomer;
    }

    @Override
    public List<SummaryCustomerByAgeDTO> summaryCustomerByAgeOrderByAgeDesc() {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        return null;
    }

}
