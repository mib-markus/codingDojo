package de.codingdojo

import de.codingdojo.utils.Action

class Caveman {

    fun main(args: Array<String>) {

        val result: String = if (args.isNotEmpty()) {
            run(args[0])
        } else {
            run(null)
        }

        println(result)
    }

    fun run(input: String?): String {
        val action: Action
        action = if (input == null) {
            run(emptyList(), emptyList())
        } else {
            val actions = input.split(",")
            run(Action.parse(actions[0]), Action.parse(actions[1]))
        }

        return action.name.slice(0..0)
    }

    private fun run(myActions: List<Action>, opponentsActions: List<Action>): Action {

        if (getSharpness(myActions) == 0 && getSharpness(opponentsActions) == 0) {
            return Action.SHARPEN
        }
        if (getSharpness(myActions) == 5) {
            return Action.POKE
        }
        if (opponentsActions[opponentsActions.size - 1] == Action.POKE && opponentsActions[opponentsActions.size - 2] == Action.SHARPEN && getSharpness(
                myActions
            ) > 0
        ) {
            return Action.POKE
        }
        if (getSharpness(myActions) > getSharpness(opponentsActions) && getSharpness(opponentsActions) == 0) {
            return Action.SHARPEN
        }
        if (opponentsActions[opponentsActions.size - 1] == Action.BLOCK && opponentsActions[opponentsActions.size - 2] == Action.BLOCK) {
            return Action.SHARPEN
        }
        return if (getSharpness(myActions) > getSharpness(opponentsActions) + 1) {
            Action.POKE
        } else Action.BLOCK
    }

    private fun getSharpness(actions: List<Action>): Int = getSharpnessRecursive(actions, 0)

    fun getSharpnessRecursive(actions: List<Action>, originalSharpness: Int): Int =
        when {
            actions.isEmpty() -> originalSharpness
            actions.last() == Action.SHARPEN -> getSharpnessRecursive(
                actions.subList(0, actions.size - 1),
                originalSharpness + 1
            )
            actions.last() == Action.POKE && originalSharpness > 0 -> getSharpnessRecursive(
                actions
                    .subList(0, actions.size - 1),
                originalSharpness - 1
            )
            else -> getSharpnessRecursive(actions.subList(0, actions.size - 1), originalSharpness)
        }
}
