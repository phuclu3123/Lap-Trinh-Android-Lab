package com.example.adr1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adr1.ui.theme.ADR1Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            ADR1Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    HomeScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {

    var count by rememberSaveable {
        mutableIntStateOf(0)
    }

    val greenDark = Color(0xFF173C32)
    val green = Color(0xFF286A55)
    val cream = Color(0xFFF5F2E9)
    val orange = Color(0xFFE8A44A)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(cream)
            .padding(24.dp)
    ) {

        /* HEADER */

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {

                Text(
                    text = "ADR1",
                    color = greenDark,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Learning Studio",
                    color = greenDark,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(greenDark),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "P",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }


        Spacer(
            modifier = Modifier.height(36.dp)
        )


        /* HERO */

        Text(
            text = "Học chắc.",
            fontSize = 48.sp,
            lineHeight = 50.sp,
            fontWeight = FontWeight.Bold,
            color = greenDark
        )

        Text(
            text = "Làm được.",
            fontSize = 48.sp,
            lineHeight = 50.sp,
            fontWeight = FontWeight.Bold,
            color = green
        )


        Spacer(
            modifier = Modifier.height(16.dp)
        )


        Text(
            text = "Một không gian nhỏ để thử nghiệm Jetpack Compose và xây dựng giao diện Android hiện đại.",
            color = Color(0xFF5E6863),
            fontSize = 16.sp,
            lineHeight = 24.sp
        )


        Spacer(
            modifier = Modifier.height(30.dp)
        )


        /* MAIN CARD */

        Card(
            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(24.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            )
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                greenDark,
                                green
                            )
                        )
                    )
                    .padding(26.dp)
            ) {

                Column {

                    Text(
                        text = "TODAY",
                        color = Color(0xFFB9D8CD),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Text(
                        text = "Jetpack Compose",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "UI được tạo hoàn toàn bằng Kotlin",
                        color = Color(0xFFD7E7E1),
                        fontSize = 15.sp
                    )


                    Spacer(
                        modifier = Modifier.height(28.dp)
                    )


                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        InfoBox(
                            title = "12",
                            subtitle = "Lessons"
                        )

                        InfoBox(
                            title = "72%",
                            subtitle = "Progress"
                        )
                    }
                }
            }
        }


        Spacer(
            modifier = Modifier.height(24.dp)
        )


        /* COUNTER CARD */

        Card(
            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(20.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),

            elevation = CardDefaults.cardElevation(
                defaultElevation = 3.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(22.dp)
            ) {

                Text(
                    text = "Compose State",
                    color = greenDark,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = "Bạn đã nhấn nút $count lần",
                    color = Color(0xFF69736F),
                    fontSize = 15.sp
                )


                Spacer(
                    modifier = Modifier.height(18.dp)
                )


                Button(
                    onClick = {
                        count++
                    },

                    colors = ButtonDefaults.buttonColors(
                        containerColor = orange,
                        contentColor = Color(0xFF302514)
                    ),

                    shape = RoundedCornerShape(14.dp)
                ) {

                    Text(
                        text = "Nhấn thử",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}


@Composable
fun InfoBox(
    title: String,
    subtitle: String
) {

    Column(
        modifier = Modifier
            .background(
                color = Color.White.copy(alpha = 0.12f),
                shape = RoundedCornerShape(14.dp)
            )
            .padding(
                horizontal = 18.dp,
                vertical = 14.dp
            )
    ) {

        Text(
            text = title,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = subtitle,
            color = Color(0xFFC9DDD6),
            fontSize = 12.sp
        )
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HomeScreenPreview() {

    ADR1Theme {
        HomeScreen()
    }
}