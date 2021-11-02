package jp.co.sample.domain;

public class Administrator {

	// ID
	private Integer id;
	// 管理者名
	private String name;
	// メールアドレス
	private String mailAddres;
	// パスワード
	private String password;
	
	public Administrator() {
		super();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMailAddres() {
		return mailAddres;
	}
	public void setMailAddres(String mailAddres) {
		this.mailAddres = mailAddres;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Administrator [id=" + id + ", name=" + name + ", mailAddres=" + mailAddres + ", password=" + password
				+ "]";
	}
	
	
}
