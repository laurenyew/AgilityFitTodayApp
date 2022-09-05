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
    
    @Published
    private(set) var currentState: WorkoutExecutionState = .NotStarted
    
    @Published
    private(set) var workoutSequence: WorkoutSequence?
    
    @Published
    private(set) var estimatedTimeLeft: String?
    
    @Published
    private(set) var currentWorkoutItemIndex: Int?
    
    private var estimatedTimeLeftInSecs: Int?
    
    private var timer: Timer?
    
    init(sequenceID: UUID) {
        workoutSequence = workoutRepo.loadWorkoutSequence(id: sequenceID)
        estimatedTimeLeftInSecs = workoutSequence?.estimatedTimeInSecs()
        estimatedTimeLeft = workoutSequence?.estimatedTimeFormattedString()
        currentWorkoutItemIndex = -1
    }
    
    /// Floating Action Button Click
    /// Update Workout Execution Status
    func onFABButtonClick() {
        switch(currentState){
        case .NotStarted:
            restartWorkout()
        case .InProgress:
            stopWorkout()
        case .Stopped:
            startWorkout()
        case .Cancelled:
            restartWorkout()
        case .Completed:
            restartWorkout()
        }
    }
    
    func cancelWorkout()  {
        currentState = .Cancelled
        // TODO: Show UI for check if want to cancel before backing out
    }
    
    func completeWorkout()  {
        currentState = .Completed
        // TODO: Show UI for completed workout (animation? / popup for sharing)
        // TODO: Save workout as latest completed / for history
    }
    
    private func restartWorkout(){
        currentWorkoutItemIndex = 0
        startWorkout()
    }
    
    private func startWorkout(){
        currentState = .InProgress
        // TODO: timer = Timer.scheduledTimer(timeInterval: <#T##TimeInterval#>, target: <#T##Any#>, selector: <#T##Selector#>, userInfo: <#T##Any?#>, repeats: <#T##Bool#>)
    }
    
    private func stopWorkout() {
        currentState = .Stopped
    }
}
