package com.pms.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.app.dao.SupervisorDao;
import com.pms.app.entity.Supervisor;
import com.pms.base.dao.BaseDao;
import com.pms.base.service.BaseService;

@Service
@Transactional
public class SupervisorService extends BaseService<Supervisor, String> {

	@Autowired private SupervisorDao supervisorDao;

	@Override
	protected BaseDao<Supervisor, String> getEntityDao() {
		return supervisorDao;
	}
	
	public List<Supervisor> findByUsernameAndPassword(String username, String password) {
		return supervisorDao.findByUsernameAndPassword(username, password);
	}
	
	public Supervisor findOneByWarehouseId(String warehouseId) {
		List<Supervisor> supervisors = supervisorDao.findByWarehouseId(warehouseId);
		if(supervisors.isEmpty()) return null;
		return supervisors.get(0);
	}
	
}
