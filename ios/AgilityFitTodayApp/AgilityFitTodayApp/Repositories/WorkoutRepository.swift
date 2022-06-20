//
//  WorkoutRepository.swift
//  AgilityFitTodayApp
//
//  Created by laurenyew on 5/11/22.
//

import Foundation

/// Workout Repository
///
/// Business logic for all workout model functions
///
class WorkoutRepository {
    
    /// Sorted workout sequences by name
    private(set) var workoutSet: WorkoutSet = []
    
    init() {
        loadBaseWorkoutSequences()
    }
    
    /// Load workout sequences from base JSON file
    private func loadBaseWorkoutSequences(){
        if workoutSet.isEmpty {
            if let path = Bundle.main.path(forResource: "BaseWorkoutSequences", ofType: "json") {
                do {
                    let data = try Data(contentsOf: URL(fileURLWithPath: path))
                    let decoder = JSONDecoder()
                    let baseWorkoutSet = try decoder.decode(WorkoutSet.self, from: data)
                    workoutSet = baseWorkoutSet
                } catch {
                    print("error:\(error)")
                }
            }
        }
    }
}
