package org.alex.admin.web.service.impl;

import org.alex.admin.web.entity.Blog;
import org.alex.admin.web.mapper.BlogMapper;
import org.alex.admin.web.service.IBlogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GaoJun.Zhou
 * @since 2017-06-30
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {
	
}
