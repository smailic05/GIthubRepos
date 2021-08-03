package com.example.githubrepos.retrofit

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

data class Repositories(

	@field:SerializedName("total_count")
	val totalCount: Int? = null,

	@field:SerializedName("incomplete_results")
	val incompleteResults: Boolean? = null,

	@field:SerializedName("items")
	val items: List<ItemRepos?>? = null
)

@Entity(tableName = "repositories")
data class ItemRepos(

	@field:SerializedName("stargazers_count")
	val stargazersCount: Int? = null,

	@field:SerializedName("score")
	val score: Double? = null,
	@PrimaryKey
	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("forks")
	val forks: Int? = null,

	@field:SerializedName("full_name")
	val fullName: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("owner")
	@TypeConverters(Converters::class)
	val owner: Owner? = null,

	@field:SerializedName("url")
	val url: String? = null,

)


data class Owner(

	@field:SerializedName("login")
	val login: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

)
class Converters {
	@TypeConverter
	fun fromOwner(value: Owner?): String {
		return value?.login+"$"+value?.avatarUrl
	}

	@TypeConverter
	fun toOwner(string: String): Owner {
		val temp=string.split("$")
		return Owner(login = temp[0],avatarUrl = temp[1])
	}
}
