//
//  WorkoutSequence.swift
//  AgilityFitTodayApp
//
//  Created by laurenyew on 3/30/22.
//

import Foundation

typealias WorkoutSet = [WorkoutSequence]

class WorkoutSequence: Codable, Identifiable {
    let id = UUID()
    var name: String
    var description: String
    var workoutItems: [WorkoutItem]
    var workoutType: WorkoutType
    var isFavorite: Bool = false
    
    init(name: String, description: String, workoutItems: [WorkoutItem], workoutType: WorkoutType, isFavorite: Bool) {
        self.name = name
        self.description = description
        self.workoutItems = workoutItems
        self.workoutType = workoutType
        self.isFavorite = isFavorite
    }
    
    // Helper init
    init(name: String, description: String, workoutItems: [WorkoutItem], workoutType: WorkoutType) {
        self.name = name
        self.description = description
        self.workoutItems = workoutItems
        self.workoutType = workoutType
        self.isFavorite = false
    }
    
    /// Handle JSON parsing
    required init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        if let name = try container.decodeIfPresent(String.self, forKey: .name) {
            self.name = name
        } else {
            fatalError("Invalid name in Workout decoder for WorkoutSequence creation")
        }
        if let description = try container.decodeIfPresent(String.self, forKey: .description) {
            self.description = description
        } else {
            fatalError("Invalid description in Workout decoder for WorkoutSequence creation")
        }
        if let workoutItems = try container.decodeIfPresent([WorkoutItem].self, forKey: .workoutItems) {
            self.workoutItems = workoutItems
        } else {
            fatalError("Invalid workoutItems in Workout decoder for WorkoutSequence creation")
        }
        if let workoutType = try container.decodeIfPresent(WorkoutType.self, forKey: .workoutType) {
            self.workoutType = workoutType
        } else {
            fatalError("Invalid workoutType in Workout decoder for WorkoutSequence creation")
        }
        if let isFavorite = try container.decodeIfPresent(Bool.self, forKey: .isFavorite) {
            self.isFavorite = isFavorite
        } else {
            self.isFavorite = false
        }
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
