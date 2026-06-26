package com.example.linkedup.repository

import android.content.Context
import android.net.Uri
import com.example.linkedup.ui.screens.CvApi

object CvRepository : CvApi {

    private var localCv: Uri? = null

    override fun saveCvLocally(uri: Uri) {
        localCv = uri
    }

    override fun getLocalCv(): Uri? {
        return localCv
    }

    override fun uploadCvToRecruiter(
        recruiterId: String,
        onResult: (Boolean) -> Unit
    ) {
        // später Firebase / Storage / signaling
        onResult(true)
    }

    override fun receiveCv(onResult: (Uri?) -> Unit) {
        onResult(localCv)
    }
}