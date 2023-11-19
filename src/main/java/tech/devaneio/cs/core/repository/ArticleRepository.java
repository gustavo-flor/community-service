package tech.devaneio.cs.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import tech.devaneio.cs.core.entity.Article;
import tech.devaneio.cs.core.entity.ArticleStatus;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {

    Optional<Article> findByIdAndUserId(Long id, Long userId);

    Page<Article> findAllByStatus(ArticleStatus status, Pageable pageable);

}
