package vn.fis.training.store;

import vn.fis.training.domain.Customer;
import vn.fis.training.domain.CustomerStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public final class InMemoryCustomerStore {
    /**
     *  Map dung de lu tru customer trong he thong
     */
    private final Map<UUID, Customer> mapCustomer = new HashMap<>();

//    public Customer findById(UUID id) {
//        List<Customer> list = findAll();
//        Optional<Customer> op = list.stream().filter(c -> c.getId().equals(id)).findFirst();
//        return op.orElse(null);
//    }

    /**
     * @param customer doi tuong customer (da duoc chuan hoa truoc do)
     * @return Customer: Doi tuong customer sau khi da duoc luu thanh cong vao he thong
     * @throws vn.fis.training.exception.ApplicationException tuong ung.
     */
    public Customer insertOrUpdate(Customer customer) {
        mapCustomer.put(customer.getId(),customer);
        return  customer;
    }

    /**
     * @return : tra ve toan bo danh sach customer trong he thong
     */
    public List<Customer> findAll() {
       return  new ArrayList<>(mapCustomer.values());
    }

    /**
     * @param id: Id cua customer muon delete
     */
    public void deleteById(UUID id) {
        mapCustomer.remove(id);
    }
}
