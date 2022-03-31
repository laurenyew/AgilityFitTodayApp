//
//  WorkoutSequence.swift
//  AgilityFitTodayApp
//
//  Created by laurenyew on 3/30/22.
//

import Foundation

class WorkoutSequence {
    let id: Int
    var name: String
    var description: String
    var workoutItems: [WorkoutItem]
    var workoutType: WorkoutType
    var isFavorite: Bool
    
    init(id: Int, name: String, description: String, workoutItems: [WorkoutItem], workoutType: WorkoutType, isFavorite: Bool) {
        self.id = id
        self.name = name
        self.description = description
        self.workoutItems = workoutItems
        self.workoutType = workoutType
        self.isFavorite = isFavorite
    }
    
    // Helper init
    init(name: String, description: String, workoutItems: [WorkoutItem], workoutType: WorkoutType) {
        self.id = Int.random(in: 0..<Int.max)
        self.name = name
        self.description = description
        self.workoutItems = workoutItems
        self.workoutType = workoutType
        self.isFavorite = false
    }
    
    func estimatedTimeInSecs() -> Int {
        return self.workoutItems.map { item in
            item.estimatedTimeInSecs()
        }.reduce(0, +)
    }
    
    func estimatedTimeFormattedString() -> String {
        let estimatedTime = TimeInterval.init(estimatedTimeInSecs())
        return estimatedTime.minuteSecond
    }
}
