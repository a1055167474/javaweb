package cn.swu.edu;

import java.util.List;

import cn.swu.edu.Customer;
import cn.swu.edu.CriteriaCustomer;
import cn.swu.edu.CustomerDao;
import cn.swu.edu.Dao;

public class CustomerDaoJdbcImpl extends Dao<Customer> implements CustomerDao{

    @Override
    public List<Customer> getAll() {
        String sql = "select * from customer";
        return getForList(sql);
    }

    @Override
    public void save(Customer customer) {
        String sql = "insert into customer(name,address,phone) values (?,?,?)";
        update(sql, customer.getName(), customer.getAddress(), customer.getPhone());
    }

    @Override
    public Customer get(Integer id) {
        String sql = "select * from customer where id = ?";
        return get(sql, id);
    }

    @Override
    public void delete(Integer id) {
        String sql = "delete from customer where id = ? ";
        update(sql, id);
    }

    @Override
    public long getCountWithName(String name) {
        String sql = "select count(id) from customer where name = ?";
        return getForValue(sql, name);
    }

    @Override
    public List<Customer> getForListWithCriteriaCustomer(CriteriaCustomer cc) {
        String sql = "SELECT id,name,address,phone FROM customer WHERE name LIKE ? AND "
                + "address LIKE ? AND phone LIKE ?";
        //注意：修改了 CriteriaCustomer 的 getter 方法，使其返回的字符串中有 "%%",
        //若返回值为 null 则返回 "%%" ,若不为 null， 则返回 "% " + 字段本身的值 + " %"
        return getForList(sql, cc.getName(), cc.getAddress(), cc.getPhone());
    }

}
