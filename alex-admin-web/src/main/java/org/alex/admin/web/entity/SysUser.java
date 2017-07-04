package org.alex.admin.web.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author GaoJun.Zhou
 * @since 2017-06-30
 */
@TableName("sys_user")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type=IdType.UUID)
	private String id;
    /**
     * 用户名
     */
	@NotEmpty(message = "用户名不能为空")
	@Length(min = 4, max = 16, message = "用户名长度为4-16之间")
	@Pattern(regexp = "[a-zA-Z]{6,16}", message = "用户名不合法")
	private String userName;
    /**
     * 密码
     */
	@NotBlank(message="密码不能为空")
	@Length(min = 6, max = 16, message = "密码长度为6-16之间")
	private String password;
    /**
     * 用户状态,1-启用,-1禁用
     */
	private Integer userState;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 描述
     */
	private String userDesc;
    /**
     * 头像
     */
	private String userImg;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUserState() {
		return userState;
	}

	public void setUserState(Integer userState) {
		this.userState = userState;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
