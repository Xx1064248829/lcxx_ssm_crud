package test;


import bean.Department;
import bean.Employee;
import dao.DepartmentMapper;
import dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * 测试dao层的工作
 * @author  Xx、
 * 推荐spring的项目就可以用spring的单元测试，可以自动注入我们需要的组件
 * 1.导入SpringTest模块
 * 2.@ContextConfiguration指定Spring配置文件的位置
 * 3.直接autowired要使用的组件即可
 */

//使用单元测试模块时，需要删除pm依赖中对单元测试的限制<scope>
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;

    /**
     * 测试DepartmentMapper
     */
    @Test
    public void testCRUD(){
        /*//1.创建SpringIOC容器
        ApplicationContext ioc= new ClassPathXmlApplicationContext("applicationContext.xml");
        //2.从容器中获取Mapper
        ioc.getBean(DepartmentMapper.class);*/
        //System.out.println(departmentMapper);
        //1.插入几个部门
        //departmentMapper.insertSelective(new Department(null,"开发部"));
        //departmentMapper.insertSelective(new Department(null,"测试部"));

        //2.生成员工数据，测试员工插入
        //employeeMapper.insertSelective(new Employee(null,"Jerry","M","Jerry@ecut.com",1));
        //employeeMapper.insertSelective(new Employee(null,"TOM","N","TOM@ecut.com",1));

        //3.批量插入多个员工；使用可执行批量操作的sqlSession
        EmployeeMapper mapper=sqlSession.getMapper(EmployeeMapper.class);
        for(int i=0;i<100;i++){
            String uid=UUID.randomUUID().toString().substring(0,5)+i;
            mapper.insertSelective(new Employee(null,uid,"M",uid+"@ecut.com",1));
        }
        System.out.println("批量完成！");

    }

}
