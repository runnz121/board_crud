package article.service

import article.entity.Article
import article.entity.BoardArticleCount
import article.repository.ArticleRepository
import article.repository.BoardArticleCountRepository
import article.service.request.ArticleRequest
import article.service.response.ArticleResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import snowflake.Snowflake

@Service
open class ArticleService(

    private val articleRepository: ArticleRepository,
    private val boardArticleCountRepository: BoardArticleCountRepository

) {

    private val snowFlake = Snowflake()

    @Transactional
    open fun create(request: ArticleRequest): ArticleResponse {

        val article = articleRepository.save(
            Article.create(snowFlake.nextId(), request.title, request.content, request.boardId, request.writerId)
        )

        val result = boardArticleCountRepository.increase(request.boardId)
        if (result == 0) {
            boardArticleCountRepository.save(
                BoardArticleCount.init(request.boardId, 1L)
            )
        }

        return ArticleResponse.from(article)
    }

    @Transactional
    open fun update(articleId: Long, request: ArticleRequest): ArticleResponse {

        val article = articleRepository.findById(articleId).orElseThrow()
        article.update(request.title, request.content)

        return ArticleResponse.from(article)
    }

    open fun read(articleId: Long): ArticleResponse {
        val article = articleRepository.findById(articleId).orElseThrow()
        return ArticleResponse.from(article)
    }
}