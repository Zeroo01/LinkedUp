package com.example.linkedup.ui.screens.Applicant

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.linkedup.data.AuthRepository
import com.example.linkedup.repository.CvRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage


@Composable
fun ApplicantProfileScreen(
    onLogout: () -> Unit
) {

    // ---------------- STATE ----------------
    var isEditing by remember { mutableStateOf(false) }

    var firstName by remember { mutableStateOf("Max") }
    var lastName by remember { mutableStateOf("Mustermann") }
    var status by remember { mutableStateOf("Student") }

    var company by remember { mutableStateOf("Beispiel GmbH") }
    var position by remember { mutableStateOf("Werkstudent") }
    var start by remember { mutableStateOf("2024") }
    var end by remember { mutableStateOf("2025") }

    var skills by remember { mutableStateOf(listOf("Kotlin", "Firebase")) }

    // Profilbild
    var profileImageUrl by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {

        val uid = AuthRepository.getCurrentUserId()
            ?: return@LaunchedEffect

        FirebaseFirestore.getInstance()
            .collection("users")
            .document(uid)
            .get()
            .addOnSuccessListener { document ->

                profileImageUrl =
                    document.getString("profileImageUrl")

                firstName =
                    document.getString("firstName") ?: firstName

                lastName =
                    document.getString("lastName") ?: lastName

                status =
                    document.getString("status") ?: status
            }
    }

    // ---------------- IMAGE PICKER ----------------
    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            uploadImageToFirebase(it) { url ->

                profileImageUrl = url

                saveProfileToFirestore(
                    firstName,
                    lastName,
                    status,
                    url
                )
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // ---------------- HEADER ----------------
        item {

            Text(
                text = "LinkedUp",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                // ---------------- PROFILE IMAGE ----------------
                Column {

                    Card(
                        modifier = Modifier.size(90.dp)
                    ) {
                        if (profileImageUrl != null) {
                            AsyncImage(
                                model = profileImageUrl,
                                contentDescription = "Profile Image",
                                modifier = Modifier.fillMaxSize()
                            )
                        } else {
                            Box(modifier = Modifier.fillMaxSize())
                        }
                    }

                    Spacer(Modifier.height(8.dp))

                    if (isEditing) {
                        Button(
                            onClick = { imagePicker.launch("image/*") }
                        ) {
                            Text("Foto ändern")
                        }
                    }

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "$firstName $lastName",
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = status,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                // EDIT BUTTON
                Button(
                    onClick = { isEditing = !isEditing }
                ) {
                    Text(if (isEditing) "Fertig" else "Bearbeiten")
                }
            }

            Spacer(Modifier.height(20.dp))
        }

        // ---------------- CV ----------------
        item {

            val cvPicker = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent()
            ) { uri ->
                uri?.let {
                    CvRepository.saveCvLocally(it)
                }
            }

            val cv = CvRepository.getLocalCv()

            ProfileSection("Lebenslauf") {

                Text(
                    text = if (cv != null)
                        "CV gespeichert "
                    else
                        "Kein Lebenslauf hochgeladen"
                )

                Spacer(Modifier.height(8.dp))

                Button(onClick = {
                    cvPicker.launch("application/pdf")
                }) {
                    Text(if (cv != null) "CV ändern" else "CV hochladen")
                }
            }

            Spacer(Modifier.height(16.dp))
        }

        // ---------------- PERSONAL DATA ----------------
        item {
            ProfileSection("Persönliche Daten") {

                if (isEditing) {

                    OutlinedTextField(
                        value = firstName,
                        onValueChange = { firstName = it },
                        label = { Text("Vorname") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(8.dp))

                    OutlinedTextField(
                        value = lastName,
                        onValueChange = { lastName = it },
                        label = { Text("Nachname") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(8.dp))

                    OutlinedTextField(
                        value = status,
                        onValueChange = { status = it },
                        label = { Text("Status") },
                        modifier = Modifier.fillMaxWidth()
                    )

                } else {

                    Text("$firstName $lastName")
                    Text(status)
                }
            }

            Spacer(Modifier.height(16.dp))
        }

        // ---------------- EXPERIENCE ----------------
        item {
            ProfileSection("Erfahrungen") {

                if (isEditing) {

                    OutlinedTextField(
                        value = company,
                        onValueChange = { company = it },
                        label = { Text("Unternehmen") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(8.dp))

                    OutlinedTextField(
                        value = position,
                        onValueChange = { position = it },
                        label = { Text("Position") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(8.dp))

                    Row {

                        OutlinedTextField(
                            value = start,
                            onValueChange = { start = it },
                            label = { Text("Start") },
                            modifier = Modifier.weight(1f)
                        )

                        Spacer(Modifier.width(8.dp))

                        OutlinedTextField(
                            value = end,
                            onValueChange = { end = it },
                            label = { Text("Ende") },
                            modifier = Modifier.weight(1f)
                        )
                    }

                } else {

                    Text(company)
                    Text(position)
                    Text("$start - $end")
                }
            }

            Spacer(Modifier.height(16.dp))
        }

        // ---------------- SKILLS ----------------
        item {
            ProfileSection("Kenntnisse") {

                if (isEditing) {

                    OutlinedTextField(
                        value = skills.joinToString(", "),
                        onValueChange = {
                            skills = it.split(",").map { s -> s.trim() }
                        },
                        label = { Text("Skills (kommagetrennt)") },
                        modifier = Modifier.fillMaxWidth()
                    )

                } else {
                    FlowRowSimple(skills)
                }
            }

            Spacer(Modifier.height(20.dp))
        }

        // ---------------- SAVE ----------------
        item {

            if (isEditing) {
                Button(
                    onClick = {
                        isEditing = false

                        saveProfileToFirestore(
                            firstName,
                            lastName,
                            status,
                            profileImageUrl
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Speichern")
                }

                Spacer(Modifier.height(12.dp))
            }
        }

        // ---------------- LOGOUT ----------------
        item {

            OutlinedButton(
                onClick = {
                    onLogout()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Abmelden")
            }
        }
    }
}


fun uploadImageToFirebase(
    uri: Uri,
    onSuccess: (String) -> Unit
) {
    val storageRef = FirebaseStorage.getInstance().reference
    val fileRef = storageRef.child("profileImages/${System.currentTimeMillis()}.jpg")

    fileRef.putFile(uri)
        .continueWithTask { fileRef.downloadUrl }
        .addOnSuccessListener { url ->
            onSuccess(url.toString())
        }
}

fun saveProfileToFirestore(
firstName: String,
lastName: String,
status: String,
imageUrl: String?
) {

    val uid = AuthRepository.getCurrentUserId()
        ?: return

    val data = mapOf(
        "firstName" to firstName,
        "lastName" to lastName,
        "status" to status,
        "profileImageUrl" to imageUrl
    )

    FirebaseFirestore.getInstance()
        .collection("users")
        .document(uid)
        .set(data, SetOptions.merge())
}


@Composable
fun ProfileSection(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp)) {

            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(12.dp))
            content()
        }
    }
}

@Composable
fun FlowRowSimple(items: List<String>) {
    Column {
        items.forEach { item ->
            Card(modifier = Modifier.padding(bottom = 6.dp)) {
                Text(
                    text = item,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}