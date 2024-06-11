package com.example.assignment_ph36760.View

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.assignment_ph36760.Model.Entity.Cart
import com.example.assignment_ph36760.Model.Entity.Favorite
import com.example.assignment_ph36760.Model.Entity.Product
import com.example.assignment_ph36760.Model.Entity.Screens
import com.example.assignment_ph36760.R
import com.example.assignment_ph36760.ViewModel.CartViewModel
import com.example.assignment_ph36760.ViewModel.FavoriteViewModel

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.DecimalFormat

class DetailsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent.getStringExtra("id") ?: ""
        val name = intent.getStringExtra("name") ?: ""
        val image = intent.getStringExtra("image") ?: ""
        val description = intent.getStringExtra("description") ?: ""
        val price = intent.getFloatExtra("price", 0.0f)
        val evaluate = intent.getFloatExtra("evaluate", 0.0f)
        val quantity = intent.getIntExtra("quantity", 0)
        val colorJson = intent.getStringExtra("color") ?: "[]"
        var userID: String? = ""
        val colorList = Gson().fromJson(colorJson, Array<Product.Color>::class.java).toList()

        setContent {
            val navController = rememberNavController()
            val cartViewModel = CartViewModel()
            val favoriteViewModel = FavoriteViewModel()
            DetailsScreen(navController, id ,name, image, description, price, evaluate, quantity, colorList, cartViewModel, favoriteViewModel)

        }
    }

}
@Composable
fun DetailsScreen(
    navController: NavController,
    id: String,
    name: String,
    image: String,
    description: String,
    price: Float,
    evaluate: Float,
    quantity: Int,
    color: List<Product.Color>,
    cartViewModel: CartViewModel,
    favoriteViewModel: FavoriteViewModel
) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_credentials", Context.MODE_PRIVATE)
    val userId = sharedPreferences.getString("userId", "")
//        var selectedColor by remember { mutableStateOf(colorsList.first()) }
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
            AsyncImage(
                model = image,
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
//                        ColorSelectionRow(
//                            colors = color,
//                            selectedColor = selectedColor,
//                            onColorSelected = { colorOption ->
//                                selectedColor = colorOption
//                            }
//                        )

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
                        onClick = { navController.navigate(Screens.Cart.screens) },
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
                                .clickable {
                                    val favorite = Favorite(
                                        productId = id,
                                        productName = name,
                                        productImage = image,
                                        quantity = 1,
                                        productPrice = price.toDouble()
                                    )
                                    favoriteViewModel.addToFavorite(favorite)
                                    Toast.makeText(context, "Đã thêm vào yêu thích", Toast.LENGTH_SHORT).show()
                                }
                        )
                    }

                    Spacer(modifier = Modifier.padding(10.dp))

                    Button(
                        onClick = {
                            val cart = Cart(
                                productId = id,
                                productName = name,
                                productImage = image,
                                quantity = 1,
                                productPrice = price.toDouble()
                            )
                            cartViewModel.addToCart(cart)
                            Toast.makeText(context, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show()
                        },
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

//    @Composable
//    fun ColorSelectionRow(
//        colors: List<colorProduct>,
//        selectedColor: colorProduct,
//        onColorSelected: (colorProduct) -> Unit
//    ) {
//        Column(
//            modifier = Modifier
//                .padding(5.dp)
//        ) {
//            colors.forEach { colorOption ->
//                val color = when (colorOption.nameColor) {
//                    "White" -> Color.White
//                    "Black" -> Color.Black
//                    "Green" -> Color.Green
//                    else -> Color.Gray
//                }
//                Box(
//                    modifier = Modifier
//                        .size(40.dp)
//                        .padding(4.dp)
//                        .clip(CircleShape)
//                        .background(color)
//                        .clickable { onColorSelected(colorOption) }
//                        .border(
//                            3.dp,
//                            if (colorOption == selectedColor) Color.Gray else Color.Transparent,
//                            CircleShape
//                        )
//                        .shadow(2.dp)
//                )
//            }
//        }
//    }





