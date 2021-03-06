package com.example.lulu.presentation.garments_list.components

import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lulu.presentation.add_garments.core.AddEditGarmentViewModel
import com.example.lulu.presentation.garments_list.core.GarmentsEvents
import com.example.lulu.presentation.garments_list.core.GarmentsListViewModel
import com.example.lulu.presentation.util.TestTags
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class,ExperimentalFoundationApi::class)
@Composable
fun GarmentsListScreen(
    state: ModalBottomSheetState,
    viewModel: GarmentsListViewModel = hiltViewModel(),
    addEditViewModel: AddEditGarmentViewModel = hiltViewModel(),

){
    val stateViewModel = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope =  rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = Color.Cyan,
        modifier = Modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .testTag(TestTags.ORDER_SECTION),
                    garmentOrder = stateViewModel.garmentOrder,
                    onOrderChange = {
                        viewModel.onEvent(GarmentsEvents.Order(it))
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(stateViewModel.garments) { garment ->
                    GarmentItem(
                        garment = garment,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                scope.launch {
                                    state.show()
                                } },
                        onDeleteClick = {
                            viewModel.onEvent(GarmentsEvents.DeleteGarment(garment))
                            scope.launch {
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Garment Deleted",
                                    actionLabel = "Undo"
                                )
                                if(result == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(GarmentsEvents.RestoreGarment)
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

