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
}
