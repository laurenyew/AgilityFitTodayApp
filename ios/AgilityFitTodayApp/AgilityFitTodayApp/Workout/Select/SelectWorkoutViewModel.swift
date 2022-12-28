//
//  SelectWorkoutViewModel.swift
//  AgilityFitTodayApp
//
//  Created by laurenyew on 6/19/22.
//

import Foundation
import SwiftUI

class SelectWorkoutViewModel: ObservableObject {
    
    @Published
    private(set) var workoutSet: WorkoutSet = []
    @Published
    private(set) var workoutTypes: [WorkoutType] = []
    
    // TODO: This doesn't seem right. How should we properly pass around the workout repository?
    convenience init() {
        let workoutRepo = AppEnvironment.current.workoutRepository
        self.init(workoutRepo: workoutRepo)
    }
    
    init(workoutRepo: WorkoutRepository) {
        workoutSet = workoutRepo.workoutSetSortedByType
        workoutTypes = workoutRepo.workoutTypes
    }
}
