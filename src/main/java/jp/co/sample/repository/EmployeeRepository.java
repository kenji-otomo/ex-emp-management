package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

/**
 * Employeeのレポジトリ
 * 
 * @author ootomokenji
 *
 */
@Repository
public class EmployeeRepository {

	private static final RowMapper<Employee>EMPLOYEE_ROW_MAPPER = new BeanPropertyRowMapper<>(Employee.class);
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 従業員情報を全て、入社日の降順で取得します
	 * 
	 * @return　List<Employee> 全従業員情報が入ったリスト
	 */
	public List<Employee> findAll() {
		String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count "
				+ " FROM employees ORDER BY hire_date DESC";
		
		List<Employee>list = template.query(sql, EMPLOYEE_ROW_MAPPER);
		
		return list;
	}
	
	/**
	 * IDで検索された従業員情報を取得
	 * 
	 * @param id　欲しい情報の従業員ID
	 * @return　Employee 従業員情報の入ったオブジェクト
	 */
	public Employee load(Integer id) {
		String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count "
				+ " FROM employees WHERE id = :id;";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		
		Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
		
		return employee;
	}
	
	/**
	 * IDを指定し、ID以外の情報を書き換える
	 * 
	 * @param employee　上書きしたい情報の入ったEmployeeオブジェクト
	 */
	public void update(Employee employee) {
		String sql = "UPDATE employees "
				+ "SET name = :name, image = :image, gender = :gender, hire_date = :hireDate, "
				+ "mail_address = :mailAddress, zip_code = :zipCode, address = :address, "
				+ "telephone = :telephone, salary = :salary, characteristics = :characteristics, "
				+ "dependents_count = :dependentsCount "
				+ " WHERE id = :id";
		
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		
		template.update(sql, param);
	}
	
}
