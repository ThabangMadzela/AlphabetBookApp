package com.alphabetbook
/**
 * Each item in the gridview
 * @author Thabang Thubane
 * @since 16 Sep 2022
 *
 */
class Item {
    var name:String? = null
    var image:Int? = null

    constructor(name: String, image: Int){
        this.name = name
        this.image = image
    }
}