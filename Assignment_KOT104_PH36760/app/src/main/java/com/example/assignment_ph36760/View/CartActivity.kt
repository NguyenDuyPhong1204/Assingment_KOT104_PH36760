package com.example.assignment_ph36760.View

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.assignment_ph36760.Model.Entity.Cart
import com.example.assignment_ph36760.Model.Entity.Screens
import com.example.assignment_ph36760.R
import com.example.assignment_ph36760.Response.ApiResponse
import com.example.assignment_ph36760.ViewModel.CartViewModel
import java.text.DecimalFormat

class CartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val cartViewModel = CartViewModel()
            val navController = rememberNavController()
            CartScreen(cartViewModel = cartViewModel, navController)
        }
    }
}

//val listCart = listOf(
//    CartModel("Black Simple Lamp", R.drawable.imageproduct1, 12.00, 1),
//    CartModel("Minimal Stand", R.drawable.imageproduct2, 25.00, 1),
//    CartModel("Coffee Chair", R.drawable.imageproduct3, 20.00, 1),
//    CartModel("Simple Desk", R.drawable.imageproduct4, 50.00, 1)
//)


@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
@Composable
fun CartScreen(cartViewModel: CartViewModel, navController: NavController) {
    LaunchedEffect(Unit) {
        cartViewModel.loadCart()
    }
    var promocode by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val cart by cartViewModel.cart.observeAsState(null)
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "find",
                    modifier = Modifier.size(20.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    TextStyle(
                        title = "My cart",
                        fontSize = 20.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF303030),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .height(25.dp)
                            .fillMaxWidth(),
                        fontFamily = FontFamily(Font(R.font.merriweather_black))
                    )
                }


            }

            LazyListCart(list = cart, cartViewModel)


            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = promocode, onValueChange = { promocode = it },
                modifier = Modifier
                    .width(335.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                shape = RoundedCornerShape(10.dp),
                label = { Text(text = "Enter your promo code") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    cursorColor = Color.White
                ),
                trailingIcon = {
                    Button(
                        modifier = Modifier
                            .width(55.dp)
                            .height(55.dp)
                            .align(alignment = Alignment.CenterHorizontally),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF242424)),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {}
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.next),
                            contentDescription = "next",
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp)
                                .align(Alignment.CenterVertically)
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .width(335.dp)
                    .height(44.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextStyle(
                    title = "Total: ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF808080),
                    textAlign = null,
                    modifier = Modifier.height(25.dp),
                    fontFamily = FontFamily(Font(R.font.nunitosan))
                )
//                val formattedPrice = DecimalFormat("$ ##,##.00").format(price)
                TextStyle(
                    title = "$ 95.00",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF303030),
                    textAlign = null,
                    modifier = Modifier.height(25.dp),
                    fontFamily = FontFamily(Font(R.font.nunitosan))
                )
            }


            Button(modifier = Modifier
                .width(334.dp)
                .height(50.dp)
                .align(alignment = Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF242424)),
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    cartViewModel.deleteAll()
                    val intent = Intent(context, CheckOutActivity::class.java)
                    context.startActivity(intent)
                }) {

                TextStyle(
                    title = "Check out",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .height(25.dp)
                        .fillMaxWidth(),
                    fontFamily = FontFamily(Font(R.font.nunitosan))
                )

            }
        }


    }


}

@Composable
private fun LazyListCart(list: ApiResponse<List<Cart>>?, cartViewModel: CartViewModel) {
    val cartList = list?.data ?: emptyList()
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(410.dp)
            .padding(20.dp)
    ) {
        itemsIndexed(cartList) { index, item ->
            ItemCart(
                id = item._id ?: "",
                name = item.productName,
                image = item.productImage,
                price = item.productPrice,
                quantity = item.quantity,
                cartViewModel = cartViewModel
            )
        }
    }
}


@Composable
private fun ItemCart(
    id: String,
    name: String,
    image: String,
    price: Double,
    quantity: Int,
    cartViewModel: CartViewModel
) {
    Spacer(modifier = Modifier.padding(bottom = 20.dp))
    Column(
        modifier = Modifier
            .width(340.dp)
            .height(100.dp)
    ) {

        Row(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
            ) {
                AsyncImage(
                    model = image,
                    contentDescription = "image",
                    modifier = Modifier.fillMaxSize()
                )
            }


            Column(modifier = Modifier.width(220.dp)) {
                TextStyle(
                    title = name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF999999),
                    textAlign = null,
                    modifier = Modifier.height(25.dp),
                    fontFamily = FontFamily(Font(R.font.nunitosan))
                )
                val formattedPrice = DecimalFormat("$ ##,##.00").format(price)
                TextStyle(
                    title = formattedPrice,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF242424),
                    textAlign = null,
                    modifier = Modifier.height(40.dp),
                    fontFamily = FontFamily(Font(R.font.nunitosan))
                )


                Row(
                    modifier = Modifier.width(113.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .clip(RoundedCornerShape(3.dp))
                            .background(Color(0xFFf3f3f3))
                            .width(30.dp)
                            .height(30.dp),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.cong),
                            contentDescription = null,
                            modifier = Modifier
                                .width(14.dp)
                                .height(14.dp)
                        )
                    }

                    TextStyle(
                        title = "01",
                        fontSize = 18.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF242424),
                        textAlign = null,
                        modifier = Modifier.height(25.dp),
                        fontFamily = FontFamily(Font(R.font.nunitosan))
                    )


                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .clip(RoundedCornerShape(3.dp))
                            .background(Color(0xFFf3f3f3))
                            .width(30.dp)
                            .height(30.dp),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.tru),
                            contentDescription = null,
                            modifier = Modifier
                                .width(14.dp)
                                .height(14.dp)
                        )
                    }


                }
            }

            Image(
                painter = painterResource(id = R.drawable.x),
                contentDescription = "xoa",
                modifier = Modifier
                    .size(20.dp)
                    .clickable {
                        cartViewModel.deleteCart(id)
                    }

            )

        }


    }
}


@Composable
private fun TextStyle(
    title: String,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    color: Color,
    textAlign: TextAlign?,
    modifier: Modifier,
    fontFamily: FontFamily
) {
    Text(
        text = title,
        fontSize = fontSize,
        fontWeight = fontWeight,
        lineHeight = 30.sp,
        fontFamily = fontFamily,
        color = color,
        modifier = modifier,
        textAlign = textAlign ?: null
    )
}