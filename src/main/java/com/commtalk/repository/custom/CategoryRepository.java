//package com.commtalk.repository.custom;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import com.commtalk.model.Category;
//
//public class CategoryRepository {
//	
//	@PersistenceContext
//    private EntityManager em;
//	
//	public List<Category> findAllWithBoards() {
//        String query = "select distinct c from Category c" +
//                " join fetch c.boards b";
//
//        return em.createQuery(query, Category.class)
//                .getResultList();
//    }
//
//}
