package de.codingdojo

import de.codingdojo.utils.Action
import kotlin.random.Random

class CavemanSecondPlayer {

    fun main(args: Array<String>) =
        if (args.isNotEmpty()) {
            println(run(args[0]))
        } else {
            println(run(null))
        }

    fun run(input: String?): String =
        if (input == null) {
            readyPlayerTwo(emptyList(), emptyList()).name.slice(0..0)
        } else {
            val actions = input.split(",")
            readyPlayerTwo(Action.parse(actions[0]), Action.parse(actions[1])).name.slice(0..0)
        }

    private fun readyPlayerTwo(myActions: List<Action>, opponentsActions: List<Action>): Action =

        when (Pair(getSharpness(myActions), getSharpness(opponentsActions))) {
            Pair(0, 0) -> Action.SHARPEN
            Pair(5, 0), Pair(5, 1), Pair(5, 2), Pair(5, 3), Pair(5, 4) -> Action.POKE
            Pair(0, 1) -> Action.BLOCK
            Pair(0, 2), Pair(0, 3), Pair(0, 4), Pair(0, 5) -> getSharpenOrBlock()
            Pair(1, 0) -> Action.SHARPEN
            Pair(2, 0), Pair(3, 0), Pair(4, 0) -> getSharpenOrPoke()
            Pair(1, 1) -> Action.BLOCK
            Pair(1, 2) -> getBlockOrPoke()
            else -> getRandomAction()
        }

    private fun getRandomAction() = when (Random.nextInt(0, 2)) {
        0 -> Action.POKE
        1 -> Action.BLOCK
        2 -> Action.SHARPEN
        else -> Action.BLOCK
    }

    private fun getRandomOfTwo(action1: Action, action2: Action) = when (Random.nextInt(0, 1)) {
        0 -> action1
        1 -> action2
        else -> Action.BLOCK
    }

    private fun getSharpness(actions: List<Action>): Int = Caveman().getSharpnessRecursive(actions, 0)

    private fun getBlockOrPoke() = getRandomOfTwo(Action.BLOCK, Action.POKE)

    private fun getSharpenOrPoke() = getRandomOfTwo(Action.SHARPEN, Action.POKE)

    private fun getSharpenOrBlock() = getRandomOfTwo(Action.SHARPEN, Action.BLOCK)
}
