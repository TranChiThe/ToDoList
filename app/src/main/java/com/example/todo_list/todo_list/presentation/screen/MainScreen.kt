package com.example.todo_list.todo_list.presentation.screen
import androidx.compose.foundation.layout.Box
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todo_list.todo_list.domain.repository.TaskRepository
import com.example.todo_list.todo_list.domain.use_case.task_usecase.AddTaskUseCase
import com.example.todo_list.todo_list.domain.use_case.task_usecase.DeleteTaskUseCase
import com.example.todo_list.todo_list.domain.use_case.task_usecase.LoadTaskUseCase
import com.example.todo_list.todo_list.domain.use_case.task_usecase.UpdateTaskUseCase
import com.example.todo_list.todo_list.presentation.navigation.NavItem
import com.example.todo_list.todo_list.presentation.viewmodel.TaskViewModel


@Composable
fun MainScreen (modifier: Modifier = Modifier) {
    val viewModel: TaskViewModel = hiltViewModel()

//    val loadTasksUseCase = LoadTaskUseCase(repository)
//    val addTaskUseCase = AddTaskUseCase(repository)
//    val updateTaskUseCase = UpdateTaskUseCase(repository)
//    val deleteTaskUseCase = DeleteTaskUseCase(repository)
//
//    val viewModel: TaskViewModel = viewModel(
//        factory = TaskViewModel.factory(
//            loadTasksUseCase,
//            addTaskUseCase,
//            updateTaskUseCase,
//            deleteTaskUseCase
//        )
//    )

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
        ContentScreen(modifier = Modifier.padding(innerPadding), selectedIndex, viewModel = viewModel)
    }
}

@Composable
fun ContentScreen (modifier: Modifier = Modifier, selectedIndex: Int, viewModel: TaskViewModel) {
    Box(modifier = modifier) {
        when (selectedIndex) {
            0 -> HomeScreen(modifier, viewModel)
            
            1 -> CalendarPage()
            2 -> SearchPage()
            3 -> FolderPage()
        }
    }
}