package com.example.assignment_ph36760.View

import android.content.Intent
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
import androidx.navigation.compose.rememberNavController
import com.example.assignment_ph36760.R
import com.example.assignment_ph36760.Model.Product
import com.example.assignment_ph36760.Model.ProductType
import com.example.assignment_ph36760.Model.colorProduct
import com.google.gson.Gson
import java.text.DecimalFormat

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { HomeScreen() }


    }
}

val listProductType = listOf(
    ProductType(
        "Popular",
        R.drawable.star
    ),
    ProductType(
        "Chair",
        R.drawable.chair
    ),
    ProductType(
        "Table",
        R.drawable.table,
    ),
    ProductType(
        "Armchair",
        R.drawable.sofa,
    ),
    ProductType(
        "Bed",
        R.drawable.bed
    ),
)


val listProduct = listOf(
    Product(
        "Black Simple Lamp",
        R.drawable.imageproduct1,
        "Minimal Stand is made of by natural wood. The design that is very simple and minimal. This is truly one of the best furnitures in any family for now. With 3 different colors, you can easily select the best match for your home. ",
        12.00,
        4.5,
        20,
        color = listOf(colorProduct("Black"), colorProduct("Green"))
    ),

    Product(
        "Minimal Stand",
        R.drawable.imageproduct2,
        "Minimal Stand is made of by natural wood. The design that is very simple and minimal. This is truly one of the best furnitures in any family for now. With 3 different colors, you can easily select the best match for your home. ",
        25.00,
        4.5,
        20,
        color = listOf(
            colorProduct("Black"), colorProduct("Green")
        )
    ),

    Product(
        "Coffee Chair",
        R.drawable.imageproduct3,
        "Minimal Stand is made of by natural wood. The design that is very simple and minimal. This is truly one of the best furnitures in any family for now. With 3 different colors, you can easily select the best match for your home. ",
        20.00,
        4.5,
        20,
        color = listOf(
            colorProduct("Black"), colorProduct("Green")
        )
    ),

    Product(
        "Simple Desk",
        R.drawable.imageproduct4,
        "Minimal Stand is made of by natural wood. The design that is very simple and minimal. This is truly one of the best furnitures in any family for now. With 3 different colors, you can easily select the best match for your home. ",
        50.00,
        4.5,
        20,
        color = listOf(
            colorProduct("Black"), colorProduct("Green")
        )
    )
)

@Preview(showBackground = true)
@Composable
fun HomeScreen() {

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


            LazyProductType(listProductType)

            LazyProduct(list = listProduct)
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
private fun LazyProductType(list: List<ProductType>) {

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        itemsIndexed(listProductType) { index, item ->
            ItemProductType(name = item.nameProductType, image = item.image)
        }
    }
}


@Composable
private fun LazyProduct(list: List<Product>) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(15.dp)) {
        itemsIndexed(listProduct) { index, item ->
            ItemProduct(
                name = item.nameProduct,
                image = item.imageProduct,
                description = item.description,
                price = item.price,
                evaluate = item.evaluate,
                quantity = item.quantity,
                color = item.color,
            )
        }
    }
}

@Composable
private fun ItemProductType(name: String, image: Int) {

    Column(
        modifier = Modifier.padding(10.dp),
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
                Image(
                    painter = painterResource(id = image),
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
    name: String,
    image: Int,
    description: String,
    price: Double,
    evaluate: Double,
    quantity: Int,
    color: List<colorProduct>,
) {
    val navigationController = rememberNavController()
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .width(157.dp)
            .height(260.dp)
            .padding(10.dp)
            .clickable {
                val intent = Intent(context, DetailsActivity::class.java)
                // Định nghĩa hàm extension để chuyển đổi danh sách thành chuỗi JSON

                intent.putExtra("name", name)
                intent.putExtra("image", image)
                intent.putExtra("description", description)
                intent.putExtra("price", price)
                intent.putExtra("evaluate", evaluate)
                intent.putExtra("quantity", quantity)
                intent.putExtra("colors", color.toJson())
                context.startActivity(intent)
            }
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .align(alignment = Alignment.CenterHorizontally),
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
                Image(
                    painter = painterResource(id = image),
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

inline fun <reified T> List<T>.toJson(): String {
    return Gson().toJson(this)
}