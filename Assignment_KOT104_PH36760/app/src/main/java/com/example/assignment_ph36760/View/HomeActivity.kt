package com.example.assignment_ph36760.View

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.example.assignment_ph36760.R
import com.example.assignment_ph36760.Model.Entity.Product
import com.example.assignment_ph36760.Model.Entity.ProductType
import com.example.assignment_ph36760.Model.Entity.Screens
import com.example.assignment_ph36760.Response.ApiResponse

import com.example.assignment_ph36760.ViewModel.HomeViewModel
import com.google.gson.Gson
import java.text.DecimalFormat

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val homeViewModel = HomeViewModel()
            val navController = rememberNavController()
            HomeScreen(homeViewModel, navController)
        }


    }
}

//@Preview(showBackground = true)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel, navController: NavController) {
    val productTypes by homeViewModel.productType.observeAsState(null)
    val product by homeViewModel.product.observeAsState(null)
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.find),
                    contentDescription = "find",
                    modifier = Modifier.size(24.dp)
                )

                Column(
                    modifier = Modifier
                        .width(125.dp)
                        .height(50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextStyle(
                        title = "Make home",
                        fontSize = 18.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF909090),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.height(25.dp),
                        fontFamily = FontFamily(Font(R.font.gelasio_variablefont_wght))
                    )

                    TextStyle(
                        title = "BEAUTIFUL",
                        fontSize = 20.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF303030),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.height(25.dp),
                        fontFamily = FontFamily(Font(R.font.gelasio_variablefont_wght))
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.cart),
                    contentDescription = "cart",
                    modifier = Modifier.size(24.dp)
                )

            }


            productTypes?.let { LazyProductType(it, homeViewModel::getProductByType) }

            LazyProduct(list = product, navController)
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


@Composable
private fun LazyProductType(
    list: ApiResponse<List<ProductType>>?,
    onProductTypeClicked: (String) -> Unit
) {
    val productTypeList = list?.data ?: emptyList()
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        itemsIndexed(productTypeList) { index, item ->
            ItemProductType(
                name = item.title,
                image = item.imageType,
                typeID = item._id,
                onProductTypeClicked = { productTypeId -> onProductTypeClicked(productTypeId) }
            )
        }
    }
}


@Composable
private fun LazyProduct(list: ApiResponse<List<Product>>?, navController: NavController) {
    val productList = list?.data ?: emptyList()

    LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(15.dp)) {
        itemsIndexed(productList) { index, item ->
            ItemProduct(
                id = item._id,
                name = item.productName,
                image = item.productImage,
                description = item.description,
                price = item.productPrice,
                evaluate = item.evaluate,
                quantity = item.quantity,
                color = item.colors,
                navController
            )
        }
    }
}

@Composable
private fun ItemProductType(
    name: String,
    image: String,
    typeID: String,
    onProductTypeClicked: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                onProductTypeClicked(typeID)
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .size(44.dp)
                .align(alignment = Alignment.CenterHorizontally),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F1F1))
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                AsyncImage(
                    model = image,
                    contentDescription = "imageItem",
                    modifier = Modifier
                        .size(28.dp)
                        .align(alignment = Alignment.Center),
                    alignment = Alignment.Center
                )
            }
        }

        TextStyle(
            title = name,
            fontSize = 14.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF999999),
            textAlign = TextAlign.Center,
            modifier = Modifier.height(25.dp),
            fontFamily = FontFamily(Font(R.font.nunitosan))
        )
    }

}

@Composable
private fun ItemProduct(
    id: String,
    name: String,
    image: String,
    description: String,
    price: Float,
    evaluate: Float,
    quantity: Int,
    color: List<Product.Color>,
    navController: NavController
) {
    val context = LocalContext.current
    val colorJson = Gson().toJson(color)
    Column(
        modifier = Modifier
            .width(157.dp)
            .height(260.dp)
            .padding(10.dp)

    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .align(alignment = Alignment.CenterHorizontally)
                .clickable {
                    val intent = Intent(context, DetailsActivity::class.java).apply {
                        putExtra("id", id)
                        putExtra("name", name)
                        putExtra("image", image)
                        putExtra("description", description)
                        putExtra("price", price)
                        putExtra("evaluate", evaluate)
                        putExtra("quantity", quantity)
                        putExtra("color", colorJson)
                    }
                    context.startActivity(intent)
                },
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F1F1))
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp)) // Bo góc hình ảnh
            ) {
                AsyncImage(
                    model = image,
                    contentDescription = "imageItem",
                    modifier = Modifier
                        .fillMaxSize()
                        .align(alignment = Alignment.Center),
                    alignment = Alignment.Center,
                )
            }
        }


        TextStyle(
            title = name,
            fontSize = 14.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF606060),
            textAlign = null,
            modifier = Modifier.height(25.dp),
            fontFamily = FontFamily(Font(R.font.nunitosan))
        )
        val formattedPrice = DecimalFormat("$ ##,##.00").format(price)
        TextStyle(
            title = formattedPrice,
            fontSize = 14.sp,
            fontWeight = FontWeight(700),
            color = Color(0xFF303030),
            textAlign = null,
            modifier = Modifier.height(25.dp),
            fontFamily = FontFamily(Font(R.font.nunitosan))
        )
    }
}
