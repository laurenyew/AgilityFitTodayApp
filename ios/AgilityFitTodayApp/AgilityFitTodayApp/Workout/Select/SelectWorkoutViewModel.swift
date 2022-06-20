//
//  SelectWorkoutViewModel.swift
//  AgilityFitTodayApp
//
//  Created by laurenyew on 6/19/22.
//

import Foundation
import SwiftUI

class SelectWorkoutViewModel: ObservableObject {
    private let workoutRepo = AppEnvironment.current.workoutRepository
    
    @Published
    private(set) var workoutSet: WorkoutSet = []
    
    init() {
        workoutSet = workoutRepo.workoutSet
    }
}
