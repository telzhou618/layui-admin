package org.alex.admin.web.mapper;

import java.util.List;
import java.util.Map;

import org.alex.admin.web.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 角色菜单关联表 Mapper 接口
 * </p>
 *
 * @author GaoJun.Zhou
 * @since 2017-06-30
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

	List<Map<String, Object>> selectAuthByRoleId(@Param("roleId") String roleId);

}