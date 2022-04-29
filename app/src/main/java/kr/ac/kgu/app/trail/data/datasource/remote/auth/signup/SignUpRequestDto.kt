package kr.ac.kgu.app.trail.data.datasource.remote.auth.signup

import com.google.gson.annotations.SerializedName

data class SignUpRequestDto(@SerializedName("snsId")
                            var snsId: String,
                            @SerializedName("name")
                            var name: String,)
