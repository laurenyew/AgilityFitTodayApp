//
//  WorkoutType.swift
//  AgilityFitTodayApp
//
//  Created by laurenyew on 3/30/22.
//

import Foundation

enum WorkoutType: String, Codable {
    case Cardio = "CARDIO"
    case Core = "CORE"
    case UpperBodyStrength = "UPPER_BODY_STRENGTH"
    case LowerBodyStrength = "LOWER_BODY_STRENGTH"
    case Stretch = "STRETCH"
    case Strength = "STRENGTH"
    case Rest = "REST"
    
    func uiString() -> String {
        switch(self){
        case .UpperBodyStrength:
            return "Upper-body Strength"
        case .LowerBodyStrength:
            return "Lower-body Strength"
        default:
            return self.rawValue.capitalized(with: Locale.current)
        }
    }
}

extension WorkoutType: Identifiable {
    var id: Self { self }
}
