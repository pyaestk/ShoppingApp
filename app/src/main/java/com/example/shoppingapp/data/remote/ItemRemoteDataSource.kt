package com.example.shoppingapp.data.remote

import android.util.Log
import com.example.shoppingapp.data.model.request.AddToCartRequest
import com.example.shoppingapp.data.model.response.CartItemResponse
import com.example.shoppingapp.data.model.response.ItemResponse
import com.example.shoppingapp.data.util.toModel
import com.example.shoppingapp.domain.model.CartItemModel
import com.example.shoppingapp.domain.model.ItemModel
import com.example.shoppingapp.domain.util.Response
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class ItemRemoteDataSource(
    private val firebaseDatabase: FirebaseDatabase
) {
    fun loadItems(): Flow<Response<List<ItemModel>>> = callbackFlow {
        val ref = firebaseDatabase.getReference("Items")
        /*.orderByChild("showRecommended")
        .equalTo(true)*/
        trySend(Response.Loading()).isSuccess
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<ItemModel>()
                for (itemSnapShot in snapshot.children) {
                    itemSnapShot.getValue(ItemResponse::class.java)
                        ?.let { list.add(it.toModel()) }
                }
                Log.i("loadItems", list.toString())
                trySend(Response.Success(list)).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("loadItems", error.toString())
                trySend(Response.Error(message = error.message)).isSuccess
            }
        }
        ref.addValueEventListener(valueEventListener)
        awaitClose {
            ref.removeEventListener(valueEventListener)
        }
    }

    fun loadItemByCategory(categoryId: Int): Flow<Response<List<ItemModel>>> = callbackFlow {
        val query = firebaseDatabase
            .getReference("Items")
            .orderByChild("categoryId")
            .equalTo(categoryId.toString())

        trySend(Response.Loading())

        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<ItemModel>()
                for (itemSnapShot in snapshot.children) {
                    itemSnapShot.getValue(ItemResponse::class.java)?.let {
                        list.add(it.toModel())
                    }
                }
                Log.i("loadItemByCategory", "Category ID: $categoryId, Items found: ${list.size}")
                trySend(Response.Success(list))
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("loadItemByCategory", "Error")
                trySend(Response.Error(message = error.message))
            }
        }

        query.addValueEventListener(valueEventListener)

        awaitClose {
            query.removeEventListener(valueEventListener)
        }
    }

    suspend fun addItemToCart(id: Int, quantity: Int, size: String): Response<Unit> {
        val userId = "1"
        return try {
            FirebaseFirestore.getInstance()
                .collection("User")
                .document(userId)
                .collection("CartItem")
                .document(id.toString())
                .set(
                    AddToCartRequest(
                        itemId = id,
                        quantity = quantity,
                        size = size,
                    )
                )
                .await()

            Log.d("addItemToCart", "Item added to Firestore cart")
            Response.Success(Unit)
        } catch (e: Exception) {
            Log.e("addItemToCart", "Error adding item to Firestore cart", e)
            Response.Error(e.message ?: "Unknown error")
        }
    }

    fun loadCartItemWithDetails(): Flow<Response<List<CartItemModel>>> = callbackFlow {
        val userId = "1"
        val firestore = FirebaseFirestore.getInstance()
        val realtimeDb = firebaseDatabase.getReference("Items")

        trySend(Response.Loading())

        // First: listen to cart items from Firestore
        val cartRef = firestore
            .collection("User")
            .document(userId)
            .collection("CartItem")

        val cartListener = cartRef.addSnapshotListener { cartSnapshot, error ->
            if (error != null) {
                trySend(Response.Error(error.message ?: "Unknown error")).isSuccess
                return@addSnapshotListener
            }

            if (cartSnapshot == null || cartSnapshot.isEmpty) {
                trySend(Response.Success(emptyList())).isSuccess
                return@addSnapshotListener
            }

            // Collect basic cart data
            val cartItems = cartSnapshot.documents.mapNotNull {
                it.toObject(AddToCartRequest::class.java)
            }

            // Fetch all items once from Realtime DB
            realtimeDb.get().addOnSuccessListener { itemSnapshot ->
                val allItems = itemSnapshot.children.mapNotNull {
                    it.getValue(ItemResponse::class.java)
                }

                // Join cart items with item details
                val cartDetails = cartItems.mapNotNull { cartItem ->
                    val matchingItem = allItems.find { it.id == cartItem.itemId }
                    matchingItem?.let {
                        CartItemResponse(
                            itemId = cartItem.itemId,
                            quantity = cartItem.quantity,
                            size = cartItem.size,
                            title = it.title,
                            price = it.price,
                            picUrl = it.picUrl.firstOrNull() ?: ""
                        )
                    }
                }.map {
                    it.toModel()
                }
                Log.i("cartItems", cartDetails.toString())
                trySend(Response.Success(cartDetails)).isSuccess

            }.addOnFailureListener { ex ->
                Log.i("cartItems", ex.toString())
                trySend(Response.Error(ex.message ?: "Failed to load item details")).isSuccess
            }
        }

        awaitClose {
            cartListener.remove()
        }
    }

    suspend fun incrementCartItemQuantity(itemId: Int): Response<Unit> {
        val userId = "1"
        val firestore = FirebaseFirestore.getInstance()
        val cartItemRef = firestore
            .collection("User")
            .document(userId)
            .collection("CartItem")
            .document(itemId.toString())

        return try {
            // Get the current quantity
            val snapshot = cartItemRef.get().await()
            val currentItem = snapshot.toObject(AddToCartRequest::class.java)

            if (currentItem != null) {
                val newQuantity = currentItem.quantity + 1

                // Update quantity
                cartItemRef.update("quantity", newQuantity).await()
                Response.Success(Unit)
            } else {
                Response.Error("Item not found in cart")
            }

        } catch (e: Exception) {
            Response.Error(e.message ?: "Unknown error")
        }
    }

    suspend fun decrementCartItemQuantity(itemId: Int): Response<Unit> {
        val userId = "1"
        val firestore = FirebaseFirestore.getInstance()
        val cartItemRef = firestore
            .collection("User")
            .document(userId)
            .collection("CartItem")
            .document(itemId.toString())

        return try {
            // Get the current quantity
            val snapshot = cartItemRef.get().await()
            val currentItem = snapshot.toObject(AddToCartRequest::class.java)

            if (currentItem != null && currentItem.quantity > 1) {
                val newQuantity = currentItem.quantity - 1

                // Update quantity
                cartItemRef.update("quantity", newQuantity).await()
                Response.Success(Unit)
            } else {
                Response.Error("Item not found in cart")
            }

        } catch (e: Exception) {
            Response.Error(e.message ?: "Unknown error")
        }
    }


}