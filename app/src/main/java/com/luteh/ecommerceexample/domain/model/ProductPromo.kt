package com.luteh.ecommerceexample.domain.model

import android.os.Parcelable
import com.luteh.ecommerceexample.data.local.entity.ProductPromoEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductPromo(
    val id: String, // 6723
    val imageUrl: String, // https://upload.wikimedia.org/wikipedia/commons/thumb/7/76/Nintendo-Switch-Console-Docked-wJoyConRB.jpg/430px-Nintendo-Switch-Console-Docked-wJoyConRB.jpg
    val title: String, // Nitendo Switch
    val description: String, // The Nintendo Switch was released on March 3, 2017 and is Nintendo's second entry in the eighth generation of home video game consoles. The system was code-named Nintendo NX prior to its official announcement. It is a hybrid device that can be used as a home console inserted to the Nintendo Switch Dock attached to a television, stood up on a table with the kickstand, or as a tablet-like portable console. It features two detachable wireless controllers called Joy-Con, that can be used individually or attached to a grip to provide a more traditional game pad form. Both Joy-Con are built with motion sensors and HD Rumble, Nintendo's haptic vibration feedback system for improved gameplay experiences. However, only the right Joy-Con has an NFC reader on its analog joystick for Amiibo and an IR sensor on the back. The Nintendo Switch Pro Controller is a traditional style controller much like the one of the Gamecube.
    val price: String, // $340
    val loved: Int // 0
):Parcelable {
    companion object {
        fun mapToEntity(productPromo: ProductPromo): ProductPromoEntity {
            return productPromo.let {
                ProductPromoEntity(
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
}