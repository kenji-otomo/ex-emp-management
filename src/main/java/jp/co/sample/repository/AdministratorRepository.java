package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * 管理者のリポジトリ
 * 管理者情報の挿入、メールアドレスとパスワードから管理者情報を取得
 * 
 * @author ootomokenji
 *
 */
@Repository
public class AdministratorRepository {

	/**
	 * RowMapperで一行ずつオブジェクト化
	 * 
	 */
	private static final RowMapper<Administrator>ADMIN_ROW_MAPPER = 
			
			(rs,i) -> {
				Administrator administrator = new Administrator();
				administrator.setId(rs.getInt("id"));
				administrator.setName(rs.getString("name"));
				administrator.setMailAddres(rs.getString("mail_address"));
				administrator.setPassword(rs.getString("password"));
				return administrator;
			};
			
			//new BeanPropertyRowMapper<>(Administrator.class);
	
	@Autowired
	private NamedParameterJdbcTemplate template ;
	
	/**
	 * 受け取ったAdministratorオブジェクトをインサートします
	 * 
	 * @param administrator　挿入したいAdmnistratorオブジェクト
	 */
	public void insert(Administrator administrator) {
		String sql = "INSERT INTO administrators (name,mail_address,password) "
				+ "VALUES (:name,:mail_address,:password);";
		
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		
		template.update(sql, param);
	}
	
	/**
	 * メアドとパスワードで管理者情報を検索
	 * 
	 * @param mailAddress　
	 * @param password
	 * @return　検索された管理者情報　一件だとは思うけど一応リストにした
	 */
	public List<Administrator> findByMailAddressAndPassword(String mailAddress,String password) {
		
		String sql = "SELECT id,name,mail_address,password FROM administrators WHERE mail_address = :mail_address AND password = :password ;";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("mail_address", mailAddress).addValue("password", password);
		
		List<Administrator>adminList = template.query(sql, param, ADMIN_ROW_MAPPER);
		
		if (adminList.size() == 0) {
			return null;
		}
		
		return adminList;
	}
	
}
