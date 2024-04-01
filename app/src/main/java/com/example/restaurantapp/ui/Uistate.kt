package com.example.restaurantapp.ui

import com.example.restaurantapp.Model.Makanan
import com.example.restaurantapp.Model.Pelanggan

data class AddUIState(
    val addEvent: AddMakanan = AddMakanan(),
    val addPelanggan: AddPelanggan = AddPelanggan()
)

data class AddMakanan(
    val id: String = "",
    val nama: String = "",
    val Harga: String = "",
    val Jenis: String = "",
)

data class AddPelanggan(
    val Id: String = "",
    val plgn: String = "",
    val Nomormeja : String = "",
)

fun AddMakanan.toAdmin(): Makanan = Makanan(
    id = id,
    namamkn = nama,
    harga = Harga,
    jenis = Jenis

)
fun AddPelanggan.toPelanggan(): Pelanggan = Pelanggan(
    id = Id,
    pelanggan = plgn,
    nomormeja = Nomormeja,

    )

data class DetailUIState(
    val addEvent: AddMakanan = AddMakanan(),
    val addPelanggan: AddPelanggan = AddPelanggan()
)

fun Makanan.toDetailAdmin(): AddMakanan =
    AddMakanan(
        id = id,
        nama = namamkn,
        Harga = harga,
        Jenis = jenis
    )
fun Pelanggan.toDetailPelanggan(): AddPelanggan =
    AddPelanggan(
        Id = id,
        plgn = pelanggan,
        Nomormeja = nomormeja,
    )
fun Makanan.toUIStateAdmin(): AddUIState = AddUIState(
    addEvent = this.toDetailAdmin()
)

data class MakananUIState(
    val listMakanan: List<Makanan> = listOf(),
    val dataLength: Int = 0
)