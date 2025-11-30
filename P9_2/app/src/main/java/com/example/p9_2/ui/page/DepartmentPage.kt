package com.example.p9_2.ui.page

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.p9_2.ui.department.DepartmentViewModel

@Composable
fun DepartmentPage(departmentViewModel: DepartmentViewModel = viewModel()) {
    val departments by departmentViewModel.departments.collectAsState()

    LazyColumn {
        items(departments) { department ->
            Text(
                text = "${department.id} ${department.name}",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
