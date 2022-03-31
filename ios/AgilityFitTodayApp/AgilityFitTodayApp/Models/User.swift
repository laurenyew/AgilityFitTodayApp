//
//  User.swift
//  AgilityFitTodayApp
//
//  Created by laurenyew on 3/30/22.
//

import Foundation

class User {
    let id: Int
    var name: String
    var age: Int // age in years
    var sex: String
    var weight: Double // weight in lbs
    let isDog: Bool
    
    init(id: Int, name: String, age: Int, sex: String, weight: Double, isDog: Bool) {
        self.id = id
        self.name = name
        self.age = age
        self.sex = sex
        self.weight = weight
        self.isDog = isDog
    }
}
