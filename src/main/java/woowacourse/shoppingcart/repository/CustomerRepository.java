package woowacourse.shoppingcart.repository;

import org.springframework.stereotype.Repository;
import woowacourse.shoppingcart.domain.Customer;
import woowacourse.shoppingcart.domain.Password;
import woowacourse.shoppingcart.repository.dao.CustomerDao;

@Repository
public class CustomerRepository {

    private final CustomerDao customerDao;

    public CustomerRepository(final CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Long create(final Customer customer) {
        return customerDao.create(customer);
    }

    public Customer findById(final Long id) {
        return customerDao.findById(id);
    }

    public Customer login(final String username, final String password) {
        return customerDao.login(username, password);
    }

    public void update(final Customer newCustomer) {
        customerDao.update(newCustomer);
    }

    public void updatePassword(final Long id, final Password oldPassword, final Password newPassword) {
        customerDao.findById(id);
        customerDao.updatePassword(id, oldPassword.getPassword(), newPassword.getPassword());
    }

    public void delete(final Long id) {
        customerDao.delete(id);
    }
}
