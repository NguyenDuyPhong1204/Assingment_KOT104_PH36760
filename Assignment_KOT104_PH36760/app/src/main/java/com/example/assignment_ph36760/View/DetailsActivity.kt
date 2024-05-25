package com.example.assignment_ph36760.View

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment_ph36760.R
import com.example.assignment_ph36760.Model.colorProduct
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.DecimalFormat

class DetailsActivity : ComponentActivity() {
    private lateinit var name: String
    private var image: Int = 0
    private lateinit var description: String
    private var price: Double = 0.0
    private var evaluate: Double = 0.0
    private var quantity: Int = 0
    private lateinit var colors: String
    private lateinit var colorsList: List<colorProduct>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = intent
        name = data.getStringExtra("name") ?: ""
        image = data.getIntExtra("image", 0)
        description = data.getStringExtra("description") ?: ""
        price = data.getDoubleExtra("price", 0.0)
        evaluate = data.getDoubleExtra("evaluate", 0.0)
        quantity = data.getIntExtra("quantity", 0)
        colors = data.getStringExtra("colors") ?: ""
        colorsList = Gson().fromJson(colors, object : TypeToken<List<colorProduct>>() {}.type)
        Log.d("Name", "onCreate: " + name)
        setContent {
            DetailsScreen()
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun DetailsScreen() {
        var selectedColor by remember { mutableStateOf(colorsList.first()) }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .padding(start = 40.dp)
                        .clip(RoundedCornerShape(bottomStart = 30.dp)),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .shadow(3.dp, shape = RoundedCornerShape(40.dp))
                            .background(Color.White),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        ColorSelectionRow(
                            colors = colorsList,
                            selectedColor = selectedColor,
                            onColorSelected = { colorOption ->
                                selectedColor = colorOption
                            }
                        )

                    }
                }

                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .padding(16.dp)
                        .shadow(5.dp, shape = RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .width(40.dp)
                        .height(40.dp),
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(15.dp)
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Text(
                    text = name.toString(),
                    color = Color(0xFF303030),
                    fontSize = 24.sp,
                    fontFamily = FontFamily(
                        Font(
                            R.font.gelasio_variablefont_wght,
                            FontWeight.Normal
                        )
                    ),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    val formattedPrice = DecimalFormat("$ ##,##.00").format(price)
                    Text(
                        text = formattedPrice,
                        color = Color(0xFF303030),
                        fontSize = 30.sp,
                        fontFamily = FontFamily(Font(R.font.nunitosan, FontWeight.Normal)),
                        fontWeight = FontWeight(700),
                        modifier = Modifier
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .padding(10.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .background(Color(0xFFf3f3f3))
                                .width(25.dp)
                                .height(25.dp),
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.cong),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(10.dp)
                                    .height(10.dp)
                            )
                        }

                        Text(
                            text = "01",
                            color = Color(0xFF303030),
                            fontSize = 18.sp,
                            fontFamily = FontFamily(
                                Font(
                                    R.font.nunitosan,
                                    FontWeight.Normal
                                )
                            ),
                            fontWeight = FontWeight(800),
                            modifier = Modifier
                        )

                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .padding(10.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .background(Color(0xFFf3f3f3))
                                .width(25.dp)
                                .height(25.dp),
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.tru),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(10.dp)
                                    .height(10.dp)
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.rate),
                        contentDescription = null,
                        modifier = Modifier
                            .height(20.dp)
                            .width(20.dp)
                    )

                    Spacer(modifier = Modifier.padding(5.dp))

                    Text(
                        text = evaluate.toString(),
                        color = Color(0xFF303030),
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.nunitosan, FontWeight.Normal)),
                        fontWeight = FontWeight(700),
                        modifier = Modifier
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    Text(
                        text = "( 50 reviews )",
                        color = Color(0xFF808080),
                        fontSize = 14.sp,
                        fontFamily = FontFamily(
                            Font(
                                R.font.nunitosan,
                                FontWeight.Normal
                            )
                        ),
                        fontWeight = FontWeight(800),
                        modifier = Modifier
                    )
                }

                Text(
                    text = description.toString(),
                    color = Color(0xFF606060),
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.nunitosan, FontWeight.Normal)),
                    fontWeight = FontWeight(800),
                    modifier = Modifier
                        .padding(top = 5.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Row(
                        modifier = Modifier
                            .height(60.dp)
                    ) {
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .shadow(1.dp, shape = RoundedCornerShape(8.dp))
                                .background(Color(0xFFF0F0F0))
                                .width(60.dp)
                                .height(60.dp),
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.mark),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(24.dp)
                                    .height(24.dp)
                            )
                        }

                        Spacer(modifier = Modifier.padding(10.dp))

                        Button(
                            onClick = {},
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .shadow(4.dp, shape = RoundedCornerShape(10.dp)),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF242424),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "Add to cart",
                                fontSize = 16.sp,
                                fontFamily = FontFamily(
                                    Font(
                                        R.font.nunitosan,
                                        FontWeight.Normal
                                    )
                                ),
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun ColorSelectionRow(
        colors: List<colorProduct>,
        selectedColor: colorProduct,
        onColorSelected: (colorProduct) -> Unit
    ) {
        Column(
            modifier = Modifier
                .padding(5.dp)
        ) {
            colors.forEach { colorOption ->
                val color = when (colorOption.nameColor) {
                    "White" -> Color.White
                    "Black" -> Color.Black
                    "Green" -> Color.Green
                    else -> Color.Gray
                }
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(color)
                        .clickable { onColorSelected(colorOption) }
                        .border(
                            3.dp,
                            if (colorOption == selectedColor) Color.Gray else Color.Transparent,
                            CircleShape
                        )
                        .shadow(2.dp)
                )
            }
        }
    }


}



