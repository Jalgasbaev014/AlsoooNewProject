package com.g.alsooonewproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.g.alsooonewproject.ui.theme.AlsoooNewProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlsoooNewProjectTheme {
                MainPage()
            }
        }
    }
}

@Composable
fun MainPage() {
    var selectTab by remember { mutableStateOf(TabScreens.Home) }

    val cartItems = remember { mutableStateListOf<Product>() }
    
    val availableProducts = remember { 
        mutableStateListOf<Product>().apply { addAll(productList) } 
    }

    Scaffold(bottomBar = {
        NavigationBar {
            NavigationBarItem(
                selected = selectTab == TabScreens.Home,
                onClick = { selectTab = TabScreens.Home },
                icon = { Icon(Icons.Default.Home, contentDescription = null) },
                label = { Text("Home") }
            )
            NavigationBarItem(
                selected = selectTab == TabScreens.Cart,
                onClick = { selectTab = TabScreens.Cart },
                icon = { Icon(Icons.Default.ShoppingCart, contentDescription = null) },
                label = { Text("Cart") }
            )
            NavigationBarItem(
                selected = selectTab == TabScreens.Profile,
                onClick = { selectTab = TabScreens.Profile },
                icon = { Icon(Icons.Default.Person, contentDescription = null) },
                label = { Text("Profile") }
            )
        }
    }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectTab) {
                TabScreens.Home -> HomePage(
                    products = availableProducts,
                    onPurchaseClick = { product ->
                        cartItems.add(product)
                        availableProducts.remove(product)
                        selectTab = TabScreens.Cart
                    }
                )
                TabScreens.Cart -> CardPage(
                    cartItems = cartItems,
                    onRemoveItem = { product ->
                        cartItems.remove(product)
                        availableProducts.add(product)
                    }
                )
                TabScreens.Profile -> ProfilePage(cartItems = cartItems)
            }
        }
    }
}

data class Product(
    val id: Int,
    val name: String,
    val price: String,
    val description: String,
    val imageUrl: String,
    val color: Color
)

val productList = listOf(
    Product(
        id = 1,
        name = "iPhone 17 Pro",
        price = "$1299",
        description = "512GB, eSIM. Apple A19 Pro chip, OLED Super Retina XDR screen.",
        imageUrl = "https://storage.googleapis.com/static.alana.ai/user_uploads/6594ed8d-5654-463d-82c2-849c258ce885.jpg",
        color = Color(0xFFE2E2E2)
    ),
    Product(
        id = 2,
        name = "Xiaomi 13T Pro 5G",
        price = "$610",
        description = "MediaTek Dimensity 9200+, 144Hz AMOLED screen, 50MP + 12MP + 50MP Leica Camera. 12/512GB.",
        imageUrl = "https://storage.googleapis.com/static.alana.ai/user_uploads/45229615-562a-4467-939e-d30df80b8750.jpg",
        color = Color(0xFF1A1A1A)
    ),
    Product(
        id = 3,
        name = "Samsung Galaxy S25",
        price = "$899",
        description = "Next level. 6.2\" 1080x2340 pixels, 50MP Camera, 12GB RAM Snapdragon 8 Elite, 4000mAh.",
        imageUrl = "https://storage.googleapis.com/static.alana.ai/user_uploads/82a7f53c-a71f-49b8-aa4c-22345eb60773.jpg",
        color = Color(0xFF3F51B5)
    ),
    Product(
        id = 4,
        name = "MOFT Snap Case",
        price = "$39",
        description = "Soft Touch, Color That Lasts. MOVA Snap Case for iPhone 17 series.",
        imageUrl = "https://storage.googleapis.com/static.alana.ai/user_uploads/154dfc4a-6798-4c31-9a74-d2e537441bc1.jpg",
        color = Color(0xFFBDC3C7)
    ),
    Product(
        id = 5,
        name = "Hair Hybrids",
        price = "$45",
        description = "Personalized treatments for hair health.",
        imageUrl = "https://storage.googleapis.com/static.alana.ai/user_uploads/d40ef87a-3603-4c55-885b-a63e264bc807.jpg",
        color = Color(0xFF7E5742)
    ),
    Product(
        id = 6,
        name = "Nike Air Force 1",
        price = "$59",
        description = "Air Force 1 Low Supreme by Nike. Classic white leather design with iconic red box logo.",
        imageUrl = "https://storage.googleapis.com/static.alana.ai/user_uploads/c3653147-5a1e-450e-a4b5-ea926b47c0b9.jpg",
        color = Color(0xFFF5F5F5)
    )
)

@Composable
fun HomePage(products: List<Product>, onPurchaseClick: (Product) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Items",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        
        if (products.isEmpty()) {
            item {
                Box(
                    modifier = Modifier.fillParentMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("All product in Cart!")
                }
            }
        } else {
            items(products, key = { it.id }) { product ->
                ProductItem(product = product, onPurchaseClick = { onPurchaseClick(product) })
            }
        }
    }
}

@Composable
fun ProductItem(product: Product, onPurchaseClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(product.color.copy(alpha = 0.1f)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = product.price,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.ExtraBold
                    )
                    
                    Button(
                        onClick = onPurchaseClick,
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp),
                        modifier = Modifier.height(32.dp)
                    ) {
                        Text(
                            text = "To card",
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CardPage(cartItems: List<Product>, onRemoveItem: (Product) -> Unit) {
    if (cartItems.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Cart is empty")
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    text = "Cart",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            items(cartItems, key = { it.id }) { product ->
                CartItem(product = product, onRemove = { onRemoveItem(product) })
            }
        }
    }
}

@Composable
fun CartItem(product: Product, onRemove: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = product.price,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            }
            Box {
                IconButton(onClick = { expanded = true }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Menu"
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Remove Item") },
                        onClick = {
                            onRemove()
                            expanded = false
                        },
                        leadingIcon = { Icon(Icons.Default.Delete, contentDescription = null) }
                    )
                    DropdownMenuItem(
                        text = { Text("Change Item") },
                        onClick = {
                            onRemove()
                            expanded = false
                        },
                        leadingIcon = { Icon(Icons.Default.Edit, contentDescription = null) }
                    )
                    DropdownMenuItem(
                        text = { Text("Cancel Item") },
                        onClick = {
                            onRemove()
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ProfilePage(cartItems: List<Product>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            // User Info
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.size(60.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Aydarbek",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "aydarbek@example.com",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }

        item {
            // Stats Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    ProfileStatItem(
                        icon = Icons.Default.ShoppingCart,
                        label = "Number of orders",
                        value = "${cartItems.size} ta"
                    )
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
                    ProfileStatItem(
                        icon = Icons.Default.Home,
                        label = "Shipping Address",
                        value = "Nukus."
                    )
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
                    ProfileStatItem(
                        icon = Icons.Default.Star,
                        label = "Bonus Balls",
                        value = "250 ball"
                    )
                }
            }
        }

        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Settings",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                SettingsItem(icon = Icons.Default.Notifications, title = "Notifications")
                SettingsItem(icon = Icons.Default.Settings, title = "Application settings")
                SettingsItem(icon = Icons.Default.Info, title = "Help and support")
            }
        }

        item {
            Button(
                onClick = {  },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                contentPadding = PaddingValues(16.dp)
            ) {
                Text("Exit", fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ProfileStatItem(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = label, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.weight(1f))
        Text(text = value, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun SettingsItem(icon: ImageVector, title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = Color.Gray
        )
    }
}

enum class TabScreens {
    Home, Cart, Profile
}
