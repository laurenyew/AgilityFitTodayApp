//
//  StartWorkoutViewModel.swift
//  AgilityFitTodayApp
//
//  Created by laurenyew on 8/21/22.
//

import Foundation


class StartWorkoutViewModel: ObservableObject {
    private let workoutRepo = AppEnvironment.current.workoutRepository
    
    @Published
    private(set) var workoutSequence: WorkoutSequence?
    
    init(sequenceID: UUID) {
        workoutSequence = workoutRepo.loadWorkoutSequence(id: sequenceID) 
    }
}
