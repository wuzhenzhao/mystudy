package com.wuzz.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuzz.demo.datasource.DataSourceNames;
import com.wuzz.demo.datasource.TargetDataSource;
import com.wuzz.demo.entity.SysUser;
import com.wuzz.demo.mapper.SysUserMapper;
import com.wuzz.demo.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public SysUser findUserByFirstDb(long id) {
        return this.baseMapper.selectById(id);
    }

    @TargetDataSource(name = DataSourceNames.SECOND)
    @Override
    public SysUser findUserBySecondDb(long id) {

        return this.baseMapper.selectById(id);
    }

}
