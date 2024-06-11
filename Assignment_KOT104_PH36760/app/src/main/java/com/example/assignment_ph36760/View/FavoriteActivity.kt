package com.example.assignment_ph36760.View

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment_ph36760.R
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.assignment_ph36760.Model.Entity.Cart
import com.example.assignment_ph36760.Model.Entity.Favorite
import com.example.assignment_ph36760.Response.ApiResponse
import com.example.assignment_ph36760.ViewModel.CartViewModel
import com.example.assignment_ph36760.ViewModel.FavoriteViewModel
import java.text.DecimalFormat

class FavoriteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val favoriteViewModel = FavoriteViewModel()
            FavoriteScreen(favoriteViewModel = favoriteViewModel)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
@Composable
fun FavoriteScreen(favoriteViewModel: FavoriteViewModel) {
//    var promocode by remember {
//        mutableStateOf("")
//    }
    LaunchedEffect(Unit) {
        favoriteViewModel.loadFavorite()
    }
    val favorite by favoriteViewModel.favorite.observeAsState(null)
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()) {
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
                    Text(
                        text = "My favorite",
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

            LazyListFavorite(list = favorite, favoriteViewModel)


            Spacer(modifier = Modifier.height(30.dp))


            Button(modifier = Modifier
                .width(334.dp)
                .height(50.dp)
                .align(alignment = Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF242424)),
                shape = RoundedCornerShape(8.dp),
                onClick = {}) {

                Text(
                    text = "Add all to cart",
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
private fun LazyListFavorite(list: ApiResponse<List<Favorite>>?, favoriteViewModel: FavoriteViewModel) {
    val favoriteList = list?.data?: emptyList()
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(410.dp)
            .padding(20.dp)
    ) {
        itemsIndexed(favoriteList) { index, item ->
            ItemFavorite(
                id = item._id?:"",
                name = item.productName,
                image = item.productImage,
                price = item.productPrice,
                quantity = item.quantity,
                favoriteViewModel = favoriteViewModel
            )
        }
    }
}


@Composable
private fun ItemFavorite(id: String,name: String, image: String, price: Double, quantity: Int, favoriteViewModel: FavoriteViewModel) {
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
                Text(
                    text = name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF999999),
                    textAlign = null,
                    modifier = Modifier.height(25.dp),
                    fontFamily = FontFamily(Font(R.font.nunitosan))
                )
                val formattedPrice = DecimalFormat("$ ##,##.00").format(price)
                Text(
                    text = formattedPrice,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF242424),
                    textAlign = null,
                    modifier = Modifier.height(40.dp),
                    fontFamily = FontFamily(Font(R.font.nunitosan))
                )


                Image(
                    painter = painterResource(id = R.drawable.x),
                    contentDescription = "xoa",
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {
                            favoriteViewModel.deleteFavorite(id)
                        }

                )

            }


        }
    }
}


//    @Composable
//    private fun TextStyle2(
//        title: String,
//        fontSize: TextUnit,
//        fontWeight: FontWeight,
//        color: Color,
//        textAlign: TextAlign?,
//        modifier: Modifier,
//        fontFamily: FontFamily
//    ) {
//        Text(
//            text = title,
//            fontSize = fontSize,
//            fontWeight = fontWeight,
//            lineHeight = 30.sp,
//            fontFamily = fontFamily,
//            color = color,
//            modifier = modifier,
//            textAlign = textAlign ?: null
//        )
//    }