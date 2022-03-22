package com.example.marsrobots.models.responces

import com.google.gson.annotations.SerializedName

data class NasaResponse (
    @SerializedName("collection") var collection : Collection)

data class Collection (
        @SerializedName("version") var version : String,
        @SerializedName("href") var href : String,
        @SerializedName("items") var items : List<Items>,
        @SerializedName("metadata") var metadata : Metadata,
        @SerializedName("links") var links : List<Links>
)

data class Links (
        @SerializedName("rel") var rel : String,
        @SerializedName("prompt") var prompt : String,
        @SerializedName("href") var href : String
)

data class Metadata (
        @SerializedName("total_hits") var totalHits : Int
)

data class Items (
        @SerializedName("href") var href : String,
        @SerializedName("data") var data : List<Data>,
        @SerializedName("links") var links : List<Links>
)

data class Data (
        @SerializedName("description") var description : String,
        @SerializedName("title") var title : String,
        @SerializedName("photographer") var photographer : String,
        @SerializedName("keywords") var keywords : List<String>,
        @SerializedName("location") var location : String,
        @SerializedName("nasa_id") var nasaId : String,
        @SerializedName("media_type") var mediaType : String,
        @SerializedName("date_created") var dateCreated : String,
        @SerializedName("center") var center : String
)

