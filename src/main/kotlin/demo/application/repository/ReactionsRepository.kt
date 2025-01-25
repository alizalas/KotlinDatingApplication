package demo.application.repository

import demo.application.dto.Reaction
import org.springframework.stereotype.Repository

@Repository
class ReactionsRepository (
    val reactions: MutableList<Reaction>
) {

    fun react(reaction: Boolean, firstUserId: Int, secondUserId: Int) {
        val oldReaction = reactions.find { it.secondUserId == secondUserId && it.firstUserId == firstUserId }

        if (oldReaction != null) {
            oldReaction.reaction = reaction
        } else {
            reactions.add(Reaction(reactions.size, secondUserId, firstUserId, reaction))
        }
    }
}