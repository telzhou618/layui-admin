package org.alex.admin.web.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModelProperty;

/**
 *
 * 博客
 *
 */
@TableName("tb_blog")
public class Blog implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@TableId(type = IdType.UUID)
	@ApiModelProperty(value = "id",hidden=true)
	private String id;
	/**
	 * 标题
	 */
	@NotBlank(message="文章标题不能为空")
	private String title;
	/**
	 * 内容
	 */
	@NotBlank(message="文章内容不能为空")
	private String content;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 用户ID
	 */
	@ApiModelProperty(hidden=true)
	private String userId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
