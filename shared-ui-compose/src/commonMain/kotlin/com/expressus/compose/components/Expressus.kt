package com.expressus.compose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.expressus.compose.components.centerPanel.MachineFrame
import com.expressus.compose.components.leftPanel.CoffeeSlot
import com.expressus.compose.components.leftPanel.FaucetOffsets
import com.expressus.compose.components.rightPanel.CircularButton
import com.expressus.compose.components.rightPanel.Display
import com.expressus.compose.themes.CoffeeSelectorsTheme

@Composable
fun ExpressusMobile(isGrinding: Boolean, isPouring: Boolean, isMakingCoffee: Boolean, status: String, makeCoffee: () -> Unit) {
    MachineFrame(Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CoffeeSlot(
                modifier = Modifier
                    .aspectRatio(1f)
                    .padding(10.dp),
                isGrinding = isGrinding,
                isPouring = isPouring,
                pouringSpeed = 2L,
                slotOffset = 50.dp,
                faucetOffsets = FaucetOffsets(10.dp, 10.dp)
            )
            Spacer(Modifier.size(50.dp))
            Display(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 50.dp),
                text = status
            )
            Spacer(Modifier.size(50.dp))
            CoffeeSelectorsTheme {
                CircularButton(size = 70.dp, enabled = !isMakingCoffee) { makeCoffee() }
            }
        }
    }
}