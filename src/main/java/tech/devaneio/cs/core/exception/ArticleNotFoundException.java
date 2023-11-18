package tech.devaneio.cs.core.exception;

public class ArticleNotFoundException extends ResourceNotFoundException {

    public ArticleNotFoundException() {
        super("Article not found");
    }

}
