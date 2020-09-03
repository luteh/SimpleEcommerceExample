package com.luteh.ecommerceexample.data.local.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.luteh.ecommerceexample.domain.model.ProductPromo

@Entity(tableName = "product_promo")
data class ProductPromoEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: String, // 6723
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String, // https://upload.wikimedia.org/wikipedia/commons/thumb/7/76/Nintendo-Switch-Console-Docked-wJoyConRB.jpg/430px-Nintendo-Switch-Console-Docked-wJoyConRB.jpg
    @ColumnInfo(name = "title")
    val title: String, // Nitendo Switch
    @ColumnInfo(name = "description")
    val description: String, // The Nintendo Switch was released on March 3, 2017 and is Nintendo's second entry in the eighth generation of home video game consoles. The system was code-named Nintendo NX prior to its official announcement. It is a hybrid device that can be used as a home console inserted to the Nintendo Switch Dock attached to a television, stood up on a table with the kickstand, or as a tablet-like portable console. It features two detachable wireless controllers called Joy-Con, that can be used individually or attached to a grip to provide a more traditional game pad form. Both Joy-Con are built with motion sensors and HD Rumble, Nintendo's haptic vibration feedback system for improved gameplay experiences. However, only the right Joy-Con has an NFC reader on its analog joystick for Amiibo and an IR sensor on the back. The Nintendo Switch Pro Controller is a traditional style controller much like the one of the Gamecube.
    @ColumnInfo(name = "price")
    val price: String, // $340
    @ColumnInfo(name = "loved")
    var loved: Int = 0 // 0
) {
    companion object {
        fun mapToDomain(productPromoEntities: List<ProductPromoEntity>): List<ProductPromo> =
            productPromoEntities.map {
                ProductPromo(
                    id = it.id,
                    imageUrl = it.imageUrl,
                    title = it.title,
                    description = it.description,
                    price = it.price,
                    loved = it.loved
                )
            }

        fun mapToDomain(productPromoEntity: ProductPromoEntity): ProductPromo =
            productPromoEntity.let {
                ProductPromo(
                    id = it.id,
                    imageUrl = it.imageUrl,
                    title = it.title,
                    description = it.description,
                    price = it.price,
                    loved = it.loved
                )
            }
    }
}