package hu.ait.weatherapp.ui.screen

// for a 'val' variable
import androidx.compose.runtime.getValue
// for a `var` variable also add
import androidx.compose.runtime.setValue
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitesScreen(
    onNavigateToWeatherScreen: (String) -> Unit,
    citiesViewModel: CitiesViewModel = hiltViewModel()
) {

    var showAddCityDialog by rememberSaveable {
        mutableStateOf(false)
    }

    Column {
        TopAppBar(
            title = {
                Text(text="Weather app")
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            actions = {
                IconButton(onClick = {
                    citiesViewModel.clearAllCities()
                }) {
                    Icon(Icons.Filled.Delete, null)
                }
                IconButton(onClick = {
                    showAddCityDialog = true
                }) {
                    Icon(Icons.Filled.AddCircle, null)
                }
            })

        Column(modifier = Modifier.padding(10.dp)) {

            if (showAddCityDialog) {
                AddNewCityForm(
                    citiesViewModel,
                    { showAddCityDialog = false }
                )
            }

            if (citiesViewModel.getAllCities().isEmpty())
                Text(text = "no cities. add some!")
            else {
                LazyColumn(modifier = Modifier.fillMaxHeight()) {
                    items(citiesViewModel.getAllCities()) {
                        ItemCard(city = it,
                            onRemoveItem = { citiesViewModel.removeItem(it) },
                            onCardClicked = { onNavigateToWeatherScreen(it) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ItemCard(
    city: String,
    onRemoveItem: () -> Unit = {},
    onCardClicked: () -> Unit = {}
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier.padding(10.dp)
            .clickable { onCardClicked() }
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    Modifier.fillMaxWidth(0.6f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(city)
                    Spacer(modifier = Modifier.fillMaxWidth(0.8f))
                }
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "delete",
                    modifier = Modifier.clickable {
                        onRemoveItem()
                    },
                    tint = Color.Red
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun AddNewCityForm(
    citiesViewModel: CitiesViewModel,
    onDialogDismiss: () -> Unit = {}
) {
    var context = LocalContext.current

    var cityErrorState by rememberSaveable {
        mutableStateOf(false)
    }

    var errorText by rememberSaveable {
        mutableStateOf("")
    }

    fun validateCity(text: String) {
        cityErrorState = false
    }

    Dialog(
        onDismissRequest = onDialogDismiss
    ) {
        var city by rememberSaveable {
            mutableStateOf("")
        }

        Column(
            Modifier
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(10.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = city,
                singleLine = true,
                trailingIcon = {
                    if (cityErrorState) {
                        Icon(
                            Icons.Filled.Warning, "Please enter a city",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                },
                onValueChange = {
                    city = it
                    validateCity(city)
                },
                label = { Text(text = "City name")}
            )
            if (cityErrorState) {
                Text(
                    text = errorText,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            Row {
                Button(onClick = {
                    validateCity(city)
                    if(!cityErrorState) {
                            citiesViewModel.addToList(
                                city
                            )
                        onDialogDismiss()
                        city = "" //reset fields to empty
                    }
                }) {
                    Text(text = "Add city")
                }

            }
        }
    }
}