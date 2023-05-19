package com.ead.project.ourivesariarumor.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseClient @Inject constructor() {

    val database : FirebaseFirestore = Firebase.firestore
    val auth : FirebaseAuth get() = FirebaseAuth.getInstance()
    val storage : FirebaseStorage get() = FirebaseStorage.getInstance()
}