package service;

import bean.Employee;
import bean.EmployeeExample;
import dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.management.openmbean.CompositeData;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;
    public List<Employee> getAll() {
        return employeeMapper.selectByExampleWithDept(null);
    }

    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    @Autowired
    EmployeeService employeeService;
    /**
     * 查询员工数据
     * @return
     */
    @RequestMapping("/emps")
    public String getEmps(@RequestParam(value="pn",defaultValue = "1")Integer pn){
        //这不是一个分页查询；
        //引入PageHelper分页插件
        List<Employee> emps = employeeService.getAll();
        return "list";
    }

    /**
     * 检验用户名是否可用
     *
     * @param empName
     * @return true：代表当前姓名可用  false：不可用
     */
    public boolean checkUser(String empName) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        long count = employeeMapper.countByExample(example);
        //不等于零就会返回false
        return count == 0;
    }

    /**
     * 按照员工id查询员工
     * @param id
     * @return
     */
    public Employee getEmp(Integer id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }

    public Employee updateEmp(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
        return employee;
    }
}
