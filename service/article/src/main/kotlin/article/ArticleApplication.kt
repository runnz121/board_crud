package article

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ArticleApplication

fun main(args: Array<String>) {
    runApplication<ArticleApplication>(*args)
}