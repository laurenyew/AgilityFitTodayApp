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
    
    /// Sorted workout sequences by category
    private(set) var workoutSetSortedByType: WorkoutSet = []
    
    private(set) var workoutTypes: [WorkoutType] = []
    
    init() {
        loadBaseWorkoutSequences()
    }
    
    // TODO: Eventually we want to use CoreData instead of this.
    func loadWorkoutSequence(id: UUID) -> WorkoutSequence? {
        return workoutSet.first { sequence in
            sequence.id == id
        }
    }
    
    /// Load workout sequences from base JSON file
    private func loadBaseWorkoutSequences(){
        if workoutSet.isEmpty {
            if let path = Bundle.main.path(forResource: "BaseWorkoutSequences", ofType: "json") {
                do {
                    let data = try Data(contentsOf: URL(fileURLWithPath: path))
                    let decoder = JSONDecoder()
                    let baseWorkoutSet = try decoder.decode(WorkoutSet.self, from: data)
                    parseBaseWorkoutSet(baseWorkoutSet)
                } catch {
                    print("error:\(error)")
                }
            }
        }
    }
    
    /// Parse Base Workout Set into appropriate data sets
    private func parseBaseWorkoutSet(_ baseWorkoutSet: WorkoutSet) {
        workoutSet = baseWorkoutSet
        workoutSetSortedByType = baseWorkoutSet.sorted(by: {
            $0.workoutType.rawValue < $1.workoutType.rawValue
        })
        workoutSetSortedByType.forEach { workoutSequence in
            let type = workoutSequence.workoutType
            if !workoutTypes.contains(type) {
                workoutTypes.append(type)
            }
        }
    }
}
