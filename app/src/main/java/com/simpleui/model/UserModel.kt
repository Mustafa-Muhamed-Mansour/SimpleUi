package com.simpleui.model

class UserModel {

    var email: String = ""
    var name: String = ""
    var phone: String = ""

    constructor() {

    }

    constructor(email: String, name: String, phone: String) {
        this.email = email
        this.name = name
        this.phone = phone
    }


}