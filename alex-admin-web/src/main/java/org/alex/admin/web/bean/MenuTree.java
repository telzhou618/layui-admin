package org.alex.admin.web.bean;

import org.alex.admin.web.entity.SysMenu;

public class MenuTree {

	private String id;
	
	private String pId;
	
	private String name;
	
	private boolean checked = false;
	
	private boolean open = false;
	
	private boolean isParent = false;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

	public MenuTree() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MenuTree(SysMenu menu) {

		this.id = menu.getId();
		this.pId = menu.getPid();
		this.name = menu.getMenuName();
		this.isParent = menu.getDeep()!=3?true:false;
		this.open = menu.getDeep() == 1 ? true : false;
	}
	
}
