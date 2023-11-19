package tech.devaneio.cs.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tech.devaneio.cs.core.search.ArticleSearchable;
import tech.devaneio.cs.core.entity.Article;
import tech.devaneio.cs.core.repository.ArticleRepository;

import java.util.Optional;

import static tech.devaneio.cs.core.entity.ArticleStatus.PUBLISHED;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Optional<Article> findById(final Long id) {
        return articleRepository.findById(id);
    }

    public Optional<Article> findByIdAndUserId(final Long id, final Long userId) {
        return articleRepository.findByIdAndUserId(id, userId);
    }

    public Optional<Article> findPublishedById(final Long id) {
        return findById(id)
            .filter(Article::isPublished);
    }

    public Article save(final Article article) {
        return articleRepository.save(article);
    }

    public Page<Article> findAll(final ArticleSearchable searchable, final PageRequest pageRequest) {
        return articleRepository.findAll(searchable.specification(), pageRequest);
    }

    public Page<Article> findAllPublished(final PageRequest pageRequest) {
        return articleRepository.findAllByStatus(PUBLISHED, pageRequest);
    }

    public void deleteById(final Long id) {
        articleRepository.deleteById(id);
    }

}
