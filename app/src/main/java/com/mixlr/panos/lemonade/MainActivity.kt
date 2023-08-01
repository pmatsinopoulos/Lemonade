package com.mixlr.panos.lemonade

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mixlr.panos.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }

    private fun generateNumberOfTimesToSqueeze() = (2..4).random()
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    var step by remember {
        mutableStateOf(1)
    }
    var squeezeTimes by remember {
        mutableStateOf(1)
    }
    var numberOfTimesToSqueeze by remember {
        mutableStateOf((2..4).random())
    }
    val image = when (step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        4 -> R.drawable.lemon_restart
        else -> throw IllegalArgumentException("step: $step is not between 1 and 4")
    }
    val prompt = when (step) {
        1 -> stringResource(R.string.step_1_prompt)
        2 -> stringResource(R.string.step_2_prompt)
        3 -> stringResource(R.string.step_3_prompt)
        4 -> stringResource(R.string.step_4_prompt)
        else -> throw IllegalArgumentException("step: $step is not between 1 and 4")
    }
    Column(
    ) {
        Row(
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                text = stringResource(id = R.string.lemonade),
                modifier = Modifier
                    .background(Color.Yellow)
                    .padding(20.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        Row(
        ) {
            Column(
                modifier = Modifier
                    .wrapContentSize(Alignment.Center)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        Log.d("Lemonade", "onClick: numberOfTimesToSqueeze = $numberOfTimesToSqueeze")
                        if (step == 2) {
                            squeezeTimes++
                            if (squeezeTimes >= numberOfTimesToSqueeze) {
                                step++
                            }
                        } else {
                            step++
                        }
                        if (step == 5) {
                            step = 1
                            squeezeTimes = 1
                            numberOfTimesToSqueeze = (2..4).random()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .background(colorResource(id = R.color.teal_200), RoundedCornerShape(30.dp))
                        .padding(30.dp)
                ) {
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = "Start",
                        modifier = Modifier.background(Color.Transparent)
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = prompt
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LemonadeAppPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}
