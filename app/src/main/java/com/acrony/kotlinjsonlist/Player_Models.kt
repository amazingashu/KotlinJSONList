package com.acrony.kotlinjsonlist


class Players_Model {

    var name: String? = null
    var country: String? = null
    var city: String? = null

    fun getNames(): String {
        return name.toString()

    }

    fun setNames(name: String) {
        this.name = name
    }


    fun getCountrys(): String {

        return country.toString()
    }

    fun setCountrys(name: String) {
        this.country = country
    }

    fun getCitys(): String {
        return city.toString()
    }

    fun setCitys(name: String) {
        this.city = city
    }

}

