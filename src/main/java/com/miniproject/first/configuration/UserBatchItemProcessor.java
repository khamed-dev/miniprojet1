package com.miniproject.first.configuration;

import java.time.LocalDate;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.miniproject.first.domain.UserBatch;

public class UserBatchItemProcessor implements ItemProcessor<UserBatch, UserBatch> {
	private LocalDate dob;

	@Override
	public UserBatch process(UserBatch item) throws Exception {
		item.setDob(dob.of(2021 - item.getAge(), 1, 1));
		return item;
	}

}
