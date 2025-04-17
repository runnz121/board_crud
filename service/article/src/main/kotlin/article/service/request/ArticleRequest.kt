package article.service.request

data class ArticleRequest(
    
    val title: String,
    val content: String,
    val writerId: Long,
    val boardId: Long
)
