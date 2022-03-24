//
//  AgilityFitTodayTheme.swift
//  AgilityFitTodayApp
//
//  Created by laurenyew on 3/23/22.
//

import Foundation
import SwiftUI

enum ThemeName {
    case lightTheme
    case darkTheme
}

var themes: [Theme] = [
    // Light Theme
    Theme(logoColor: logoColor, backgroundColor: .white, primaryColor: gold500, primaryVariantColor: gold200, secondaryColor: teal200, surfaceColor: gold100, dividerColor: dividerColor, shadowColor: .gray, bodyTextColor: .black
         ),
    // Dark Theme
    Theme(logoColor: logoColor, backgroundColor: .gray, primaryColor: gold200, primaryVariantColor: gold700, secondaryColor: teal200, surfaceColor: gold100, dividerColor: dividerColor, shadowColor: .gray, bodyTextColor: .white)
]

class Theme: ObservableObject {
    @Published var logoColor: Color
    @Published var backgroundColor: Color
    @Published var primaryColor: Color
    @Published var primaryVariantColor: Color
    @Published var secondaryColor: Color
    @Published var surfaceColor: Color
    @Published var dividerColor: Color
    @Published var shadowColor: Color
    @Published var bodyTextColor: Color
    
    init(logoColor: Color, backgroundColor: Color, primaryColor: Color, primaryVariantColor: Color, secondaryColor: Color, surfaceColor: Color, dividerColor: Color, shadowColor: Color, bodyTextColor: Color){
        self.logoColor = logoColor
        self.backgroundColor = backgroundColor
        self.primaryColor = primaryColor
        self.primaryVariantColor = primaryVariantColor
        self.secondaryColor = secondaryColor
        self.surfaceColor = surfaceColor
        self.dividerColor = dividerColor
        self.shadowColor = shadowColor
        self.bodyTextColor = bodyTextColor
    }
}

/// Colors used in the app theme

let logoColor = Color(hex: "FFFF9800")
let gold700 = Color(hex:"FFB86F05")
let gold500 = Color(hex:"FFDDAC19")
let gold200 = Color(hex:"FFFFC107")
let gold100 = Color(hex:"FFFCFBF7")
let gold150 = Color(hex:"FFF5EABD")
let teal200 = Color(hex:"FF23C1F6")

let dividerColor = Color(hex:"FF6D6A6F")
let cardColor = gold150



//private val DarkColorPalette = darkColors(
//    primary = gold200,
//    primaryVariant = gold700,
//    secondary = teal200
//)
//
//private val LightColorPalette = lightColors(
//    primary = gold500,
//    primaryVariant = gold200,
//    secondary = teal200,
//    surface = gold100,
//)
