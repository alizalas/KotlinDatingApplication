package demo.application.dto

data class Reaction(
    val id: Int,
    val firstUserId: Int,
    val secondUserId: Int,
    var reaction: Boolean
)
