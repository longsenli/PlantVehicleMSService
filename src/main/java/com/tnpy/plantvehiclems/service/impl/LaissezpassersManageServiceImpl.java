package com.tnpy.plantvehiclems.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tnpy.plantvehiclems.mapper.mysql.TbLaissezPasserInfoMapper;
import com.tnpy.plantvehiclems.model.mysql.TbLaissezPasserInfo;
import com.tnpy.plantvehiclems.service.ILaissezpassersManageService;

@Service("laissezpassersManageService")
public class LaissezpassersManageServiceImpl implements  ILaissezpassersManageService{
	@Autowired
	TbLaissezPasserInfoMapper tbLaissezPasserInfoMapper;

	@Override
	public int insert(TbLaissezPasserInfo tbLaissezPasserInfo) {
		// TODO Auto-generated method stub
		return tbLaissezPasserInfoMapper.insert(tbLaissezPasserInfo);
	}

	@Override
	public TbLaissezPasserInfo selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return tbLaissezPasserInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TbLaissezPasserInfo> listAll() {
		// TODO Auto-generated method stub
		return tbLaissezPasserInfoMapper.listAll();
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return tbLaissezPasserInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TbLaissezPasserInfo tbLaissezPasserInfo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(TbLaissezPasserInfo tbLaissezPasserInfo) {
		// TODO Auto-generated method stub
		return tbLaissezPasserInfoMapper.updateByPrimaryKey(tbLaissezPasserInfo);
	}
	

}
