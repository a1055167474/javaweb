package cn.swu.edu;

import java.util.List;

import cn.swu.edu.Customer;

public interface CustomerDao {
    
    /**
     * 模糊查询 返回满足条件的List
     * @param cc ：封装了查询条件
     * @return
     */
    public List<Customer> getForListWithCriteriaCustomer(CriteriaCustomer cc);

    /**
     * 查询 返回 List
     */
    public List<Customer> getAll();
    
    /**
     * 增加
     */
    public void save(Customer customer);
    
    /**
     * 修改 更新时候的查询（根据id）显示
     */
    public Customer get(Integer id);
    
    /**
     * 删除
     */
    public void delete(Integer id);
    
    /**
     * 返回和name 相同的记录数
     */
    public long getCountWithName(String name);
}
