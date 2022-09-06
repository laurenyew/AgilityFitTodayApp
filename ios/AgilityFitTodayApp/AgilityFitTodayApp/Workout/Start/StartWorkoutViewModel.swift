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
    private(set) var currentWorkoutItemIndex: Int
    
    private var workoutItems: [WorkoutItem]
    
    private var estimatedTotalTimeLeftInSecs: Int
    
    private var estimatedItemTimeLeftInSecs: Int
    
    private var timer: Timer?
    
    init(sequenceID: UUID) {
        let sequence = workoutRepo.loadWorkoutSequence(id: sequenceID)
        workoutSequence = sequence
        estimatedTotalTimeLeftInSecs = sequence?.estimatedTimeInSecs() ?? 0
        estimatedItemTimeLeftInSecs = sequence?.workoutItems.first?.estimatedTimeInSecs() ?? 0
        estimatedTimeLeft = sequence?.estimatedTimeFormattedString()
        workoutItems = sequence?.workoutItems ?? []
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
        timer?.invalidate()
        // TODO: Show UI for check if want to cancel before backing out
    }
    
    func completeWorkout()  {
        currentState = .Completed
        timer?.invalidate()
        // TODO: Show UI for completed workout (animation? / popup for sharing)
        // TODO: Save workout as latest completed / for history
    }
    
    private func restartWorkout(){
        currentWorkoutItemIndex = 0
        startWorkout()
    }
    
    private func startWorkout(){
        currentState = .InProgress
        timer = Timer.scheduledTimer(timeInterval: 1, target: self, selector: #selector(updateTimeLeft), userInfo: nil, repeats: true)
    }
    
    private func stopWorkout() {
        currentState = .Stopped
        timer?.invalidate()
    }
    
    /// Update Workout Execution for Time Left
    ///
    /// If we've used up all the time, complete the workout
    /// Otherwise, update the UI state for estimated time left as well as the selected workout item
    ///
    @objc private func updateTimeLeft() {
        if estimatedTotalTimeLeftInSecs <= 0 {
            completeWorkout()
        } else {
            estimatedTotalTimeLeftInSecs = estimatedTotalTimeLeftInSecs - 1
            estimatedTimeLeft = TimeInterval.init(estimatedTotalTimeLeftInSecs).minuteSecond
            estimatedItemTimeLeftInSecs = estimatedItemTimeLeftInSecs - 1
            
            if estimatedItemTimeLeftInSecs <= 0 {
                currentWorkoutItemIndex = currentWorkoutItemIndex + 1
                if currentWorkoutItemIndex >= 0 && currentWorkoutItemIndex < workoutItems.count {
                    let currentWorkoutItem = workoutItems[currentWorkoutItemIndex]
                    estimatedItemTimeLeftInSecs = currentWorkoutItem.estimatedTimeInSecs()
                }
            }
        }
    }
}
