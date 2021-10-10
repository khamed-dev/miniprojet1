package com.miniproject.first.configuration;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.miniproject.first.domain.UserBatch;
import com.miniproject.first.repository.UserBatchRepo;

public class UserBatchItemWriter implements ItemWriter<UserBatch> {
	@Autowired
	private UserBatchRepo userBatchRepo;
	
	@Override
	public void write(List<? extends UserBatch> items) throws Exception {
		// TODO Auto-generated method stub
		userBatchRepo.saveAll(items);
		
	}

}
