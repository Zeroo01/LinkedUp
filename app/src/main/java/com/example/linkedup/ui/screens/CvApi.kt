package com.example.linkedup.ui.screens

import android.net.Uri

interface CvApi {

    fun saveCvLocally(uri: Uri)

    fun getLocalCv(): Uri?

    fun uploadCvToRecruiter(
        recruiterId: String,
        onResult: (Boolean) -> Unit
    )

    fun receiveCv(
        onResult: (Uri?) -> Unit
    )
}