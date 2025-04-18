package article.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
open class Article(

    articleId: Long,
    title: String,
    content: String,
    boardId: Long,
    writerId: Long,
    createdAt: LocalDateTime,
    modifiedAt: LocalDateTime

) {
    @Id
    var articleId: Long = articleId
        protected set

    var title: String = title
        protected set

    var content: String = content
        protected set

    var boardId: Long = boardId
        protected set

    var writerId: Long = writerId
        protected set

    var createdAt: LocalDateTime = createdAt
        protected set

    var modifiedAt: LocalDateTime = modifiedAt
        protected set

    protected constructor() : this(
        articleId = 0L,
        title = "",
        content = "",
        boardId = 0L,
        writerId = 0L,
        createdAt = LocalDateTime.now(),
        modifiedAt = LocalDateTime.now()
    )

    companion object {
        fun create(
            articleId: Long,
            title: String,
            content: String,
            boardId: Long,
            writerId: Long
        ) = Article().apply {
            this.articleId = articleId
            this.title = title
            this.content = content
            this.boardId = boardId
            this.writerId = writerId
            this.createdAt = LocalDateTime.now()
            this.modifiedAt = createdAt
        }
    }

    fun update(title: String,
               content: String) {

        this.title = title
        this.content = content
        modifiedAt = LocalDateTime.now()
    }
}