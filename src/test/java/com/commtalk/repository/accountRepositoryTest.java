package com.commtalk.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.commtalk.CommtalkApplication;
import com.commtalk.model.Account;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest(classes=CommtalkApplication.class)
public class accountRepositoryTest {

	@Autowired
	private AccountRepository accountRepository;
	
	@Test
	@Transactional
	public void testGetAccountByNickname() throws JsonProcessingException {
		Account account = accountRepository.findById("talktalk").orElse(null);
		assertNotNull(account);
	}
}
