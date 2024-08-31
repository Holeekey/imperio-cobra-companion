package org.imperiocobra.companion

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.imperiocobra.companion.probabilities.chest.ChestRewards
import org.imperiocobra.companion.probabilities.consumables.ConsumableRewards
import org.imperiocobra.companion.probabilities.consumables.LessThanOneGoldConsumableRewards
import org.imperiocobra.companion.probabilities.dungeon.DungeonProbabilities
import org.imperiocobra.companion.probabilities.dungeon.DungeonRewards
import org.imperiocobra.companion.probabilities.functions.rollMatch
import org.imperiocobra.companion.probabilities.functions.rollRange
import org.imperiocobra.companion.probabilities.kraken.KrakenProbabilities
import org.imperiocobra.companion.probabilities.kraken.KrakenRewards
import org.imperiocobra.companion.probabilities.kraken.KrakenVictoryProbabilities
import org.imperiocobra.companion.probabilities.kraken.KrakenVictoryRewards
import org.imperiocobra.companion.probabilities.`legendary-rewards`.LegendaryRewards
import org.imperiocobra.companion.probabilities.quest.mandatory.MandatoryQuestProbabilities
import org.imperiocobra.companion.probabilities.quest.mandatory.MandatoryQuestRewards
import org.imperiocobra.companion.probabilities.quest.optional.OptionalQuestProbabilities
import org.imperiocobra.companion.probabilities.quest.optional.OptionalQuestRewards
import org.imperiocobra.companion.probabilities.`random-encounter`.one.RandomEncounterOneProbabilities
import org.imperiocobra.companion.probabilities.`random-encounter`.one.RandomEncounterOneRewards
import org.imperiocobra.companion.probabilities.`random-encounter`.three.RandomEncounterThreeProbabilities
import org.imperiocobra.companion.probabilities.`random-encounter`.three.RandomEncounterThreeRewards
import org.imperiocobra.companion.probabilities.`random-encounter`.two.RandomEncounterTwoProbabilities
import org.imperiocobra.companion.probabilities.`random-encounter`.two.RandomEncounterTwoRewards
import org.imperiocobra.companion.probabilities.terrain.one.TerrainOneProbabilities
import org.imperiocobra.companion.probabilities.terrain.one.TerrainOneRewards
import org.imperiocobra.companion.probabilities.terrain.three.TerrainThreeProbabilities
import org.imperiocobra.companion.probabilities.terrain.three.TerrainThreeRewards
import org.imperiocobra.companion.probabilities.terrain.two.TerrainTwoProbabilities
import org.imperiocobra.companion.probabilities.terrain.two.TerrainTwoRewards
import org.imperiocobra.companion.probabilities.tools.ToolsRewards
import org.imperiocobra.companion.probabilities.weapons.WeaponsRewards
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val dropButtonColor = MaterialTheme.colors.surface
        var showTerrain by remember { mutableStateOf(false) }
        var showRandomEncounter by remember { mutableStateOf(false) }
        var showQuest by remember { mutableStateOf(false) }
        var showDialog by remember { mutableStateOf(false) }
        var message by remember { mutableStateOf("message") }
        if (showDialog) {
            Dialog(onDismissRequest = { showDialog = false }) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp)
                        .padding(16.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Text(
                        text = message,
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
        Column(Modifier.verticalScroll(rememberScrollState()).fillMaxWidth().padding(16.dp), horizontalAlignment = Alignment.Start) {
            Button(onClick = { showTerrain = !showTerrain }) {
                Text("Terrenos")
            }
            AnimatedVisibility(showTerrain) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Button(onClick = {
                        message = rollRange(TerrainOneProbabilities, TerrainOneRewards)
                        showDialog = true
                    }, colors = ButtonDefaults.buttonColors(backgroundColor = dropButtonColor)) {
                        Text("Nivel 1")
                    }
                    Button(onClick = {
                        message = rollRange(TerrainTwoProbabilities, TerrainTwoRewards)
                        showDialog = true
                    }, colors = ButtonDefaults.buttonColors(backgroundColor = dropButtonColor)) {
                        Text("Nivel 2")
                    }
                    Button(onClick = {
                        message = rollRange(TerrainThreeProbabilities, TerrainThreeRewards)
                        showDialog = true
                    }, colors = ButtonDefaults.buttonColors(backgroundColor = dropButtonColor)) {
                        Text("Nivel 3")
                    }
                    Button(onClick = {
                        message = rollRange(KrakenProbabilities, KrakenRewards)
                        showDialog = true
                    }, colors = ButtonDefaults.buttonColors(backgroundColor = dropButtonColor)) {
                        Text("Mar")
                    }
                }
            }
            Spacer(Modifier.padding(vertical = 10.dp))
            Button(onClick = { showRandomEncounter = !showRandomEncounter }) {
                Text("Encuentros Aleatorios")
            }
            AnimatedVisibility(showRandomEncounter) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Button(onClick = {
                        message = rollRange(RandomEncounterOneProbabilities, RandomEncounterOneRewards)
                        showDialog = true
                    }, colors = ButtonDefaults.buttonColors(backgroundColor = dropButtonColor)) {
                        Text("Enemigo nv. 1")
                    }
                    Button(onClick = {
                        message = rollRange(RandomEncounterTwoProbabilities, RandomEncounterTwoRewards)
                        showDialog = true
                    }, colors = ButtonDefaults.buttonColors(backgroundColor = dropButtonColor)) {
                        Text("Enemigo nv. 2")
                    }
                    Button(onClick = {
                        message = rollRange(RandomEncounterThreeProbabilities, RandomEncounterThreeRewards)
                        showDialog = true
                    }, colors = ButtonDefaults.buttonColors(backgroundColor = dropButtonColor)) {
                        Text("Enemigo nv. 3")
                    }
                    Button(onClick = {
                        var reward = rollRange(KrakenVictoryProbabilities, KrakenVictoryRewards)
                        if(reward == "Recompensa legendaria"){
                            reward =  rollMatch(LegendaryRewards)
                        }
                        message = reward
                        showDialog = true
                    }, colors = ButtonDefaults.buttonColors(backgroundColor = dropButtonColor)) {
                        Text("Kraken")
                    }
                }
            }
            Spacer(Modifier.padding(vertical = 10.dp))
            Button(onClick = {
                var reward = rollRange(DungeonProbabilities, DungeonRewards)
                if (reward == "Recompensa legendaria"){
                    reward = rollMatch(LegendaryRewards)
                }
                message = reward
                showDialog = true
            }) {
                Text("Mazmorra")
            }
            Spacer(Modifier.padding(vertical = 10.dp))
            Button(onClick = { showQuest = !showQuest }) {
                Text("Pruebas")
            }
            AnimatedVisibility(showQuest) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Button(onClick = {
                        var reward = rollRange(MandatoryQuestProbabilities, MandatoryQuestRewards)
                        if( reward == "Consumible al azar"){
                            reward = rollMatch(ConsumableRewards)
                        }
                        message = reward
                        showDialog = true
                    }, colors = ButtonDefaults.buttonColors(backgroundColor = dropButtonColor)) {
                        Text("Obligatoria")
                    }
                    Button(onClick = {
                        var reward = rollRange(OptionalQuestProbabilities, OptionalQuestRewards)
                        if( reward == "Consumible a decisión de menos de 1.00 de oro"){
                            reward = rollMatch(LessThanOneGoldConsumableRewards)
                        }
                        message = reward
                        showDialog = true
                    }, colors = ButtonDefaults.buttonColors(backgroundColor = dropButtonColor)) {
                        Text("Opcional")
                    }
                }
            }
            Spacer(Modifier.padding(vertical = 10.dp))
            Button(onClick = {
                message = rollMatch(ChestRewards)
                showDialog = true
            }) {
                Text("Cofre")
            }
            Spacer(Modifier.padding(vertical = 10.dp))
            Button(onClick = {
                message = rollMatch(WeaponsRewards)
                showDialog = true
            }) {
                Text("Arma al azar")
            }
            Spacer(Modifier.padding(vertical = 10.dp))
            Button(onClick = {
                message = rollMatch(ConsumableRewards)
                showDialog = true
            }) {
                Text("Consumible al azar")
            }
            Spacer(Modifier.padding(vertical = 10.dp))
            Button(onClick = {
                message = rollMatch(ToolsRewards)
                showDialog = true
            }) {
                Text("Herramienta al azar")
            }
            Spacer(Modifier.padding(vertical = 10.dp))
            Button(onClick = {
                message = "Consumibles: " + "\n" +
                        rollMatch(ConsumableRewards) + "\n" +
                        rollMatch(ConsumableRewards) + "\n" +
                        rollMatch(ConsumableRewards) + "\n" +
                        rollMatch(ConsumableRewards) + "\n" +
                        rollMatch(ConsumableRewards) + "\n" +
                        rollMatch(ConsumableRewards) + "\n" + "\n" +
                        "Herramientas: " + "\n" +
                        rollMatch(ToolsRewards) + "\n" +
                        rollMatch(ToolsRewards) + "\n" +
                        rollMatch(ToolsRewards) + "\n" +
                        rollMatch(ToolsRewards) + " aumentado/a(s)" + "\n" + "\n" +
                        "Armas: " + "\n" +
                        rollMatch(WeaponsRewards) + "\n" +
                        rollMatch(WeaponsRewards) + "\n" +
                        rollMatch(WeaponsRewards) + " aumentado/a(s)" +"\n" + "\n"

                showDialog = true
            }) {
                Text("Tienda de los dioses")
            }
            Spacer(Modifier.padding(vertical = 10.dp))
        }
    }
}