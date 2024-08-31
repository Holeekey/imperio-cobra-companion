package org.imperiocobra.companion.probabilities.functions

fun rollMatch( rewards: List<String> ): String{

    val randomNumber = (0..rewards.size - 1).random()

    return rewards[randomNumber]
    
}