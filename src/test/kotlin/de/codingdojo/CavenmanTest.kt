package de.codingdojo

import de.codingdojo.utils.Action
import org.junit.jupiter.api.Test

class CavemanTest {

    private var countWinsPlayerOne = 0
    private var countWinsPlayerTwo = 0

    private val namePlayerOne = "Test"
    private val namePlayerTwo = "Markus"

    @Test
    fun begin() {
        (0..99).forEach { _ ->
            gameSetup()
        }
    }

    private fun gameSetup() {
        var myActions = ""
        var opponentsActions = ""

        (0..99).forEach { _ ->
            val myInput = if (myActions.isEmpty()) null else "$myActions,$opponentsActions"
            val opponentsInput = if (opponentsActions.isEmpty()) null else "$opponentsActions,$myActions"

            myActions += playerOneActions(myInput)
            opponentsActions += playerTwoActions(opponentsInput)

            val result = battle(Action.parse(myActions), Action.parse(opponentsActions))
            if (result != null) {
                print("$myActions,$opponentsActions")
                println(" - $result")
                println("$namePlayerOne, $countWinsPlayerOne")
                println("$namePlayerTwo, $countWinsPlayerTwo")
                println()
                return
            }
        }

        print("$myActions,$opponentsActions")
        println(" - Stalemate")
    }

    private fun playerTwoActions(opponentsInput: String?): String {
        return CavemanSecondPlayer().run(opponentsInput)
    }

    private fun playerOneActions(myInput: String?): String {
        return Caveman().run(myInput)
    }

    private fun battle(actions1: List<Action>, actions2: List<Action>): String? {
        val sharpness1 = getSharpness(actions1)
        val sharpness2 = getSharpness(actions2)
        val current1 = actions1[actions1.size - 1]
        val current2 = actions2[actions1.size - 1]

        if (current1 == Action.POKE && current2 == Action.POKE) {
            return if (sharpness1 == 0 && sharpness2 > 0) {
                countWinsPlayerTwo++
                "$namePlayerTwo wins"
            } else if (sharpness2 == 0 && sharpness1 > 0) {
                countWinsPlayerOne++
                "$namePlayerOne wins"
            } else {
                null
            }
        } else if (current1 == Action.POKE && current2 == Action.SHARPEN) {
            return if (sharpness1 > 0) {
                countWinsPlayerOne++
                "$namePlayerOne wins"
            } else {
                null
            }
        } else if (current2 == Action.POKE && current1 == Action.SHARPEN) {
            return if (sharpness2 > 0) {
                countWinsPlayerTwo++
                "$namePlayerTwo wins"
            } else {
                null
            }
        } else if (current1 == Action.POKE && current2 == Action.BLOCK) {
            return if (sharpness1 >= 5) {
                countWinsPlayerOne++
                "$namePlayerOne wins"
            } else {
                null
            }
        } else if (current2 == Action.POKE && current1 == Action.BLOCK) {
            return if (sharpness2 >= 5) {
                countWinsPlayerTwo++
                "$namePlayerTwo wins"
            } else {
                null
            }
        }

        return null
    }

    private fun getSharpness(actions: List<Action>): Int {
        var sharpness = 0
        (0 until actions.size - 1).forEach { i ->
            val action = actions[i]
            if (action == Action.SHARPEN) {
                sharpness++
            } else if (action == Action.POKE && sharpness > 0) {
                sharpness--
            }
        }
//        println(actions + " " + sharpness)
        return sharpness
    }
}
