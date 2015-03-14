package com.elearning.rest3.repository;

import java.util.List;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.elearning.rest3.domain.Product;

public interface ProductRepository extends SolrCrudRepository<Product, String> {
	List<Product> findByNameStartingWith(String name);
}
