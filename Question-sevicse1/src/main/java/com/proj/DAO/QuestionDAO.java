package com.proj.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proj.models.Question;

@Repository
public interface QuestionDAO extends JpaRepository<Question, Integer> {

	public List<Question> findByCategory(String catagory);

}
