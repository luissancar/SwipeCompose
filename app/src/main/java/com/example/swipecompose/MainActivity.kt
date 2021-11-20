package com.example.swipecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swipecompose.ui.theme.Purple200
import com.example.swipecompose.ui.theme.Purple500
import com.example.swipecompose.ui.theme.SwipeComposeTheme
import kotlin.math.roundToInt


@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwipeComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ComposeSwipe()
                }
            }
        }
    }


}

@ExperimentalMaterialApi
@Composable
private fun ComposeSwipe() {
    var bgColor by remember { mutableStateOf(Purple200)}

    val color = animateColorAsState(
        targetValue = bgColor,
        animationSpec = tween(
            durationMillis = 2000
        )
    )

    val squareSize =150.dp
    val swipeAbleState= rememberSwipeableState(initialValue = 0)
    val sizePx = with(LocalDensity.current) { squareSize.toPx()}
    val anchors = mapOf(0f to 0, sizePx to 1)

    Column (
        modifier = Modifier.fillMaxSize()
            ){
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .background(Purple500)
                .padding(15.dp),
        )
        {
            Text(text = "Ejemplo compose Swipe")
        }

        Column(
            modifier = Modifier
                .padding(20.dp)
                .background(Color.Transparent)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Color.LightGray)
                .swipeable(
                    state = swipeAbleState,
                    anchors = anchors,
                    thresholds = { _, _ ->
                        FractionalThreshold(0.3f)
                    },
                    orientation = Orientation.Horizontal
                )
            ){
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    IconButton(
                        onClick = { bgColor= Color.Red },
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Color.Cyan)) {
                        Icon(
                            Icons.Filled.Edit,
                            contentDescription = "Edit",
                            tint = Color.Red
                        )
                        
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    IconButton(
                        onClick = { bgColor= Color.Black },
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Color.DarkGray)) {
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = "Delete",
                            tint = Color.Green
                        )

                    }
                    
                }
                Box(
                    modifier = Modifier
                        .offset {
                            IntOffset(
                                swipeAbleState.offset.value.roundToInt(), 0
                            )
                        }
                        .clip(RoundedCornerShape(15.dp))
                        .fillMaxWidth()
                        .height(150.dp)
                        .fillMaxHeight()
                        .background(color.value)
                        .align(Alignment.CenterStart)
                )
                {
                    Column  (
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp),
                            verticalArrangement = Arrangement.Center
                            ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {

                            Image(
                                painter = painterResource(id = R.drawable.nosfe),
                                contentDescription = "Circle Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(70.dp)
                                    .clip(CircleShape) )
                        }
                        Spacer(modifier = Modifier.padding(10.dp))

                        Column {
                            Text(
                                text = "Swipe Layout",
                                color = Color.White,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.padding(10.dp))

                            Text(
                                text = "Lorem Ipsum is simply dummy text of the printing and type setting industry...",
                                color = Color.White,
                                fontSize = 18.sp
                            )
                        }
                    }
                }
            }

        }

    }

}


@ExperimentalMaterialApi
@Composable
@Preview
fun Previ(){
    ComposeSwipe()
}
