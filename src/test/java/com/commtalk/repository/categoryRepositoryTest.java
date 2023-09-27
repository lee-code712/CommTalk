package com.commtalk.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.commtalk.CommtalkApplication;
import com.commtalk.model.Category;

@SpringBootTest(classes=CommtalkApplication.class)
public class categoryRepositoryTest {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	@Transactional
	public void testSave() {
		Category category = new Category();
		category.setName("카테고리2");
		categoryRepository.save(category);
		
		List<Category> categories = categoryRepository.findAll();
		assertNotNull(categories);
	}
	
	@Test
	public void testSelectAllWithBoards() {
		List<Category> categories = categoryRepository.findAllWithBoards();
		assertNotNull(categories);
	}
}
