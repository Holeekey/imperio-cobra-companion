package org.imperiocobra.companion.probabilities.functions

fun rollRange(probabilities: List<Float>, rewards: List<String>): String {



    var randomNumber = (1..100).random()

    var reward: String = ""

    for (i in 0..probabilities.size - 2) {

        if (probabilities[i] <= randomNumber && randomNumber < probabilities[i + 1]) {
            reward = rewards[i]
        }

    }

    return reward

}