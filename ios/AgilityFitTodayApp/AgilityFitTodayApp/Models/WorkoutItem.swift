//
//  WorkoutItem.swift
//  AgilityFitTodayApp
//
//  Created by laurenyew on 3/30/22.
//

import Foundation

class WorkoutItem {
    let id: Int
    let quantity: Int
    let itemBase: WorkoutItemBase
    let isFavorite: Bool
    
    init(id: Int, quantity: Int, itemBase: WorkoutItemBase, isFavorite: Bool) {
        self.id = id
        self.quantity = quantity
        self.itemBase = itemBase
        self.isFavorite = isFavorite
    }

    // Helper init
    init(quantity: Int, itemBase: WorkoutItemBase) {
        self.id = Int.random(in: 0..<Int.max)
        self.quantity = quantity
        self.itemBase = itemBase
        self.isFavorite = false
    }
    
    func estimatedTimeInSecs() -> Int {
       return itemBase.baseEstimatedTime * quantity * 60 // mins to secs
    }
    
    func estimatedTimeFormattedString() -> String {
        let estimatedTime = TimeInterval.init(estimatedTimeInSecs())
        return estimatedTime.minuteSecond
    }
}

struct WorkoutItemBase {
    let name: String
    let description: String
    let baseEstimatedTime: Int // in munites
    let isMeasuredInReps: Bool
    let workoutType: WorkoutType
}

enum BasicWorkoutItems {
    case Rest
    case Crunches
    case PushUps
    case Squats
    case Treadmill
    case Stretch
    
    func toWorkoutItemBase() -> WorkoutItemBase {
        switch(self){
        case .Rest: return WorkoutItemBase(name: "Rest", description: "Rest, Breathe, & Hydrate", baseEstimatedTime: 1, isMeasuredInReps: false, workoutType: .Rest)
        case .Crunches: return WorkoutItemBase(name: "Crunches", description: "On your back, hands behind head, half sit-up", baseEstimatedTime: 1, isMeasuredInReps: true, workoutType: .Core)
        case .PushUps: return WorkoutItemBase(name: "Push-ups", description: "In a plank position with your hands below your shoulders, bend and straighten your elbows to 90 degrees", baseEstimatedTime: 1, isMeasuredInReps: true, workoutType: .UpperBodyStrength)
        case .Squats: return WorkoutItemBase(name: "Squats", description: "Knees over ankles, bend your knees with straight back (w/ or w/o weights)", baseEstimatedTime: 1, isMeasuredInReps: true, workoutType: .LowerBodyStrength)
        case .Treadmill: return WorkoutItemBase(name: "Treadmill", description: "Walk / Run in intervals on the treadmill", baseEstimatedTime: 5, isMeasuredInReps: false, workoutType: .Cardio)
        case .Stretch: return WorkoutItemBase(name: "Stretch", description: "Stretch / Cooldown", baseEstimatedTime: 5, isMeasuredInReps: false, workoutType: .Stretch)
        }
    }
}
