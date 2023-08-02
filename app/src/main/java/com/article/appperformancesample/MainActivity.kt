package com.article.appperformancesample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.article.appperformancesample.ui.theme.AppPerformanceSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppPerformanceSampleTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center ,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val infiniteTrans = rememberInfiniteTransition()
                    var initialValue by remember {
                        mutableStateOf(0f)
                    }
                    var finalValue by remember {
                        mutableStateOf(0f)
                    }

                    RotatingHandComponent(
                        infiniteTransition = infiniteTrans ,
                        finalValue = finalValue ,
                        initialValue = initialValue
                    )
                    Spacer(Modifier.height(20.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceAround ,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(onClick = {
                            finalValue = 0f
                            initialValue = 360f
                        }) {
                            Text(text = "rotate left")
                        }
                        Button(onClick = {
                            finalValue = 360f
                            initialValue = 0f
                        }) {
                            Text(text = "rotate right")
                        }
                    }

                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RotatingHandComponent(
    initialValue : Float = 0f ,
    finalValue : Float = 0f ,
    infiniteTransition : InfiniteTransition
){
    val rotation by infiniteTransition.animateFloat(
        initialValue = initialValue,
        targetValue = finalValue,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 3000 ,
                easing = FastOutLinearInEasing
            )
        )
    )

        Image(
            painter = painterResource(id = R.drawable.baseline_back_hand_24) ,
            contentDescription = "Image of a backhand" ,
            modifier = Modifier.size(250.dp).rotate(rotation)
        )

}
