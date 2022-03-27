//
//  AppEnvironment.swift
//  AgilityFitTodayApp
//
//  Created by laurenyew on 3/23/22.
//

import Foundation
import SwiftUI

/// This is a global singleton class for us to keep all of the state in one place
class AppEnvironment {
    static let current = AppEnvironment()
    
    @State var theme:Theme = themes[0]
}

/// Handle App Theming
extension AppEnvironment {
    func updateTheme(name:ThemeName) {
        switch(name){
        case .darkTheme:  theme = themes[1]
        default:  theme = themes[0]
        }
    }
}
