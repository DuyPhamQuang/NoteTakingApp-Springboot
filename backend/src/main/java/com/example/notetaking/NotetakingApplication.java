package com.example.notetaking;

import com.example.notetaking.Entity.Note;
import com.example.notetaking.Repositories.SearchRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.modelmapper.ModelMapper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@SpringBootApplication
//@EnableAutoConfiguration
//@ComponentScan(basePackages = {"com.example.notetaking"})
@EnableJpaRepositories(repositoryBaseClass = SearchRepositoryImpl.class)
public class NotetakingApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotetakingApplication.class, args);
	}

	@Bean
	public ApplicationRunner buildIndex(Indexer indexer) throws Exception {
		return (ApplicationArguments args) -> {
			indexer.indexPersistedData("com.example.notetaking.Entity.Note");
		};
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}


