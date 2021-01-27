package com.seigneur.gauvain.data_adapter.utils

import androidx.room.TypeConverter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.seigneur.gauvain.data_adapter.models.remote.token.AccessToken
import java.util.ArrayList
import java.util.Date

object RoomConverter {

    @TypeConverter
    @JvmStatic
    fun toDate(timestamp: Long?): Date? {
        return if (timestamp == null) null else Date(timestamp)
    }

    @TypeConverter
    @JvmStatic
    fun accessTokenToString(accessToken: AccessToken?): String? {
        val gson = Gson()
        return if (accessToken == null) null else gson.toJson(accessToken)
    }

    @TypeConverter
    @JvmStatic
    fun stringToAccessToken(string: String): AccessToken? {
        val type = object : TypeToken<AccessToken>() {
        }.type
        return Gson().fromJson<AccessToken>(string, type)
    }



    @TypeConverter
    @JvmStatic
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    @JvmStatic
    fun stringArrayToString(arrayString: Array<String>?): String? {
        val gson = Gson()
        return if (arrayString == null) null else gson.toJson(arrayString)
    }

    @TypeConverter
    @JvmStatic
    fun stringToArrayString(string: String): Array<String>? {
        val listType = object : TypeToken<Array<String>>() {

        }.type
        return Gson().fromJson<Array<String>>(string, listType)
    }

    @TypeConverter
    @JvmStatic
    fun arrayListFromString(value: String): ArrayList<String>? {
        val listType = object : TypeToken<ArrayList<String>>() {

        }.type
        val gson = Gson()
        return gson.fromJson<ArrayList<String>>(value, listType)
    }

    @TypeConverter
    @JvmStatic
    fun stringFromArrayList(list: ArrayList<String>?): String? {
        val gson = Gson()
        return if (list == null) null else gson.toJson(list)
    }

    @TypeConverter
    @JvmStatic
    fun stringToMapString(string: String?): Map<String, String>? {
        val listType = object : TypeToken<Map<String, String>>() {}.type
        return Gson().fromJson<Map<String, String>>(string, listType)
    }

    @TypeConverter
    @JvmStatic
    fun mapStringToString(mapString: Map<String, String>?): String? {
        val gson = Gson()
        return if (mapString == null) null else gson.toJson(mapString)
    }

}