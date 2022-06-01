package kr.ac.kgu.app.trail.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo(
    var name : String,
    var lastWorkDate: Int,
    var distanceTotal: Int,
    var timeTotal: Int,
    var stepCountTotal: Int,
): Parcelable


