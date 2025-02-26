package com.example.todo_list.todo_list.presentation.screen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.todo_list.todo_list.presentation.navigation.NavItem


@Composable
fun MainScreen (modifier: Modifier = Modifier) {
    val NavItemList = listOf(
        NavItem("Home", Icons.Default.Home),
        NavItem("Calendar", Icons.Default.DateRange),
        NavItem("Search", Icons.Default.Search),
        NavItem("Folder", Icons.Filled.Menu)
    )

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                NavItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                        },
                        icon = {
                            BadgedBox(badge = {
                                Badge() {
                                    Text(text = "2")
                                }
                            }) {
                                Icon(imageVector = navItem.icon, contentDescription = "")
                            }
                        },
                        label = {Text(text = navItem.label)}
                    )
                }
            }
        }
    ) { innerPadding ->
        ContentScreen(modifier = Modifier.padding(innerPadding), selectedIndex)
    }
}

@Composable
fun ContentScreen (modifier: Modifier = Modifier, selectedIndex: Int) {
    when(selectedIndex){
        0 -> HomePage()
        1 -> CalendarPage()
        2 -> SearchPage()
        3 -> FolderPage()
    }
}