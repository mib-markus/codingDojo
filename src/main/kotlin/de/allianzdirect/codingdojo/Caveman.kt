package de.allianzdirect.codingdojo

import de.allianzdirect.codingdojo.utils.Action

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
        // TODO implement this
        return Action.SHARPEN
    }

}