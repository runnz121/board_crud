package article.controller

import article.service.ArticleService
import article.service.request.ArticleRequest
import article.service.response.ArticleResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/articles")
class ArticleController(

    private val articleService: ArticleService
) {

    @GetMapping("/{articleId}")
    fun read(
        @PathVariable articleId: Long
    ): ArticleResponse = articleService.read(articleId)

    @PostMapping("/v1/articles")
    fun create(
        @RequestBody request: ArticleRequest
    ): ArticleResponse = articleService.create(request)

    @PutMapping("/{articleId}")
    fun update(@PathVariable articleId: Long,
               @RequestBody articleRequest: ArticleRequest
    ): ArticleResponse = articleService.update(articleId, articleRequest)
}