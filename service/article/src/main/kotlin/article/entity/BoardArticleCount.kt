package article.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "board_article_count")
open class BoardArticleCount(

    @Id
    val boardId: Long,

    var articleCount: Long = 0L

) {
    protected constructor() : this(0L)

    companion object {
        /** 처음 생성할 때만 쓰는 팩토리 메서드 */
        fun init(boardId: Long, count: Long = 1L) = BoardArticleCount(boardId, count)
    }
}
