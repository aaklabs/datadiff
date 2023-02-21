package com.aak.util.compare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aak.util.compare.diff.model.DBSource;
import com.aak.util.compare.repo.DBRepo;
import com.aak.util.compare.repo.MySqlRepo;
import com.aak.util.compare.repo.PostgresRepo;

@Service
public class DBRepositoryFactory {

	@Autowired
	private MySqlRepo db1Repo;

	@Autowired
	private PostgresRepo db2Repo;

	
	public DBRepo getRepo(DBSource type) throws Exception {
		switch (type) {
		case DB1:
			return db1Repo;
		case DB2:
			return db2Repo;
		default:
			throw new Exception("Invalid type." + type);
		}
	}
}
