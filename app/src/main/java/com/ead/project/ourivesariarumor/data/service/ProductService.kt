package com.ead.project.ourivesariarumor.data.service

import android.content.Context
import android.net.Uri
import android.util.Log
import android.webkit.MimeTypeMap
import com.ead.project.ourivesariarumor.data.repository.FirebaseClient
import com.ead.project.ourivesariarumor.data.util.hashMap
import com.ead.project.ourivesariarumor.domain.model.Product
import com.google.firebase.storage.UploadTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductService @Inject constructor(
    private val context: Context,
    private val firebase: FirebaseClient
) {

    private val productsCollection by lazy {
        firebase.database.collection(PRODUCTS_PATH)
    }

    private val productsStorage by lazy {
        firebase.storage.getReference(PRODUCTS_PATH)
    }

    suspend fun createProductTable(product: Product, productPhotoUri : Uri) = runCatching {

        val fileExtension = getUriFileExtension(productPhotoUri)?:return@runCatching false

        val id = getLastId() + 1
        val productStoragePathing = "$PRODUCTS_PATH/${product.id?:id}.${fileExtension}"

        val imageRef = productsStorage.storage.reference.child(productStoragePathing)
        val uploadImageProductTask = imageRef.putFile(productPhotoUri).await()

        if (uploadImageProductTask.hasUploadingError()) {
            imageRef.delete()
            return@runCatching false
        }

        productsCollection
            .document(id.toString())
            .set(hashMap(
                product = product.copy(
                    id = id,
                    image = productStoragePathing
                ))
            )
            .await()
    }.isSuccess

    private val auxiliaryDataList = mutableListOf<Product>()

    suspend fun getProductsTable() : List<Product> = withContext(Dispatchers.IO) {
        return@withContext try {

            auxiliaryDataList.clear()
            productsCollection.get().await().forEach { document ->
                auxiliaryDataList.add(
                    Product.fromData(document.data).
                    getStorageReference()?:return@forEach
                )
            }

            Log.d("testing", "getProductsTable: $auxiliaryDataList")

            auxiliaryDataList

        }
        catch (e : Exception) {
            e.printStackTrace()
            Log.e("error", "getProductsTable: ${e.message}")
            emptyList()
        }
    }

    private suspend fun Product.getStorageReference() : Product? {
        return copy(
            image = productsStorage.storage.
            reference.child(image?:return null).
            downloadUrl.await().toString()
        )
    }

    private fun UploadTask.TaskSnapshot.hasUploadingError() : Boolean
    = !task.isComplete || error != null

    private fun getUriFileExtension(productPhotoUri: Uri) : String? {
        val typeOfFile = context.contentResolver.getType(productPhotoUri) ?: return null
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(typeOfFile)
    }

    private suspend fun getLastId() : Long {
        return (productsCollection.get().await().lastOrNull()?.id
            ?: productsCollection.get().await().size()).toString().toLong()
    }

    companion object {
        const val PRODUCTS_PATH = "products"
    }
}