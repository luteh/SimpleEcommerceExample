package com.luteh.ecommerceexample.data.local.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.luteh.ecommerceexample.domain.model.Category

@Entity(tableName = "category")
data class CategoryEntity(
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String, // https://img.icons8.com/cute-clipart/2x/apple-watch-apps.png
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int, // 131
    @ColumnInfo(name = "name")
    val name: String // Jam Tangan
) {
    companion object {
        fun mapToDomain(categoryEntity: List<CategoryEntity>): List<Category> =
            categoryEntity.map {
                Category(
                    imageUrl = it.imageUrl,
                    id = it.id,
                    name = it.name
                )
            }
    }
}