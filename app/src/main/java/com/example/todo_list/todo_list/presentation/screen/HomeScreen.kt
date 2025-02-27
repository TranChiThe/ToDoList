package com.example.todo_list.todo_list.presentation.screen

import android.annotation.SuppressLint
import android.widget.GridLayout
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todo_list.todo_list.presentation.viewmodel.TaskViewModel
//import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todo_list.todo_list.domain.entities.Task
import com.example.todo_list.todo_list.domain.repository.TaskRepository


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(modifier: Modifier = Modifier, repository: TaskRepository) {
    val taskViewModel: TaskViewModel = viewModel(
        factory = TaskViewModel.factory(repository)
    )
    var showInputField by remember { mutableStateOf(false) }
    var newTaskTitle by remember { mutableStateOf("") }
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            AddButton { showInputField = true }
        },
        floatingActionButtonPosition = FabPosition.End
    ){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Text(text = "Task List",
                fontSize = 30.sp
            )
            Column {
                if(showInputField) {
                    TaskInputField(
                        taskTitle = newTaskTitle,
                        onTaskTitleChange = {newTaskTitle = it},
                        onSave = {
                            if (newTaskTitle.isNotBlank()) {
                                taskViewModel.addTask(newTaskTitle, "1")
                                newTaskTitle = ""
                                showInputField = false
                            }
                        },
                        onCancel = {
                            newTaskTitle = ""
                            showInputField = false
                        }
                    )
                }
            }
            val tasks by taskViewModel.tasks.collectAsState()
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(tasks) { task ->
                    TaskItem(
                        task = task,
                        viewModel = taskViewModel
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun AddButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .padding(bottom = 70.dp, start = 16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add",
            tint = Color.Black,
            modifier = Modifier.size(30.dp)
        )
    }
}

@Composable
fun TaskItem (task: Task, viewModel: TaskViewModel) {
    var isChecked by remember { mutableStateOf(task.status == "Pending") }
    val maxLength = 18
    val displayTitle = if (task.title.length > maxLength) {
        "${task.title.substring(0, maxLength)}..." // Cắt và thêm dấu ba chấm
    } else {
        task.title
    }
    Row (
        modifier = Modifier
            .padding(10.dp)
            .height(80.dp)
    ){
        Box(
            modifier = Modifier
                .size(size = 1000.dp)
                .shadow(elevation = 5.dp,shape = RoundedCornerShape(20.dp))
                .background(color = Color.LightGray, shape = RoundedCornerShape(20.dp)),
        ) {
            Row {
                Checkbox(checked = isChecked, onCheckedChange = { isChecked = it })
                Text(
                    text = displayTitle,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 10.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (task.status == "Pending") {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Icon(
                            tint = Color.Blue,
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            modifier = Modifier
                                .size(35.dp)
                                .align(Alignment.TopEnd)
                                .padding(top = 0.dp, end = 10.dp)
                        )
                    }
                } else {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Icon(
                            tint = Color.Red,
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Menu Icon",
                            modifier = Modifier
                                .size(35.dp)
                                .align(Alignment.TopEnd)
                                .padding(top = 0.dp, end = 10.dp)
                        )
                    }
                }


            }
            HandleButton(
                deleteTask = {
                    viewModel.deleteTask(task)
                },
                editTask = {
                    //                    if (isEditing && editedTitle.isNotBlank()) {
                    //                        viewModel.updateTask(task.copy(title = editedTitle)) // Lưu chỉnh sửa
                    //                    }
                    //                    isEditing = !isEditing // Chuyển đổi chế độ chỉnh sửa
                }
            )
        }
    }
}


@Composable
fun TaskInputField(
    taskTitle: String,
    onTaskTitleChange: (String) -> Unit,
    onSave: () -> Unit,
    onCancel: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        TextField(
            value = taskTitle,
            onValueChange = onTaskTitleChange,
            label = { Text("Enter new task ...") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Button(onClick = onSave,
            ) {
                Text("Save")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onCancel) {
                Text("Cancel")
            }
        }
    }
}

@Composable
fun HandleButton (deleteTask: () -> Unit, editTask: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 35.dp, end = 10.dp)
        .size(35.dp)
        , horizontalArrangement = Arrangement.End) {
        Button(onClick = deleteTask,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Red
            ),
        ) {
            Icon(imageVector = Icons.Default.Delete,
                contentDescription = null,
            )
        }
        Button(onClick = editTask,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Blue
            ),
            ) {
            Icon(imageVector = Icons.Default.Edit,
                contentDescription = null,
            )
        }
    }
}