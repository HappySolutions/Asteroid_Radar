package com.udacity.asteroidradar

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.SerializedName

@JsonClass(generateAdapter = true)
@Entity(tableName = "asteroids_table")
@Parcelize
data class Asteroid(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Long?,

    @ColumnInfo(name = "codename")
    @SerializedName("codename")
    val codename: String?,

    @ColumnInfo(name = "closeApproachDate")
    @SerializedName("codename")
    val closeApproachDate: String?,

    @ColumnInfo(name = "absoluteMagnitude")
    @SerializedName("absoluteMagnitude")
    val absoluteMagnitude: Double?,

    @ColumnInfo(name = "estimatedDiameter")
    @SerializedName("estimatedDiameter")
    val estimatedDiameter: Double?,

    @ColumnInfo(name = "relativeVelocity")
    @SerializedName("relativeVelocity")
    val relativeVelocity: Double?,

    @ColumnInfo(name = "distanceFromEarth")
    @SerializedName("distanceFromEarth")
    val distanceFromEarth: Double?,

    @ColumnInfo(name = "isPotentiallyHazardous")
    @SerializedName("isPotentiallyHazardous")
    val isPotentiallyHazardous: Boolean?

    ) : Parcelable

fun ArrayList<Asteroid>.asDomainModel(): Array<Asteroid> {
    return map {
        Asteroid(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }
        .toTypedArray()
}
