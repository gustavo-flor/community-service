package tech.devaneio.cs.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.devaneio.cs.core.entity.Article;
import tech.devaneio.cs.core.entity.ArticleStatus;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findAllByStatus(ArticleStatus status, Pageable pageable);

}
