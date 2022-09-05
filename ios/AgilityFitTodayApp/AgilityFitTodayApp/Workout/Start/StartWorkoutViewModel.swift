//
//  StartWorkoutViewModel.swift
//  AgilityFitTodayApp
//
//  Created by laurenyew on 8/21/22.
//

import Foundation

/// Business Logic for Start Workout Feature
class StartWorkoutViewModel: ObservableObject {
    private let workoutRepo = AppEnvironment.current.workoutRepository
    
    private enum WorkoutExecutionState {
        case NotStarted
        case InProgress
        case Stopped
        case Cancelled
        case Completed
    }
    
    private var currentState: WorkoutExecutionState = .NotStarted
    
    @Published
    private(set) var workoutSequence: WorkoutSequence?
    
    @Published
    private(set) var estimatedTimeLeft: String?
    
    @Published
    private(set) var currentWorkoutItemIndex: Int?
    
    private var estimatedTimeLeftInSecs: Int?
    
    init(sequenceID: UUID) {
        workoutSequence = workoutRepo.loadWorkoutSequence(id: sequenceID)
        estimatedTimeLeftInSecs = workoutSequence?.estimatedTimeInSecs()
        estimatedTimeLeft = workoutSequence?.estimatedTimeFormattedString()
        currentWorkoutItemIndex = -1
    }
    
    func startWorkout(){
        currentState = .InProgress
        
    }
    
    func stopWorkout() {
        currentState = .Stopped
    }
    
    func cancelWorkout()  {
        currentState = .Cancelled
    }
    
    func completeWorkout()  {
        currentState = .Completed
    }
}
